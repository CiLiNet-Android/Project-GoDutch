package com.cilinet.godutch.category.dal;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;

import com.cilinet.godutch.category.entity.Category;
import com.cilinet.godutch.framework.dal.BaseDal;

public class CategoryDal extends BaseDal<Category> {
	
	public CategoryDal(Context context) {
		super(context);
	}

	private static final class TABLE {
		public static final String NAME = "Category";
		public static final String COLUMN_ID = "id";
		public static final String COLUMN_NAME = "name";
		public static final String COLUMN_STATE = "state";
		public static final String COLUMN_CREATEDATE = "createDate";
	}

	@Override
	public List<Category> query(String whereSql) {
		return null;
	}

	@Override
	protected ContentValues createContValsByEnity(Category entity) {
		return null;
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

}
