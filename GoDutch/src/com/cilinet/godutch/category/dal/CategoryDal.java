package com.cilinet.godutch.category.dal;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cilinet.godutch.R;
import com.cilinet.godutch.category.entity.Category;
import com.cilinet.godutch.framework.application.BaseApplication;
import com.cilinet.godutch.framework.dal.BaseDal;
import com.cilinet.godutch.framework.dal.SqliteDataBaseOpenHelper.SQLiteTableOpenHelper;

public class CategoryDal extends BaseDal<Category> implements SQLiteTableOpenHelper {
	
	private static final String TAG = "CategoryDal";
	
	public CategoryDal(Context context) {
		super(context);
	}

	public static final class TABLE {
		public static final String NAME = "Category";
		public static final String COLUMN_ID = "CategoryID";
		public static final String COLUMN_NAME = "CategoryName";
		public static final String COLUMN_TYPEFLAG = "TypeFlag";
		public static final String COLUMN_PARENTID = "ParentID";
		public static final String COLUMN_PATH = "Path";
		public static final String COLUMN_STATE = "State";
		public static final String COLUMN_CREATEDATE = "CreateDate";
	}

	@Override
	protected ContentValues createContValsByEnity(Category entity) {
		ContentValues _contentValues = new ContentValues();
		
		_contentValues.put(TABLE.COLUMN_ID, entity.id);
		_contentValues.put(TABLE.COLUMN_NAME, entity.name);
		_contentValues.put(TABLE.COLUMN_TYPEFLAG, entity.typeFlag);
		_contentValues.put(TABLE.COLUMN_PARENTID, entity.parentId);
		_contentValues.put(TABLE.COLUMN_PATH, entity.path);
		_contentValues.put(TABLE.COLUMN_STATE, entity.state);
		_contentValues.put(TABLE.COLUMN_CREATEDATE, formatDate(entity.createDate));

		return _contentValues;
	}

	@Override
	protected String getTableName() {
		return TABLE.NAME;
	}

	@Override
	protected String getPKName() {
		return TABLE.COLUMN_NAME;
	}

	@Override
	protected String getPKValue(Category entity) {
		return String.valueOf(entity.id);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder s_CreateTableScript = new StringBuilder();

		s_CreateTableScript.append("		CREATE  TABLE " + TABLE.NAME + "(");
		s_CreateTableScript.append("				[" + TABLE.COLUMN_ID + "] integer PRIMARY KEY AUTOINCREMENT NOT NULL");
		s_CreateTableScript.append("				,[" + TABLE.COLUMN_NAME + "] varchar(20) NOT NULL");
		s_CreateTableScript.append("				,[" + TABLE.COLUMN_TYPEFLAG + "] varchar(20) NOT NULL");
		s_CreateTableScript.append("				,[" + TABLE.COLUMN_PARENTID + "] integer NOT NULL");
		s_CreateTableScript.append("				,[" + TABLE.COLUMN_PATH + "] text NOT NULL");
		s_CreateTableScript.append("				,[" + TABLE.COLUMN_CREATEDATE + "] datetime NOT NULL");
		s_CreateTableScript.append("				,[" + TABLE.COLUMN_STATE + "] integer NOT NULL");
		s_CreateTableScript.append("				)");

		if(BaseApplication.IS_DEBUG){
			Log.i(TAG, s_CreateTableScript.toString());
		}
		
		db.execSQL(s_CreateTableScript.toString());
		
		initDefaultData(db);
	}

	private void initDefaultData(SQLiteDatabase db) {
		Category _category = new Category();
		_category.typeFlag = getContext().getString(R.string.PayoutTypeFlag);
		_category.path = "-";
		
		String[] _categoryNames = getContext().getResources().getStringArray(R.array.InitDefaultCategoryName);
		for(String _categoryName : _categoryNames){
			_category.name = _categoryName;
			
			ContentValues _contentValues = createContValsByEnity(_category);
			long _newCategoryId = db.insert(TABLE.NAME, null, _contentValues);

			String _categoryPath = String.valueOf(_newCategoryId);
			db.execSQL("UPDATE " + TABLE.NAME + " SET " + TABLE.COLUMN_PATH + "=? WHERE " + TABLE.COLUMN_ID + "=?",new Object[]{_categoryPath,_newCategoryId});
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

	@Override
	protected ArrayList<Category> cursorToList(Cursor cursor) {
		return null;
	}

}
