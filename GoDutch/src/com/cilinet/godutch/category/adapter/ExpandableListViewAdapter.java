package com.cilinet.godutch.category.adapter;

import java.util.ArrayList;

import com.cilinet.godutch.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
	
	private Context context;
	
	private ArrayList<GroupItem> groupItemList;
	
	
	public ExpandableListViewAdapter(Context context,ArrayList<GroupItem> groupList){
		this.context = context;
		this.groupItemList = groupList;
	}
	

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		return null;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groupItemList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return groupItemList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,View view, ViewGroup parent) {
		GroupHolder _groupHolder;
		if(null == view){
			_groupHolder = new GroupHolder();
			
			view = LayoutInflater.from(context).inflate(R.layout.category_group_item, null);
			_groupHolder.name = (TextView) view.findViewById(R.id.category_group_item_left);
			_groupHolder.cateGory = (TextView) view.findViewById(R.id.category_group_item_right);
			
			view.setTag(_groupHolder);
			
		}else{
			_groupHolder = (GroupHolder) view.getTag();
		}
		GroupItem _group_item = (GroupItem) getGroup(groupPosition);
		_groupHolder.name.setText(_group_item.name);
		_groupHolder.cateGory.setText(_group_item.category);
		
		return view;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}
	
	private class GroupHolder{
		public TextView name;
		public TextView cateGory;
	}

}
