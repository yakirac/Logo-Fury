/*
 * CardLayout.java
 *
 * Copyright (c) 2010 Yakira C. Bristol, Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.logofury;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.util.Log;

/**
 * The positions of the cards in the grid
 * @author Yakira C. Bristol
 *
 */
public class CardLayout {
	
	private Integer[] images = {
            R.drawable.bugatti, R.drawable.corvettesmall, R.drawable.ferrari,
            R.drawable.lamborghinismall, R.drawable.maseratismall, R.drawable.maybachsmall      
    };
	private Integer[] sImages = {
			R.drawable.bugattilogo, R.drawable.corvettelogo, R.drawable.ferrarilogo, 
			R.drawable.lamborghinilogo, R.drawable.maseratilogo, R.drawable.maybachlogo};
	
	private boolean[] randomCards = {false, false, false, false, 
			false, false, false, false, false, false, false, false};
	
	private Integer[] cards;
	private ArrayList<Integer> numbersChosen;
	private Card[] gameCards;
	
	private Random randomGenerator;
	
	private Context context;
	private int numberOfCards;
	
	/**
	 * Constructor that takes an instance of the Context 
	 * the number of cards to set up the level with 
	 * @param c the instance of the Context needed to create the cards
	 * @param numCards the number of cards to set up the level with
	 */
	public CardLayout(Context c, int numCards){
		numberOfCards = numCards;
		cards = new Integer[numberOfCards];
		
		randomGenerator = new Random();
		numbersChosen = new ArrayList<Integer>();
		
		gameCards = new Card[numberOfCards];
		
		context = c;
	}
	
	/**
	 * Sets up the positions of the cards in the grid
	 */
	public void setCardLayout(){
		int imagePos = 0;
		int i = 0;
		int next = randomGenerator.nextInt(numberOfCards);
		while(i < randomCards.length){
			//Log.d("Next random number", "Random number = " + next);
			//Log.d("Does numbersChosen contain next?", String.valueOf(numbersChosen.contains(next)));
			//Log.d("I is", i + "");
			if(!(numbersChosen.contains(next))){
					numbersChosen.add(next);
					//Log.d("Image position", "Image Position = " + imagePos);
					if(!(imagePos == (numberOfCards/2))){
						cards[next] = images[imagePos];
						Card newCard = new Card(context);
						newCard.setName(String.valueOf(imagePos));
						//Log.d("In Card Layout", "Name of card is = " + String.valueOf(imagePos));
						gameCards[next] = newCard;
						imagePos++;
					}
					else{
						imagePos = 0;
						images = sImages;
						//Log.d("In else", "Image Position = " + imagePos);
						cards[next] = images[imagePos];
						Card newCard = new Card(context);
						newCard.setName(String.valueOf(imagePos));
						//Log.d("In Card Layout", "Name of card is = " + String.valueOf(imagePos));
						gameCards[next] = newCard;
						imagePos++;
					}
					i++;
			}
			else{
				next = randomGenerator.nextInt(numberOfCards);
			}
			
		}
	}
	
	/**
	 * Gets the layout of cards created by this class
	 * @return cards the layout of cards created
	 */
	public Integer[] getCardLayout(){
		return cards;
	}
	
	/**
	 * Gets the array of cards created
	 * @return gamesCards the array of cards created
	 */
	public Card[] getGameCards(){
		return gameCards;
	}
	
	/**
	 * Sets the first integer array of images
	 * @param newI the new integer array of images
	 * 				to set the images array to
	 */
	public void setImages(Integer[] newI){
		images = newI;
	}
	
	/**
	 * Sets the second integer array of images
	 * @param newS the new integer array of images
	 * 				to set the sImages array to
	 */
	public void setSImages(Integer[] newS){
		sImages = newS;
	}
	
	/**
	 * Sets the randomCards array to a new array of booleans
	 * @param rCards the new boolean array to set the 
	 * 					randomCards boolean array to
	 */
	public void setRandomCards(boolean[] rCards){
		randomCards = rCards;
	}

}
