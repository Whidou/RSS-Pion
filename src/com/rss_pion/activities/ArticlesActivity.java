/***************************************************************************
 * @file ArticlesActivity.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ***************************************************************************/
package com.rss_pion.activities;

import java.util.Iterator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.rss_pion.R;
import com.rss_pion.beans.Article;
import com.rss_pion.configuration.Constants;
import com.rss_pion.database.dao.ArticleDAO;
import com.rss_pion.ui.adapter.ArticleAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class ArticlesActivity.
 */
public class ArticlesActivity extends RSS_PionActivity {

	/***************************************************************************
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 ***************************************************************************/
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
						ArticlesActivity.this.startActivity(browserIntent);
					}
				});

		// Ajout d'un listener au clic long
		Constants.listViewOfArticles
				.setOnItemLongClickListener(new OnItemLongClickListener() {
					@Override
					public boolean onItemLongClick(final AdapterView<?> arg0,
							final View arg1, final int arg2, final long arg3) {
						// Suppression de l'article sélectionné :
						ArticleDAO.deleteArticleFromDB(
						        Constants.listOfArticles.get(arg2));
						// Rafraichissement de l'affichage :
						ArticlesActivity.this.updateListDeArticles();
						return true;
					}

				});
	}

	/***************************************************************************
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 ***************************************************************************/
	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		final MenuInflater inflater = this.getMenuInflater();
		inflater.inflate(R.menu.flux_menu, menu);
		return true;
	}

	/***************************************************************************
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 ***************************************************************************/
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

	/***************************************************************************
	 * @see android.app.Activity#onPrepareOptionsMenu(android.view.Menu)
	 ***************************************************************************/
	@Override
	public boolean onPrepareOptionsMenu(final Menu menu) {
		return true;
	}

	/***************************************************************************
	 * @see com.rss_pion.activities.RSS_PionActivity#onResume()
	 ***************************************************************************/
	@Override
	protected void onResume() {
		super.onResume();
		Constants.adapterOfArticles.notifyDataSetChanged();
	}

	/**
	 * Update list de articles.
	 */
	private void updateListDeArticles() {
		final Iterator<Article> it = Constants.focusedFlux
				.getArticles().iterator();
		Constants.listOfArticles.clear();
		while (it.hasNext()) {
			final Article article = new Article();
			article.translateDaoToObject(it.next());
			Constants.listOfArticles.add(article);
		}
		Constants.adapterOfArticles.notifyDataSetChanged();
	}
}