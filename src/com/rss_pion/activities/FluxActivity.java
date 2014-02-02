/***************************************************************************
 * @file FluxActivity.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ***************************************************************************/
package com.rss_pion.activities;

import java.util.Iterator;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.rss_pion.R;
import com.rss_pion.beans.Article;
import com.rss_pion.beans.Flux;
import com.rss_pion.configuration.Constants;
import com.rss_pion.database.SqlHandler;
import com.rss_pion.database.dao.FluxDAO;
import com.rss_pion.database.dao.ImageDAO;
import com.rss_pion.dialogs.AddFluxDialogFragment;
import com.rss_pion.network.NetworkUpdateTask;
import com.rss_pion.tests.Tests;
import com.rss_pion.ui.adapter.FluxAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class FluxActivity.
 */
public class FluxActivity extends RSS_PionActivity {

	/**
	 * On create.
	 * 
	 * @param savedInstanceState : The saved instance state
	 */
	@Override
	protected void onCreate(final Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// Initialisation des Bitmap pour les objets de test :
		final ImageDAO flux_1_image_dao = new ImageDAO();
		flux_1_image_dao.setImage(BitmapFactory
				.decodeResource(this.getApplicationContext().getResources(),
						R.drawable.ic_launcher));
		Tests.flux_1.setImage(flux_1_image_dao);

		// UI :
		ActionBar actionBar;
		EditText fluxLinkInput;

		// Action Bar
		actionBar = this.getActionBar();
		actionBar.setCustomView(R.layout.flux_action_bar);

		// Champ d'ajout de flux
		fluxLinkInput = (EditText) actionBar.getCustomView().findViewById(
				R.id.flux_link_input);
		fluxLinkInput.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(final TextView v, final int actionId,
					final KeyEvent event) {
				Toast.makeText(FluxActivity.this, "okok", Toast.LENGTH_SHORT)
						.show();
				return false;
			}
		});
		fluxLinkInput.setSelected(false);

		// Affichage de l'AB
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
				| ActionBar.DISPLAY_SHOW_HOME);

		// Liaison avec le layout principal
		this.setContentView(R.layout.list_view_layout);

		// Définition de la liste visuelle des flux
		Constants.listViewOfFlux = (ListView) this.findViewById(R.id.listView1);

		// Définition de l'adaptateur liant la liste à son affichage
		Constants.adapterOfFlux = new FluxAdapter(this,
				R.layout.flux_listview_row, Constants.listOfFlux);

		// Mise en place de l'adaptateur
		Constants.listViewOfFlux.setAdapter(Constants.adapterOfFlux);

		// Ajout d'un listener au clic
		Constants.listViewOfFlux
				.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(final AdapterView<?> arg0,
							final View arg1, final int arg2, final long arg3) {
						// Mémorisation du flux à l'origine du click :
						Constants.focusedFlux = FluxDAO
								.getFluxFromDB(Constants.listOfFlux.get(arg2)
										.getId());

						// Chargement des articles du flux :
						final Iterator<Article> it = Constants.focusedFlux
								.getArticles().iterator();
						Constants.listOfArticles.clear();
						while (it.hasNext()) {
							final Article article = new Article();
							article.translateDaoToObject(it.next());
							Constants.listOfArticles.add(article);
						}

						// Ouverture de l'activité gérant les articles du flux :
						final Intent intent = new Intent(FluxActivity.this,
								ArticlesActivity.class);
						FluxActivity.this.startActivity(intent);
					}
				});

		// Ajout d'un listener au clic long
		Constants.listViewOfFlux
				.setOnItemLongClickListener(new OnItemLongClickListener() {
					@Override
					public boolean onItemLongClick(final AdapterView<?> arg0,
							final View arg1, final int arg2, final long arg3) {
						// Mémorisation du flux à l'origine du click :
						Constants.focusedFlux = FluxDAO
								.getFluxFromDB(Constants.listOfFlux.get(arg2)
										.getId());

						// Suppression du flux à l'origine du click :
						FluxDAO.deleteFluxInTheDataBase(Constants.focusedFlux
								.getId());

						// Rafraichissement de l'affichage :
						FluxActivity.this.updateListDeFlux();
						return true;
					}

				});

		// Déclare que le changement est prêt à être effectué
		fluxLinkInput.beginBatchEdit();

		// Suppression de l'éventuelle base de donnée existante (à commenter
		// après validation)
		this.deleteDatabase(SqlHandler.DATABASE_NAME);

		// Initialise le conteneur de base de donné :
		Constants.sqlHandler = new SqlHandler(this);
	}

	/**
	 * On create options menu.
	 * 
	 * @param menu : The menu
	 * @return true, if successful
	 */
	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		final MenuInflater inflater = this.getMenuInflater();
		inflater.inflate(R.menu.flux_menu, menu);
		return true;
	}

	/**
	 * On options item selected.
	 * 
	 * @param item : The item
	 * @return true, if successful
	 */
	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add_flux:
		    (new AddFluxDialogFragment()).show(getFragmentManager(), "addFlux");
		    /*
			// Ajout d'un nouveau flux de test :
			try {
				final FluxDAO fluxDAO = (FluxDAO) (Tests.flux_1
						.translateObjectToDao());
				fluxDAO.insertInTheDataBase(Tests.flux_1);
			} catch (final IllegalAccessException e) {
				Log.d("onOptionsItemSelected, add_flux", e.getMessage());
			} catch (final IllegalArgumentException e) {
				Log.d("onOptionsItemSelected, add_flux", e.getMessage());
			}
            */
			this.updateListDeFlux();
			return true;
        case R.id.maj_flux:
            (new NetworkUpdateTask()).execute();
            return true;
		case R.id.help_flux:
			return true;
		case R.id.exit_flux:
			System.exit(0);
			return true;
		default:
			return false;
		}
	}

	/**
	 * On prepare options menu.
	 * 
	 * @param menu : The menu
	 * @return true, if successful
	 */
	@Override
	public boolean onPrepareOptionsMenu(final Menu menu) {
		return true;
	}

	/**
	 * On resume.
	 */
	@Override
	protected void onResume() {
		super.onResume();
		this.updateListDeFlux();
	}

	/**
	 * Update list de flux.
	 */
	private void updateListDeFlux() {
		Constants.listOfFlux.clear();
		for (Flux flux : FluxDAO.getFluxFromDB()) {
			Constants.listOfFlux.add(flux);
		}
		Constants.adapterOfFlux.notifyDataSetChanged();
	}
}