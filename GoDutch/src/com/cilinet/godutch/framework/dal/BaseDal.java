package com.cilinet.godutch.framework.dal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * 主要的作用，就是跟数据库进行交互，将模型封装的数据存入到对应的表中
 * @author zhxl
 *
 * @param <T>
 */
public abstract class BaseDal<T> {
	
	private Context mContext;
	
	private SQLiteDatabase mSqLiteDatabase;
	
	public BaseDal(Context context){
		mContext = context;
	}
	
	/**
	 * 增
	 */
	public boolean insert(T entity){
		ContentValues _contVals = createContValsByEnity(entity);
		
		long _result = getSqLiteDatabase().insert(getTableName(), null, _contVals);
		
		return _result > -1;
	}
	
	/**
	 * 删
	 */
	public boolean deletet(T entity){
		String _whereClause = getPKName() + "=?";
		int _result = getSqLiteDatabase().delete(getTableName(), _whereClause, new String[]{getPKValue(entity)});
		
		return _result > -1;
	}
	
	/**
	 * 改
	 */
	public boolean update(T entity){
		String _whereClause = getPKName() + "=?";
		int _result = getSqLiteDatabase().update(getTableName(), createContValsByEnity(entity), _whereClause, new String[]{getPKValue(entity)});
		
		return _result > -1;
	}
	
	/**
	 * 开启事务
	 */
	public void beginTransaction(){
		getSqLiteDatabase().beginTransaction();
	}
	
	/**
	 * 设置事务成功标志位
	 */
	public void setTransactionSuccessful(){
		getSqLiteDatabase().setTransactionSuccessful();
	}
	
	/**
	 * 提交事务
	 */
	public void endTransaction(){
		getSqLiteDatabase().endTransaction();
	}
	
	/**
	 * 查
	 */
	public abstract List<T> query(String whereSql);
	
	
	/**
	 * 从实体中创建ContentValues
	 * @param entity
	 * @return
	 */
	protected abstract ContentValues createContValsByEnity(T entity);
	
	/**
	 * 
	 * @return String 数组
	 * String[0] 表名
	 * String[1] 主键的名称
	 */
	protected abstract String getTableName();
	
	protected abstract String getPKName();
	
	protected abstract String getPKValue(T entity);
	
	protected Context getContext(){
		return mContext;
	}
	
	private static final String DEFULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * Date -> String
	 */
	@SuppressLint("SimpleDateFormat")
	protected String formatDate(Date date) {
		SimpleDateFormat _dateFormat = new SimpleDateFormat(DEFULT_DATE_FORMAT);
		return _dateFormat.format(date);
	}
	
	/**
	 * String -> Date
	 */
	@SuppressLint("SimpleDateFormat")
	protected Date parseDate(String date){
		SimpleDateFormat _dateFormat = new SimpleDateFormat(DEFULT_DATE_FORMAT);
		try {
			return _dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public SQLiteDatabase getSqLiteDatabase() {
		if(null == mSqLiteDatabase){
			mSqLiteDatabase = SqliteDataBaseOpenHelper.getInstance(mContext).getWritableDatabase();
		}
		return mSqLiteDatabase;
	}
	
	
	
}
