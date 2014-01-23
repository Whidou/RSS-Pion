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

import java.util.LinkedList;

import android.app.Application;
import android.widget.ListView;

import com.rss_pion.beans.Article;
import com.rss_pion.beans.Flux;
import com.rss_pion.database.SqlHandler;
import com.rss_pion.database.dao.FluxDAO;
import com.rss_pion.ui.adapter.ArticleAdapter;
import com.rss_pion.ui.adapter.FluxAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class Constants.
 */
public class Constants extends Application {

	/** The focused flux dao. */
	public static FluxDAO focusedFluxDAO = null;

	/** The list of flux. */
	public static LinkedList<Flux> listOfFlux = new LinkedList<Flux>();

	/** The list view of flux. */
	public static ListView listViewOfFlux;

	/** The adapter of flux. */
	public static FluxAdapter adapterOfFlux;

	/** The list of articles. */
	public static LinkedList<Article> listOfArticles = new LinkedList<Article>();

	/** The list view of articles. */
	public static ListView listViewOfArticles;

	/** The adapter of articles. */
	public static ArticleAdapter adapterOfArticles;

	/** The sql handler. */
	public static SqlHandler sqlHandler;

}