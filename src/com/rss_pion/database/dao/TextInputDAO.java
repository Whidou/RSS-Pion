/***************************************************************************//**
 * @file TextInputDAO.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 2014-02-02
 * @version 0.5
 *
 * Interface BDD pour les objets TextInput
 ******************************************************************************/

package com.rss_pion.database.dao;

/*** INCLUDES *****************************************************************/

import java.util.ArrayList;
import java.util.Iterator;

import android.database.Cursor;
import android.util.Log;

import com.rss_pion.configuration.Constants;
import com.rss_pion.database.SqlDbHelper;
import com.rss_pion.database.dao.abstracts.SerializedObject;

/*** MAIN CLASS ***************************************************************/

public class TextInputDAO extends SerializedObject {

/*** ATTRIBUTES ***************************************************************/

    //! Titre
    private String title;

    //! Lien
    private String link;

    //! Description
    private String description;

    //! Nom
    private String name;

	//! Table
	public static String nameOfTheAssociatedTable = "TEXTINPUT_IT";

	//! Champs
	public static ArrayList<String[]> fieldsOfTheAssociatedTable;
	static {
		TextInputDAO.fieldsOfTheAssociatedTable = new ArrayList<String[]>();
		TextInputDAO.fieldsOfTheAssociatedTable.add(new String[] { "name",
				"TEXT NOT NULL" });
		TextInputDAO.fieldsOfTheAssociatedTable.add(new String[] {
				"description", "TEXT NOT NULL" });
		TextInputDAO.fieldsOfTheAssociatedTable.add(new String[] { "link",
				"TEXT NOT NULL" });
		TextInputDAO.fieldsOfTheAssociatedTable.add(new String[] { "title",
				"TEXT NOT NULL" });
	}

/*** METHODS ******************************************************************/

    public TextInputDAO() {
        super();
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLink() {
        return this.link;
    }

    public String getTitle() {
        return this.title;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setLink(final String link) {
        this.link = link;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

/***************************************************************************//**
 * Suppression du text input de la BDD
 * 
 * @param id    ID du text input à supprimer
 ******************************************************************************/
	public static void deleteTextInputInTheDataBase(final Long id) {
		final String query = "SELECT * FROM "
				+ TextInputDAO.nameOfTheAssociatedTable + " WHERE id = " + id;
		final Cursor c1 = Constants.sqlHandler.selectQuery(query);
		if ((c1 != null) && (c1.getCount() != 0)) {
			if (c1.moveToFirst()) {
				do {
					Constants.sqlHandler.deleteDAO(
							TextInputDAO.nameOfTheAssociatedTable, id);
				} while (c1.moveToNext());
			}
		}
		c1.close();
	}

/**
 * Gets the flux dao.
 * 
 * @return The flux dao
 */
	public static ArrayList<FluxDAO> getFluxDAO() {

		final String query = "SELECT * FROM "
				+ ArticleDAO.nameOfTheAssociatedTable + ";";
		final Cursor c1 = Constants.sqlHandler.selectQuery(query);
		final ArrayList<FluxDAO> flux_list = new ArrayList<FluxDAO>();

		if ((c1 != null) && (c1.getCount() != 0)) {
			if (c1.moveToFirst()) {
				do {
					final FluxDAO fluxDAO = new FluxDAO();
					fluxDAO.setIdCloud(Long.parseLong(c1.getString(c1
							.getColumnIndex("cloud"))));
					fluxDAO.setCopyright(c1.getString(c1
							.getColumnIndex("copyright")));
					fluxDAO.setDescription(c1.getString(c1
							.getColumnIndex("description")));
					fluxDAO.setDocs(c1.getString(c1.getColumnIndex("docs")));
					fluxDAO.setFeed(c1.getString(c1.getColumnIndex("feed")));
					fluxDAO.setGenerator(c1.getString(c1
							.getColumnIndex("generator")));
					fluxDAO.setId(Long.parseLong(c1.getString(c1
							.getColumnIndex("id"))));
					fluxDAO.setIdImage(Long.parseLong(c1.getString(c1
							.getColumnIndex("image"))));
					fluxDAO.setLanguage(c1.getString(c1
							.getColumnIndex("language")));
					fluxDAO.setLastBuildDate(c1.getLong(c1
							.getColumnIndex("lastBuildDate")));
					fluxDAO.setLink(c1.getString(c1.getColumnIndex("link")));
					fluxDAO.setManagingEditor(c1.getString(c1
							.getColumnIndex("managingEditor")));
					fluxDAO.setNumberOfArticles(Integer.parseInt(c1
							.getString(c1.getColumnIndex("numberOfArticles"))));
					fluxDAO.setNumberOfReadArticles(Integer.parseInt(c1
							.getString(c1
									.getColumnIndex("numberOfReadArticles"))));
					fluxDAO.setOwnRate(Integer.parseInt(c1.getString(c1
							.getColumnIndex("ownRate"))));
					fluxDAO.setPubDate(c1.getLong(c1
							.getColumnIndex("pubDate")));
					fluxDAO.setRating(c1.getString(c1.getColumnIndex("rating")));
					fluxDAO.setSkipDays(c1.getString(c1
							.getColumnIndex("skipDays")));
					fluxDAO.setSkipHours(c1.getString(c1
							.getColumnIndex("skipHours")));
					fluxDAO.setIdTextInput(Long.parseLong(c1.getString(c1
							.getColumnIndex("textInput"))));
					fluxDAO.setTitle(c1.getString(c1.getColumnIndex("title")));
					fluxDAO.setTtl(Integer.parseInt(c1.getString(c1
							.getColumnIndex("ttl"))));
					fluxDAO.setWebMaster(c1.getString(c1
							.getColumnIndex("webMaster")));
					flux_list.add(fluxDAO);
				} while (c1.moveToNext());
			}
		}

		c1.close();

		return flux_list;
	}

/***************************************************************************//**
 * Insère le text input dans la BDD
 * 
 * @param objects   
 * 
 * @return          Id de l'entrée
 ******************************************************************************/
	@Override
	public Long insertInTheDataBase(final Object... objects)
			throws IllegalAccessException, IllegalArgumentException {

		String names = "";

		final Iterator<String[]> it = TextInputDAO
		        .fieldsOfTheAssociatedTable.iterator();
		while (it.hasNext()) {
			names += it.next()[0] + (it.hasNext() ? ", " : ")");
		}

		Constants.sqlHandler.executeQuery("INSERT INTO " +
		        TextInputDAO.nameOfTheAssociatedTable + " (" + names +
		        " VALUES ('" + this.getName() + "', '" + this.getDescription() +
		        "', " + this.getLink() + "');");

		Log.d("TEXT INPUT ADDED", this.toString());

		return SqlDbHelper.lastInsertId(TextInputDAO.nameOfTheAssociatedTable);
	}

/***************************************************************************//**
 * Retourne un string correspondant au text input
 * 
 * @return  Texte de description du text input
 * 
 * @see java.lang.Object#toString()
 ******************************************************************************/
	@Override
	public String toString() {
		return "TextInputDAO [title=" + this.title + ", link=" + this.link +
		        ", description=" + this.description + ", name=" + this.name +
		        "]";
	}
}
