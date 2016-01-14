package com.raxdenstudios.square.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

public class ViewPagerAdapter<T> extends PagerAdapter {

	public interface ViewPagerAdapterCallbacks<T> {
		View onInflateView(ViewPagerAdapter<T> adapter, int index);
		void onInitView(ViewPagerAdapter<T> adapter, View view, int index);
		CharSequence onTitleView(ViewPagerAdapter<T> adapter, int index);
		void onLoadView(ViewPagerAdapter<T> adapter, View view, int index);
		void onDestroyView(ViewPagerAdapter<T> adapter, View view, int index);
	}
	
	@SuppressWarnings("rawtypes")
	private static ViewPagerAdapterCallbacks dummyCallbacks = new ViewPagerAdapterCallbacks() {
		
		@Override
		public void onLoadView(ViewPagerAdapter adapter, View view, int index) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onInitView(ViewPagerAdapter adapter, View view, int index) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public View onInflateView(ViewPagerAdapter adapter, int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void onDestroyView(ViewPagerAdapter adapter, View view, int index) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public CharSequence onTitleView(ViewPagerAdapter adapter, int index) {
			// TODO Auto-generated method stub
			return null;
		}
	};
		
	@SuppressWarnings("unchecked")
	private ViewPagerAdapterCallbacks<T> callbacks = dummyCallbacks;	
	
	private List<T> items;
	
	public ViewPagerAdapter(List<T> items, ViewPagerAdapterCallbacks<T> callbacks) {
		this.items = items;
		this.callbacks = callbacks;
	}
	
	@Override
	public Object instantiateItem(View collection, int index) {
		View view = callbacks.onInflateView(this, index);
		
		if (view != null) {
			callbacks.onInitView(this, view, index);
			((ViewPager)collection).addView(view);
			callbacks.onLoadView(this, view, index);
		}
		
		return view;
	}	
	
	public void clear() {
		if (items != null) items.clear();
	}
	
	@Override
	public void destroyItem(View collection, int index, Object object) {
		((ViewPager)collection).removeView((View)object);
		callbacks.onDestroyView(this, (View)object, index);
	}
	
	@Override
	public int getCount() {
		return items != null && !items.isEmpty() ? items.size() : 0;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((View)object);
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return callbacks.onTitleView(this, position);
	}	

	public List<T> getItems() {
		return items;
	}
	
}
