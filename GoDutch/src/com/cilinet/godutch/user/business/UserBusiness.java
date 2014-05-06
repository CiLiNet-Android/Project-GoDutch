package com.cilinet.godutch.user.business;

import java.util.ArrayList;

import android.content.Context;

import com.cilinet.godutch.framework.business.BaseBusiness;
import com.cilinet.godutch.user.dal.UserDal;
import com.cilinet.godutch.user.entity.User;

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
	 * 查看已启用人员
	 */
	public ArrayList<User> queryAvailableUsers(){
		String _whereSql = "WHERE state=1";
		return mUserDal.query(_whereSql);
	}
	
	/**
	 * 查看所有人员
	 */
	public ArrayList<User> queryAllUsers(){
		return mUserDal.query(null);
	}
	
	/**
	 * 查看用户名是否已存在
	 */
	public boolean checkNameIfExists(String name){
		String _whereSql = "WHERE name='" + name + "'";
		ArrayList<User> _users = mUserDal.query(_whereSql);
		
		return _users.size() > 0;
	}
	
	/**
	 * 禁用人员
	 */
	public boolean disableUser(int userId){
		return mUserDal.updateStateById(userId, 0);
	}
	
	/**
	 * 启用人员
	 */
	public boolean enableUser(int userId){
		return mUserDal.updateStateById(userId, 1);
	}
	
	/**
	 * 修改人员
	 */
	public boolean updateUser(User user){
		return mUserDal.update(user);
	}

}
