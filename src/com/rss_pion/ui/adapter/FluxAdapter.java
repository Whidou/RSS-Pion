/***************************************************************************//**
 * @file    FluxAdapter.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2013-12-19
 * @version 1.0
 *
 * Adaptateur définissant l'affichage des flux.
 ******************************************************************************/

package com.rss_pion.ui.adapter;

/*** INCLUDES *****************************************************************/

import java.util.Date;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
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

    //! Informations d'agencement
	private final int layoutResourceId;

	//! Liste des flux
	private ArrayList<Flux> data;

/*** METHODS ******************************************************************/

	public FluxAdapter(
	        final Context context,
	        final int layoutResourceId,
	        final ArrayList<Flux> data) {

		super(context, layoutResourceId, data);

		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

/***************************************************************************//**
 * @see android.widget.ArrayAdapter#getView(int, android.view.View,
 *      android.view.ViewGroup)
 ******************************************************************************/
	@Override
	public View getView(
	        final int position,
	        final View convertView,
			final ViewGroup parent) {

		View row = convertView;
		FluxHolder holder = null;
		BitmapDrawable d;

		if (row == null) {

			final LayoutInflater inflater =
			        ((Activity) this.context).getLayoutInflater();
			row = inflater.inflate(this.layoutResourceId, parent, false);

            // Assignation des vues à configurer
			holder = new FluxHolder();
			holder.imageView =
			        (ImageView) row.findViewById(R.id.fluxImageView);
			holder.lastBuildDateView =
			        (TextView) row.findViewById(R.id.fluxLastBuildDateView);
			holder.titleView = 
			        (TextView) row.findViewById(R.id.fluxTitleView);
			holder.numberOfReadArticlesView =
			        (TextView) row.findViewById(
			                R.id.fluxNumberOfReadArticlesView);

			row.setTag(holder);
		} else {
			holder = (FluxHolder) row.getTag();
		}

		final Flux flux = this.data.get(position);

        if (flux == null) {
            return row;
        }

        // Image
		if (flux.getImage() != null) {
		    d = flux.getImage().getDrawable();
		} else {
		    d = (BitmapDrawable) context.getResources().getDrawable(
		            R.drawable.icon_flux_default);
		}
        holder.imageView.setImageDrawable(d);

		// Articles lus
		holder.numberOfReadArticlesView.setText(
		        flux.getNumberOfUnreadArticles().toString() +
		        "/" +
				flux.getNumberOfArticles().toString());
		holder.lastBuildDateView.setText(
		        Constants.dateFormat.format(new Date(flux.getPubDate())));

		// Titre
		final SpannableString spanTitleView =
		        new SpannableString(flux.getTitle());
		spanTitleView.setSpan(
		        new UnderlineSpan(), 0, spanTitleView.length(), 0);

		// Mise en évidence si il reste des articles non lus
		if (flux.getNumberOfUnreadArticles() > 0) {
			spanTitleView.setSpan(new StyleSpan(Typeface.BOLD), 0,
					spanTitleView.length(), 0);
		}
		holder.titleView.setText(spanTitleView);

		return row;
	}

/*** SUBCLASSES ***************************************************************/

/***************************************************************************//**
 * Données à afficher pour chaque flux
 ******************************************************************************/
    private static class FluxHolder {
    
    	//! Image
    	ImageView imageView;
    
    	//! Titre
    	TextView titleView;
    
    	//! Date de dernière mise à jour
    	TextView lastBuildDateView;
    
    	//! Nombre d'articles lus
    	TextView numberOfReadArticlesView;
    }
}