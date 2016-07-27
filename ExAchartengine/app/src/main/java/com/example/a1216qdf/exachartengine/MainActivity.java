package com.example.a1216qdf.exachartengine;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;


import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager mSensorManager;
    private Sensor mAcce;
    private float[] acceXYZ = new float[3];
    private float time = 0;
    private GraphChart[] aaa = new  GraphChart[3];
    private String[] axis = {"X axis","Y axis","Z axis"};
    private Timer timer = new Timer();
    Boolean flagStrat = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAcce = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        LinearLayout layout1 = (LinearLayout)findViewById(R.id.layoutX);
        LinearLayout layout2 = (LinearLayout)findViewById(R.id.layoutY);
        LinearLayout layout3 = (LinearLayout)findViewById(R.id.layoutZ);



        for (int i = 0; i < aaa.length; i++){
            aaa[i] = new GraphChart(axis[i],this);
        }


        layout1.addView(aaa[0].mChart);
        layout2.addView(aaa[1].mChart);
        layout3.addView(aaa[2].mChart);

        timer.schedule(timerTask,500,500);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        acceXYZ[0] = sensorEvent.values[0];
        acceXYZ[1] = sensorEvent.values[1];
        acceXYZ[2] = sensorEvent.values[2];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            time = (float)(time + 0.5);
            for (int i = 0; i < aaa.length; i++){
                aaa[i].updateChart(time,acceXYZ[i]);
            }
        }
    };

    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if (flagStrat){
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }
    };

    public void onResume(){
        super.onResume();
        mSensorManager.registerListener(this,mAcce,SensorManager.SENSOR_DELAY_NORMAL);
        flagStrat = true;
    }

    public void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
        flagStrat = false;
    }

    public void onDestory(){
        super.onDestroy();
        timer.cancel();
    }


}
