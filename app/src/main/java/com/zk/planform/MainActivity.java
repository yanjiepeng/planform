/*
 * Copyright(c) 2016  shanghaiMJ intelligence
 */

package com.zk.planform;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * 2016年5月19日 09:59:01
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView arrow1,arrow2,arrow20,
                                arrow_down1,arrow_down2,arrow_down3,arrow_down4,arrow_down5,arrow_down6;
    private ImageView arrow6,arrow7,arrow8,arrow9,arrow10;

    private TextView tv_start_show,tv_reset,tv_cut_table,tv_distin,tv_scan_codes,tv_spot_weld,tv_line_weld;

    private TextView tv_carry_robot,tv_stack_machine,tv_weld_robot;

    public boolean flag = true;

    List<View> widgetList = new ArrayList<View>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
       android.support.v7.app.ActionBar ab =  this.getSupportActionBar();
        ab.setTitle("流程演示图");
        ab.setSubtitle("工业4.0");

        InitWidget();

//        Configuration config = getResources().getConfiguration();
//        int smallestScreenWidth = config.smallestScreenWidthDp;
//        Log.e("smallest width : '", smallestScreenWidth+"");

    //    CarryRobotAnim();

        //StartShow();
    }

    private void InitWidget() {

        arrow1 = (TextView) findViewById(R.id.arrow_1);
        arrow2 = (TextView) findViewById(R.id.arrow_2);

        arrow6 = (ImageView) findViewById(R.id.arrow_6);
        arrow7 = (ImageView) findViewById(R.id.arrow_7);
        arrow8 = (ImageView) findViewById(R.id.arrow_8);
        arrow9 = (ImageView) findViewById(R.id.arrow_9);
        arrow10= (ImageView) findViewById(R.id.arrow_10);

        tv_cut_table = (TextView) findViewById(R.id.tv_cut_table);
        tv_distin = (TextView) findViewById(R.id.tv_distin);
        tv_scan_codes = (TextView) findViewById(R.id.tv_scan_codes);
        tv_spot_weld = (TextView) findViewById(R.id.tv_spot_weld);
        tv_line_weld = (TextView) findViewById(R.id.tv_line_weld);

        tv_start_show = (TextView) findViewById(R.id.tv_start_show);
        tv_reset = (TextView) findViewById(R.id.tv_reset);
        tv_start_show.setOnClickListener(this);
        tv_reset.setOnClickListener(this);

        //底部箭头

        arrow_down1 = (TextView) findViewById(R.id.arrow_down_1);
        arrow_down2 = (TextView) findViewById(R.id.arrow_down_2);
        arrow_down3 = (TextView) findViewById(R.id.arrow_down_3);
        arrow_down4 = (TextView) findViewById(R.id.arrow_down_4);
        arrow_down5 = (TextView) findViewById(R.id.arrow_down_5);
        arrow_down6 = (TextView) findViewById(R.id.arrow_down_6);
        arrow20 = (TextView) findViewById(R.id.arrow_20);

        tv_carry_robot = (TextView) findViewById(R.id.carry_robot);
        tv_stack_machine = (TextView) findViewById(R.id.stack_machine);
        tv_weld_robot = (TextView) findViewById(R.id.tv_weld_robot);


        widgetList.add(arrow_down1);
        widgetList.add(arrow_down2);
        widgetList.add(arrow_down3);
        widgetList.add(arrow_down4);
        widgetList.add(arrow_down5);
        widgetList.add(arrow_down6);


        widgetList.add(arrow1);
        widgetList.add(arrow2);
        widgetList.add(arrow6);
        widgetList.add(arrow7);
        widgetList.add(arrow8);
        widgetList.add(arrow9);
        widgetList.add(arrow10);
        widgetList.add(arrow20);

        for (int i = 0; i <widgetList.size() ; i++) {
            widgetList.get(i).setVisibility(View.INVISIBLE);
            if ( i == 6) {

            }
        }
    }

    /**
     * 搬运机器人动画
     */
    private void CarryRobotAnim() {

        TranslateAnimation ta = new TranslateAnimation(0,-900,0,0);
        ta.setDuration(1500);
        ta.setFillAfter(true);
        tv_carry_robot.startAnimation(ta);

    }

    /**
     * 搬运返回动画
     */

    private void CarryRobotAnimBack() {

        TranslateAnimation ta = new TranslateAnimation(-900,0,0,0);
        ta.setDuration(1500);
        ta.setFillAfter(true);
        tv_carry_robot.startAnimation(ta);

    }
    /**
     * 焊接机器人动画
     */

    private void WeldRobotAnim() {

        TranslateAnimation ta = new TranslateAnimation(50,-50,0,0);
        ta.setDuration(700);
        ta.setRepeatCount(1);               //设置重复次数
        ta.setRepeatMode(Animation.REVERSE);
        tv_weld_robot.startAnimation(ta);

    }

    /**
     * 控件闪烁
     */

    private void startFlick(View view ) {

        if( null == view ){

            return;

        }

        Animation alphaAnimation = new AlphaAnimation( 1, 0 );

        alphaAnimation.setDuration( 300 );

        alphaAnimation.setInterpolator( new LinearInterpolator( ) );

        alphaAnimation.setRepeatCount(3);

        alphaAnimation.setRepeatMode(Animation.REVERSE);

        view.startAnimation(alphaAnimation);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_start_show:
                flag =true;
                    StartShow();
                break;
            case R.id.tv_reset:
                flag = false;
                   ResetPage();
                break;
            }
        }

    /**
     * 重置界面
     */
    private void ResetPage() {

        for (int i = 0; i <widgetList.size() ; i++) {
            widgetList.get(i).setVisibility(View.INVISIBLE);
            if ( i == 6) {
                arrow1.setText("AGV小车采集原料");
                Drawable leftDrawable = getResources().getDrawable(R.mipmap.arrow_left);
                leftDrawable.setBounds(0, 0, leftDrawable.getMinimumWidth(), leftDrawable.getMinimumHeight());
                arrow1.setCompoundDrawables(leftDrawable, null, null, null);
            }
        }
    }

    private void StartShow() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(flag) {
                    for (int i = 0; i < widgetList.size(); i++) {
                        final int index = i;
                        //执行与UI线程
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                widgetList.get(index).setVisibility(View.VISIBLE);
                                Boolean flick = false;
                                if (index == 6) {
                                    startFlick(tv_stack_machine);
                                }
                                if (index == 7) {
                                    CarryRobotAnim();
                                }
                                if (index == 8) {
                                    startFlick(tv_cut_table);
                                }
                                if (index == 9) {
                                    startFlick(tv_distin);
                                }
                                if (index == 10) {
                                    startFlick(tv_scan_codes);
                                }
                                if (index == 11) {
                                    startFlick(tv_spot_weld);
                                    WeldRobotAnim();
                                }
                                if (index == 12) {
                                    startFlick(tv_line_weld);
                                    CarryRobotAnimBack();
                                }
                                if (index == 13) {
                                    arrow1.setText("AGV小车收集成品");
                                    arrow1.setGravity(Gravity.TOP);
                                    startFlick(arrow1);
                                    Drawable rightDrawable = getResources().getDrawable(R.mipmap.arrow);
                                    rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
                                    arrow1.setCompoundDrawables(null, null, rightDrawable, null);
                                    new Handler().postDelayed(new Runnable() {
                                        public void run() {
                                            //execute the task
                                            startFlick(tv_stack_machine);

                                        }
                                    }, 1000);
                                    new Handler().postDelayed(new Runnable() {
                                        public void run() {
                                            //execute the task
                                          ResetPage();

                                        }
                                    }, 500);
                                }

                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
