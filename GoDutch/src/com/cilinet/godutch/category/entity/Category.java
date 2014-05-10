package com.cilinet.godutch.category.entity;

import com.cilinet.godutch.framework.entity.BaseEntity;

/**
 * 消费类别
 * @author zhxl
 *
 */
public class Category extends BaseEntity {
	
	/** 类型标记名称 **/
	public String typeFlag;
	
	/** 父类别Id **/
	public int parentId = 0;
	
	/** 类别路径(面包屑) **/
	public String path;
	
	
}
