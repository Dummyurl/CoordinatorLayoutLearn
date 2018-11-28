package com.superman.coordinatorlayout.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.jar.Attributes;

/**
 * 作者 Superman
 * 日期 2018/11/27 11:13.
 * 文件 CoordinatorLayoutLearn
 * 描述
 */

public class SimpleTitleBehavior extends CoordinatorLayout.Behavior<View> {
    //列表顶部与title底部重合时，列表的滑动距离
    private float deltaY;

    public SimpleTitleBehavior() {
    }

    /**
     * 注意不要忘了重写两个参数的构造函数，否则无法在xml文件中使用该Behavior，我们重写了两个方法：
     */
    public SimpleTitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * layoutDependsOn()：
     * 使用该Behavior的View要监听哪个类型的View的状态变化。
     * 其中参数parant代表CoordinatorLayout，child代表使用该Behavior的View，
     * dependency代表要监听的View。这里要监听RecyclerView。
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof RecyclerView;
    }

    /**
     * onDependentViewChanged()：当被监听的View状态变化时会调用该方法，
     * 参数和上一个方法一致。所以我们重写该方法，
     * 当RecyclerView的位置变化时，进而改变title的位置。
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (deltaY == 0) {//title底部与列表顶部重合时，列表的滑动距离，可能为负，当title的高度大于recycleview的距离顶部的距离时，
            //dependency.getY()为recycleview的顶部到父布局顶部的距离
            deltaY = dependency.getY() - child.getHeight();//刚进入界面时，deltay默认为0，然后滑动初始时给他赋值，为重合时的距离，此值不再改变，因为加了条件deltay==0
        }

        float dy = dependency.getY() - child.getHeight();
//        if(dy>0){//这样写也可以
//            float y = -(dy / deltaY) * child.getHeight();
//            child.setTranslationY(y);
//        }
        dy = dy < 0 ? 0 : dy;//dy<0说明已经过了重合的临近点继续向上滑动了，child不必再滑动，就给dy赋值为0，dy>0,计算出child应该滑动的距离y，child高度*比例，向上滑动为负
        float y = -(dy / deltaY) * child.getHeight();
        child.setTranslationY(y);//移动
        child.setAlpha(1 - dy / deltaY);//修改透明度
        Log.i("yyy", "deltay=" + deltaY + "dy=" + dy + "deY=" + dependency.getY() + "child=" + child.getHeight() + ",y=" + y + "");
        return true;
    }
}
