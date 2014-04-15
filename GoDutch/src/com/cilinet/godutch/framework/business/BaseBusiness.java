package com.cilinet.godutch.framework.business;

import android.content.Context;

/**
 * 业务逻辑层基类
 * @author zhxl
 *
 */
public class BaseBusiness {
	
	private Context mContext;
	
	public BaseBusiness(Context context){
		mContext = context;
	}
	
	protected Context getContext(){
		return mContext;
	}

}
