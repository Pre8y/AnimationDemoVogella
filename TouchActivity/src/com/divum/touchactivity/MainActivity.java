package com.divum.touchactivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button1 = (Button)findViewById(R.id.touch_button);
		button1.setOnClickListener(this);
		Button button2 = (Button) findViewById(R.id.test_button);
		button2.setOnClickListener(this);
		Button button3 = (Button)findViewById(R.id.move_button);
		button3.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent ;
		switch(v.getId())
		{
		
		case R.id.touch_button:
			intent = new Intent(MainActivity.this, TouchActivity.class);
			startActivity(intent);
			break;
		case R.id.test_button:
			intent = new Intent(MainActivity.this, TestActivity.class);
			startActivity(intent);
			break;
		case R.id.move_button:
			intent = new Intent(MainActivity.this, MoveActivity.class);
			startActivity(intent);
			break;
		}
		
		
	}

}
