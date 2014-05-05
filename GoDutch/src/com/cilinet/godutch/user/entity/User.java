package com.cilinet.godutch.user.entity;

import java.util.Date;

import com.cilinet.godutch.framework.entity.BaseEntity;

/**
 * 人员管理的模型
 * 
 * @author zhxl
 * 
 */
public class User extends BaseEntity {
	
	/** 用户状态：启用 **/
	public static final int USER_STATE_ENABLE = 1;
	/** 用户状态：禁用 **/
	public static final int USER_STATE_DISABLE = 0;
	
	public User(){}
	
	public User(String userName,Integer userState,Date userCreateDate){
		name = userName;
		state = userState;
		createDate = userCreateDate;
	}

	public String toString() {
		return "id: " + id + " && " + "name: " + name + " && " + "createDate: "
				+ createDate + " && " + "state: " + state;
	}
	
	public String getState(){
		String _state = null;
		
		if(state == USER_STATE_ENABLE){
			_state = "启用";
		}else if(state == USER_STATE_DISABLE){
			_state = "禁用";
		}
		
		return _state;
	}

}
