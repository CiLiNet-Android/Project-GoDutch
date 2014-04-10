package com.cilinet.godutch.framework.exception;

import java.lang.Thread.UncaughtExceptionHandler;

import android.content.Context;

public class GlobalExceptionHandler implements UncaughtExceptionHandler {
	
	private static final String TAG = "GlobalExceptionHandler";
	
	private static UncaughtExceptionHandler mDefaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
	
	public GlobalExceptionHandler(Context context){
		
	}

	@Override
	public void uncaughtException(Thread thread, Throwable throwable) {
		/**
		 * 记录错误日志
		 */
		
		
		Thread.setDefaultUncaughtExceptionHandler(mDefaultUncaughtExceptionHandler);
	}

}
