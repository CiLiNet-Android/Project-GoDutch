package com.cilinet.godutch.user.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ListView;

import com.cilinet.godutch.R;
import com.cilinet.godutch.framework.activity.FrameActivity;
import com.cilinet.godutch.framework.view.TopBarView;
import com.cilinet.godutch.user.adapter.UserListAdapter;
import com.cilinet.godutch.user.business.UserBusiness;
import com.cilinet.godutch.user.entity.User;

public class UserActivity extends FrameActivity {
	
	private ListView listV_user;
	private UserListAdapter mUserListAdapter;
	
	private UserBusiness mUserBusiness;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendCenterView(R.layout.activity_user);
		
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
		
		//顶部
		TopBarView _topBarView = new TopBarView(this);
	//	_topBarView.setTitle(getString(R.string.appGridTextUserManage) + "(" + ")");
		
		listV_user = (ListView)findViewById(R.id.listV_user);
		ArrayList<User> _boundData = mUserBusiness.queryAllUsers();
		mUserListAdapter = new UserListAdapter(_boundData, this);
		listV_user.setAdapter(mUserListAdapter);
		
		
		_topBarView.setTitle(getString(R.string.appGridTextUserManage) + "(" + String.valueOf( _boundData.size() ) + ")");
	}

}
