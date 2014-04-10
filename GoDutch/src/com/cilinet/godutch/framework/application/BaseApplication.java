package com.cilinet.godutch.framework.application;

import com.cilinet.godutch.framework.exception.GlobalExceptionHandler;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 全局初始化
 * @author zhxl
 *
 */
public class BaseApplication extends Application {
	
	/**
	 * 应用所在的手机屏幕的相关参数
	 */
	public static int appScreenWidth;
	public static int appScreenHeight;
	public static float appScreenDensity;
	public static int appScreenDensityDpi;

	@Override
	public void onCreate() {
		super.onCreate();
		
		initDisplayMetrics();
		
		Thread.setDefaultUncaughtExceptionHandler(new GlobalExceptionHandler(this));
	}
	
	/**
	 * 初始化应用所在的手机屏幕的相关参数
	 */
	private void initDisplayMetrics(){
		DisplayMetrics _displayMetrics = new DisplayMetrics();
		
		WindowManager _windowManager = (WindowManager)super.getSystemService(Context.WINDOW_SERVICE);
		_windowManager.getDefaultDisplay().getMetrics(_displayMetrics);
		
		boolean _isPortrait = _displayMetrics.widthPixels < _displayMetrics.heightPixels;
		appScreenWidth = _isPortrait? _displayMetrics.widthPixels : _displayMetrics.heightPixels;
		appScreenHeight = _isPortrait? _displayMetrics.heightPixels : _displayMetrics.widthPixels;
		
		appScreenDensity = _displayMetrics.density;
		appScreenDensityDpi = _displayMetrics.densityDpi;
	}
	
	

}
