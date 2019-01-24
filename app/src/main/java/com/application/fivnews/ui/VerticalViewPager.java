package com.application.fivnews.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/** Uses a combination of a PageTransformer and swapping X & Y coordinates
 * of touch events to create the illusion of a vertically scrolling ViewPager.
 *
 * Requires API 11+
 *
 */
public class VerticalViewPager extends ViewPager {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private int mSwipeOrientation;

    public VerticalViewPager(Context context) {
        super(context);
        mSwipeOrientation = HORIZONTAL;
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setSwipeOrientation(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(mSwipeOrientation == VERTICAL ? swapXY(event) : event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (mSwipeOrientation == VERTICAL) {
            boolean intercepted = super.onInterceptHoverEvent(swapXY(event));
            swapXY(event);
            return intercepted;
        }
        return super.onInterceptTouchEvent(event);
    }

    public void setSwipeOrientation(int swipeOrientation) {
        if (swipeOrientation == HORIZONTAL || swipeOrientation == VERTICAL)
            mSwipeOrientation = swipeOrientation;
        else
            throw new IllegalStateException("Swipe Orientation can be either CustomViewPager.HORIZONTAL" +
                    " or CustomViewPager.VERTICAL");
        initSwipeMethods();
    }

    private void setSwipeOrientation(Context context, AttributeSet attrs) {
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomViewPager);
        mSwipeOrientation = 1;//typedArray.getInteger(R.styleable.CustomViewPager_swipe_orientation, 0);
//        typedArray.recycle();
        initSwipeMethods();
    }

    private void initSwipeMethods() {
        if (mSwipeOrientation == VERTICAL) {
            // The majority of the work is done over here
            setPageTransformer(true, new VerticalPageTransformer());
            // The easiest way to get rid of the overscroll drawing that happens on the left and right
            setOverScrollMode(OVER_SCROLL_NEVER);
        }
    }


    private MotionEvent swapXY(MotionEvent event) {
        float width = getWidth();
        float height = getHeight();

        float newX = (event.getY() / height) * width;
        float newY = (event.getX() / width) * height;

        event.setLocation(newX, newY);
        return event;
    }

    private class VerticalPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            if (position < -1) {
                // This page is way off-screen to the left
                page.setAlpha(0);
            } else if (position <= 1) {
                page.setAlpha(1);

                // Counteract the default slide transition
                page.setTranslationX(page.getWidth() * -position);

                // set Y position to swipe in from top
                float yPosition = position * page.getHeight();
                page.setTranslationY(yPosition);
            } else {
                // This page is way off screen to the right
                page.setAlpha(0);
            }
        }
    }
}