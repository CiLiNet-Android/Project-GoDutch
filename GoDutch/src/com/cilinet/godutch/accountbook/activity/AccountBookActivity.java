package com.cilinet.godutch.accountbook.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.cilinet.godutch.R;
import com.cilinet.godutch.accountbook.adapter.AccountBookAdapter;
import com.cilinet.godutch.accountbook.adapter.AccountListVItem;
import com.cilinet.godutch.framework.activity.FrameActivity;
import com.cilinet.godutch.framework.view.BotmSlideMenuView;
import com.cilinet.godutch.framework.view.BotmSlideMenuView.SlideMenuItem;

public class AccountBookActivity extends FrameActivity implements BotmSlideMenuView.OnSlideMenuItemClickListener {
	
	private ListView listViewActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//设置页眉
		getTopBarView().setTitle(loadString(R.string.appGridTextAccountBookManage));
		
		appendCenterView(R.layout.activity_listview);
		
		init();
		
		bindSlideMenuItems(R.array.SlideMenuAccountBook);
		bindSlideMenuItemsClickListener(this);
		
	}

	private void init(){
		
		initListView();
	}

	//初使化ListView并绑定Adapter
	private void initListView() {
		listViewActivity = (ListView) findViewById(R.id.listV_user);
		
		ArrayList<AccountListVItem> boundData = new ArrayList<AccountListVItem>();
		
		//以下为测试之用，用完删除
		AccountListVItem item_1 = new AccountListVItem();
		item_1.name = "AA"; item_1.text = "合计消费0元"; item_1.count = "共0笔";
		
		AccountListVItem item_2 = new AccountListVItem();
		item_2.name = "BB"; item_2.text = "合计消费0元"; item_2.count = "共0笔";
		
		AccountListVItem item_3 = new AccountListVItem();
		item_3.name = "CC"; item_3.text = "合计消费0元"; item_3.count = "共0笔";
		
		boundData.add(item_1);
		boundData.add(item_2);
		boundData.add(item_3);
		
		listViewActivity.setAdapter(new AccountBookAdapter(boundData,this));
	}

	
	//底部滑动菜单的Item单击事件处理
	@Override
	public void onSlideMenuItemClick(View view, SlideMenuItem slideMenuItem) {
		showToast(slideMenuItem.title);
		getBotmSlideMenuView().slide();
	}
}
