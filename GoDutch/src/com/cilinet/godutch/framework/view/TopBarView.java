package com.cilinet.godutch.framework.view;

import android.app.Activity;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;

import com.cilinet.godutch.R;

/**
 * 顶部标题栏
 * @author zhxl
 *
 */
public class TopBarView implements View.OnClickListener{
	
	private RelativeLayout framework_top;
	
	private TextView txtV_topBarTitle;
	
	private ImageButton imgBtn_topBarBack;
	
	private Activity mActivity;
	
	public TopBarView(Activity activity){
		framework_top = (RelativeLayout)activity.findViewById(R.id.framework_top);
		
		initView();
	}

	private void initView() {
		txtV_topBarTitle = (TextView)framework_top.findViewById(R.id.txtV_topBarTitle);
		
		imgBtn_topBarBack = (ImageButton)framework_top.findViewById(R.id.imgBtn_topBarBack);
		imgBtn_topBarBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		mActivity.finish();
	}
	
	/**
	 * 设置标题
	 */
	public void setTitle(String title){
		txtV_topBarTitle.setText(title);
	}
	
	/**
	 * 隐藏回退按钮
	 */
	public void hideBackBtn(){
		imgBtn_topBarBack.setVisibility(View.GONE);
	}
	
}
