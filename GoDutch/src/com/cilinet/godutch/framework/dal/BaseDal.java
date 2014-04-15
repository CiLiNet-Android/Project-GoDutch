package com.cilinet.godutch.framework.dal;

import java.util.List;

import android.content.Context;

/**
 * 主要的作用，就是跟数据库进行交互，将模型封装的数据存入到对应的表中
 * @author zhxl
 *
 * @param <T>
 */
public abstract class BaseDal<T> {
	
	private Context mContext;
	
	public BaseDal(Context context){
		mContext = context;
	}
	
	/**
	 * 增
	 */
	public abstract boolean insert(T model);
	
	/**
	 * 删
	 */
	public abstract boolean deletet(T model);
	
	/**
	 * 改
	 */
	public abstract boolean update(T model);
	
	/**
	 * 查
	 */
	public abstract List<T> query(String whereSql);
	
	protected Context getContext(){
		return mContext;
	}

}
