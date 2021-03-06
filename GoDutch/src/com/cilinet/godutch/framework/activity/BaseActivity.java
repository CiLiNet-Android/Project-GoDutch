package com.cilinet.godutch.framework.activity;

import java.lang.reflect.Field;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * Activity基类，定义公共的Activity会用到的方法
 * 
 * @author zhxl
 * 
 */
public class BaseActivity extends Activity {

	private static final int TOAST_DURATION = Toast.LENGTH_LONG;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	/**
	 * 显示土司对话框
	 */
	protected void showToast(int resId) {
		Toast.makeText(this, resId, TOAST_DURATION).show();
	}

	protected void showToast(String msg) {
		Toast.makeText(this, msg, TOAST_DURATION).show();
	}

	/**
	 * 启动新的Activity
	 */
	protected void startActivity(Class<?> classObj) {
		Intent _intent = new Intent(this, classObj);
		super.startActivity(_intent);
	}

	/**
	 * 保持Dialog不自动关闭
	 * 
	 * @param dialog
	 */
	protected void setAlertDialogClosable(AlertDialog dialog, boolean closable) {
		try {
			Field field = dialog.getClass().getSuperclass()
					.getDeclaredField("mShowing");
			field.setAccessible(true);
			field.set(dialog, closable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加载布局文件
	 */
	protected View inflateView(int layoutResId) {
		return LayoutInflater.from(this).inflate(layoutResId, null);
	}

	/**
	 *  加载string文件获取值
	 */
	protected String loadString(int stringResId) {
		return getResources().getString(stringResId);
	}
}
