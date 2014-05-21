package com.cilinet.godutch.category.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cilinet.godutch.R;
import com.cilinet.godutch.framework.activity.FrameActivity;

public class NewCategoryActivity extends FrameActivity {
	
	private Spinner category_spinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 设置页眉
		getTopBarView().setTitle(loadString(R.string.TitleAdd)+ loadString(R.string.ActivityCenterItemCategory));
		
		appendCenterView(R.layout.add_category);
		
		//显示底部保存/取消按钮
		showBottomSaveOrCancelBtn();

		initSpinner();
	}
	
	private void initSpinner(){
		category_spinner = (Spinner) findViewById(R.id.category_spinner);
		
		
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
		
		String[] categorys = getResources().getStringArray(R.array.InitDefaultCategoryName);
		
		for(String categoryName : categorys){
			spinnerAdapter.add(categoryName);
		}
		
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		
		category_spinner.setAdapter(spinnerAdapter);
		
	}
}
