package com.example.windows.shake;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SensorManager sm;
    private float accelVal;
    private float accelLast;
    private float shake;
    private float asmi;
   // final Intent asmi = new Intent(this , Main2Activity.class);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
                sm.registerListener(sensorListener,sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        accelVal=SensorManager.GRAVITY_EARTH;
        accelLast=SensorManager.GRAVITY_EARTH;
        shake=0.00f;



    }

    private  final SensorEventListener sensorListener =new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x=sensorEvent.values[0];
            float y=sensorEvent.values[1];
            float z=sensorEvent.values[2];
            accelLast=accelVal;
            accelVal=(float) Math.sqrt((double)(x*x+y*y+z*z));
            float delta=accelVal-accelLast;
            shake=shake*0.9f+delta;


            if(shake>12)
            {
                // Toast.makeText(this, "Volunteer added", Toast.LENGTH_LONG).show();

                // startActivity(asmi);
                startActivity(new Intent(MainActivity.this, Main2Activity.class));


            }






        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}
