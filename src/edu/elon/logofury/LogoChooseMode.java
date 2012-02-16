/*
 * LogoChooseMode.java
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


/**
 * Lets the player choose between an easy or timed mode
 * @author Yakira C. Bristol
 *
 */
public class LogoChooseMode extends Activity{
	
	private Button easyMode;
	private Button timedMode;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mode_menu);
		
		easyMode = (Button)findViewById(R.id.easyMode);
		easyMode.setOnClickListener(easyModeButtonListener);
		
		timedMode = (Button)findViewById(R.id.timedMode);
		timedMode.setOnClickListener(timedModeButtonListener);
	}
	
	private OnClickListener easyModeButtonListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			startEasyMode();
		}
    	
    };
    
    private OnClickListener timedModeButtonListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			startTimedMode();
		}
    	
    };
    
    private void startEasyMode(){
    	Intent intent = new Intent(LogoChooseMode.this, CardBackgroundChooser.class);
    	intent.putExtra("mode", "easy");
    	
    	this.startActivity(intent);
    }
    
    private void startTimedMode(){
    	Intent intent = new Intent(LogoChooseMode.this, CardBackgroundChooser.class);
    	intent.putExtra("mode", "timed");
    	
    	this.startActivity(intent);
    }

}
