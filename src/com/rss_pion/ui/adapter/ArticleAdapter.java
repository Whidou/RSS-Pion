/***************************************************************************//**
 * @file    ArticleAdapter.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-01-23
 * @version 1.0
 *
 * Adaptateur d'affichage pour les articles
 ******************************************************************************/

package com.rss_pion.ui.adapter;

/*** INCLUDES *****************************************************************/

import java.util.Date;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rss_pion.R;
import com.rss_pion.beans.Article;
import com.rss_pion.configuration.Constants;

/*** MAIN CLASS ***************************************************************/

public class ArticleAdapter extends ArrayAdapter<Article> {

/*** ATTRIBUTES ***************************************************************/

	//! Contexte d'exécution
	private final Context context;

	//! Informations d'agencement
	private final int layoutResourceId;

	//! Articles à afficher
	private ArrayList<Article> data = new ArrayList<Article>();

/*** METHODS ******************************************************************/

	public ArticleAdapter(final Context context, final int layoutResourceId,
			final ArrayList<Article> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

/***************************************************************************//**
 * @see android.widget.ArrayAdapter#getView(
 * int, android.view.View, android.view.ViewGroup)
 ******************************************************************************/
	@Override
	public View getView(
	        final int position,
	        final View convertView,
	        final ViewGroup parent) {

		View row = convertView;
		ArticleHolder holder = null;

		if (row == null) {

			final LayoutInflater inflater = ((Activity) this.context)
					.getLayoutInflater();
			row = inflater.inflate(this.layoutResourceId, parent, false);

            // Assignation des vues à configurer
			holder = new ArticleHolder();
			holder.titleView = (TextView) row
					.findViewById(R.id.articleTitleView);
			holder.pubDateView = (TextView) row
					.findViewById(R.id.articlePubDateView);
			holder.categoryView = (TextView) row
					.findViewById(R.id.articleCategoryView);
			holder.authorView = (TextView) row
					.findViewById(R.id.articleAuthorView);
			holder.descriptionView = (TextView) row
					.findViewById(R.id.articleDescriptionView);

			row.setTag(holder);
		} else {
			holder = (ArticleHolder) row.getTag();
		}

        final Article article = this.data.get(position);
        
        if (article == null) {
            return row;
        }

        // Titre
		final SpannableString spanTitleView =
		        new SpannableString(article.getTitle());

		// Mise en évidence si non lu
		if (!article.getIsRead()) {
			spanTitleView.setSpan(
			        new StyleSpan(Typeface.BOLD), 0,spanTitleView.length(), 0);
		}

		holder.titleView.setText(spanTitleView);

		// Auteur
		if (article.getAuthor() != null && !article.getAuthor().equals("")) {
		    holder.authorView.setText(article.getAuthor() + ", ");
		}

		// Date de publication
		holder.pubDateView.setText(
		        "le " +
                Constants.dateFormat.format(new Date(article.getPubDate())));

		// Catégories
		if (!article.getCategories().isEmpty()) {
		    holder.categoryView.setText(article.getCategories().toString());
		}
		holder.descriptionView.setText(article.getHtmlDescription());

		return row;
	}

/*** SUBCLASSES ***************************************************************/

/***************************************************************************//**
 * Données à afficher pour chaque article
 ******************************************************************************/
    private static class ArticleHolder {
    
    	//! Titre
    	TextView titleView;
    
    	//! Date de publication
    	TextView pubDateView;
    
    	//! Catégories
    	TextView categoryView;
    
    	//! Auteur
    	TextView authorView;
    
    	//! Description
    	TextView descriptionView;
    }
}