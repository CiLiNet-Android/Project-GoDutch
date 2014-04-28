package com.cilinet.godutch.main.activity;

import java.lang.ref.WeakReference;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.cilinet.godutch.R;
import com.cilinet.godutch.framework.activity.FrameActivity;
import com.cilinet.godutch.framework.view.TopBarView;
import com.cilinet.godutch.main.adapter.MainGrdVAdapter;
import com.cilinet.godutch.main.adapter.MainGrdVAdapterItem;
import com.cilinet.godutch.user.activity.UserActivity;

public class MainActivity extends FrameActivity implements AdapterView.OnItemClickListener{
	
	private GridView grdV_main;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		appendCenterView(R.layout.activity_main);
		
		bindSlideMenuItems(R.array.SlideMenuActivityMain);
		
		init();
	}

	
	private void init() {
		initView();
	}


	private void initView() {
		//顶部
		TopBarView _topBarView = getTopBarView();
		_topBarView.setTitle(getString(R.string.WelcomeInfo));
		_topBarView.hideBackBtn();
		
		//中部
		grdV_main = (GridView)findViewById(R.id.grdV_main);
		grdV_main.setAdapter(new MainGrdVAdapter(this));
		grdV_main.setOnItemClickListener(this);
		
		//底部
	}


	public static final int USER_MANAGE = 0;
	public static final int ACCOUNT_BOOK_MANAGE = 1;
	public static final int CATEGORY_MANAGE = 2;
	public static final int PAYOUT_ADD = 3;
	public static final int PAYOUT_MANAGE = 4;
	public static final int PAYOUT_STATISTICS_MANAGE = 5;
	
	private static class MainHandler extends Handler {
		private WeakReference<MainActivity> mWeakReference;
		
		public MainHandler(MainActivity mainActivity){
			mWeakReference = new WeakReference<MainActivity>(mainActivity);
		}
		
		@Override
		public void handleMessage(Message msg) {
			MainActivity _mainActivity = mWeakReference.get();
			if(null != _mainActivity){
				super.handleMessage(msg);
				
				switch(msg.what){
					//人员管理
					case USER_MANAGE: {
						_mainActivity.startActivity(UserActivity.class);
						break;
					}
					default: {
						break;
					}
				}
			}
		}
		
	}
	
	private MainHandler mMainHandler = new MainHandler(this);
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		MainGrdVAdapterItem _mainGrdVAdapterItem = (MainGrdVAdapterItem)parent.getAdapter().getItem(position);
		switch(_mainGrdVAdapterItem.nameResId){
			//如果用户点击了人员管理
			case R.string.appGridTextUserManage: {
				mMainHandler.sendEmptyMessage(USER_MANAGE);
				break;
			}
			//类别管理
			case R.string.appGridTextCategoryManage: {
	
			}
			default: {
				break;
			}
		}
	}

}
