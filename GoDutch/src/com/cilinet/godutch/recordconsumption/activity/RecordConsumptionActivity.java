package com.cilinet.godutch.recordconsumption.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.cilinet.godutch.R;
import com.cilinet.godutch.framework.activity.FrameActivity;

public class RecordConsumptionActivity extends FrameActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//设置页眉
		getTopBarView().setTitle(loadString(R.string.TitleAdd) + loadString(R.string.PayoutTypeFlag));
		//绑定中间区域的布局
		appendCenterView(R.layout.activity_record_consumption);
		
		showBottomSaveOrCancelBtn();	
		
	}
	
}
