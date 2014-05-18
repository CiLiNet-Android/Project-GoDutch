package com.cilinet.godutch.category.business;

import java.util.ArrayList;

import android.content.Context;

import com.cilinet.godutch.category.dal.CategoryDal;
import com.cilinet.godutch.category.entity.Category;
import com.cilinet.godutch.framework.business.BaseBusiness;

public class cateGoryBusiness extends BaseBusiness{
	
	private CategoryDal  categoryDal;

	public cateGoryBusiness(Context context) {
		super(context);
	}
	
	/** 查看类别 **/
	private ArrayList<Category> quetyCategory(){
		return categoryDal.query(null);
	}

}
