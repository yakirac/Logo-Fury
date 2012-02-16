/*
 * LogoFury.java
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
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewAnimator;

/**
 * The activity that starts the game
 * 
 * @author Yakira C. Bristol
 * 
 */
public class LogoFury extends Activity {
	/** Called when the activity is first created. */

	private Button startButton;
	private Button quitButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		startButton = (Button) findViewById(R.id.start);
		startButton.setOnClickListener(startButtonListener);

		quitButton = (Button) findViewById(R.id.quit);
		quitButton.setOnClickListener(quitButtonListener);

	}

	private void startGame() {
		// Intent intent = new Intent(this, LogoLevel1.class);
		// Intent intent = new Intent(this, LogoLevel2.class);
		// Intent intent = new Intent(this, LogoLevel3.class);
		//Intent intent = new Intent(this, CardBackgroundChooser.class);
		Intent intent = new Intent(this, LogoChooseMode.class);

		this.startActivity(intent);
	}

	private OnClickListener startButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			startGame();
		}

	};

	private OnClickListener quitButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			finish();
		}

	};
}