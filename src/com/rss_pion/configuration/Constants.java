/***************************************************************************
 * @file Constants.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ***************************************************************************/
package com.rss_pion.configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import android.app.Application;
import android.util.SparseArray;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.rss_pion.beans.Article;
import com.rss_pion.beans.Flux;
import com.rss_pion.beans.GroupArticleDetails;
import com.rss_pion.beans.GroupFluxDetails;
import com.rss_pion.database.SqlHandler;
import com.rss_pion.ui.adapter.ArticleAdapter;
import com.rss_pion.ui.adapter.ArticleDetailsExpandableListAdapter;
import com.rss_pion.ui.adapter.FluxAdapter;
import com.rss_pion.ui.adapter.FluxDetailsExpandableListAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class Constants.
 */
public class Constants extends Application {

	/** The focused flux dao. */
	public static Flux focusedFlux = null;

	/** The list of flux. */
	public static ArrayList<Flux> listOfFlux = new ArrayList<Flux>();

	/** The list view of flux. */
	public static ListView listViewOfFlux;

	/** The adapter of flux. */
	public static FluxAdapter adapterOfFlux;

	/** The list of articles. */
	public static ArrayList<Article> listOfArticles = new ArrayList<Article>();

	/** The list view of articles. */
	public static ListView listViewOfArticles;

	/** The adapter of articles. */
	public static ArticleAdapter adapterOfArticles;

	/** The sql handler. */
	public static SqlHandler sqlHandler;

	public static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd", Locale.US);

	public static ArrayList<String> categoriesInDB = new ArrayList<String>();

	public static SparseArray<GroupFluxDetails> groupsOfFluxDetails = new SparseArray<GroupFluxDetails>();

	public static ExpandableListView listViewOfFluxDetails;

	public static FluxDetailsExpandableListAdapter adapterOfFluxDetails;

	public static SparseArray<GroupArticleDetails> groupsOfArticleDetails = new SparseArray<GroupArticleDetails>();

	public static ExpandableListView listViewOfArticleDetails;

	public static ArticleDetailsExpandableListAdapter adapterOfArticleDetails;

}