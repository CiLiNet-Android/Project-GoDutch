package com.cilinet.godutch.framework.dal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cilinet.godutch.user.dal.UserDal;

public class SqliteDataBaseOpenHelper extends SQLiteOpenHelper {
	
	private static SqliteDataBaseOpenHelper mSqliteDataBaseOpenHelper;
	
	private Context mContext;
	
	private SqliteDataBaseOpenHelper(Context context){
		super(context, SqliteDataBaseConfig.DATABASE_NAME, null, SqliteDataBaseConfig.DATABASE_VERSION);
		mContext = context;
	}
	
	public static SqliteDataBaseOpenHelper getInstance(Context context){
		if(null == mSqliteDataBaseOpenHelper){
			mSqliteDataBaseOpenHelper = new SqliteDataBaseOpenHelper(context);
		}
		
		return mSqliteDataBaseOpenHelper;
	}
	
	public static interface SQLiteTableOpenHelper {
		public void onCreate(SQLiteDatabase db);
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
	}

	/**
	 * 创建数据表
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		SQLiteTableOpenHelper s = (SQLiteTableOpenHelper)new UserDal(mContext);
		s.onCreate(db);
	}

	/**
	 * 更新数据库
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}


	

}
