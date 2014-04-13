package com.cilinet.godutch.user.activity;

import android.os.Bundle;

import com.cilinet.godutch.R;
import com.cilinet.godutch.framework.activity.FrameActivity;

public class UserActivity extends FrameActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendCenterView(R.layout.activity_user);
	}

}
