/***************************************************************************
 * @file RSS_PionTextView.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ***************************************************************************/
package com.rss_pion.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class RSS_PionTextView.
 */
public class RSS_PionTextView extends TextView {

	/** The context. */
	Context context;

	/** The ttf name. */
	String ttfName;

	/** The tag. */
	String TAG = this.getClass().getName();

	/**
	 * Instantiates a new RS s_ pion text view.
	 *
	 * @param context : The context
	 * @param attrs : The attrs
	 */
	public RSS_PionTextView(final Context context, final AttributeSet attrs) {
		super(context, attrs);
		this.context = context;

		for (int i = 0; i < attrs.getAttributeCount(); i++) {
			this.ttfName = attrs.getAttributeValue(
					"http://schemas.android.com/apk/res/com.rss_pion",
					"ttf_name");
			this.initAsset();
		}

	}

	/**
	 * Inits the asset.
	 */
	private void initAsset() {
		final Typeface font = Typeface.createFromAsset(
				this.context.getAssets(), this.ttfName);
		this.setTypeface(font);
	}

	/***************************************************************************
	 * @see android.widget.TextView#setTypeface(android.graphics.Typeface)
	 ***************************************************************************/
	@Override
	public void setTypeface(final Typeface tf) {
		super.setTypeface(tf);
	}

}