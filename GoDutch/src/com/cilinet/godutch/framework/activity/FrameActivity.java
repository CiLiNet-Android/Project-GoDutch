package com.cilinet.godutch.framework.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import com.cilinet.godutch.R;

/**
 * Activity用到的业务方面的通用方法
 * 
 * @author zhxl
 * 
 */
public class FrameActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		super.setContentView(R.layout.framework);

	}

	protected void appendCenterView(int layoutResId) {
		FrameLayout framework_center = (FrameLayout) findViewById(R.id.framework_center);

		View _view = inflateView(layoutResId);

		FrameLayout.LayoutParams _layoutParams = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);

		framework_center.addView(_view, _layoutParams);
	}

}
