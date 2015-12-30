package com.scorpio.tools.listener;

import com.scorpio.tools.activity.ViewPagerActivity;

import android.support.v4.view.ViewPager;
/**
 * viewPager滑动时的监听器
 * @author yangyang
 *
 */
public class PagerListener implements ViewPager.OnPageChangeListener {

	/*
     * （非 Javadoc）
     * 
     * @see android.support.v4.view.ViewPager.OnPageChangeListener#
     * onPageScrollStateChanged(int) 此方法是在状态改变的时候调用，其中arg0这个参数
     * 有三种状态（0，1，2）。
     * 
     * arg0 == 1的时候表示正在滑动，
     * arg0 == 2的时候表示滑动完毕了，
     * arg0 == 0的时候表示什么都没做。
     * 
     * 当页面开始滑动的时候，三种状态的变化顺序为（1，2，0），演示如下：
     */
	@Override
	public void onPageScrollStateChanged(int arg0) {
		System.out.println(arg0);
	}

	/*
     * （非 Javadoc）
     * 
     * @see
     * android.support.v4.view.ViewPager.OnPageChangeListener#onPageScrolled
     * 
     * arg0 :当前页面，及你点击滑动的页面
     * arg1 :当前页面偏移的百分比
     * arg2 :当前页面偏移的像素位置 
     * 
     * (int, float, int) pagerNum:第几个界面（从0开始计数） offset:偏移量，计算页面滑动的距离
     */
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	/*
     * （非 Javadoc）
     * 
     * @see
     * android.support.v4.view.ViewPager.OnPageChangeListener#onPageSelected
     * (int) 判断当前是哪个view
     */
	@Override
	public void onPageSelected(int position) {
		ViewPagerActivity.tipsTv.setText(String.valueOf(position+1));
	}

}
