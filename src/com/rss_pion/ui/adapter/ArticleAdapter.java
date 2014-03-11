/***************************************************************************
 * @file ArticleAdapter.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ***************************************************************************/
package com.rss_pion.ui.adapter;

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

// TODO: Auto-generated Javadoc
/**
 * The Class ArticleAdapter.
 */
public class ArticleAdapter extends ArrayAdapter<Article> {

	/** The context. */

	private final Context context;

	/** The layout resource id. */
	private final int layoutResourceId;

	/** The data. */
	private ArrayList<Article> data = new ArrayList<Article>();

	/**
	 * Instantiates a new article adapter.
	 * 
	 * @param context : The context
	 * @param layoutResourceId : The layout resource id
	 * @param data : The data
	 */
	public ArticleAdapter(final Context context, final int layoutResourceId,
			final ArrayList<Article> data) {
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
		ArticleHolder holder = null;

		if (row == null) {
			final LayoutInflater inflater = ((Activity) this.context)
					.getLayoutInflater();
			row = inflater.inflate(this.layoutResourceId, parent, false);
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

		final SpannableString spanTitleView = new SpannableString(
				article.getTitle());
		if (!article.getIsRead()) {
			spanTitleView.setSpan(new StyleSpan(Typeface.BOLD), 0,
					spanTitleView.length(), 0);
		}

		holder.titleView.setText(spanTitleView);

		if (article.getAuthor() != null && !article.getAuthor().equals("")) {
		    holder.authorView.setText(article.getAuthor() + ", ");
		}

		holder.pubDateView.setText(
		        "le " +
                Constants.dateFormat.format(new Date(article.getPubDate())));

		if (!article.getCategories().isEmpty()) {
		    holder.categoryView.setText(article.getCategories().toString());
		}

		holder.descriptionView.setText(article.getHtmlDescription());

		return row;
	}

    /* HOLDER */
    /**
     * The Class ArticleHolder.
     */
    private static class ArticleHolder {
    
    	/** The title view. */
    	TextView titleView;
    
    	/** The pub date view. */
    	TextView pubDateView;
    
    	/** The category view. */
    	TextView categoryView;
    
    	/** The author view. */
    	TextView authorView;
    
    	/** The description view. */
    	TextView descriptionView;
    }
}