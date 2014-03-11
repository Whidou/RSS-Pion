/***************************************************************************//**
 * @file    FluxAdapter.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2013-12-19
 * @version 0.5
 *
 * Adaptateur définissant l'affichage des flux.
 ******************************************************************************/

package com.rss_pion.ui.adapter;

/*** INCLUDES *****************************************************************/

import java.util.Date;
import java.util.LinkedList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rss_pion.R;
import com.rss_pion.beans.Flux;
import com.rss_pion.configuration.Constants;

/*** MAIN CLASS ***************************************************************/

public class FluxAdapter extends ArrayAdapter<Flux> {

/*** ATTRIBUTES ***************************************************************/

	//! Contexte
	private final Context context;

	//! ?
	private final int layoutResourceId;

	//! Liste des flux
	private LinkedList<Flux> data;

/*** METHODS ******************************************************************/

	public FluxAdapter(
	        final Context context,
	        final int layoutResourceId,
	        final LinkedList<Flux> data) {

		super(context, layoutResourceId, data);

		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

/***************************************************************************
 * @see android.widget.ArrayAdapter#getView(int, android.view.View,
 *      android.view.ViewGroup)
 ***************************************************************************/
	@Override
	public View getView(
	        final int position,
	        final View convertView,
			final ViewGroup parent) {

		View row = convertView;
		FluxHolder holder = null;
		BitmapDrawable d;

		if (row == null) {
			final LayoutInflater inflater = ((Activity) this.context)
					.getLayoutInflater();
			row = inflater.inflate(this.layoutResourceId, parent, false);
			holder = new FluxHolder();
			holder.imageView = (ImageView) row.findViewById(R.id.fluxImageView);
			holder.lastBuildDateView = (TextView) row
					.findViewById(R.id.fluxLastBuildDateView);
			holder.titleView = (TextView) row.findViewById(R.id.fluxTitleView);
			holder.numberOfReadArticlesView = (TextView) row
					.findViewById(R.id.fluxNumberOfReadArticlesView);
			row.setTag(holder);
		} else {
			holder = (FluxHolder) row.getTag();
		}

		final Flux flux = this.data.get(position);

        if (flux == null) {
            return row;
        }

        Log.d("FA", "image:"+flux.getImage());
		if (flux.getImage() != null) {
		    d = flux.getImage().getDrawable();
            Log.d("FAI", "Drawable: "+d);
		    holder.imageView.setImageDrawable(d);
		}

		holder.numberOfReadArticlesView.setText(
		        flux.getNumberOfUnreadArticles().toString() +
		        "/" +
				flux.getNumberOfArticles().toString());

		holder.lastBuildDateView.setText(
		        Constants.dateFormat.format(new Date(flux.getPubDate())));

		final SpannableString spanTitleView = new SpannableString(
				flux.getTitle());

		if (flux.getNumberOfUnreadArticles() > 0) {
			spanTitleView.setSpan(new StyleSpan(Typeface.BOLD), 0,
					spanTitleView.length(), 0);
		}
		spanTitleView.setSpan(
		        new UnderlineSpan(), 0, spanTitleView.length(), 0);
		holder.titleView.setText(spanTitleView);

		return row;
	}

/*** SUBCLASSES ***************************************************************/

/**
 * The Class FluxHolder.
 */
    private static class FluxHolder {
    
    	/** The image view. */
    	ImageView imageView;
    
    	/** The title view. */
    	TextView titleView;
    
    	/** The last build date view. */
    	TextView lastBuildDateView;
    
    	/** The number of read articles view. */
    	TextView numberOfReadArticlesView;
    }
}
