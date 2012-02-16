/*
 * Card.java
 *
 * Copyright (c) 2010 Yakira C. Bristol, Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.logofury;

import android.content.Context;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Represents a single card in the game
 * @author Yakira C. Bristol
 *
 */
public class Card extends ImageView{
	
	private Card card;
	private Context cText;
	private String name;
	private int thisPosition;
	private Animation previous;
	private Animation next;
	private int howManyAnims = 0;
	private String cardColor;
	
	private Integer[] cards;

	/**
	 * Constructor for the Card class that takes and instance 
	 * of the Context. Sets up all the information for the card 
	 * how it will look in the grid
	 * @param context the instance of the context to set this classes
	 * 					instance of the context to
	 */
	public Card(Context context) {
		super(context);
		
		this.setLayoutParams(new GridView.LayoutParams(85, 85));
        this.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.setPadding(8, 8, 8, 8);
        card = this;
        cText = context;
        cards = new Integer[12];
	}
	
	/**
	 * Applies the rotation on the card from the Rotate3dAnimation class
	 * @param position  the position of the view
	 * @param start where the view starts
	 * @param end where the view should end up
	 * 
	 * Source = http://developer.android.com/resources/samples/index.html
	 */
	public void applyRotation(int position, float start, float end){
    	// Find the center of the container
        final float centerX = this.getWidth() / 2.0f;
        final float centerY = this.getHeight() / 2.0f;

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Rotate3dAnimation rotation =
                new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);
        
        if(howManyAnims == 1){
        	next = rotation;
        }
        rotation.setDuration(300);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new DisplayNextView(position, cText));
        
        if(howManyAnims == 0){
        	this.startAnimation(rotation);
        }
        //this.startAnimation(rotation);
       
        
    }
	
	
	/**
	 * Gets the name of this card
	 * @param newName the String to set the name of
	 * 					this card to
	 */
	public void setName(String newName){
		name = newName;
	}
	
	/**
	 * Gets the name of this card
	 * @return name the name of this card
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Sets the array of cards
	 * @param newCards the new array of cards to set
	 * 			the current array of cards to 
	 */
	public void setCards(Integer[] newCards){
		cards = newCards;
	}
	
	/**
	 * Gets the array of cards
	 * @return cards the array of cards
	 */
	public Integer[] getCards(){
		return cards;
	}
	
	/**
	 * Sets the position of the of this card
	 * @param pos the position to set this cards 
	 * 				position to
	 */
	public void setPosition(int pos){
		thisPosition = pos;
	}
	
	/**
	 * Gets the position of the of this card
	 * @return thisPosition the position of this card
	 */
	public int getPosition(){
		return thisPosition;
	}
	
	/**
	 * Sets how many animations have been called by this card
	 * @param anims the number to set the number of animations to
	 */
	public void setHowManyAnims(int anims){
		howManyAnims = anims;
	}
	
	/**
	 * Sets which color the card needs to be when face down.
	 * Based on this color the card will choose the right image 
	 * to display
	 * @param color the color string that cardColor will be set to
	 * 					in this case gray or black
	 */
	public void setCardColor(String color){
		cardColor = color;
	}
	
	
	/**
     * This class listens for the end of the first half of the animation.
     * It then posts a new action that effectively swaps the views when the container
     * is rotated 90 degrees and thus invisible.
     * Source = http://developer.android.com/resources/samples/index.html
     */
    public final class DisplayNextView implements Animation.AnimationListener {
        private final int mPosition;
        private Context c;
        Rotate3dAnimation rAnim;

        private DisplayNextView(int position, Context context) {
            mPosition = position;
            c = context;
        }

        public void onAnimationStart(Animation animation) {
        	//card.post(new SwapViews(mPosition, c));
        }

        public void onAnimationEnd(Animation animation) {
            //container.post(new SwapViews(mPosition));
        	card.post(new SwapViews(mPosition, c));
     
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    /**
     * This class is responsible for swapping the views and start the second
     * half of the animation.
     * Source = http://developer.android.com/resources/samples/index.html
     * 
     * I had to make this class a listener in order the second animation to begin 
     * after the first animation ended.
     * The image needed to be shown before it the card was flipped back over so 
     * the player could remember what the image was at that cards position
     */
    public final class SwapViews implements Runnable, Animation.AnimationListener{
        private final int mPosition;
        private Context context;
        private Card view2;

        public SwapViews(int position, Context c) {
            mPosition = position;
            context = c;
            view2 = new Card(context);
            view2.setImageResource(R.drawable.lfg);
        }

        public void run() {
            final float centerX = card.getWidth() / 2.0f;
            final float centerY = card.getHeight() / 2.0f;
            Rotate3dAnimation rotation;
            
            if (mPosition > -1) {
                //card.setVisibility(View.GONE);
                //view2.setImageResource(R.drawable.kobe2);
                //view2.setVisibility(View.VISIBLE);
                //view2.requestFocus();
            	//Log.d("length of cards", "Number of cards = " + card.getCards().length);
            	//Log.d("Position", "Position of card = " + card.getPosition());
                card.setImageResource(cards[thisPosition]);
                card.requestFocus();
            	//card = view2;
            	//card.requestFocus();
                
                //changed the second value from 180 to 0 to get it to stop flipping the picture
                //what it did was swap the views but then when the views were swapped again the 
                //first picture was reversed. I want it to stay the same.
                rotation = new Rotate3dAnimation(90, 0, centerX, centerY, 310.0f, false);
            } else {
                //view2.setVisibility(View.GONE);
                //card.setVisibility(View.VISIBLE);
                //card.requestFocus();
            	if(cardColor.equals("black")){
                    card.setImageResource(R.drawable.lfb);
                    card.requestFocus();
            	}else{
	                card.setImageResource(R.drawable.lfg);
	                card.requestFocus();
            	}
                
                rotation = new Rotate3dAnimation(90, 0, centerX, centerY, 310.0f, false);
            }

            rotation.setDuration(300);
            rotation.setFillAfter(true);
            //rotation.setStartOffset(rotationDelay);
            rotation.setInterpolator(new DecelerateInterpolator());
            
            

            card.startAnimation(rotation);
            onAnimationEnd(rotation);
        }

		@Override
		public void onAnimationEnd(Animation animation) {
			//Log.d("Animation", "At the end of the animation");
			if(!(card.next == null)){
				card.next = null;
				card.howManyAnims = 0;
				card.applyRotation(-1, 0, 90);
			}
			
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
			
		}
    }

}
