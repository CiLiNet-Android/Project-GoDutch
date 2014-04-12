package com.cilinet.godutch.main.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cilinet.godutch.R;

public class MainGrdVAdapter extends BaseAdapter {
	
	private static class MainGrdVItem {
		public int iconResId;
		public int nameResId;
		
		public MainGrdVItem(int iconResId,int nameResId){
			this.iconResId = iconResId;
			this.nameResId = nameResId;
		}
	}
	
	private static ArrayList<MainGrdVItem> mMainGrdVItems;
	static {
		mMainGrdVItems = new ArrayList<MainGrdVAdapter.MainGrdVItem>();
		mMainGrdVItems.add(new MainGrdVItem(R.drawable.grid_payout, R.string.appGridTextPayoutAdd));
		mMainGrdVItems.add(new MainGrdVItem(R.drawable.grid_bill, R.string.appGridTextPayoutManage));
		mMainGrdVItems.add(new MainGrdVItem(R.drawable.grid_report, R.string.appGridTextStatisticsManage));
		mMainGrdVItems.add(new MainGrdVItem(R.drawable.grid_account_book, R.string.appGridTextAccountBookManage));	
		mMainGrdVItems.add(new MainGrdVItem(R.drawable.grid_category, R.string.appGridTextCategoryManage));
		mMainGrdVItems.add(new MainGrdVItem(R.drawable.grid_user, R.string.appGridTextUserManage));
	}
	
	private Context mContext;
	
	public MainGrdVAdapter(Context context){
		mContext = context;
	}
	
	@Override
	public int getCount() {
		return mMainGrdVItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mMainGrdVItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	class ViewHolder {
		public ImageView imgV_mainItemIcon;
		public TextView txtV_mainItemName;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		ViewHolder _viewHolder;
		if(null == convertView){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.main_grdv_item, null);
			
			_viewHolder = new ViewHolder();
			_viewHolder.imgV_mainItemIcon = (ImageView)convertView.findViewById(R.id.imgV_mainItemIcon);
			_viewHolder.txtV_mainItemName = (TextView)convertView.findViewById(R.id.txtV_mainItemName);
			
			convertView.setTag(_viewHolder);
		}else {
			_viewHolder = (ViewHolder)convertView.getTag();
		}
		
		MainGrdVItem _mainGrdVItem = (MainGrdVItem)getItem(position);
		
		_viewHolder.imgV_mainItemIcon.setImageResource(_mainGrdVItem.iconResId);
		_viewHolder.txtV_mainItemName.setText(_mainGrdVItem.nameResId);
		
		return convertView;
	}

}
