package com.cilinet.godutch.user.dal;

import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cilinet.godutch.R;
import com.cilinet.godutch.framework.application.BaseApplication;
import com.cilinet.godutch.framework.dal.BaseDal;
import com.cilinet.godutch.framework.dal.SqliteDataBaseOpenHelper.SQLiteTableOpenHelper;
import com.cilinet.godutch.user.entity.User;

public class UserDal extends BaseDal<User> implements SQLiteTableOpenHelper{
	
	private static final String TAG = "UserDal";
	
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
	
	/**
	 * 切换人员的状态
	 */
	public boolean updateStateById(int id,int state){
		ContentValues _contVals = new ContentValues();
		_contVals.put(TABLE.COLUMN_STATE, state);
		
		String _whereClause = TABLE.COLUMN_ID + "=?";
		
		int _result = getSqLiteDatabase().update(TABLE.NAME, _contVals, _whereClause, new String[]{String.valueOf(id)});
		
		return _result > -1;
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
		StringBuilder _createTableScript = new StringBuilder();

		_createTableScript.append("		Create  TABLE ").append(TABLE.NAME).append("(");
		_createTableScript.append("				[").append(TABLE.COLUMN_ID).append("] integer PRIMARY KEY AUTOINCREMENT NOT NULL");
		_createTableScript.append("				,[").append(TABLE.COLUMN_NAME).append("] varchar(10) NOT NULL");
		_createTableScript.append("				,[").append(TABLE.COLUMN_CREATEDATE).append("] datetime NOT NULL");
		_createTableScript.append("				,[").append(TABLE.COLUMN_STATE).append("] integer NOT NULL");
		_createTableScript.append("				)");
		
		if(BaseApplication.IS_DEBUG){
			Log.i(TAG, _createTableScript.toString());
		}
		
		//创建表
		db.execSQL(_createTableScript.toString());
		
		//初始化一些数据
		initDefaultUsers(db);
	}


	private void initDefaultUsers(SQLiteDatabase db) {
		String[] _userNames = getContext().getResources().getStringArray(R.array.InitDefaultUserName);
		
		for(String _userName : _userNames){
			db.execSQL("INSERT INTO " + TABLE.NAME + "(" + TABLE.COLUMN_NAME + "," + TABLE.COLUMN_CREATEDATE + "," + TABLE.COLUMN_STATE + ") VALUES(?,?,?)", new Object[]{_userName,formatDate(new Date()),1});
		}
		
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

	@Override
	protected ArrayList<User> cursorToList(Cursor cursor) {
		ArrayList<User> _users = new ArrayList<User>();
		
		while(cursor.moveToNext()){
			User _user = new User();
			
			_user.id = cursor.getInt(cursor.getColumnIndex(TABLE.COLUMN_ID));
			_user.name = cursor.getString(cursor.getColumnIndex(TABLE.COLUMN_NAME));
			_user.createDate = parseDate(cursor.getString(cursor.getColumnIndex(TABLE.COLUMN_CREATEDATE)));
			_user.state = cursor.getInt(cursor.getColumnIndex(TABLE.COLUMN_STATE));
			
			_users.add(_user);
		}
		
		return _users;
	}

}
