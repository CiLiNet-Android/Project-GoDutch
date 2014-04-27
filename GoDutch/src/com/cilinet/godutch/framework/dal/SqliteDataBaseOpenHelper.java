package com.cilinet.godutch.framework.dal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.cilinet.godutch.framework.utils.Reflection;

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
		//1.从配置的资源中获取到子模块对应的Dal的完整类名
		String[] _sqliteDALClassNames = SqliteDataBaseConfig.getInstance(mContext).getSqliteDALClassNames();
		for(String _sqliteDALClassName : _sqliteDALClassNames){
			try {
				//3.通过类对象，创建类的实例
				SQLiteTableOpenHelper _sQLiteTableOpenHelper = (SQLiteTableOpenHelper)Reflection.newInstance(_sqliteDALClassName, new Object[]{mContext}, new Class[]{Context.class});
				
				//4、调用这个实例的onCreate()
				_sQLiteTableOpenHelper.onCreate(db);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 更新数据库
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}


	

}
