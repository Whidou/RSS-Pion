/***************************************************************************//**
 * @file    ArticlesActivity.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-01-23
 * @version 1.0
 *
 * Activité listant les articles d'un flux
 ******************************************************************************/

package com.rss_pion.activities;

/*** INCLUDES *****************************************************************/

import java.util.ArrayList;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.rss_pion.R;
import com.rss_pion.beans.Article;
import com.rss_pion.configuration.Constants;
import com.rss_pion.database.dao.ArticleDAO;
import com.rss_pion.ui.adapter.ArticleAdapter;
import com.rss_pion.ui.adapter.ArticleDetailsExpandableListAdapter;

/*** MAIN CLASS ***************************************************************/

public class ArticlesActivity extends RSS_PionActivity {

/***************************************************************************//**
 * @see android.app.Activity#onCreate(android.os.Bundle)
 ******************************************************************************/
	@Override
	protected void onCreate(final Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// Liaison avec le layout principal :
		this.setContentView(R.layout.list_view_layout);

		// Définition de la liste visuelle des articles
		Constants.listViewOfArticles = (ListView) this
				.findViewById(R.id.listView1);

		// Définition de l'adaptateur liant la liste à son affichage
		Constants.adapterOfArticles = new ArticleAdapter(this,
				R.layout.article_listview_row, Constants.listOfArticles);

		// Mise en place de l'adaptateur
		Constants.listViewOfArticles.setAdapter(Constants.adapterOfArticles);

		// Ajout d'un listener au clic
		Constants.listViewOfArticles
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(final AdapterView<?> arg0,
							final View arg1, final int arg2, final long arg3) {

						String url;
						Intent browserIntent;

						// Obtention de l'URL à charger
						url = Constants.listOfArticles.get(arg2).getLink();
						if (!url.startsWith("http://")
								&& !url.startsWith("https://")) {
							url = "http://" + url;
						}

						// Ouverture du navigateur
						browserIntent = new Intent(Intent.ACTION_VIEW, Uri
								.parse(url));

						// Mise à "lu" de l'article
						ArticleDAO.updateIsReadArticleFromDB(
								Constants.listOfArticles.get(arg2), 1);

						// Lancement du navigateur
						ArticlesActivity.this.startActivity(browserIntent);
					}
				});

		// Ajout d'un listener au clic long
		Constants.listViewOfArticles
				.setOnItemLongClickListener(new OnItemLongClickListener() {
					@Override
					public boolean onItemLongClick(final AdapterView<?> arg0,
							final View arg1, final int arg2, final long arg3) {
						// Initialise la boite de dialogue des options des
						// articles :
						final Dialog dialog = new Dialog(ArticlesActivity.this);
						dialog.setContentView(R.layout.dialog_article);
						dialog.setTitle("Article Options");
						dialog.setCancelable(true);
						dialog.setOnCancelListener(new OnCancelListener() {
							@Override
							public void onCancel(final DialogInterface arg0) {
								// Rafraichissement de l'affichage :
								ArticlesActivity.this.updateListDeArticles();
							}
						});

						// Implémente le bouton affichant les détails des
						// articles :
						final Button button_print_infos = (Button) dialog
								.findViewById(R.id.diag_box_articles_print_infos);
						button_print_infos
								.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(final View v) {
										// Initialise la boite de dialogue des
										// options des articles :
										final Dialog dialog2 = new Dialog(
												ArticlesActivity.this);
										dialog2.setContentView(R.layout.dialog_article_details);
										dialog2.setTitle("Article Details :");
										dialog2.setCancelable(true);
										Constants.listOfArticles.get(arg2)
												.toDetails();
										Constants.listViewOfArticleDetails = (ExpandableListView) dialog2
												.findViewById(R.id.listViewArticleDetails);
										Constants.adapterOfArticleDetails = new ArticleDetailsExpandableListAdapter(
												dialog2,
												Constants.groupsOfArticleDetails);
										Constants.listViewOfArticleDetails
												.setAdapter(Constants.adapterOfArticleDetails);
										dialog2.show();
									}
								});

						// Implémente le bouton permettant de considérer
						// l'article comme lu :
						final Button button_articles_read = (Button) dialog
								.findViewById(R.id.diag_box_articles_read);
						button_articles_read
								.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(final View v) {
										ArticleDAO.updateIsReadArticleFromDB(
												Constants.listOfArticles
														.get(arg2), 1);
										dialog.cancel();
									}
								});

						// Implémente le bouton permettant de considérer
						// l'article comme non lu :
						final Button button_all_articles_not_read = (Button) dialog
								.findViewById(R.id.diag_box_articles_not_read);
						button_all_articles_not_read
								.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(final View v) {
										ArticleDAO.updateIsReadArticleFromDB(
												Constants.listOfArticles
														.get(arg2), 0);
										dialog.cancel();
									}
								});

						// Implémente le bouton supprimant l'article :
						final Button button_delete = (Button) dialog
								.findViewById(R.id.diag_box_articles_delete);
						button_delete.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(final View v) {
								// Suppression de l'article sélectionné :
								ArticleDAO
										.deleteArticleFromDB(Constants.listOfArticles
												.get(arg2));
								// Rafraichissement de l'affichage :
								ArticlesActivity.this.updateListDeArticles();
								dialog.cancel();
							}
						});

						// Affiche la boite de dialogue :
						dialog.show();

						// Rafraichissement de l'affichage :
						ArticlesActivity.this.updateListDeArticles();
						return true;
					}

				});
	}

/***************************************************************************//**
 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
 ******************************************************************************/
	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		final MenuInflater inflater = this.getMenuInflater();
		inflater.inflate(R.menu.article_menu, menu);
		return true;
	}

/***************************************************************************//**
 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
 ******************************************************************************/
	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
		case R.id.help_article:
			return true;
		case R.id.exit_article:
			System.exit(0);
			return true;
		default:
			return false;
		}
	}

/***************************************************************************//**
 * @see android.app.Activity#onPrepareOptionsMenu(android.view.Menu)
 ******************************************************************************/
	@Override
	public boolean onPrepareOptionsMenu(final Menu menu) {
		return true;
	}

/***************************************************************************//**
 * @see com.rss_pion.activities.RSS_PionActivity#onResume()
 ******************************************************************************/
	@Override
	protected void onResume() {
		super.onResume();
		Constants.adapterOfArticles.notifyDataSetChanged();
	}

/***************************************************************************//**
 * Mise à jour de la liste des articles
 ******************************************************************************/
	private void updateListDeArticles() {
		Constants.listOfArticles = (ArrayList<Article>) ArticleDAO
				.getArticlesFromDB(Constants.focusedFlux.getId());
		Constants.adapterOfArticles.notifyDataSetChanged();
	}
}