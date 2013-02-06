package com.divum.touchactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class TestActivity extends Activity implements OnTouchListener, AnimationListener{


	private static final int NONE = 0;
	private static final int PINCH = 1;
	int mode = NONE;
	ImageView image1, image2;
	
	private static int PINCH_IN = 3;
	private static int PINCH_OUT = 4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		View linear = findViewById(R.id.linear);
	
		System.out.println("-----linear-----"+linear);
		linear.setOnTouchListener(this);
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		View linear1 = v.findViewById(R.id.linear1);
		View linear2 = v.findViewById(R.id.linear2);
		
		switch(event.getAction() & MotionEvent.ACTION_MASK){
		case MotionEvent.ACTION_DOWN: 
			System.out.println("-----ACTION DOWN---");
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			mode = NONE;
			System.out.println("-------ACTION UP-----");
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			mode=PINCH;
			System.out.println("------ACTION POINTER DOWN----");
			break;
		case MotionEvent.ACTION_MOVE:
			System.out.println("------ACTION MOVE------");
			if(mode==PINCH)
			{
				float x = event.getX(0)-event.getX(1);
				float y = event.getY(0)-event.getY(1);
				if(x>0 && y<0)
					mode = PINCH_IN;
				else 
					mode = PINCH_OUT;
				System.out.println("-----PINCH-----" +(mode==PINCH_IN?"PINCH IN":"PINCH_OUT"));
				if(mode==PINCH_IN){
					Animation animation1 = AnimationUtils.loadAnimation(TestActivity.this, R.anim.pinch_in_upper);
					animation1.setAnimationListener(this);
					Animation animation2 = AnimationUtils.loadAnimation(TestActivity.this, R.anim.pinch_in_lower);
					animation2.setAnimationListener(this);
					linear1.setAnimation(animation1);
					linear2.setAnimation(animation2);
					linear1.setVisibility(View.VISIBLE);
					linear2.setVisibility(View.VISIBLE);
				}
				else if(mode==PINCH_OUT)
				{
					Animation animation1 = AnimationUtils.loadAnimation(TestActivity.this, R.anim.pinch_out_upper);
					animation1.setAnimationListener(this);
					Animation animation2 = AnimationUtils.loadAnimation(TestActivity.this, R.anim.pinch_out_lower);
					animation2.setAnimationListener(this);
					linear1.setAnimation(animation1);
					linear2.setAnimation(animation2);
					linear1.setVisibility(View.INVISIBLE);
					linear2.setVisibility(View.INVISIBLE);
				}
			}
			break;
		}
		
		return true;
	}
	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
//		finish();
	}
	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
	
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
//		findViewById(R.id.linear1).setVisibility(View.VISIBLE);
//		findViewById(R.id.linear2).setVisibility(View.VISIBLE);
		super.onResume();
	}

	
}
