package com.superman.coordinatorlayout;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * @author Superman
 *         仿bilibili视频播放界面coordinatorlayout使用
 *         <p>
 *         监听 appbarlayout
 *         CollapsingToolbarLayout 收缩布局本身没有监听函数，
 *         他也是监听 appbarlayout 实现动画的，这里我们也去监听 appbarlayout 的滚动变化，
 *         自己记录维护 appbarlayout 的展开和折叠状态值。
 */
public class BilibiliActivity extends AppCompatActivity {
    AppBarLayout mAppBar;
    ConstraintLayout mShowL;
    ConstraintLayout mGoneL;
    FloatingActionButton mFab;
    NestedScrollView mScrollView;
    // appbar展开状态
    public static final int STAET_EXPANDED = 1;
    // appbar折叠状态
    public static final int STAET_COLLAPSED = -1;
    // appbar中间状态
    public static final int STAET_ING = 0;
    int state;

    // appbar 响应滚动状态
    public static final int SCROLL_ING = -1;
    // appbar 不响应滚动状态
    public static final int SCROLL_NO = 0;

    int state_scroll = SCROLL_ING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilibili);
        mAppBar = findViewById(R.id.app_bar);
        mShowL = findViewById(R.id.constraint_show);
        mGoneL = findViewById(R.id.constraint_gone);
        mFab = findViewById(R.id.view_fab);
        mScrollView = findViewById(R.id.view_nest);
        //给appbarlayout添加监听
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int totalScrollRange = appBarLayout.getTotalScrollRange();//最大偏移值
                Log.d("appbarlayout", "total:" + totalScrollRange + "/Offset:" + verticalOffset);
                verticalOffset = Math.abs(verticalOffset);//取绝对值，后面比较滑动的距离
                if (verticalOffset == 0) {//偏移距离为0的时候为展开状态
                    if (state != STAET_EXPANDED) {
                        state = STAET_EXPANDED;
                        mShowL.setVisibility(View.VISIBLE);
                        mGoneL.setVisibility(View.GONE);
                    }
                } else if (verticalOffset >= totalScrollRange) {//折叠状态下，verticalOffset=totalScrollRange
                    if (state != STAET_COLLAPSED) {
                        state = STAET_COLLAPSED;
                        mShowL.setVisibility(View.GONE);
                        mGoneL.setVisibility(View.VISIBLE);
                    }
                } else {//0<verticalOffset<totalScrollRange
                    if (state != STAET_ING) {
                        state = STAET_ING;//默认状态
                        mShowL.setVisibility(View.VISIBLE);
                        mGoneL.setVisibility(View.GONE);
                    }
                }
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (state == STAET_EXPANDED) {
                    if (state_scroll == SCROLL_ING) {
                        // 取消滚动控件的嵌套滚动，核心的就是这个函数了，大家注意啊
                        mScrollView.setNestedScrollingEnabled(false);
                        state_scroll = SCROLL_NO;
                    } else if (state_scroll == SCROLL_NO) {
                        mScrollView.setNestedScrollingEnabled(true);
                        state_scroll = SCROLL_ING;
                    }
                }

            }
        });

        //点击播放的时候，从折叠状态恢复到展开
        findViewById(R.id.iv_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (state == STAET_COLLAPSED) {
                    // 处于折叠状态时才能玩
                    mAppBar.setExpanded(true);
                    state = STAET_EXPANDED;
                }
            }
        });
    }


}
