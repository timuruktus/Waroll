package ru.timuruktus.waroll.ExtendedDefaultClasses;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.daimajia.slider.library.SliderLayout;

public class ExtendedSliderLayout extends SliderLayout {

    public ExtendedSliderLayout(Context context) {
        super(context);
    }

    public ExtendedSliderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExtendedSliderLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

}
