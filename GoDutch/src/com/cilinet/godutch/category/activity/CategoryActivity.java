package com.cilinet.godutch.category.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.cilinet.godutch.R;
import com.cilinet.godutch.category.adapter.ChildItem;
import com.cilinet.godutch.category.adapter.ExpandableListViewAdapter;
import com.cilinet.godutch.category.adapter.GroupItem;
import com.cilinet.godutch.category.business.CategoryBusiness;
import com.cilinet.godutch.category.entity.Category;
import com.cilinet.godutch.framework.activity.FrameActivity;
import com.cilinet.godutch.framework.view.BotmSlideMenuView;
import com.cilinet.godutch.framework.view.BotmSlideMenuView.SlideMenuItem;

/**
 * 类别管理
 * 
 * @author zhxl
 * 
 */
public class CategoryActivity extends FrameActivity implements BotmSlideMenuView.OnSlideMenuItemClickListener{
	
	private static final int ADD_CATEGORY = 0;
	private static final int STATISTICS_CATEGORY = 1;
	
	private static final String TAG = "CategoryActivity";
	
	private CategoryBusiness categoryBusiness;
	
	private ExpandableListView expaListView;
	
	private ArrayList<GroupItem> groupItemList;
	
	private ArrayList<ChildItem> childItemList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//绑定中间区域的ExpandableListView
		appendCenterView(R.layout.category_center_expandablelistview);
		
		//绑定底部的滑动菜单并注册Item的单击事件
		bindSlideMenuItems(R.array.SlideMenuCategory);
		bindSlideMenuItemsClickListener(this);

		init();
		
		//设置顶部的页眉
		getTopBarView().setTitle(getResources().getString(R.string.appGridTextCategoryManage) + "(" +  groupItemList.size() + ")");

	}
	
	private void init(){
		
		initMember();
		initExpandableListView();
	}
	
	//初使化成员变量
	private void initMember() {

		categoryBusiness = new CategoryBusiness(this);
		
		groupItemList = new ArrayList<GroupItem>();
		
		childItemList = new ArrayList<ChildItem>();
		
	}

	//初使化ExpandableListView并且绑定Adapter
	private void initExpandableListView(){
		
		expaListView = (ExpandableListView) findViewById(R.id.expandableListView);
		
		ArrayList<Category> _categoryList = categoryBusiness.quetyCategory();
		
		for(Category _category: _categoryList){
			GroupItem _groupItem = new GroupItem( );
			_groupItem.name = _category.name;
			_groupItem.category = String.valueOf(childItemList.size()) + String.valueOf(getResources().getString(R.string.ActivityCenterItemCategory));
			groupItemList.add(_groupItem);
			
			Log.i(TAG, _category.toString());
		}

		expaListView.setAdapter(new ExpandableListViewAdapter(this,groupItemList));
	}

	
	//底部滑动菜单的Item单击事件处理
	@Override
	public void onSlideMenuItemClick(View view, SlideMenuItem slideMenuItem) {

		switch (slideMenuItem.id) {
		
		case ADD_CATEGORY:
				startActivity(NewCategoryActivity.class);
			break;
			
		case STATISTICS_CATEGORY:
				showToast(slideMenuItem.title);
			break;

		default:
			break;
		}
		
//		getBotmSlideMenuView().slide();
	
	}

	
	
	
	
}
