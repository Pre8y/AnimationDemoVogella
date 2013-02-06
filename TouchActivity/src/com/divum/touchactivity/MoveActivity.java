package com.divum.touchactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class MoveActivity extends Activity implements OnClickListener , AnimationListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.move);
		ImageView image = (ImageView)findViewById(R.id.move_image);
		image.setOnClickListener(this);
		
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Animation animation = AnimationUtils.loadAnimation(MoveActivity.this, R.anim.move_image);
		animation.setAnimationListener(this);
		v.startAnimation(animation);
		v.setClickable(false);
	}
}
