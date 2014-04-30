package com.cilinet.godutch.user.activity;

import java.lang.reflect.Field;
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

		getTopBarView().setTitle(
				getString(R.string.appGridTextUserManage) + "("
						+ String.valueOf(_boundData.size()) + ")");
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
		 * 保持Dialog不自动关闭
		 * @param dialog
		 */
		private void keepDialog(DialogInterface dialog) {
			try {
				Field field = dialog.getClass().getSuperclass()
						.getDeclaredField("mShowing");
				field.setAccessible(true);
				field.set(dialog, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 *  关闭Dialog
		 * @param dialog
		 */
		private void destroyDialog(DialogInterface dialog) {
			try {
				Field field = dialog.getClass().getSuperclass()
						.getDeclaredField("mShowing");
				field.setAccessible(true);
				field.set(dialog, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			dialog.cancel();
		}

		/**
		 *  校验新建的用户名
		 */
		private void verifyNewUserName(DialogInterface dialog) {
			String addName = edTxt_userName.getText().toString().trim();

			/**
			 * 用户名重复则提示“该用户名已存在...”
			 */
			ArrayList<User> _userData = mUserBusiness.queryUsers();
			for (int i = 0; i < _userData.size(); i++) {
				User user = _userData.get(i);
				if (user.name.equals(addName)) {
					showToast("该用户名已存在...");
					keepDialog(dialog);
					return;
				}
			}

			/**
			 * 用户名只能由英文、中文和数字组成......
			 */
			if (addName.matches("[a-zA-Z0-9\u4e00-\u9fa5]+")) {
				showToast(addName);
				destroyDialog(dialog);
			} else {
				showToast("用户名只能由英文、中文和数字组成......");
				keepDialog(dialog);
			}
			
			// showToast("测试代码...");
		}

		
		/**
		 * Dialog的点击事件
		 */
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// 保存或更新人员
			if (which == DialogInterface.BUTTON_POSITIVE) {

				verifyNewUserName(dialog);
			}

			// 取消
			else if (which == DialogInterface.BUTTON_NEGATIVE) {
				destroyDialog(dialog);
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
						new OnUserAddOrUpdateDialogClickListener(user,
								edTxt_userName))
				.setNegativeButton(
						getString(R.string.ButtonTextCancel),
						new OnUserAddOrUpdateDialogClickListener(user,
								edTxt_userName)).show();
	}

}
