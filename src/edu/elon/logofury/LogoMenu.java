package edu.elon.logofury;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Activity that allows the player to choose what level
 * they would like to play
 * @author Yakira C. Bristol
 *
 */
public class LogoMenu extends Activity{
	
	private Button easy;
	private Button medium;
	private Button difficult;
	private Button changeBackground;
	private String color;
	private String mode;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        easy = (Button)findViewById(R.id.easy);
        easy.setOnClickListener(easyButtonListener);
        
        medium = (Button)findViewById(R.id.medium);
        medium.setOnClickListener(mediumButtonListener);
        
        difficult = (Button)findViewById(R.id.difficult);
        difficult.setOnClickListener(diffButtonListener);
        
        changeBackground = (Button)findViewById(R.id.changeBackground);
        changeBackground.setOnClickListener(changeBackButtonListener);
        
        Bundle extras = getIntent().getExtras();
        if(!(extras == null)){
        	color = extras.getString("color");
        	mode = extras.getString("mode");
        }
        
        
    }
	
	private OnClickListener easyButtonListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			startEasy();
		}
    	
    };
    
    private OnClickListener mediumButtonListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			startMedium();
		}
    	
    };
    
    private OnClickListener diffButtonListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			startDiff();
		}
    	
    };
    
    private OnClickListener changeBackButtonListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			finish();
		}
    	
    };
    
    private void startEasy(){
    	if(color.equals("gray") && mode.equals("easy")){
	    	Intent intent = new Intent(this, LogoLevel1.class);
	    	intent.putExtra("color", color);
	    	this.startActivity(intent);
    	}else if(color.equals("black") && mode.equals("easy")){
    		Intent intent = new Intent(this, LogoLevel1Black.class);
    		intent.putExtra("color", color);
	    	this.startActivity(intent);
    	}else if(color.equals("gray") && mode.equals("timed")){
    		Intent intent = new Intent(this, LogoLevel1Timed.class);
    		intent.putExtra("color", color);
	    	this.startActivity(intent);
    	}else if(color.equals("black") && mode.equals("timed")){
    		Intent intent = new Intent(this, LogoLevel1TimedBlack.class);
    		intent.putExtra("color", color);
	    	this.startActivity(intent);
    	}
    }
    
	private void startMedium(){
		if(color.equals("gray") && mode.equals("easy")){
			Intent intent = new Intent(this, LogoLevel2.class);
			intent.putExtra("color", color);
			this.startActivity(intent);
		}else if(color.equals("black") && mode.equals("easy")){
			Intent intent = new Intent(this, LogoLevel2Black.class);
			intent.putExtra("color", color);
			this.startActivity(intent);
		}else if(color.equals("gray") && mode.equals("timed")){
			Intent intent = new Intent(this, LogoLevel2Timed.class);
			intent.putExtra("color", color);
			this.startActivity(intent);
		}else if(color.equals("black") && mode.equals("timed")){
			Intent intent = new Intent(this, LogoLevel2TimedBlack.class);
			intent.putExtra("color", color);
			this.startActivity(intent);
		}
	}
	
	private void startDiff(){
		if(color.equals("gray") && mode.equals("easy")){
			Intent intent = new Intent(this, LogoLevel3.class);
			intent.putExtra("color", color);
			this.startActivity(intent);
		}else if(color.equals("black") && mode.equals("easy")){
			Intent intent = new Intent(this, LogoLevel3Black.class);
			intent.putExtra("color", color);
			this.startActivity(intent);
		}else if(color.equals("gray") && mode.equals("timed")){
			Intent intent = new Intent(this, LogoLevel3Timed.class);
			intent.putExtra("color", color);
			this.startActivity(intent);
		}else if(color.equals("black") && mode.equals("timed")){
			Intent intent = new Intent(this, LogoLevel3TimedBlack.class);
			intent.putExtra("color", color);
			this.startActivity(intent);
		}
		
	}

}
