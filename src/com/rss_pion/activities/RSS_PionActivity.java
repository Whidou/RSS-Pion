/***************************************************************************//**
 * @file    RSS_PionActivity.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-02-08
 * @version 0.5
 *
 * Classe parentes des activités de l'application
 ******************************************************************************/

package com.rss_pion.activities;

/*** INCLUDES *****************************************************************/

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rss_pion.ui.RSS_PionTextView;

/*** MAIN CLASS ***************************************************************/

public abstract class RSS_PionActivity extends Activity {

/***************************************************************************//**
 * @see android.app.Activity#onResume()
 ******************************************************************************/
	@Override
	protected void onResume() {

		super.onResume();

		final ViewGroup root = (ViewGroup) this.findViewById(
		        android.R.id.content);

		// Assignation de la police spéciale
		this.setAllTypeface(root);
	}

/***************************************************************************//**
 * Modifie la police des tous les textes de façon récursive
 * 
 * @param view  Élément à modifier
 ******************************************************************************/
	protected void setAllTypeface(final View view) {

		int i;
		Typeface tf;

		// Application récursive aux enfants
		if (view instanceof ViewGroup) {
			for (i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
				this.setAllTypeface(((ViewGroup) view).getChildAt(i));
			}

		// Assignation de la police aux textes
		} else if ((view instanceof TextView)
				&& !(view instanceof RSS_PionTextView)) {
			tf = Typeface.createFromAsset(
			        this.getAssets(), "fonts/trebucbd.ttf");
			((TextView) view).setTypeface(tf);
		}
	}
}