package com.example.englishteacher;

import android.app.Activity;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class JuegoActivity extends Activity implements SensorEventListener {

	private SensorManager mgr;
    private Sensor accelerometer;
    private int rotation;
    MovinBall pelota;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		pelota= new MovinBall(this);
		
		//super.setContentView(R.layout.tablero);
		super.setContentView(pelota);
		
        mgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        accelerometer = mgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        WindowManager window = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        int apiLevel = Integer.parseInt(Build.VERSION.SDK);
        if(apiLevel < 8) {
            rotation = window.getDefaultDisplay().getOrientation();
        }
        else {
        	rotation = window.getDefaultDisplay().getRotation();
        }
        Display display=window.getDefaultDisplay();
        
        
        pelota.setxMax((float)display.getWidth() - 50);
        pelota.setyMax((float)display.getHeight()-50);
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	  // ignore orientation/keyboard change
	  super.onConfigurationChanged(newConfig);
	}
	
	@Override
    protected void onResume() {
        mgr.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    	super.onResume();
    }
	
	 @Override
	 protected void onPause() {
		 mgr.unregisterListener(this, accelerometer);
		 super.onPause();
	 }
	 
	 public void onAccuracyChanged(Sensor sensor, int accuracy) {
	 }
	 
	 public void onSensorChanged(SensorEvent event) {
		 
		 String msg = String.format("X: %8.4f\nY: %8.4f\nZ: %8.4f\nRotación: %d",
				 event.values[0], event.values[1], event.values[2], rotation);
		 //pelota.update(event.values[0], event.values[1] );
		 //Log.i("Acelerometer change"," new values: "+msg);
		 pelota.update(event.values[1], event.values[0]);
		 //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
	 }
	 

}
