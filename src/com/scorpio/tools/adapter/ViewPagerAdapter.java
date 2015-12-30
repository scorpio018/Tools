package com.scorpio.tools.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerAdapter extends PagerAdapter {
	
	private List<View> viewList;
	private int pagerNum = 0;
	
	public ViewPagerAdapter(List<View> viewList) {
		this.viewList = viewList;
	}
	
	public int getPagerNum() {
		return pagerNum;
	}



	@Override
	public int getCount() {
		return viewList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	@Override
	public void destroyItem(View container, int position, Object object) {
		View view = viewList.get(position);
		if (view != null) {
			((ViewPager)container).removeView(view);
		}
	}
	
	@Override
	public Object instantiateItem(View container, int position) {
		View view = null;
		try {
			view = viewList.get(position);
			if (view.getParent() == null) {
				((ViewPager)container).addView(view, 0);
			} else {
				/*
	             * 很难理解新添加进来的view会自动绑定一个父类，由于一个儿子view不能与两个父类相关，
	             * 所以得解绑不这样做否则会产生 viewpager java.lang.IllegalStateException:
	             * The specified child already has a parent. You must call
	             * removeView() on the child's parent first.
	             */
	            ((ViewGroup) view.getParent()).removeView(view);
	            ((ViewPager) container).addView(view, 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pagerNum = position;
		}
		return view;
	}

}
