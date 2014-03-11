/***************************************************************************//**
 * @file    Constants.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    23 janv. 2014
 * @version 1.0
 *
 * Constantes globales à toute l'application
 ******************************************************************************/

package com.rss_pion.configuration;

/*** INCLUDES *****************************************************************/

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

/*** MAIN CLASS ***************************************************************/

public class Constants extends Application {

/*** ATTRIBUTES ***************************************************************/

	//! Flux sélectionné
	public static Flux focusedFlux = null;

	//! Liste des flux
	public static ArrayList<Flux> listOfFlux = new ArrayList<Flux>();

	//! Affichage des flux
	public static ListView listViewOfFlux;

	//! Adapteur d'affichage des flux
	public static FluxAdapter adapterOfFlux;

	//! Liste des articles
	public static ArrayList<Article> listOfArticles = new ArrayList<Article>();

	//! Affichage des articles
	public static ListView listViewOfArticles;

	//! Adapteur d'affichage des articles
	public static ArticleAdapter adapterOfArticles;

	//! Gestionnaire de la BDD
	public static SqlHandler sqlHandler;

    //! Format d'affichage des dates
	public static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd",
			Locale.US);

    //! Liste des catégories
	public static ArrayList<String> categoriesInDB = new ArrayList<String>();

    //! Détails concernant le flux courant
	public static SparseArray<GroupFluxDetails> groupsOfFluxDetails =
	        new SparseArray<GroupFluxDetails>();

	//! Affichage des détails de flux
	public static ExpandableListView listViewOfFluxDetails;

	//! Adaptateur d'affichage des détails de flux
	public static FluxDetailsExpandableListAdapter adapterOfFluxDetails;

	//! Détails concernant l'article courant
	public static SparseArray<GroupArticleDetails> groupsOfArticleDetails =
	        new SparseArray<GroupArticleDetails>();

	//! Affichage des détails d'article
	public static ExpandableListView listViewOfArticleDetails;

    //! Adaptateur d'affichage des détails d'article
	public static ArticleDetailsExpandableListAdapter adapterOfArticleDetails;

}