package com.cilinet.godutch.framework.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cilinet.godutch.R;
import com.cilinet.godutch.framework.adapter.AbsBaseAdapter;
import com.cilinet.godutch.framework.utils.UnitTransformUtil;

/**
 * 底部滑动菜单
 * @author zhxl
 *
 */
public class BotmSlideMenuView implements View.OnClickListener,View.OnKeyListener,OnItemClickListener{
	
	private static final String TAG = "BotmSlideMenuView";
	
	private RelativeLayout framework_bottom;
	
	private RelativeLayout lyot_botmBtnBar;
	
	private Activity mActivity;
	
	private ListView listV_botmSlideMenu;
	
	private boolean mIsSlideUp;
	
	public BotmSlideMenuView(Activity activity){
		mActivity = activity;
		
		init();
	}
	
	private void init() {
		initVariables();
		initView();	
		
		if(mActivity instanceof OnSlideMenuItemClickListener){
			/**
			 * 给btomBtnBar注册了点击事件
			 * 菜单ListView初始化
			 */
			initSlideMenu();
		}
		
	}

	private void initSlideMenu() {
		lyot_botmBtnBar = (RelativeLayout)findViewById(R.id.lyot_botmBtnBar);
		lyot_botmBtnBar.setOnClickListener(this);
		
		lyot_botmBtnBar.setFocusableInTouchMode(true);
		lyot_botmBtnBar.setOnKeyListener(this);
		
		listV_botmSlideMenu = (ListView)findViewById(R.id.listV_botmSlideMenu);
		listV_botmSlideMenu.setOnItemClickListener(this);
	}

	private void initVariables() {
		mIsSlideUp = false;
	}

	private void initView() {
		framework_bottom = (RelativeLayout)mActivity.findViewById(R.id.framework_bottom);
		
	}

	private View findViewById(int id){
		return mActivity.findViewById(id);
	}

	@Override
	public void onClick(View v) {
		slide();
	}
	
	public void slide(){
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
		RelativeLayout.LayoutParams _layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, UnitTransformUtil.dip2px(mActivity, 68));
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
	public static class SlideMenuItem {
		public int id;
		public String title; 
		
		public SlideMenuItem(int id,String title){
			this.id = id;
			this.title = title;
		}
	}
	
	public void bindSlideItems(int menuItemsResId){
		String[] _itemsTitle = mActivity.getResources().getStringArray(menuItemsResId);
		
		ArrayList<SlideMenuItem> _slideMenuItems = new ArrayList<SlideMenuItem>();
		for(int i = 0; i < _itemsTitle.length; i ++){
			_slideMenuItems.add(new SlideMenuItem(i, _itemsTitle[i]));
		}
		
		listV_botmSlideMenu.setAdapter(new SlideMenuAdapter(_slideMenuItems,mActivity));
	}
	
	private static class SlideMenuAdapter extends AbsBaseAdapter<SlideMenuItem> {

		public SlideMenuAdapter(ArrayList<SlideMenuItem> boundData,
				Context context) {
			super(boundData, context);
		}

		private class ViewHolder {
			public TextView txtV_slideMenuItemTitle;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder _viewHolder = null;
			if(null == convertView){
				convertView = (View)LayoutInflater.from(getContext()).inflate(R.layout.botm_slidemenu_item, null);
				
				_viewHolder = new ViewHolder();
				_viewHolder.txtV_slideMenuItemTitle = (TextView)convertView.findViewById(R.id.txtV_slideMenuItemTitle);
				
				convertView.setTag(_viewHolder);
			}else {
				_viewHolder = (ViewHolder)convertView.getTag();
			}
			
			SlideMenuItem _slideMenuItem = (SlideMenuItem)getItem(position);
			_viewHolder.txtV_slideMenuItemTitle.setText(_slideMenuItem.title);
			
			return convertView;
		}

	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_UP){
			slide();
		}
		return false;
	}
	
	public static interface OnSlideMenuItemClickListener{
		public void onSlideMenuItemClick(View view,SlideMenuItem slideMenuItem);
	}

	
	private OnSlideMenuItemClickListener mOnSlideMenuItemClickListener;
	public void setOnSlideMenuItemClickListener(OnSlideMenuItemClickListener listener){
		mOnSlideMenuItemClickListener = listener;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		SlideMenuItem _slideMenuItem = (SlideMenuItem)parent.getAdapter().getItem(position);
		
		mOnSlideMenuItemClickListener.onSlideMenuItemClick(view,_slideMenuItem);
	}
	
}
