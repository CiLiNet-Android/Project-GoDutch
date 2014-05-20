package com.cilinet.godutch.framework.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.cilinet.godutch.R;
import com.cilinet.godutch.framework.view.BotmSlideMenuView;
import com.cilinet.godutch.framework.view.BotmSlideMenuView.OnSlideMenuItemClickListener;
import com.cilinet.godutch.framework.view.TopBarView;

/**
 * Activity用到的业务方面的通用方法
 * 
 * @author zhxl
 * 
 */
public class FrameActivity extends BaseActivity {

	/** 顶部标题栏 **/
	private TopBarView mTopBarView;

	/** 底部滑动控件 **/
	private BotmSlideMenuView mBotmSlideMenuView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Activity标题栏
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Activity支持的旋转方向
		super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		super.setContentView(R.layout.framework);

		init();

	}

	private void init() {
		initView();
	}

	private void initView() {
		// 初始化顶部标题栏
		mTopBarView = new TopBarView(this);

		mBotmSlideMenuView = new BotmSlideMenuView(this);
	}

	protected TopBarView getTopBarView() {
		return mTopBarView;
	}

	protected void appendCenterView(int layoutResId) {
		FrameLayout framework_center = (FrameLayout) findViewById(R.id.framework_center);

		View _view = inflateView(layoutResId);

		FrameLayout.LayoutParams _layoutParams = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);

		framework_center.addView(_view, _layoutParams);
	}

	/**
	 * 绑定滑动菜单项
	 */
	protected void bindSlideMenuItems(int slideMenuItemsResId) {
		mBotmSlideMenuView.bindSlideItems(slideMenuItemsResId);
	}

	/**
	 * 设置BotmSlideMenuView的点击事件委托者
	 */
	protected void bindSlideMenuItemsClickListener(
			OnSlideMenuItemClickListener listener) {
		mBotmSlideMenuView.setOnSlideMenuItemClickListener(listener);
	}

	protected BotmSlideMenuView getBotmSlideMenuView() {
		return mBotmSlideMenuView;
	}

	// 显示底部的保存/取消按钮
	protected void showBottomSaveOrCancelBtn() {
		// 关闭滑动菜单条
		getBotmSlideMenuView().removeSelf();
		// 显示按钮
		LinearLayout framework_bottom_btn = (LinearLayout) findViewById(R.id.framework_bottom_btn);
		framework_bottom_btn.setVisibility(View.VISIBLE);
		// 动态设置中间区域的位置
		FrameLayout framework_center = (FrameLayout) findViewById(R.id.framework_center);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		layoutParams.addRule(RelativeLayout.ABOVE, R.id.framework_bottom_btn);
		layoutParams.addRule(RelativeLayout.BELOW, R.id.framework_top);
		framework_center.setLayoutParams(layoutParams);
	}

}
