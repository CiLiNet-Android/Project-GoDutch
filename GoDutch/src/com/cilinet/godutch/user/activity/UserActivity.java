package com.cilinet.godutch.user.activity;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.cilinet.godutch.R;
import com.cilinet.godutch.framework.activity.FrameActivity;
import com.cilinet.godutch.framework.view.BotmSlideMenuView;
import com.cilinet.godutch.framework.view.BotmSlideMenuView.SlideMenuItem;
import com.cilinet.godutch.user.adapter.UserListAdapter;
import com.cilinet.godutch.user.business.UserBusiness;
import com.cilinet.godutch.user.entity.User;

public class UserActivity extends FrameActivity implements 
		BotmSlideMenuView.OnSlideMenuItemClickListener{
	
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
	
	private void init(){
		initVariables();
		
		initView();
	}
	
	private void initVariables() {
		mUserBusiness = new UserBusiness(this);
	}

	private void initView(){
		
		listV_user = (ListView)findViewById(R.id.listV_user);
		ArrayList<User> _boundData = mUserBusiness.queryAllUsers();
		mUserListAdapter = new UserListAdapter(_boundData, this);
		listV_user.setAdapter(mUserListAdapter);
		
		getTopBarView().setTitle(getString(R.string.appGridTextUserManage) + "(" + String.valueOf( _boundData.size()) + ")");
	}

	@Override
	public void onSlideMenuItemClick(View view,SlideMenuItem slideMenuItem) {
		showAddUserDialog();
		getBotmSlideMenuView().slide();
	}
	
	
	private class OnUserAddOrUpdateDialogClickListener implements DialogInterface.OnClickListener {
		
		private User mUser;
		private EditText edTxt_userName;
		
		public OnUserAddOrUpdateDialogClickListener(User user,EditText edTxt_userName){
			mUser = user;
			this.edTxt_userName = edTxt_userName;
		}
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			//保存或更新人员
			if(which == DialogInterface.BUTTON_POSITIVE){
				
			}
			//取消
			else if(which == DialogInterface.BUTTON_NEGATIVE){
				
			}
		}
	}
	
	private void showAddUserDialog(){
		View _dialogView = getLayoutInflater().inflate(R.layout.dialog_person_addoredit, null);
		EditText edTxt_userName = (EditText)_dialogView.findViewById(R.id.edTxt_userName);
		
		AlertDialog.Builder _builder = new AlertDialog.Builder(this);
		_builder
			.setTitle(getString(R.string.DialogTitleUser, getString(R.string.TitleAdd)))
			.setIcon(R.drawable.user_big_icon)
			.setView(_dialogView)
			.setPositiveButton(getString(R.string.ButtonTextSave), new OnUserAddOrUpdateDialogClickListener(null,edTxt_userName))
			.setNegativeButton(getString(R.string.ButtonTextCancel), new OnUserAddOrUpdateDialogClickListener(null,edTxt_userName))
			.show();
	}
	
	
	private void showUpdateUserDialog(User user){
		View _dialogView = getLayoutInflater().inflate(R.layout.dialog_person_addoredit, null);
		EditText edTxt_userName = (EditText)_dialogView.findViewById(R.id.edTxt_userName);
		
		AlertDialog.Builder _builder = new AlertDialog.Builder(this);
		_builder
			.setTitle(getString(R.string.DialogTitleUser, getString(R.string.TitleAdd)))
			.setIcon(R.drawable.user_big_icon)
			.setView(_dialogView)
			.setPositiveButton(getString(R.string.ButtonTextSave), new OnUserAddOrUpdateDialogClickListener(user,edTxt_userName))
			.setNegativeButton(getString(R.string.ButtonTextCancel), new OnUserAddOrUpdateDialogClickListener(user,edTxt_userName))
			.show();
	}

}
