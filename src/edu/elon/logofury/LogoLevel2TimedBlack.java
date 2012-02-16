/*
 * LogoLevel2TimedBlack.java
 *
 * Copyright (c) 2010 Yakira C. Bristol, Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.logofury;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.ViewAnimator;
import android.widget.AdapterView.OnItemClickListener;

/**
 * The timed Medium level of the game with the 
 * cards having a black background
 * @author Yakira C. Bristol
 *
 */
public class LogoLevel2TimedBlack extends Activity{
	private ViewAnimator vAnimator;
	private GridView gView;
	private TextView tv;
	private ImageAdapter iAdapter;
	private CardLayout layout;
	private Card view;
	private Card firstView;
	private int clickCounter = 1;
	private String first = "";
	private String second = "";
	private String color;
	private int matches = 0;
	private int turns = 0;
	private int time = 90;
	
	private Integer[] images = {
            R.drawable.burberry, R.drawable.calvinklein, R.drawable.giorgioarmani,
            R.drawable.givenchy, R.drawable.gucci, R.drawable.lacoste, 
            R.drawable.poloralphlauren, R.drawable.versace
    };
	
	private Integer[] sImages = {
			R.drawable.burberrylogo, R.drawable.calvinkleinlogo, R.drawable.giorgioarmanilogo,
            R.drawable.givenchylogo, R.drawable.guccilogo, R.drawable.lacostelogo, 
            R.drawable.poloralphlaurenlogo, R.drawable.versacelogo};
	
	private boolean[] randomCards = {
			false, false, false, false, 
			false, false, false, false, 
			false, false, false, false, 
			false, false, false, false};
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timed_level2);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		tv = (TextView)findViewById(R.id.time);
		tv.setText("1:30");
		
		Integer[] thumbIds = {
				R.drawable.lfb, R.drawable.lfb, R.drawable.lfb,
	            R.drawable.lfb, R.drawable.lfb, R.drawable.lfb,
	            R.drawable.lfb, R.drawable.lfb, R.drawable.lfb,
	            R.drawable.lfb, R.drawable.lfb, R.drawable.lfb,
	            R.drawable.lfb, R.drawable.lfb, R.drawable.lfb,
	            R.drawable.lfb
	            
	    };
		
		layout = new CardLayout(this.getBaseContext(), 16);
		layout.setImages(images);
		layout.setRandomCards(randomCards);
		layout.setSImages(sImages);
		layout.setCardLayout();
		
		vAnimator = (ViewAnimator)findViewById(R.id.animator);
		vAnimator.setAnimateFirstView(true);
		vAnimator.setAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
		
		gView = (GridView)findViewById(R.id.gridview);
		iAdapter = new ImageAdapter(this, layout.getGameCards());
		iAdapter.setThumbsId(thumbIds);
		gView.setAdapter(iAdapter);
		
		Bundle extras = getIntent().getExtras();
		if(!(extras == null)){
			color = extras.getString("color");
		}
		
		/*
         * Gets the item from the grid that was clicked. If two items
         * were clicked then it checks whether they match. If only one
         * item was clicked it waits for a second item to be clicked.
         * 
         * Source = http://developer.android.com/resources/samples/index.html
         */
		gView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				if(clickCounter == 1){
					view = (Card)v;
					view.setCardColor(color);
					firstView = view;
					view.setCards(layout.getCardLayout());
					first = view.getName();
					view.applyRotation(0, 0, 90);
					clickCounter++;
					turns++;
					time = time - 3;
					checkTimer();
				}
				else if(clickCounter == 2){
					view = (Card)v;
					view.setCardColor(color);
					view.setCards(layout.getCardLayout());
					second = view.getName();
					time = time - 3;
					if(!(firstView.getPosition() ==  view.getPosition())){
						view.applyRotation(0, 0, 90);
						view.setHowManyAnims(1);
						turns++;
						
						//Log.d("The name of the first card", "First name = " + first);
						//Log.d("The name of the second card", "Second name = " + second);
					}
					else{
						firstView.applyRotation(-1, 0, 90);
						view.applyRotation(-1, 0, 90);
						clickCounter = 1;
						firstView = null;
						view = null;
					}
					checkMatch();
					checkTimer();
				}
			}
			
		});
		
	}
	
	/**
	 * Creates the dialog at the end of this level that asks if you want
	 * to go to the next level
	 * @return dialog the dialog that will pop up at the end of the level
	 */
	public AlertDialog createDialog(){
		AlertDialog dialog = new AlertDialog.Builder(LogoLevel2TimedBlack.this)
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					
					startNewLevel();
					finish();
					
				}
			})
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					
					finish();
					
				}
			}).create();
		dialog.setIcon(R.drawable.lfbsmall);
		dialog.setTitle("Logo Fury");
		dialog.setMessage("Congratulations you won." + "\nNumber of turns = " 
				+ turns + "\nWould you like to go the next level?");
		
		return dialog;
	}
	
	/**
	 * Creates the dialog that tells you you lost if your time runs out 
	 * and you haven't made all the correct matches. Asks if you want
	 * to play again
	 * @return dialog the dialog that will pop up at the end of the level
	 */
	public AlertDialog createLoseDialog(){
		AlertDialog dialog = new AlertDialog.Builder(LogoLevel2TimedBlack.this)
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					
					finish();
				}
			})
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					
					finish();
				}
			}).create();
		dialog.setIcon(R.drawable.lfbsmall);
		dialog.setTitle("Logo Fury");
		dialog.setMessage("Sorry your time ran out." + "\nNumber of turns = " 
				+ turns + "\nWould you like to play again?");
		
		return dialog;
	}
	
	/**
	 * Starts the next level of the game
	 */
	public void startNewLevel(){
		Intent intent = new Intent(LogoLevel2TimedBlack.this, LogoLevel3TimedBlack.class);
		intent.putExtra("color", color);
		
		this.startActivity(intent);
	}
	
	private void checkMatch(){
		if(!(first.equals(second))){
			firstView.applyRotation(-1, 0, 90);
			view.applyRotation(-1, 0, 90);
			clickCounter = 1;
			firstView = null;
			view = null;
		}
		else{
			if(matches == 7){
				AlertDialog moveOn = createDialog();
				moveOn.show();
			}else{
				clickCounter = 1;
				matches++;
				firstView = null;
				view = null;
			}
		}
	}
	
	private void checkTimer(){
		if(time == 0 && matches < 7){
			AlertDialog youLost = createLoseDialog();
			youLost.show();
		}else if(time < 9){
			tv.setText(":0" + time);
		}else if(time > 9 && time < 59){
			tv.setText(":" + time);
		}else if(time > 59 && time < 69){
			tv.setText("1:0" + (time - 60));
		}else if(time > 69 && time < 90){
			tv.setText("1:" + (time - 60));
		}
	}
	
	public void onPause(){
		super.onPause();
	}
	
	public void onStop(){
		super.onStop();
	}

}
