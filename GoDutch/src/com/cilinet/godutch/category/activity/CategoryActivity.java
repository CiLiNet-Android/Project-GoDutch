package com.cilinet.godutch.category.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.cilinet.godutch.R;
import com.cilinet.godutch.category.adapter.ExpandableListViewAdapter;
import com.cilinet.godutch.category.adapter.Group_item;
import com.cilinet.godutch.framework.activity.FrameActivity;

/**
 * 类别管理
 * 
 * @author zhxl
 * 
 */
public class CategoryActivity extends FrameActivity {
	
	private int cateGoryCount = 0;
	
	private ExpandableListView expaListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendCenterView(R.layout.category_center_expandablelistview);
		
		init();
		
		
	}
	
	private void init(){
		initExpandableListView();
	}
	
	private void initExpandableListView(){
		
		ArrayList<Group_item> _groupItemList = new ArrayList<Group_item>();
		
		expaListView = (ExpandableListView) findViewById(R.id.expandableListView);
		
		String[] _cateGoryGroup = getResources().getStringArray(R.array.InitDefaultCategoryName);
		for(String _groupItemName : _cateGoryGroup){
			Group_item _group_item = new Group_item(_groupItemName, String.valueOf(cateGoryCount) + getResources().getString(R.string.ActivityCenterItemCategory));
			
			_groupItemList.add(_group_item);
		}

		expaListView.setAdapter(new ExpandableListViewAdapter(this,_groupItemList));
	}
	
	
}
