/***************************************************************************//**
 * @file    RSS_PionTextView.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-01-23
 * @version 1.0
 *
 * Police personalisée pour le slogan...
 ******************************************************************************/

package com.rss_pion.ui;

/*** INCLUDES *****************************************************************/

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/*** MAIN CLASS ***************************************************************/

public class RSS_PionTextView extends TextView {

/*** ATTRIBUTES ***************************************************************/

	//! Contexte d'exécution
	Context context;

	//! Nom de la police
	String ttfName;

	//! Référence
	String TAG = this.getClass().getName();

/*** METHODS ******************************************************************/

	public RSS_PionTextView(final Context context, final AttributeSet attrs) {

		super(context, attrs);
		this.context = context;

		for (int i = 0; i < attrs.getAttributeCount(); i++) {
			this.ttfName = attrs.getAttributeValue(
					"http://schemas.android.com/apk/res/com.rss_pion",
					"ttf_name");
	        final Typeface font = Typeface.createFromAsset(
	                this.context.getAssets(), this.ttfName);
	        this.setTypeface(font);
		}

	}

/***************************************************************************//**
 * @see android.widget.TextView#setTypeface(android.graphics.Typeface)
 ******************************************************************************/
	@Override
	public void setTypeface(final Typeface tf) {
		super.setTypeface(tf);
	}

}