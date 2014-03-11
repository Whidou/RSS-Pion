/***************************************************************************/
/**
 * @file    FluxActivity.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-02-08
 * @version 0.5
 *
 * Activité listant les flux
 ******************************************************************************/

package com.rss_pion.activities;

/*** INCLUDES *****************************************************************/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
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
import com.rss_pion.beans.Category;
import com.rss_pion.beans.Flux;
import com.rss_pion.beans.GroupFluxDetails;
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
	public static void createData(final Flux flux) {
		GroupFluxDetails group;
		// ! URL du flux RSS
		group = new GroupFluxDetails("Feed");
		group.children.add(flux.getFeed());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Titre
		group = new GroupFluxDetails("Title");
		group.children.add(flux.getTitle());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Lien vers le site web associé
		group = new GroupFluxDetails("Link");
		group.children.add(flux.getLink());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Description
		group = new GroupFluxDetails("Description");
		group.children.add(flux.getDescription());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Langue
		group = new GroupFluxDetails("Language");
		group.children.add(flux.getLanguage());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Copyright
		group = new GroupFluxDetails("Copyright");
		group.children.add(flux.getCopyright());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Adresse courriel de l'éditeur
		group = new GroupFluxDetails("Managing Editor");
		group.children.add(flux.getManagingEditor());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Adresse courriel du webmestre
		group = new GroupFluxDetails("Web Master");
		group.children.add(flux.getWebMaster());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Date de dernière publication
		group = new GroupFluxDetails("Publication Date");
		try {
			group.children.add(flux.getPubDate().toString());
		} catch (final Exception e) {
		}
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Date de dernière génération
		group = new GroupFluxDetails("Last Build Date");
		try {
			group.children.add(flux.getLastBuildDate().toString());
		} catch (final Exception e) {
		}
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Articles
		group = new GroupFluxDetails("Categories");
		try {
			final Iterator<Category> itC = flux.getCategories().iterator();
			while (itC.hasNext()) {
				try {
					group.children.add(itC.next().getName());
				} catch (final Exception e) {
				}
			}
		} catch (final Exception e) {
		}
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Logiciel utilisé pour générer le flux
		group = new GroupFluxDetails("Generator");
		group.children.add(flux.getGenerator());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Documentation associée
		group = new GroupFluxDetails("Documentation");
		group.children.add(flux.getDocs());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Objet cloud associé
		group = new GroupFluxDetails("Cloud");
		final ArrayList<String> cloud_str = new ArrayList<String>();
		try {
			cloud_str.add("Domain : " + flux.getCloud().getDomain());
		} catch (final Exception e) {
		}
		try {
			cloud_str.add("Path : " + flux.getCloud().getPath());
		} catch (final Exception e) {
		}
		try {
			cloud_str.add("Port : " + flux.getCloud().getPort());
		} catch (final Exception e) {
		}
		try {
			cloud_str.add("Protocol : " + flux.getCloud().getProtocol());
		} catch (final Exception e) {
		}
		try {
			cloud_str.add("Register procedure : "
					+ flux.getCloud().getRegisterProcedure());
		} catch (final Exception e) {
		}
		final Iterator<String> itCloud_str = cloud_str.iterator();
		String cloud_str_tot = "";
		while (itCloud_str.hasNext()) {
			cloud_str_tot += itCloud_str.next();
			if (itCloud_str.hasNext()) {
				cloud_str_tot += "\n";
			}
		}
		group.children.add(cloud_str_tot);
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Durée (en minutes) de validité
		group = new GroupFluxDetails("Time To Live");
		try {
			group.children.add(flux.getTtl().toString());
		} catch (final Exception e) {
		}
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Classement PICS
		group = new GroupFluxDetails("Rating");
		group.children.add(flux.getRating());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Objet text input associé
		group = new GroupFluxDetails("Text Input");
		final ArrayList<String> textInput_str = new ArrayList<String>();
		try {
			textInput_str.add("Name : " + flux.getTextInput().getName());
		} catch (final Exception e) {
		}
		try {
			textInput_str.add("Title : " + flux.getTextInput().getTitle());
		} catch (final Exception e) {
		}
		try {
			textInput_str.add("Description : "
					+ flux.getTextInput().getDescription());
		} catch (final Exception e) {
		}
		try {
			textInput_str.add("Link : " + flux.getTextInput().getLink());
		} catch (final Exception e) {
		}
		final Iterator<String> itTextInput_str = textInput_str.iterator();
		String textInput_str_tot = "";
		while (itTextInput_str.hasNext()) {
			textInput_str_tot += itTextInput_str.next();
			if (itTextInput_str.hasNext()) {
				textInput_str_tot += "\n";
			}
		}
		group.children.add(textInput_str_tot);
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Heures de non-mise à jour
		group = new GroupFluxDetails("Skip Hours");
		try {
			final Iterator<Integer> itSH = flux.getSkipHours().iterator();
			while (itSH.hasNext()) {
				group.children.add(itSH.next().toString());
			}
		} catch (final Exception e) {
		}
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Jours de non-mise à jour
		group = new GroupFluxDetails("Skip Days");
		try {
			final Iterator<String> itSD = flux.getSkipDays().iterator();
			while (itSD.hasNext()) {
				group.children.add(itSD.next());
			}
		} catch (final Exception e) {
		}
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Classement utilisateur (favoris)
		group = new GroupFluxDetails("Own Rate");
		try {
			group.children.add(flux.getOwnRate().toString());
		} catch (final Exception e) {
		}
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! URL de l'image associée
		group = new GroupFluxDetails("URL Image");
		group.children.add(flux.getUrlImage());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);
	}

	/***************************************************************************/
	/**
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
						Constants.listOfArticles = new LinkedList<Article>(
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
										FluxActivity
												.createData(Constants.listOfFlux
														.get(arg2));
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
										// Rafraichissement de l'affichage :
										(new NetworkUpdateTask()).execute();
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
								// Rafraichissement de l'affichage :
								(new NetworkUpdateTask()).execute();
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
		final Cursor c = Constants.sqlHandler.selectQuery("SELECT * FROM "
				+ CategoryFluxDAO.nameOfTheAssociatedTable);
		int i = 0;
		while ((c != null) && c.moveToFirst()) {
			final String cat_loc = c.getString(c.getColumnIndex("name"));
			if (!Constants.categoriesInDB.contains(cat_loc)) {
				Constants.categoriesInDB.add(cat_loc);
			}
			i++;
		}

		// Initialise l'auto-complétion :
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.flux_action_bar, Constants.categoriesInDB);
		fluxLinkInput.setThreshold(1);
		fluxLinkInput.setAdapter(adapter);
		Log.d("TEST", "i : " + i + ",   " + Constants.categoriesInDB.toString());

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
			(new AddFluxDialogFragment()).show(this.getFragmentManager(),
					"addFlux");
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
		(new NetworkUpdateTask()).execute();
	}
}