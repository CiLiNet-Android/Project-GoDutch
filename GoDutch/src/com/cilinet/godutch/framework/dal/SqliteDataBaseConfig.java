package com.cilinet.godutch.framework.dal;

import android.content.Context;

/** 数据库级别的配置信息 
 * 
 * 在构造方面，使用了单例模式
 * **/
public class SqliteDataBaseConfig {
	
	public static final String DATABASE_NAME = "GoDutch";
	
	public static final int DATABASE_VERSION = 1;
	
	private Context mContext;
	
	private static SqliteDataBaseConfig mSqliteDataBaseConfig;
	
	private SqliteDataBaseConfig(Context context){
		mContext = context;
	}
	
	public static SqliteDataBaseConfig getInstance(Context context){
		if(null == mSqliteDataBaseConfig){
			mSqliteDataBaseConfig = new SqliteDataBaseConfig(context);
		}
		
		return mSqliteDataBaseConfig;
	}
	
	
}
