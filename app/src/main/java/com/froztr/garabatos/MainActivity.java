package com.froztr.garabatos;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout mLinearLayout;
    LinearLayout mLinearL2;
    LinearLayout mLinearLayoutHeader;
    LinearLayout mLinearLayoutHeader2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLinearLayout = (LinearLayout) findViewById(R.id.expandable);
        mLinearL2 = (LinearLayout) findViewById(R.id.expandable1);
        //set visibility to GONE
        mLinearLayout.setVisibility(View.GONE);
        mLinearL2.setVisibility(View.GONE);
        mLinearLayoutHeader = (LinearLayout) findViewById(R.id.header);
        mLinearLayoutHeader2 = (LinearLayout) findViewById(R.id.header1);

        mLinearLayoutHeader.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mLinearLayout.getVisibility()== View.GONE){
                    expand(mLinearLayout);
                }else{
                    collapse(mLinearLayout);
                }
            }
        });

        mLinearLayoutHeader2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mLinearL2.getVisibility()== View.GONE){
                    expand(mLinearL2);
                }else{
                    collapse(mLinearL2);
                }
            }
        });
    }


    /*
        *Funciones de animacion
     */
    private void expand(LinearLayout LL) {
        //set Visible
        LL.setVisibility(View.VISIBLE);

        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        LL.measure(widthSpec, heightSpec);

        ValueAnimator mAnimator = slideAnimator(0, LL.getMeasuredHeight(), LL);
        mAnimator.start();
    }


    private ValueAnimator slideAnimator(int start, int end, final LinearLayout LL) {

        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = LL.getLayoutParams();
                layoutParams.height = value;
                LL.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    private void collapse(final LinearLayout LL) {
        int finalHeight = LL.getHeight();

        ValueAnimator mAnimator = slideAnimator(finalHeight, 0, LL);

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //Height=0, but it set visibility to GONE
                LL.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

        });
        mAnimator.start();
    }




}
