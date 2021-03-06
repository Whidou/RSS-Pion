/***************************************************************************//**
 * @file    FluxActivity.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-02-08
 * @version 1.0
 *
 * Activité listant les flux
 ******************************************************************************/

package com.rss_pion.activities;

/*** INCLUDES *****************************************************************/

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.rss_pion.R;
import com.rss_pion.beans.Article;
import com.rss_pion.configuration.Constants;
import com.rss_pion.database.SqlHandler;
import com.rss_pion.database.dao.CategoryFluxDAO;
import com.rss_pion.database.dao.FluxDAO;
import com.rss_pion.dialogs.AddFluxDialogFragment;
import com.rss_pion.network.NetworkUpdateTask;
import com.rss_pion.ui.adapter.FluxAdapter;
import com.rss_pion.ui.adapter.FluxDetailsExpandableListAdapter;

/*** MAIN CLASS ***************************************************************/

public class FluxActivity extends RSS_PionActivity {

/*** METHODS ******************************************************************/

/***************************************************************************//**
 * @see android.app.Activity#onCreate(Bundle)
 ******************************************************************************/
	@Override
	protected void onCreate(final Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		ActionBar actionBar;
		AutoCompleteTextView fluxLinkInput;

		// Action Bar
		actionBar = this.getActionBar();
		actionBar.setCustomView(R.layout.flux_action_bar);

		// Champ d'ajout de flux
		fluxLinkInput = (AutoCompleteTextView) actionBar.getCustomView()
				.findViewById(R.id.flux_link_input);
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

		// Définition du layout
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
						Constants.focusedFlux = Constants.listOfFlux.get(arg2);

						// Chargement des articles du flux :
						Constants.listOfArticles = new ArrayList<Article>(
								Constants.focusedFlux.getArticles());

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
						// Initialise la boite de dialogue des options de flux :
						final Dialog dialog = new Dialog(FluxActivity.this);
						dialog.setContentView(R.layout.dialog_flux);
						dialog.setTitle("Flux Options");
						dialog.setCancelable(true);
						dialog.setOnCancelListener(new OnCancelListener() {
							@Override
							public void onCancel(final DialogInterface arg0) {
								// Rafraichissement de l'affichage :
								(new NetworkUpdateTask()).execute();
							}
						});

						// Implémente le bouton affichant les détails du flux :
						final Button button_print_infos = (Button) dialog
								.findViewById(R.id.diag_box_flux_print_infos);
						button_print_infos
								.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(final View v) {
										// Initialise la boite de dialogue des
										// options de flux :
										final Dialog dialog2 = new Dialog(
												FluxActivity.this);
										dialog2.setContentView(R.layout.dialog_flux_details);
										dialog2.setTitle("Flux Details :");
										dialog2.setCancelable(true);
										Constants.listOfFlux.get(arg2)
												.toDetails();
										Constants.listViewOfFluxDetails = (ExpandableListView) dialog2
												.findViewById(R.id.listViewFluxDetails);
										Constants.adapterOfFluxDetails = new FluxDetailsExpandableListAdapter(
												dialog2,
												Constants.groupsOfFluxDetails);
										Constants.listViewOfFluxDetails
												.setAdapter(Constants.adapterOfFluxDetails);
										dialog2.show();
									}
								});

						// Implémente le bouton permettant de considérer tous
						// les articles du flux comme lus :
						final Button button_all_articles_read = (Button) dialog
								.findViewById(R.id.diag_box_flux_all_articles_read);
						button_all_articles_read
								.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(final View v) {
										FluxDAO.updateIsReadFluxFromDB(
												Constants.listOfFlux.get(arg2),
												1);
										dialog.cancel();
									}
								});

						// Implémente le bouton permettant de considérer tous
						// les articles du flux comme non lus :
						final Button button_all_articles_not_read = (Button) dialog
								.findViewById(R.id.diag_box_flux_all_articles_not_read);
						button_all_articles_not_read
								.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(final View v) {
										FluxDAO.updateIsReadFluxFromDB(
												Constants.listOfFlux.get(arg2),
												0);
										dialog.cancel();
									}
								});

						// Implémente le bouton supprimant le flux (et tous ses
						// articles) :
						final Button button_delete = (Button) dialog
								.findViewById(R.id.diag_box_flux_delete);
						button_delete.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(final View v) {
								FluxDAO.deleteFluxFromDB(Constants.listOfFlux
										.get(arg2));
								dialog.cancel();
							}
						});

						// Affiche la boite de dialogue :
						dialog.show();

						// Rafraichissement de l'affichage :
						(new NetworkUpdateTask()).execute();

						return true;
					}

				});

		// Déclare que le changement est prêt à être effectué
		fluxLinkInput.beginBatchEdit();

		// Suppression de l'éventuelle base de donnée existante (à commenter
		// après validation)
		// this.deleteDatabase(SqlHandler.DATABASE_NAME);

		// Initialise le conteneur de base de donné :
		Constants.sqlHandler = new SqlHandler(this);

		// Récupère les catégories de flux pour l'auto-complétion :
		final Cursor c = Constants.sqlHandler.query(
		        CategoryFluxDAO.nameOfTheAssociatedTable,
		        null,
		        null,
		        null,
		        null,
                null,
                null,
		        null);
		while ((c != null) && c.moveToFirst()) {
			final String cat_loc = c.getString(c.getColumnIndex("name"));
			if (!Constants.categoriesInDB.contains(cat_loc)) {
				Constants.categoriesInDB.add(cat_loc);
			}
		}

		// Initialise l'auto-complétion :
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.flux_action_bar, Constants.categoriesInDB);
		fluxLinkInput.setThreshold(1);
		fluxLinkInput.setAdapter(adapter);

	}

/***************************************************************************//**
 * @see android.app.Activity#onCreateOptionsMenu(Menu)
 ******************************************************************************/
	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		final MenuInflater inflater = this.getMenuInflater();
		inflater.inflate(R.menu.flux_menu, menu);
		return true;
	}

/***************************************************************************//**
 * @see android.app.Activity#onOptionsItemSelected(MenuItem)
 ******************************************************************************/
	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add_flux:
			(new AddFluxDialogFragment()).show(this.getFragmentManager(),
					"addFlux");
			return true;
		case R.id.maj_flux:
			(new NetworkUpdateTask()).execute();
			return true;
		case R.id.exit_flux:
			System.exit(0);
			return true;
		default:
			return false;
		}
	}

/***************************************************************************//**
 * @see android.app.Activity#onPrepareOptionsMenu(Menu)
 ******************************************************************************/
	@Override
	public boolean onPrepareOptionsMenu(final Menu menu) {
		return true;
	}

/***************************************************************************//**
 * @see android.app.Activity#onResume
 ******************************************************************************/
	@Override
	protected void onResume() {
		super.onResume();
		(new NetworkUpdateTask()).execute();
	}
}