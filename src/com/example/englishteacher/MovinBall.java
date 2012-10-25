package com.example.englishteacher;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import pojo.Point;

public class MovinBall extends View {

	private float xMin = 0;          // This view's bounds
	private float xMax;
	private float yMin = 0;
	private float yMax;
	private float ballRadius = 20; // Ball's radius
	private float ballX = ballRadius + 20;  // Ball's center (x,y)
	private float ballY = ballRadius + 20;

	private List<Point> holes;
	
	private boolean flag=false;
	private float ballSpeedX = 6;  // Ball's speed (x,y)
	private float ballSpeedY = 4;
	private RectF ballBounds;      // Needed for Canvas.drawOval
	private boolean empalme=false;
	private Paint paint;  
	public float frameTime = 1.5f;
	public Bitmap mBitmap;
	
	private String choque="";
	
	private StringBuilder statusMsg = new StringBuilder();
	private Formatter formatter = new Formatter(statusMsg);

	public MovinBall(Context context){
		super(context);
		holes= new ArrayList<Point>();
		for (int i=1; i<=4; i++){
			Log.i("ONDRAW","i: "+i);
			Point p= new Point();
			p.setX((float)Math.random()*(xMax-40));
			p.setY((float)Math.random()*(yMax-40));
			holes.add(p);
		}
		ballX=xMax/2;
		ballY=yMax/2;
		ballBounds = new RectF();
		paint = new Paint();	
		Bitmap ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
		mBitmap = Bitmap.createScaledBitmap(ball, 40, 40, true);
	    paint.setTypeface(Typeface.SANS_SERIF);
	    paint.setFakeBoldText(true);
	    paint.setTextSize(16);
	}



	@Override
	protected void onDraw(Canvas canvas) {
		// Draw the ball
		if(!flag){		
			Log.i("ONDRAW","Flag es falso");
			holes.clear();
			for(int i=1; i<=4; i++){
				Log.i("ONDRAW","i: "+i);
				Point p= new Point();
				p.setX((float)Math.random()*(xMax-40));
				p.setY((float)Math.random()*(yMax-40));
				holes.add(p);
			}
			boolean empH=validateHoles();
			if(empH){
				holes.clear();
				onDraw(canvas);
			}

			ballX=xMax/2;
			ballY=yMax/2;
			
			flag=true;		
		}
		
		//paint.setColor(Color.GREEN);
	    //canvas.drawText(statusMsg.toString(), 10, 30, paint);
		ballBounds.set(ballX-ballRadius, ballY-ballRadius, ballX+ballRadius, ballY+ballRadius);
		canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.metallic_background), 0,0, null);
		//canvas.drawBitmap(mBitmap,ballY-ballRadius, ballX+ballRadius, null);

		Bitmap hole1=BitmapFactory.decodeResource(getResources(), R.drawable.circulo);
		Bitmap.createScaledBitmap(hole1, 40, 40, true);
		canvas.drawBitmap(hole1, ((Point)holes.get(0)).getX(), ((Point)holes.get(0)).getY(), null);
		paint.setColor(Color.GREEN);
	    canvas.drawText("A", ((Point)holes.get(0)).getX()+20, ((Point)holes.get(0)).getY()+20, paint);
		
		Bitmap hole2=BitmapFactory.decodeResource(getResources(), R.drawable.circulo);
		Bitmap.createScaledBitmap(hole2, 40, 40, true);
		canvas.drawBitmap(hole2, ((Point)holes.get(1)).getX(), ((Point)holes.get(1)).getY(), null);
		canvas.drawText("B", ((Point)holes.get(1)).getX()+20, ((Point)holes.get(1)).getY()+20, paint);
		
		Bitmap hole3=BitmapFactory.decodeResource(getResources(), R.drawable.circulo);
		Bitmap.createScaledBitmap(hole3, 40, 40, true);
		canvas.drawBitmap(hole3, ((Point)holes.get(2)).getX(), ((Point)holes.get(2)).getY(), null);
		canvas.drawText("C", ((Point)holes.get(2)).getX()+20, ((Point)holes.get(2)).getY()+20, paint);
		
		Bitmap hole4=BitmapFactory.decodeResource(getResources(), R.drawable.circulo);
		Bitmap.createScaledBitmap(hole4, 40, 40, true);
		canvas.drawBitmap(hole4, ((Point)holes.get(3)).getX(), ((Point)holes.get(3)).getY(), null);
		canvas.drawText("D", ((Point)holes.get(3)).getX()+20, ((Point)holes.get(3)).getY()+20, paint);

		final Bitmap bitmap = mBitmap;
		canvas.drawBitmap(bitmap, ballX, ballY, null);
		

		paint.setColor(Color.LTGRAY);
		//canvas.drawOval(ballBounds, paint);
		

		invalidate();  // Force a re-draw
	}
	
	public boolean validateHoles(){
		boolean emp=false;
		float x1=0;
		float y1=0;
		double d=0;
		for(int i=0; i<4;i++){
			x1=holes.get(i).getX();
			y1=holes.get(i).getY();
			for(int j=0; j<4; j++){
				if(i!=j){
					d=Math.sqrt(Math.pow(holes.get(j).getX()-x1,2)+ Math.pow(holes.get(j).getY()-y1,2));
					if(d<=50){
						emp=true;
					}
				}
			}
			
		}
		return emp;
	}

	public void update(float tempX, float tempY) {
		
	//	Log.i("UPDATE", "holes: "+holes.size());
		if(!empalme){
			
			ballSpeedX=tempX*frameTime;
			ballSpeedY=tempY*frameTime;

			float xS = (ballSpeedX/2)*frameTime;
			float yS = (ballSpeedY/2)*frameTime;

			// Get new (x,y) position
			ballX += xS;
			ballY += yS;
			// Detect collision and react

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
			empalme=validaEmpalme();
			if(empalme){
				Toast.makeText(getContext(), "GAME OVER choque en: "+choque, Toast.LENGTH_LONG).show();
				
			}
		}
		
	}
	public boolean validaEmpalme(){
		boolean emp=false;
		Log.i("validaEmpalme", "holes: "+holes.size());
		if(holes.size()>0){
			double d1=Math.sqrt(Math.pow(((Point)holes.get(0)).getX()-ballX,2)+ Math.pow(((Point)holes.get(0)).getY()-ballY,2));
			double d2=Math.sqrt(Math.pow(((Point)holes.get(1)).getX()-ballX,2)+ Math.pow(((Point)holes.get(1)).getY()-ballY,2));
			double d3=Math.sqrt(Math.pow(((Point)holes.get(2)).getX()-ballX,2)+ Math.pow(((Point)holes.get(2)).getY()-ballY,2));
			double d4=Math.sqrt(Math.pow(((Point)holes.get(3)).getX()-ballX,2)+ Math.pow(((Point)holes.get(3)).getY()-ballY,2));
			
			Log.i("MovinBall","D:"+d1);
			if(d1<=20){
				Point p= (Point)holes.get(0);
				ballX=p.getX()+10;
				ballY=p.getY()+10;
				
				choque="A";
				emp=true;
			}else if(d2<=20 ){
				Point p= (Point)holes.get(1);
				ballX=p.getX()+10;
				ballY=p.getY()+10;
				choque="B";
				emp=true;
			}else if(d3<=20 ){
				Point p= (Point)holes.get(2);
				ballX=p.getX()+10;
				ballY=p.getY()+10;
				choque="C";
				emp=true;
			}else if(d4<=20 ){
				Point p= (Point)holes.get(3);
				ballX=p.getX()+10;
				ballY=p.getY()+10;
				choque="D";
				emp=true;
			}
//			if(d1<=20 || d2<=20 || d3<=20 || d4<=20){
//				Log.i("MovinBall","Empalme");
//				emp=true;
//			}
		}
		return emp;
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
