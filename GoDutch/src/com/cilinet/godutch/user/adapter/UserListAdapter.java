package com.cilinet.godutch.user.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.cilinet.godutch.framework.adapter.AbsBaseAdapter;
import com.cilinet.godutch.user.entity.User;

public class UserListAdapter extends AbsBaseAdapter<User>{

	public UserListAdapter(ArrayList<User> boundData, Context context) {
		super(boundData, context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		return null;
	}

	

}
