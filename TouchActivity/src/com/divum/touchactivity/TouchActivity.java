package com.divum.touchactivity;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class TouchActivity extends Activity implements OnTouchListener{
	Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();
	private static final int NONE = 0;
	private static final int DRAG = 1;
	private static final int ZOOM = 2;
	int mode = NONE;
	
	PointF start = new PointF();
	PointF mid = new PointF();
	
	float oldDist = 1f;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.touch);
		
		ImageView image = (ImageView) findViewById(R.id.image1);
		image.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		ImageView image = (ImageView) v;
		// TODO Auto-generated method stub
		switch(event.getAction() & MotionEvent.ACTION_MASK){
		case MotionEvent.ACTION_DOWN: 
			savedMatrix.set(matrix);
			start.set(event.getX(), event.getY());
			mode = DRAG;
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			mode = NONE;
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			oldDist = spacing(event);
			if(oldDist>=10f)
			{
				savedMatrix.set(matrix);
				midPoint(mid, event);
				mode=ZOOM;
			}
		case MotionEvent.ACTION_MOVE:
			if(mode==DRAG)
			{
				matrix.set(savedMatrix);
				matrix.postTranslate(event.getX()-start.x, event.getY()-start.y);
			}
			else if(mode==ZOOM)
			{
				float newDist = spacing(event);
				if(newDist>10f)
				{
					matrix.set(savedMatrix);
					float scale = newDist/oldDist;
					matrix.postScale(scale, scale, mid.x, mid.y);
				}
			}
			break;
		}
		image.setImageMatrix(matrix);
		return true;
	}

	private void midPoint(PointF point, MotionEvent event) {
		// TODO Auto-generated method stub
		float x = event.getX(0)+event.getX(1);
		float y = event.getY(0)+event.getY(1);
		point.set(x/2, y/2);
	}

	private float spacing(MotionEvent event) {
		// TODO Auto-generated method stub
		float x = event.getX(0)-event.getX(1);
		float y = event.getY(0)-event.getY(1);
		
		return FloatMath.sqrt(x*x+y*y);
	}
}
