package com.cilinet.godutch.user.dal;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.cilinet.godutch.framework.dal.BaseDal;
import com.cilinet.godutch.framework.dal.SqliteDataBaseOpenHelper.SQLiteTableOpenHelper;
import com.cilinet.godutch.user.entity.User;

public class UserDal extends BaseDal<User> implements SQLiteTableOpenHelper{
	
	/**
	 * 表定义
	 * @author zhxl
	 *
	 */
	private static final class TABLE {
		public static final String NAME = "User";
		public static final String COLUMN_ID = "id";
		public static final String COLUMN_NAME = "name";
		public static final String COLUMN_STATE = "state";
		public static final String COLUMN_CREATEDATE = "createDate";
	}
	
	public UserDal(Context context){
		super(context);
	}


	@Override
	public List<User> query(String whereSql) {
		return null;
	}


	@Override
	public boolean update(User model) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected ContentValues createContValsByEnity(User entity) {
		ContentValues _contVals = new ContentValues();
		
		_contVals.put(TABLE.COLUMN_ID, entity.id);
		_contVals.put(TABLE.COLUMN_NAME, entity.name);
		_contVals.put(TABLE.COLUMN_STATE, entity.state);
		_contVals.put(TABLE.COLUMN_CREATEDATE, formatDate(entity.createDate));
		
		return _contVals;
	}


	@Override
	protected String getTableName() {
		return TABLE.NAME;
	}


	@Override
	protected String getPKName() {
		return TABLE.COLUMN_ID;
	}


	@Override
	protected String getPKValue(User entity) {
		return String.valueOf(entity.id);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table Userdfsdfs";
		db.execSQL(sql);
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}


	
}
