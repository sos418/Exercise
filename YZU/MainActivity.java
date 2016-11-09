package com.example.kuanyu.yzu1021803;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
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



    class myview extends View {
        float a=0,b=0;

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint p=new Paint();
            p.setColor(Color.RED);
            p.setAntiAlias(true);
            p.setStrokeWidth(5);
            p.setTextSize(100);

            canvas.drawText("x="+a,100,100,p);
//        canvas.drawCircle(a,b,20,p);
            Bitmap bitmap=null;
            bitmap=((BitmapDrawable)getResources().getDrawable(R.drawable.flower)).getBitmap();
            canvas.drawBitmap(bitmap, a, b, null);//出現開花
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            a=event.getX();
            b=event.getY();
            invalidate();
            return super.onTouchEvent(event);
        }

        public myview(Context context) {
            super(context);

        }
    }
}
