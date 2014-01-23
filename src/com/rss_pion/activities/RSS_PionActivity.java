/***************************************************************************
 * @file RSS_PionActivity.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ***************************************************************************/
package com.rss_pion.activities;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rss_pion.ui.RSS_PionTextView;

// TODO: Auto-generated Javadoc
/**
 * The Class RSS_PionActivity.
 */
public abstract class RSS_PionActivity extends Activity {

	/***************************************************************************
	 * @see android.app.Activity#onResume()
	 ***************************************************************************/
	@Override
	protected void onResume() {

		super.onResume();
		final ViewGroup root = ((ViewGroup) this
				.findViewById(android.R.id.content));

		// Tests des polices
		this.setAllTypeface(root);
	}

	/**
	 * Sets the all typeface.
	 *
	 * @param view : The new all typeface
	 */
	protected void setAllTypeface(final View view) {

		int i;
		Typeface tf;

		// Application récursive aux enfants
		if (view instanceof ViewGroup) {
			for (i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
				this.setAllTypeface(((ViewGroup) view).getChildAt(i));
			}

			// Tests de la police des textes
		} else if ((view instanceof TextView)
				&& !(view instanceof RSS_PionTextView)) {
			tf = Typeface.createFromAsset(this.getAssets(),
					"fonts/trebucbd.ttf");
			((TextView) view).setTypeface(tf);
		}
	}
}