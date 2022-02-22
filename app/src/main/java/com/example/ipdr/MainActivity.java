package com.example.ipdr;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener,View.OnClickListener
{

    private SensorManager mSensorMgr;
    private  TextView tvx_Accel;
    private  TextView tvy_Accel;
    private  TextView tvz_Accel;
    private TextView tx_Mag;
    private TextView ty_Mag;
    private TextView tz_Mag;

    private TextView tx_Gyro;
    private TextView ty_Gyro;
    private TextView tz_Gyro;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//使用XML中的布局文件控制UI界面

        Button bt = findViewById(R.id.btn_start);
        bt.setOnClickListener(this);

        Button bt_stop = findViewById(R.id.btn_stop);
        bt_stop.setOnClickListener(this);

        tvx_Accel = findViewById(R.id.tvx_Accel);
        tvy_Accel = findViewById(R.id.tvy_Accel);
        tvz_Accel = findViewById(R.id.tvz_Accel);

        tx_Mag = findViewById(R.id.tx_Mag);
        ty_Mag = findViewById(R.id.ty_Mag);
        tz_Mag = findViewById(R.id.tz_Mag);

        tx_Gyro = findViewById(R.id.tx_Gyro);
        ty_Gyro = findViewById(R.id.ty_Gyro);
        tz_Gyro = findViewById(R.id.tz_Gyro);

        //
        mSensorMgr = (SensorManager)getSystemService(Context.SENSOR_SERVICE);//获取传感器管理器
    }

    protected void onPause()
    {
        super.onPause();
        mSensorMgr.unregisterListener(this);
    }

    protected void onResume()
    {
        super.onResume();
    }
    protected void onStop()
    {
        super.onStop();
        mSensorMgr.unregisterListener(this);
    }
    public void onSensorChanged(SensorEvent event)
    {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
        {
            float[] values_Acc=event.values;

            tvx_Accel.setText(getString(R.string.acc_x)+Float.toString(values_Acc[0]));//输出三轴加速度计
            tvy_Accel.setText(getString(R.string.acc_y)+Float.toString(values_Acc[1]));
            tvz_Accel.setText(getString(R.string.acc_z)+Float.toString(values_Acc[2]));
        }
        if(event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD)
        {
            float[] values_Mag= event.values;
            tx_Mag.setText(getString(R.string.mag_x)+Float.toString(values_Mag[0]));//输出三轴磁力计
            ty_Mag.setText(getString(R.string.mag_y)+Float.toString(values_Mag[1]));
            tz_Mag.setText(getString(R.string.mag_z)+Float.toString(values_Mag[2]));
        }
        if(event.sensor.getType()==Sensor.TYPE_GYROSCOPE)
        {
            float[] values_Gyro= event.values;
            tx_Gyro.setText(getString(R.string.gyro_x)+Float.toString(values_Gyro[0]));//输出三轴陀螺
            ty_Gyro.setText(getString(R.string.gyro_y)+Float.toString(values_Gyro[1]));
            tz_Gyro.setText(getString(R.string.gyro_z)+Float.toString(values_Gyro[2]));

        }
    }

    public void onAccuracyChanged(Sensor sensor,int accuracy)
    {
        //不用处理，空着就行
    }
    public void onClick(View v)
    {
        if(v.getId()==R.id.btn_start)
        {
            mSensorMgr.unregisterListener(this,
                    mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
            mSensorMgr.registerListener(this,
                    mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                    SensorManager.SENSOR_DELAY_NORMAL);


            mSensorMgr.unregisterListener(this,
                    mSensorMgr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD));
            mSensorMgr.registerListener(this,
                    mSensorMgr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                    SensorManager.SENSOR_DELAY_NORMAL);


            mSensorMgr.unregisterListener(this,
                    mSensorMgr.getDefaultSensor(Sensor.TYPE_GYROSCOPE));
            mSensorMgr.registerListener(this,
                    mSensorMgr.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(v.getId()==R.id.btn_stop)
        {
            mSensorMgr.unregisterListener(this);
        }
    }
}