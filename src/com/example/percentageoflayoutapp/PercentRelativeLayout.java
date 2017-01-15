package com.example.percentageoflayoutapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class PercentRelativeLayout extends RelativeLayout {

	public PercentRelativeLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	public PercentRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PercentRelativeLayout(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int parcentWidth=View.MeasureSpec.getSize(widthMeasureSpec);
		int parcentHeight=View.MeasureSpec.getSize(heightMeasureSpec);

		int count=getChildCount();
		for (int i = 0; i < count; i++) {
			float childeWidth=0,childHeight=0;
			View childView=getChildAt(i);
			
			ViewGroup.LayoutParams lp=childView.getLayoutParams();
			
			if (lp instanceof PercentRelativeLayoutParams) {
				childeWidth=((PercentRelativeLayoutParams) lp).parcent_width;
				childHeight=((PercentRelativeLayoutParams) lp).parcent_height;
				
				if (childeWidth!=0) {
					lp.width=(int) (parcentWidth*childeWidth);
				}

				if (childHeight!=0) {
					lp.height=(int) (parcentHeight*childHeight);
				}
			}

		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new PercentRelativeLayoutParams(getContext(),attrs);
	}

	static class PercentRelativeLayoutParams extends RelativeLayout.LayoutParams{

		float parcent_width;
		float parcent_height;

		public PercentRelativeLayoutParams(Context _context, AttributeSet _set) {
			super(_context, _set);
			TypedArray array=_context.obtainStyledAttributes(_set, R.styleable.PercentLayout_Layout);
			parcent_width=array.getFloat(R.styleable.PercentLayout_Layout_layout_widthPercent, parcent_width);
			parcent_height=array.getFloat(R.styleable.PercentLayout_Layout_layout_heightPercent, parcent_height);
			array.recycle();
		}

		public PercentRelativeLayoutParams(int arg0, int arg1) {
			super(arg0, arg1);
		}

		public PercentRelativeLayoutParams(
				android.view.ViewGroup.LayoutParams arg0) {
			super(arg0);
		}

		@TargetApi(19)
		public PercentRelativeLayoutParams(LayoutParams arg0) {
			super(arg0);
		}

		public PercentRelativeLayoutParams(MarginLayoutParams arg0) {
			super(arg0);
		}

	}

}
