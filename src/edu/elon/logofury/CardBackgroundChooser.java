/*
 * CardBackgroundChooser.java
 *
 * Copyright (c) 2010 Yakira C. Bristol, Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.logofury;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Allows the user to choose a background for their cards
 * @author Yakira C. Bristol
 *
 */
public class CardBackgroundChooser extends Activity{
	
	private ImageButton gray;
	private ImageButton black;
	private Button changeMode;
	private String mode;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cbc);
		
		gray = (ImageButton)findViewById(R.id.gray);
		gray.setOnClickListener(grayButtonListener);
		
		black = (ImageButton)findViewById(R.id.black);
		black.setOnClickListener(blackButtonListener);
		
		changeMode = (Button)findViewById(R.id.changeMode);
		changeMode.setOnClickListener(changeModeButtonListener);
		
		Bundle extras = getIntent().getExtras();
		if(!(extras == null)){
			mode = extras.getString("mode");
		}
	}
	
	private OnClickListener grayButtonListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			startGray();
			
		}
		
	};
	
	private OnClickListener blackButtonListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			startBlack();
			
		}
		
	};
	
	private OnClickListener changeModeButtonListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			finish();
		}
		
	};
	
	private void startGray(){
		Intent intent = new Intent(this, LogoMenu.class);
		intent.putExtra("color", "gray");
		intent.putExtra("mode", mode);
		
		this.startActivity(intent);
	}
	
	private void startBlack(){
		Intent intent = new Intent(this, LogoMenu.class);
		intent.putExtra("color", "black");
		intent.putExtra("mode", mode);
		
		this.startActivity(intent);
	}

}
