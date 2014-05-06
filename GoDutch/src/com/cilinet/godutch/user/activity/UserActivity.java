package com.cilinet.godutch.user.activity;

import java.util.ArrayList;
import java.util.Date;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.cilinet.godutch.R;
import com.cilinet.godutch.framework.activity.FrameActivity;
import com.cilinet.godutch.framework.utils.RegexTools;
import com.cilinet.godutch.framework.view.BotmSlideMenuView;
import com.cilinet.godutch.framework.view.BotmSlideMenuView.SlideMenuItem;
import com.cilinet.godutch.user.adapter.UserListAdapter;
import com.cilinet.godutch.user.business.UserBusiness;
import com.cilinet.godutch.user.entity.User;

public class UserActivity extends FrameActivity implements
		BotmSlideMenuView.OnSlideMenuItemClickListener {

	private ListView listV_user;
	private UserListAdapter mUserListAdapter;

	private UserBusiness mUserBusiness;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		appendCenterView(R.layout.activity_user);

		bindSlideMenuItems(R.array.SlideMenuUser);
		bindSlideMenuItemsClickListener(this);

		init();
	}

	private void init() {
		initVariables();

		initView();
	}

	private void initVariables() {
		mUserBusiness = new UserBusiness(this);
	}

	private void initView() {

		listV_user = (ListView) findViewById(R.id.listV_user);
		ArrayList<User> _boundData = mUserBusiness.queryAllUsers();
		mUserListAdapter = new UserListAdapter(_boundData, this);
		listV_user.setAdapter(mUserListAdapter);
		//给ListView绑定ContextMenu
		registerForContextMenu(listV_user);
		
		getTopBarView().setTitle(
				getString(R.string.appGridTextUserManage) + "("
						+ String.valueOf(_boundData.size()) + ")");
	}
	
	private User mSelectedUser;

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		//获得用户长按的条目的位置
		AdapterView.AdapterContextMenuInfo _menuInfo = (AdapterView.AdapterContextMenuInfo)menuInfo;
		//根据位置，过得该位置上的数据
		mSelectedUser = (User)mUserListAdapter.getItem(_menuInfo.position);
		//弹出ContextMenu
		menu.setHeaderIcon(R.drawable.user_small_icon);
		menu.setHeaderTitle(mSelectedUser.name);
		
		menu.add(0, 1, 0, R.string.MenuTextEdit);
		if(mSelectedUser.state == User.USER_STATE_ENABLE){
			menu.add(0, 2, 0, R.string.MenuTextDisable);
		}else if(mSelectedUser.state == User.USER_STATE_DISABLE){
			menu.add(0, 3, 0, R.string.MenuTextEnable);
		}
		
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		int _itemId = item.getItemId();
		switch(_itemId){
		case 1: 
			showUpdateUserDialog(mSelectedUser);
			break;
		//禁用人员
		case 2:
			showDisableUserDialog(mSelectedUser);
			break;
		//启用人员
		case 3:
			showEnableUserDialog(mSelectedUser);
			break;
		default: 
			break;
		}
		
		return super.onContextItemSelected(item);
	}

	@Override
	public void onSlideMenuItemClick(View view, SlideMenuItem slideMenuItem) {
		showAddUserDialog();
		getBotmSlideMenuView().slide();
	}

	private class OnUserAddOrUpdateDialogClickListener implements
			DialogInterface.OnClickListener {

		private User mUser;
		private EditText edTxt_userName;

		public OnUserAddOrUpdateDialogClickListener(User user,
				EditText edTxt_userName) {
			mUser = user;
			this.edTxt_userName = edTxt_userName;
		}
		
		/**
		 * Dialog的点击事件
		 */
		@Override
		public void onClick(DialogInterface dialog, int which) {
			AlertDialog _dialog = (AlertDialog)dialog;
			
			// 保存或更新人员
			if (which == DialogInterface.BUTTON_POSITIVE) {
				String _userName = edTxt_userName.getText().toString().trim();
				
				boolean _validateResult = true;
				//用户名只能由英文、中文和数字组成......
				if(!RegexTools.isChineseEnglishNum(_userName)){
					showToast("用户名只能由英文、中文和数字组成......");
					_validateResult = false;
				}
				
				//如果一次校验通过
				if(_validateResult && null != mUser){
					mUser.name = _userName;
					if(mUserBusiness.updateUser(mUser)){
						showToast(R.string.TipsUpdateSuccessed);
					}else {
						showToast(R.string.TipsOperateFailed);
					}
				}
				
				//用户名重复则提示“该用户名已存在...”
				if(mUser == null && mUserBusiness.checkNameIfExists(_userName)){
					showToast("该用户名已存在...");
					_validateResult = false;
				}
				
				//如果两次校验通过，并且User还不存在
				if(_validateResult && null == mUser){
					mUser = new User(_userName,User.USER_STATE_ENABLE,new Date());
					if(mUserBusiness.addUser(mUser)){
						showToast(R.string.TipsAddSucceed);
					}else {
						showToast(R.string.TipsOperateFailed);
					}
				}

				//2、关闭dialog
				setAlertDialogClosable(_dialog, true);
				
				//3、通知UserListView重新调用Adapter里的方法(getCount...)
				ArrayList<User> _users = mUserBusiness.queryAllUsers();
				mUserListAdapter.bindData(_users);
				mUserListAdapter.notifyDataSetInvalidated();
			}else if (which == DialogInterface.BUTTON_NEGATIVE) {
				setAlertDialogClosable(_dialog, true);
			}
		}
	}

	private void showAddUserDialog() {
		View _dialogView = getLayoutInflater().inflate(
				R.layout.dialog_person_addoredit, null);
		EditText edTxt_userName = (EditText) _dialogView
				.findViewById(R.id.edTxt_userName);

		AlertDialog.Builder _builder = new AlertDialog.Builder(this);
		_builder.setTitle(
				getString(R.string.DialogTitleUser,
						getString(R.string.TitleAdd)))
				.setIcon(R.drawable.user_big_icon)
				.setView(_dialogView)
				.setPositiveButton(
						getString(R.string.ButtonTextSave),
						new OnUserAddOrUpdateDialogClickListener(null,
								edTxt_userName))
				.setNegativeButton(
						getString(R.string.ButtonTextCancel),
						new OnUserAddOrUpdateDialogClickListener(null,
								edTxt_userName)).show();
	}

	private void showUpdateUserDialog(User user) {
		View _dialogView = getLayoutInflater().inflate(R.layout.dialog_person_addoredit, null);
		
		EditText edTxt_userName = (EditText) _dialogView.findViewById(R.id.edTxt_userName);
		edTxt_userName.setText(user.name);

		AlertDialog.Builder _builder = new AlertDialog.Builder(this);
		_builder.setTitle(getString(R.string.DialogTitleUser,getString(R.string.TitleEdit)))
				.setIcon(R.drawable.user_big_icon)
				.setView(_dialogView)
				.setPositiveButton(
						getString(R.string.ButtonTextSave),
						new OnUserAddOrUpdateDialogClickListener(user,
								edTxt_userName))
				.setNegativeButton(
						getString(R.string.ButtonTextCancel),
						new OnUserAddOrUpdateDialogClickListener(user,
								edTxt_userName))
				.show();
	}
	
	private void showEnableUserDialog(final User user){
		AlertDialog.Builder _builder = new AlertDialog.Builder(this);
		_builder.setTitle(getString(R.string.DialogTitleUser, getString(R.string.TitleUserEnable)))
				.setMessage(getString(R.string.DialogMessageUserEnable, user.name))
				.setPositiveButton(R.string.ButtonTextYes, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						boolean _successed = mUserBusiness.enableUser(user.id);
						if(_successed){
							showToast(R.string.UserEnableSuccessed);
							
							ArrayList<User> _users = mUserBusiness.queryAllUsers();
							mUserListAdapter.bindData(_users);
							mUserListAdapter.notifyDataSetInvalidated();
						}else {
							showToast(R.string.TipsOperateFailed);
						}
					}
				})
				.setNegativeButton(R.string.ButtonTextNo, null)
				.show();
	}
	
	private void showDisableUserDialog(final User user){
		AlertDialog.Builder _builder = new AlertDialog.Builder(this);
		_builder.setTitle(getString(R.string.DialogTitleUser, getString(R.string.TitleUserDisable)))
				.setMessage(getString(R.string.DialogMessageUserDisable, user.name))
				.setPositiveButton(R.string.ButtonTextYes, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						boolean _successed = mUserBusiness.disableUser(user.id);
						if(_successed){
							showToast(R.string.UserDisableSuccessed);
							
							ArrayList<User> _users = mUserBusiness.queryAllUsers();
							mUserListAdapter.bindData(_users);
							mUserListAdapter.notifyDataSetInvalidated();
						}else {
							showToast(R.string.TipsOperateFailed);
						}
					}
				})
				.setNegativeButton(R.string.ButtonTextNo, null)
				.show();
	}

}
