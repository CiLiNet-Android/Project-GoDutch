package com.cilinet.godutch.category.business;

import java.util.ArrayList;

import android.content.Context;

import com.cilinet.godutch.category.dal.CategoryDal;
import com.cilinet.godutch.category.entity.Category;
import com.cilinet.godutch.framework.business.BaseBusiness;

public class CategoryBusiness extends BaseBusiness{
	
	private CategoryDal  categoryDal;

	public CategoryBusiness(Context context) {
		super(context);
		categoryDal = new CategoryDal(getContext());
	}
	
	/** 查看类别 **/
	public ArrayList<Category> quetyCategory(){
		return categoryDal.query(null);
	}

}
