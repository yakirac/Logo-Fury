/*
 * ImageAdapter.java
 *
 * Copyright (c) 2010 Android and Yakira C. Bristol Elon University 
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.logofury;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Class that the GridView needs in order to fill the view
 * with images
 * Source = http://developer.android.com/resources/samples/index.html
 *
 */
public class ImageAdapter extends BaseAdapter{
	
	private Context mContext;
	private Card[] cards;
	private int numberName = 0;

    public ImageAdapter(Context c, Card[] newCards) {
        mContext = c;
        cards = newCards;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    /**
     * I commented out the code that I did not need. They also have their comments 
     * in the code
     */
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        //ImageView imageView;
        Card newCard;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            //imageView = new ImageView(mContext);
            newCard = new Card(mContext);
            //imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setPadding(8, 8, 8, 8);
        } else {
            //imageView = (ImageView) convertView;
            newCard = (Card)convertView;
        }

        //imageView.setImageResource(mThumbIds[position]);
        //Log.d("In ImageAdapter", "Position = " + position);
        newCard.setImageResource(mThumbIds[position]);
        newCard.setPosition(position);
        //Log.d("Card Number", "Card Number = " + numberName);
        if(!(numberName == cards.length + 1)){
	        Card gameCard = cards[position];
	        Log.d("Name of the card", "Name = " + gameCard.getName());
	        newCard.setName(gameCard.getName());
	        numberName++;
        }
        //return imageView;
        return newCard;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.lfg, R.drawable.lfg, R.drawable.lfg,
            R.drawable.lfg, R.drawable.lfg, R.drawable.lfg,
            R.drawable.lfg, R.drawable.lfg, R.drawable.lfg,
            R.drawable.lfg, R.drawable.lfg, R.drawable.lfg
    };
    
    /**
     * Sets the number of cards for the grid to initially create
     * All of the cards initially have the same image
     * @param newIds the array of cards to initially create
     */
    public void setThumbsId(Integer[] newIds){
    	mThumbIds = newIds;
    }

}
