package com.cilinet.godutch.user.entity;

import com.cilinet.godutch.framework.entity.BaseEntity;

/**
 * 人员管理的模型
 * 
 * @author zhxl
 * 
 */
public class User extends BaseEntity {

	public String toString() {
		return "id: " + id + " && " + "name: " + name + " && " + "createDate: "
				+ createDate + " && " + "state: " + state;
	}

}
