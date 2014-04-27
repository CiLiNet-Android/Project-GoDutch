package com.cilinet.godutch.framework.dal;

import android.content.Context;

import com.cilinet.godutch.R;

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
	
	/** 
	 * 数据库中有哪些表需要创建
	 * 从资源文件中获得需要创建数据表的类名，然后将来通过反射来调用这些类的创建表的方法 **/
	public String[] getSqliteDALClassNames(){
		return mContext.getResources().getStringArray(R.array.SQLiteDALClassName);
	}
	
	
}
