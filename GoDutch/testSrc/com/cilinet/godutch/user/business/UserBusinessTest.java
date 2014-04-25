package com.cilinet.godutch.user.business;

import java.util.Date;

import junit.framework.Assert;
import android.content.Context;
import android.test.AndroidTestCase;

import com.cilinet.godutch.user.entity.User;



public class UserBusinessTest extends AndroidTestCase {
	
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
		Assert.assertTrue(_userBusiness.disableUser(7));
	}

	public void testEnableUser() {
		
	}
	
}
