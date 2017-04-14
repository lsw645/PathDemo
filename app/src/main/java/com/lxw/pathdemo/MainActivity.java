package com.lxw.pathdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton mFab;
    private FrameLayout mFabContainer;
    private LinearLayout mControlsContainer;

    private static final float SCALE_FACTOR = 13f;
    private static final int ANIMATION_DURATION = 900;
    private static final int MINIMUN_S_DISTANCE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFab = (ImageButton) findViewById(R.id.fab);
        bindView();
    }

    private void bindView() {
        mFab = (ImageButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    onFabPressed(v);
            }
        });
        mFabContainer= (FrameLayout) findViewById(R.id.fab_container);
        mControlsContainer= (LinearLayout) findViewById(R.id.control_ll);
    }
    private boolean isRippled =false;
    private void onFabPressed(View v) {
        Toast.makeText(this,"fab",Toast.LENGTH_SHORT).show();
        final float startX=v.getX();
        AnimatorPath path=new AnimatorPath();
        path.moveTo(0,0);
        path.lineTo(100,200);
        path.cubicTo(-200,200,-400,100,-600,50);

        ObjectAnimator animator=ObjectAnimator.ofObject(this,"haha",new PathPointEvalator(),path.getPionts().toArray());
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if(Math.abs(mFab.getX()-startX)>MINIMUN_S_DISTANCE){
                    if(!isRippled){
                        mFabContainer.setTranslationY(mFab.getHeight()/2);
                        mFab.animate().scaleX(SCALE_FACTOR).scaleY(SCALE_FACTOR)
                                .setDuration(5000)
                                .setListener(mAnimatorListener)
                                .start();
                        isRippled=true;
                    }
                }
            }
        });
        animator.start();
    }

    public void setHaha(PathPoint point){
        mFab.setTranslationX(point.x);
        if(isRippled){
            mFab.setTranslationY(point.y-mFab.getHeight()/2);
        }else{
            mFab.setTranslationY(point.y);
        }

    }

    private Animator.AnimatorListener mAnimatorListener=new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            mFab.setVisibility(View.GONE);
            int count=mControlsContainer.getChildCount();
            for(int i=0;i<count;i++){
             View view =  mControlsContainer.getChildAt(i);
                view.animate().scaleX(0.5f).scaleY(0.5f).setDuration(ANIMATION_DURATION).setStartDelay(50).start();
            }
        }
    };

}
