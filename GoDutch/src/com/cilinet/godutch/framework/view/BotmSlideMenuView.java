package com.cilinet.godutch.framework.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.cilinet.godutch.R;
import com.cilinet.godutch.framework.adapter.AbsBaseAdapter;

/**
 * 底部滑动菜单
 * @author zhxl
 *
 */
public class BotmSlideMenuView implements View.OnClickListener{
	
	private RelativeLayout framework_bottom;
	
	private RelativeLayout lyot_botmBtnBar;
	
	private Activity mActivity;
	
	private ListView listV_botmSlideMenu;
	
	private boolean mIsSlideUp;
	
	public BotmSlideMenuView(Activity activity){
		mActivity = activity;
		
		framework_bottom = (RelativeLayout)mActivity.findViewById(R.id.framework_bottom);
		
		init();
	}
	
	private void init() {
		initVariables();
		initView();	
	}

	private void initVariables() {
		mIsSlideUp = false;
	}

	private void initView() {
		lyot_botmBtnBar = (RelativeLayout)findViewById(R.id.lyot_botmBtnBar);
		lyot_botmBtnBar.setOnClickListener(this);
		
		listV_botmSlideMenu = (ListView)findViewById(R.id.listV_botmSlideMenu);

	}

	private View findViewById(int id){
		return mActivity.findViewById(id);
	}

	@Override
	public void onClick(View v) {
		slide();
	}
	
	private void slide(){
		if(!mIsSlideUp){
			slideUp();
		}else {
			slideDown();
		}
	}
	
	private void slideUp(){
		RelativeLayout.LayoutParams _layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		_layoutParams.addRule(RelativeLayout.BELOW,R.id.framework_top);
		
		framework_bottom.setLayoutParams(_layoutParams);
		
		mIsSlideUp = true;
	}
	
	private void slideDown(){
		RelativeLayout.LayoutParams _layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		_layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		
		framework_bottom.setLayoutParams(_layoutParams);
		
		mIsSlideUp = false;
	}
	
	public void removeSelf(){
		RelativeLayout lyot_framework = (RelativeLayout)mActivity.findViewById(R.id.lyot_framework);
		
		lyot_framework.removeViewInLayout(framework_bottom);
		framework_bottom = null;
	}
	
	/** 滑动菜单项 **/
	private class SlideMenuItem {
		public int id;
		public String title; 
		
		public SlideMenuItem(int id,String title){
			this.id = id;
			this.title = title;
		}
	}
	
	public void bindItems(int menuItemsResId){
		String[] _itemsTitle = mActivity.getResources().getStringArray(menuItemsResId);
		
		ArrayList<SlideMenuItem> _slideMenuItems = new ArrayList<SlideMenuItem>();
		for(int i = 0; i < _itemsTitle.length; i ++){
			_slideMenuItems.add(new SlideMenuItem(i, _itemsTitle[i]));
		}
		
		
	}
	
	private class SlideMenuAdapter extends AbsBaseAdapter<SlideMenuItem> {

		public SlideMenuAdapter(ArrayList<SlideMenuItem> boundData,
				Context context) {
			super(boundData, context);
			
			
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return null;
		}

	}

}
