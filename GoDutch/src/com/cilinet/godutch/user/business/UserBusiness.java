package com.cilinet.godutch.user.business;

import android.content.Context;

import com.cilinet.godutch.framework.business.BaseBusiness;
import com.cilinet.godutch.user.dal.UserDal;
import com.cilinet.godutch.user.model.User;

public class UserBusiness extends BaseBusiness {
	
	private UserDal mUserDal;

	public UserBusiness(Context context) {
		super(context);
		mUserDal = new UserDal(getContext());
	}
	
	/**
	 * 添加人员
	 */
	public boolean addUser(User user){
		return mUserDal.insert(user);
	}
	
	/**
	 * 禁用人员
	 */
	public boolean disableUser(int userId){
		return false;
	}
	
	/**
	 * 启用人员
	 */
	public boolean enableUser(int userId){
		return false;
	}

}
