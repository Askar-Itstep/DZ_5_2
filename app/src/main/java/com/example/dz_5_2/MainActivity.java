package com.example.dz_5_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private LinearLayout llLeft;
    private LinearLayout llRight;
    private ImageView imgOne;

    private LinearLayout curLinearlayout;
    private int tempId;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolBar	= (Toolbar)	this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolBar);
        toolBar.setTitle(R.string.wellCome);    //.app_name
        toolBar.setTitleTextColor(Color.WHITE);

        llLeft = findViewById(R.id.ll_left);
        llRight = findViewById(R.id.ll_right);

        imgOne = findViewById(R.id.ivOne);

        llRight.setOnTouchListener((View.OnTouchListener) this::handlerTouch);
        llLeft.setOnTouchListener((View.OnTouchListener) this::handlerTouch);
                curLinearlayout = llRight;

        imgOne.setBackgroundResource(R.drawable.mario1);
        tempId = -1;
    }

    @SuppressLint("ResourceAsColor")
    private boolean handlerTouch(View view, MotionEvent motionEvent) {
        curLinearlayout.setBackgroundResource(R.color.colorNoAction);
        AnimationDrawable animationDrawable = null;
        int action = motionEvent.getAction();
            switch (view.getId()) {
                case R.id.ll_left: {
                    tempId = R.id.ll_left;
                    curLinearlayout = llLeft;
                    imgOne.setScaleX(-1);
                    imgOne.setBackgroundResource(R.drawable.frame_animation_mario_run);
                    animationDrawable = (AnimationDrawable) imgOne.getBackground();
                }
                break;

                case R.id.ll_right: {
                    curLinearlayout = llRight;
                    tempId = R.id.ll_right;
                    imgOne.setScaleX(1);
                    imgOne.setBackgroundResource(R.drawable.frame_animation_mario_run);
                    animationDrawable = (AnimationDrawable) imgOne.getBackground();
                }
                
                break;
            }
        if (action == MotionEvent.ACTION_UP) {
            curLinearlayout.setBackgroundResource(R.color.colorNoAction);
            assert animationDrawable != null;
            animationDrawable.stop();
        } else if(action == MotionEvent.ACTION_DOWN){
            curLinearlayout.setBackgroundResource(R.color.colorPress);
            assert animationDrawable != null;
            animationDrawable.start();
        }

        return false;
    }

}
