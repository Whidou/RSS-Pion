/***************************************************************************
 * @file FluxAdapter.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ***************************************************************************/
/**
 * @file    FluxAdapter.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2013-12-19
 * @version 0.1
 *
 * Adaptateur définissant l'affichage des flux.
 ******************************************************************************/
package com.rss_pion.ui.adapter;

import java.util.LinkedList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
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

// TODO: Auto-generated Javadoc
/**
 * The Class FluxAdapter.
 */
public class FluxAdapter extends ArrayAdapter<Flux> {

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

		/** The category view. */
		TextView categoryView;

		/** The number of read articles view. */
		TextView numberOfReadArticlesView;
	}

	/** The context. */

	private final Context context;

	/** The layout resource id. */
	private final int layoutResourceId;

	/** The data. */
	private LinkedList<Flux> data = new LinkedList<Flux>();

	/**
	 * Instantiates a new flux adapter.
	 * 
	 * @param context : The context
	 * @param layoutResourceId : The layout resource id
	 * @param data : The data
	 */
	public FluxAdapter(final Context context, final int layoutResourceId,
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
	public View getView(final int position, final View convertView,
			final ViewGroup parent) {
		View row = convertView;
		FluxHolder holder = null;
		if (row == null) {
			final LayoutInflater inflater = ((Activity) this.context)
					.getLayoutInflater();
			row = inflater.inflate(this.layoutResourceId, parent, false);
			holder = new FluxHolder();
			holder.imageView = (ImageView) row.findViewById(R.id.fluxImageView);
			holder.categoryView = (TextView) row
					.findViewById(R.id.fluxCategoryView);
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
		holder.imageView.setImageBitmap(flux.getImage().getBitmap());
		holder.numberOfReadArticlesView.setText("Nombre d'article(s) lu(s) : "
				+ flux.getNumberOfReadArticles().toString() + "/"
				+ flux.getNumberOfArticles().toString());
		holder.categoryView.setText(flux.getCategory());
		holder.lastBuildDateView.setText(flux.getLastBuildDate());
		final SpannableString spanTitleView = new SpannableString(
				flux.getTitle());
		if (flux.getNumberOfReadArticles() < flux.getNumberOfArticles()) {
			spanTitleView.setSpan(new StyleSpan(Typeface.BOLD), 0,
					spanTitleView.length(), 0);
		}
		spanTitleView
				.setSpan(new UnderlineSpan(), 0, spanTitleView.length(), 0);
		holder.titleView.setText(spanTitleView);
		return row;
	}
}
