package com.example.englishteacher;

import android.util.Log;
import android.view.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;


public class MovinBall extends View {
	
	private float xMin = 0;          // This view's bounds
	private float xMax;
	private float yMin = 0;
	private float yMax;
	private float ballRadius = 25; // Ball's radius
	private float ballX = ballRadius + 25;  // Ball's center (x,y)
	private float ballY = ballRadius + 25;
	private float ballSpeedX = 5;  // Ball's speed (x,y)
	private float ballSpeedY = 3;
	private RectF ballBounds;      // Needed for Canvas.drawOval
	private Paint paint;  
	public float frameTime = 0.666f;
	public Bitmap mBitmap;
	public MovinBall(Context context){
		super(context);
		ballBounds = new RectF();
		paint = new Paint();	
		Bitmap ball=BitmapFactory.decodeResource(getResources(), R.drawable.ball);
		mBitmap= Bitmap.createScaledBitmap(ball, 50, 50, true);
	}
	
	
	@Override
	   protected void onDraw(Canvas canvas) {
	      // Draw the ball
		
	      ballBounds.set(ballX-ballRadius, ballY-ballRadius, ballX+ballRadius, ballY+ballRadius);
	      canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.metallic_background), 0,0, null);
	      canvas.drawBitmap(mBitmap,ballY-ballRadius, ballX+ballRadius, null);
	      //Bitmap ball=BitmapFactory.decodeResource(getResources(), R.drawable.ball);
	      //Bitmap.createScaledBitmap(ball, 50, 50, true);
	      
	      paint.setColor(Color.LTGRAY);
	      //canvas.drawOval(ballBounds, paint);
	      
	      
	      invalidate();  // Force a re-draw
	   }
	
	 public void update(float tempX, float tempY) {
		 Log.i("UPDATE","first update");
		 ballSpeedX=tempX*frameTime;
		 ballSpeedY=tempY*frameTime;
		 
		 float xS = (ballSpeedX/2)*frameTime;
		 float yS = (ballSpeedY/2)*frameTime;
		 
	      // Get new (x,y) position
	      ballX += ballSpeedX;
	      ballY += ballSpeedY;
	      // Detect collision and react
	      
	      Log.i("UPDATE", "x: "+ballX+" y:"+ballY);
	      
	      if (ballX + ballRadius > xMax) {
	         ballX = xMax-ballRadius;
	      } else if (ballX - ballRadius < xMin) {
	         ballX = xMin+ballRadius;
	      }
	      if (ballY + ballRadius > yMax) {
	         ballY = yMax - ballRadius;
	      } else if (ballY - ballRadius < yMin) {
	         ballY = yMin + ballRadius;
	      }
	   }


	public float getxMin() {
		return xMin;
	}


	public void setxMin(float xMin) {
		this.xMin = xMin;
	}


	public float getxMax() {
		return xMax;
	}


	public void setxMax(float xMax) {
		this.xMax = xMax;
	}


	public float getyMin() {
		return yMin;
	}


	public void setyMin(float yMin) {
		this.yMin = yMin;
	}


	public float getyMax() {
		return yMax;
	}


	public void setyMax(float yMax) {
		this.yMax = yMax;
	}

}
