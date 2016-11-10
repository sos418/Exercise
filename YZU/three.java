package com.example.kuanyu.exthree;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {


    private Button btn_resume,btn_blue,btn_black,btn_red;
    private ImageView iv_canvas;
    private Bitmap baseBitmap;
    private Canvas canvas;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);

        iv_canvas = (ImageView) findViewById(R.id.iv_canvas);
        btn_resume = (Button) findViewById(R.id.btn_resume);
        btn_blue = (Button) findViewById(R.id.btn_blue);
        btn_black = (Button) findViewById(R.id.btn_black);
        btn_red = (Button) findViewById(R.id.btn_red);

        btn_resume.setOnClickListener(click);
        iv_canvas.setOnTouchListener(touch);
        btn_blue.setOnClickListener(click);
        btn_black.setOnClickListener(click);
        btn_red.setOnClickListener(click);
    }

    private View.OnTouchListener touch = new View.OnTouchListener() {
        // 定義手指開始接觸座標
        float startX;
        float startY;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                // 按下動作
                case MotionEvent.ACTION_DOWN:
                    // 第一次繪圖初始化內存圖片，指定背景為白色
                    if (baseBitmap == null) {
                        baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(),
                                iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
                        canvas = new Canvas(baseBitmap);
                        canvas.drawColor(Color.WHITE);
                    }
                    startX = event.getX();
                    startY = event.getY();
                    break;
                // 手指在螢幕上動作
                case MotionEvent.ACTION_MOVE:
                    float stopX = event.getX();
                    float stopY = event.getY();

                    canvas.drawLine(startX, startY, stopX, stopY, paint);

                    startX = event.getX();
                    startY = event.getY();

                    iv_canvas.setImageBitmap(baseBitmap);
                    break;
                case MotionEvent.ACTION_UP:

                    break;
                default:
                    break;
            }
            return true;
        }
    };
    private View.OnClickListener click = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_resume:
                    resumeCanvas();
                    break;
                case R.id.btn_blue:
                    paint.setColor(Color.BLUE);
                    break;
                case R.id.btn_red:
                    paint.setColor(Color.RED);
                    break;
                case R.id.btn_black:
                    paint.setColor(Color.BLACK);
                    break;
                default:
                    break;
            }
        }
    };


    /**
     * 清除繪版
     */
    protected void resumeCanvas() {
        if (baseBitmap != null) {
            baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(),
                    iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas(baseBitmap);
            canvas.drawColor(Color.WHITE);
            iv_canvas.setImageBitmap(baseBitmap);
            Toast.makeText(MainActivity.this, "清除成功，可以重新繪圖", Toast.LENGTH_LONG).show();
        }
    }
}