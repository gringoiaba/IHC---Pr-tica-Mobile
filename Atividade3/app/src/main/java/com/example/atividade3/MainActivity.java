package com.example.atividade3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mLight;
    private Sensor mTemperature;
    private TextView lightValue;
    private TextView temperatureValue;
    private TextView coordinateX;
    private TextView coordinateY;
    private TextView coordinateZ;
    private Button gpsButton;
    private static final float THRESHOLD = 10.0f;
    private float lastX, lastY, lastZ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializes the coordinates textView's and sensorManagers
        coordinateX = findViewById(R.id.coordinateX);
        coordinateY = findViewById(R.id.coordinateY);
        coordinateZ = findViewById(R.id.coordinateZ);

        lightValue = findViewById(R.id.luminosity);

        temperatureValue = findViewById(R.id.temperature);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        gpsButton = findViewById(R.id.gps);
        ActivityCompat.requestPermissions(MainActivity.this, new
                String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        gpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSTracker g = new GPSTracker(getApplicationContext());
                Location l = g.getLocation();
                if(l!=null)
                {
                    double lat = l.getLatitude();
                    double longi = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LAT: "+lat + "LONG: " +
                            longi, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerSensorListeners();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    private void registerSensorListeners() {
        if (mAccelerometer != null) {
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            coordinateX.setText("Accelerometer not available in this device.");
        }
        if (mTemperature != null) {
            mSensorManager.registerListener(this, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            temperatureValue.setText("Temperature sensor not available in this device.");
        }
        if (mLight != null) {
            mSensorManager.registerListener(MainActivity.this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            lightValue.setText("Light sensor not available in this device.");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float sensorX = event.values[0];
            float sensorY = event.values[1];
            float sensorZ = event.values[2];

            // Calculates the difference in coordinates of the accelerometer
            float deltaX = Math.abs(sensorX - lastX);
            float deltaY = Math.abs(sensorY - lastY);
            float deltaZ = Math.abs(sensorZ - lastZ);

            // If the change in coordinates are too significant, displays a message in 2nd activity
            if (deltaX > THRESHOLD || deltaY > THRESHOLD || deltaZ > THRESHOLD) {
                // Second activity
                switchActivity();
            }

            // Updates last coordinates
            lastX = sensorX;
            lastY = sensorY;
            lastZ = sensorZ;

            // Shows the accelerometer coordinates in the textView
            coordinateX.setText(String.valueOf(sensorX));
            coordinateY.setText(String.valueOf(sensorY));
            coordinateZ.setText(String.valueOf(sensorZ));
        }
        else if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            lightValue.setText("Luminosity: " + event.values[0]);
        }
        else if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            float temperature = event.values[0];
            temperatureValue.setText("Temperature: " + temperature + " °C");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void switchActivity() {
        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra("message", "Too Fast!!!");
        startActivity(i);
    }
}