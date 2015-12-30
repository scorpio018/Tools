package com.scorpio.tools.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class TextViewDemoActivity extends Activity {
	
	private AutoCompleteTextView autoCompleteTextView;
	private String[] res = {"dota", "lina", "data", "line"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.textviewdemo_main);
		/**
		 * 1.初始化控件
		 * 2.需要一个适配器
		 * 3.初始化数据源
		 * 4.将autoCompleteTextView与adapter进行绑定
		 */
		autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, res);
		autoCompleteTextView.setAdapter(arrayAdapter);
	}
}
