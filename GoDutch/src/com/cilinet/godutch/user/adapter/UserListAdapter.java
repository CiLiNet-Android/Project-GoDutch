package com.cilinet.godutch.user.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cilinet.godutch.R;
import com.cilinet.godutch.framework.adapter.AbsBaseAdapter;
import com.cilinet.godutch.user.entity.User;

public class UserListAdapter extends AbsBaseAdapter<User>{

	public UserListAdapter(ArrayList<User> boundData, Context context) {
		super(boundData, context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder _holder;
		if(null == convertView){
			_holder = new ViewHolder();
			convertView = inflateView(R.layout.user_listview_item);
			_holder.imgV_userImg = (ImageView) convertView.findViewById(R.id.imgV_userImg);
			_holder.txtV_userName = (TextView) convertView.findViewById(R.id.txtV_userName);
			convertView.setTag(_holder);
		}else{
			_holder = (ViewHolder)convertView.getTag();
		}
		
		User _user = getBoundData().get(position);
		_holder.imgV_userImg.setImageResource(R.drawable.user_big_icon);
		_holder.txtV_userName.setText(_user.name);
		
		return convertView;
	}

	private class ViewHolder{
		public ImageView imgV_userImg;
		public TextView txtV_userName;
	}

}
