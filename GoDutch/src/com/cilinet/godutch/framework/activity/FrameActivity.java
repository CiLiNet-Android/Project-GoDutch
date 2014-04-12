package com.cilinet.godutch.framework.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

/**
 * Activity用到的业务方面的通用方法
 * @author zhxl
 *
 */
public class FrameActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
	

}
