package com.example.a1216qdf.exsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {

    private TextView acce;
    private TextView gryo;
    private TextView magn;

    private SensorManager mSensorManage;
    private Sensor mAccelerometer;
    private Sensor mGyroscope;
    private Sensor mMagetometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acce = (TextView)findViewById(R.id.accelerometer);
        gryo = (TextView)findViewById(R.id.gyroscopes);
        magn = (TextView)findViewById(R.id.magnetometers);


        mSensorManage = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManage.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGyroscope = mSensorManage.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mMagetometer = mSensorManage.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        String values = "X-axis: " + String.valueOf(event.values[0]) + "\n"
                + "Y-axis: " + String.valueOf(event.values[1]) + "\n"
                + "Z-axis: " + String.valueOf(event.values[2]) + "\n";
        if(event.sensor.equals(mAccelerometer))
            acce.setText(values);
        if (event.sensor.equals(mGyroscope))
            gryo.setText(values);
        if (event.sensor.equals(mMagetometer))
            magn.setText(values);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    protected void  onResume(){
        super.onResume();
        mSensorManage.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManage.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManage.registerListener(this, mMagetometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause(){
        super.onPause();
        mSensorManage.unregisterListener(this);
    }
}
