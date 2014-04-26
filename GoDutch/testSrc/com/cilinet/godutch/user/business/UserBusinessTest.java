package com.cilinet.godutch.user.business;

import java.util.ArrayList;
import java.util.Date;

import junit.framework.Assert;
import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Log;

import com.cilinet.godutch.user.entity.User;


public class UserBusinessTest extends AndroidTestCase {
	
	private static final String TAG = "UserBusinessTest";
	
	public void testAddUser() {
		Context _context = getContext();
		UserBusiness _userBusiness = new UserBusiness(_context);
		
		User _user = new User();
		_user.name = "邱荣泉";
		_user.createDate = new Date();
		_user.state = 1;
		
		Assert.assertTrue(_userBusiness.addUser(_user));
	}

	public void testDisableUser() {
		Context _context = getContext();
		UserBusiness _userBusiness = new UserBusiness(_context);
		Assert.assertTrue(_userBusiness.disableUser(2));
	}

	public void testEnableUser() {
		Context _context = getContext();
		UserBusiness _userBusiness = new UserBusiness(_context);
		Assert.assertTrue(_userBusiness.enableUser(2));
	}
	
	public void testQueryAllUsers(){
		Context _context = getContext();
		UserBusiness _userBusiness = new UserBusiness(_context);
		
		ArrayList<User> _users = _userBusiness.queryAllUsers();
		for(User _user : _users){
			Log.i(TAG, _user.toString());
		}
	}
	
}
