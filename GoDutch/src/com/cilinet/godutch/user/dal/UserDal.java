package com.cilinet.godutch.user.dal;

import java.util.List;

import android.content.Context;

import com.cilinet.godutch.framework.dal.BaseDal;
import com.cilinet.godutch.user.model.User;

public class UserDal extends BaseDal<User> {
	
	public UserDal(Context context){
		super(context);
	}

	@Override
	public boolean insert(User model) {
		return false;
	}

	@Override
	public boolean deletet(User model) {
		return false;
	}

	@Override
	public boolean update(User model) {
		return false;
	}

	@Override
	public List<User> query(String whereSql) {
		return null;
	}

}
