package com.example.kuanyu.testtt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new myview(getApplicationContext()));

    }
}

class myview extends View {

    float a=0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p=new Paint();//宣告畫筆
        p.setColor(Color.RED);//畫筆顏色
        p.setAntiAlias(true);
        p.setStrokeWidth(5);//畫筆寬度
        p.setTextSize(100);

        int i;
        final float N=20;
        float deltax=getWidth()/N;
        float deltay=getHeight()/N;
        float x=deltax,y=deltay;

        for(i=0;i<N-1;i++) {
            canvas.drawLine(deltax,y, x, getHeight() -deltay, p);
            canvas.drawLine(x, deltay, getWidth()-deltax,y, p);
            x+=deltax;
            y+=deltay;
        }
        canvas.drawText("x="+a,100,100,p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        a=event.getX();//得到滑鼠的X軸座標
        invalidate();//更新頁面
        return super.onTouchEvent(event);
    }

    public myview(Context context) {
        super(context);

    }
}
