package com.cilinet.godutch.framework.model;

import java.util.Date;

public class BaseModel {
	
	/** 模型的唯一标识 **/
	public Integer id;
	
	/** 模型名称 **/
	public String name;
	
	/** 模型的状态 (0表示停用，1表示启用)**/
	public Integer state;
	
	/** 创建日期 **/
	public Date createDate;

}
