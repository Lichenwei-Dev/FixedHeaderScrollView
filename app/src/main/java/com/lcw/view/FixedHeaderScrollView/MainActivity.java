package com.lcw.view.FixedHeaderScrollView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Android开发之仿微博详情页（滑动固定顶部栏效果）
 * Create by: chenwei.li
 * Date: 2017/8/22
 * time: 11:36
 * Email: lichenwei.me@foxmail.com
 */
public class MainActivity extends AppCompatActivity implements ObservableScrollView.OnObservableScrollViewScrollChanged{

    private ObservableScrollView sv_contentView;
    private LinearLayout ll_topView;
    private TextView tv_topView;
    private LinearLayout ll_fixedView;

    //用来记录内层固定布局到屏幕顶部的距离
    private int mHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sv_contentView= (ObservableScrollView) findViewById(R.id.sv_contentView);
        ll_topView= (LinearLayout) findViewById(R.id.ll_topView);
        tv_topView= (TextView) findViewById(R.id.tv_topView);
        ll_fixedView= (LinearLayout) findViewById(R.id.ll_fixedView);


        sv_contentView.setOnObservableScrollViewScrollChanged(this);
//        ViewTreeObserver viewTreeObserver=ll_topView.getViewTreeObserver();
//        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                ll_topView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                mHeight=ll_topView.getTop();
//            }
//        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            //获取HeaderView的高度，当滑动大于等于这个高度的时候，需要把topView移除当前布局，放入到外层布局
            mHeight=ll_topView.getTop();
        }
    }

    /**
     * @param l Current horizontal scroll origin. 当前滑动的x轴距离
     * @param t Current vertical scroll origin. 当前滑动的y轴距离
     * @param oldl Previous horizontal scroll origin. 上一次滑动的x轴距离
     * @param oldt Previous vertical scroll origin. 上一次滑动的y轴距离
     */
    @Override
    public void onObservableScrollViewScrollChanged(int l, int t, int oldl, int oldt) {
            if(t>=mHeight){
                if(tv_topView.getParent()!=ll_fixedView){
                    ll_topView.removeView(tv_topView);
                    ll_fixedView.addView(tv_topView);
                }
            }else{
                if(tv_topView.getParent()!=ll_topView){
                    ll_fixedView.removeView(tv_topView);
                    ll_topView.addView(tv_topView);
                }
            }
    }
}
