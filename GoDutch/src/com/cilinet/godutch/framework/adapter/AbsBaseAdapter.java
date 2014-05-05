package com.cilinet.godutch.framework.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public abstract class AbsBaseAdapter<T> extends android.widget.BaseAdapter {
	
	/** 与Adapter绑定数据 **/
	private ArrayList<T> mBoundData; 
	
	private Context mContext;
	
	protected ArrayList<T> getBoundData() {
		return mBoundData;
	}

	public AbsBaseAdapter(ArrayList<T> boundData,Context context){
		mBoundData = boundData;
		mContext = context;
	}

	@Override
	public int getCount() {
		return mBoundData.size();
	}

	@Override
	public Object getItem(int position) {
		return mBoundData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	/**
	 * 加载布局文件
	 */
	protected View inflateView(int layoutResId){
		return LayoutInflater.from(mContext).inflate(layoutResId, null);
	}
	
	protected Context getContext(){
		return mContext;
	}

	public void bindData(ArrayList<T> boundData){
		mBoundData = boundData;
	}
}
