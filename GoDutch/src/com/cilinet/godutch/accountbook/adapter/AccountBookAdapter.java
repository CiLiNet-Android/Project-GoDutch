package com.cilinet.godutch.accountbook.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cilinet.godutch.R;
import com.cilinet.godutch.framework.adapter.AbsBaseAdapter;

public class AccountBookAdapter extends AbsBaseAdapter<AccountListVItem> {

	public AccountBookAdapter(ArrayList<AccountListVItem> boundData,Context context) {
		super(boundData, context);
	}

	@Override
	public View getView(int position, View view, ViewGroup group) {
		AccountBookHolder _holder;
		if(null == view){
			_holder = new AccountBookHolder();
			view = inflateView(R.layout.account_book_item);
			_holder.icon = (ImageView) view.findViewById(R.id.account_book_listview_item_icon);
			_holder.name = (TextView) view.findViewById(R.id.account_book_listview_item_name);
			_holder.text = (TextView) view.findViewById(R.id.account_book_listview_item_text);
			_holder.count = (TextView) view.findViewById(R.id.account_book_listview_item_count);
			view.setTag(_holder);
		}else{
			_holder = (AccountBookHolder) view.getTag();
		}
		AccountListVItem _accountListVItem = (AccountListVItem) getItem(position);
		_holder.icon.setImageResource(R.drawable.account_book_big_icon);
		_holder.name.setText(_accountListVItem.name);
		_holder.text.setText(_accountListVItem.text);
		_holder.count.setText(_accountListVItem.count);
		
		return view;
	}
	
	private class AccountBookHolder{
		public ImageView icon;
		public TextView name,text,count;
	}

}
