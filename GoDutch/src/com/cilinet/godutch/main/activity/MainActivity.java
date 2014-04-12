package com.cilinet.godutch.main.activity;

import android.os.Bundle;
import android.widget.GridView;

import com.cilinet.godutch.R;
import com.cilinet.godutch.framework.activity.FrameActivity;
import com.cilinet.godutch.main.adapter.MainGrdVAdapter;

public class MainActivity extends FrameActivity {
	
	private GridView grdV_index;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		super.setContentView(R.layout.activity_main);
		
		grdV_index = (GridView)findViewById(R.id.grdV_index);
		grdV_index.setAdapter(new MainGrdVAdapter(this));
	}

}
