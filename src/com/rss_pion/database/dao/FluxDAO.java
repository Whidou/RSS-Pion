/***************************************************************************//**
 * @file    FluxDAO.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    23 janv. 2014
 * @version 0.5
 *
 * Interface BDD pour les objets flux
 ******************************************************************************/

package com.rss_pion.database.dao;

/*** INCLUDES *****************************************************************/

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.rss_pion.beans.Flux;
import com.rss_pion.configuration.Constants;

/*** MAIN CLASS ***************************************************************/

public class FluxDAO {

/*** ATTRIBUTES ***************************************************************/

	//! Table
	public static String nameOfTheAssociatedTable = "FLUX_IT";

	//! Champs
	public static ArrayList<String[]> fieldsOfTheAssociatedTable; 
	static {
		FluxDAO.fieldsOfTheAssociatedTable = new ArrayList<String[]>();
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "copyright",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "description",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "docs",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "feed",
				"TEXT NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "generator",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "idCloud",
				"INTEGER" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "idImage",
				"INTEGER" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "idTextInput",
				"INTEGER" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "language",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "lastBuildDate",
				"INTEGER" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "link",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "managingEditor",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] {
				"numberOfArticles", "INTEGER" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] {
				"numberOfReadArticles", "INTEGER" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "ownRate",
				"INTEGER" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "pubDate",
				"INTEGER" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "rating",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "skipDays",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "skipHours",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "title",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "ttl",
				"INTEGER" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "webMaster",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "urlImage",
				"TEXT" });
	}

/*** METHODS ******************************************************************/

/***************************************************************************//**
 * Insère un flux dans la BDD
 * 
 * @param flux  Flux à insérer
 * 
 * @return      ID de l'entrée en BDD
 ******************************************************************************/
    public static Long insertFluxIntoDB(Flux flux) {

        ContentValues valuesMap;
        Long idFlux, idLinkedObject;
        String strValue;

        idFlux = flux.getId();

        // Si le flux n'existe pas dans la BDD
        if (idFlux == null) {

            // Préparation des champs du flux
            valuesMap = new ContentValues();
            valuesMap.put("feed", flux.getFeed());

            // Insertion du flux
            idFlux = Constants.sqlHandler.insert(
                    FluxDAO.nameOfTheAssociatedTable,
                    "feed",
                    valuesMap);

            flux.setId(idFlux);
        }

        // Préparation des champs du flux
        valuesMap = new ContentValues();
        valuesMap.put("copyright", flux.getCopyright());
        valuesMap.put("description", flux.getDescription());
        valuesMap.put("docs", flux.getDocs());
        valuesMap.put("generator", flux.getGenerator());
        valuesMap.put("language", flux.getLanguage());
        valuesMap.put("lastBuildDate", flux.getLastBuildDate());
        valuesMap.put("link", flux.getLink());
        valuesMap.put("managingEditor", flux.getManagingEditor());
        valuesMap.put("pubDate", flux.getPubDate());
        valuesMap.put("rating", flux.getRating());
        valuesMap.put("title", flux.getTitle());
        valuesMap.put("ttl", flux.getTtl());
        valuesMap.put("webMaster", flux.getWebMaster());
        valuesMap.put("urlImage", flux.getUrlImage());

        // Concaténation des jours
        if (flux.getSkipDays() != null) {
            strValue = new String();
            for (String day : flux.getSkipDays()){
                strValue.concat(day);
            }
            valuesMap.put("skipDays", strValue);
        }

        // Concaténation des heures
        if (flux.getSkipHours() != null) {
            strValue = new String();
            for (Integer hour : flux.getSkipHours()){
                strValue.concat(hour.toString());
            }
            valuesMap.put("skipHours", strValue);
        }

        if (flux.getCloud() != null) {
            idLinkedObject = CloudDAO.insertCloudIntoDB(flux.getCloud());
            valuesMap.put("idCloud", idLinkedObject);
        } else {
            valuesMap.put("idCloud", (Long) null);
        }
/*
        if (flux.getImage() != null) {
            idLinkedObject = ImageDAO.insertImageIntoDB(flux.getImage());
            valuesMap.put("idImage", idLinkedObject);
        } else {
            valuesMap.put("idImage", (Long) null);
        }

        if (flux.getTextInput() != null) {
            idLinkedObject = TextInputDAO.insertTextInputIntoDB(
                    flux.getTextInput());
            valuesMap.put("idTextInput", idLinkedObject);
        } else {
            valuesMap.put("idTextInput", (Long) null);
        }
*/
        // Update du flux
        Constants.sqlHandler.update(FluxDAO.nameOfTheAssociatedTable,
                valuesMap,
                "id=?",
                new String[] {idFlux.toString()});

        Log.d("FLUX ADDED", flux.toString());

        return idFlux;
    }

/***************************************************************************//**
 * Retourne une liste d'interfaces, une pour chaque flux dans la BDD.
 * 
 * @return  Liste d'interfaces.
 ******************************************************************************/
    protected static List<Flux> getFluxFromDB(Cursor c) {

        final List<Flux> listeFlux = new ArrayList<Flux>();
        Cursor c2;
        Flux flux;
        Long id;
        List<String> days = new ArrayList<String>();
        List<String> categories = new ArrayList<String>();
        List<Integer> hours = new ArrayList<Integer>();

        if ((c != null) && (c.getCount() != 0)) {
            if (c.moveToFirst()) {
                do {

                    flux = new Flux();
                    
                    id = c.getLong(c.getColumnIndex("id"));

                    // Configuration du flux à partir des données
                    flux.setId(id);
                    flux.setCopyright(c.getString(
                            c.getColumnIndex("copyright")));
                    flux.setDescription(c.getString(
                            c.getColumnIndex("description")));
                    flux.setDocs(c.getString(c.getColumnIndex("docs")));
                    flux.setFeed(c.getString(c.getColumnIndex("feed")));
                    flux.setGenerator(c.getString(
                            c.getColumnIndex("generator")));
                    flux.setLanguage(c.getString(
                            c.getColumnIndex("language")));
                    flux.setLastBuildDate(c.getLong(
                            c.getColumnIndex("lastBuildDate")));
                    flux.setLink(c.getString(c.getColumnIndex("link")));
                    flux.setManagingEditor(c.getString(
                            c.getColumnIndex("managingEditor")));
                    flux.setOwnRate(c.getInt(c.getColumnIndex("ownRate")));
                    flux.setPubDate(c.getLong(c.getColumnIndex("pubDate")));
                    flux.setRating(c.getString(c.getColumnIndex("rating")));
                    flux.setTitle(c.getString(c.getColumnIndex("title")));
                    flux.setTtl(c.getInt(c.getColumnIndex("ttl")));
                    flux.setWebMaster(c.getString(
                            c.getColumnIndex("webMaster")));
                    flux.setUrlImage(c.getString(
                            c.getColumnIndex("urlImage")));

                    // Séparation des jours
                    for (String day : c.getString(
                            c.getColumnIndex("skipDays")).split("/")) {
                        days.add(day);
                    }
                    flux.setSkipDays(days);

                    // Séparation des catégories
                    for (String category : c.getString(
                            c.getColumnIndex("skipDays")).split("/")) {
                        categories.add(category);
                    }
                    flux.setCategories(categories);

                    // Séparation des heures
                    for (String hour : c.getString(
                            c.getColumnIndex("skipHours")).split("/")) {
                        try {
                            hours.add(Integer.parseInt(hour));
                        } catch (NumberFormatException e) {
                            // Les heures mal formattées sont ignorées
                        }
                    }
                    flux.setSkipHours(hours);

                    flux.setCloud(CloudDAO.getCloudFromDB(
                            c.getLong(c.getColumnIndex("idCloud"))));

                    flux.setImage(ImageDAO.getImageFromDB(
                            c.getLong(c.getColumnIndex("idImage"))));
/*
                    flux.setTextInput(new TextInput(c.getLong(
                            c.getColumnIndex("idTextInput"))));
*/

                    // Comptage des articles
                    c2 = Constants.sqlHandler.query(
                            ArticleDAO.nameOfTheAssociatedTable,
                            null,
                            "idFather=?",
                            new String[] {id.toString()},
                            null,
                            null,
                            null,
                            null);
                    flux.setNumberOfArticles(c2.getCount());

                    // Comptage des articles lus
                    c2 = Constants.sqlHandler.query(
                            ArticleDAO.nameOfTheAssociatedTable,
                            null,
                            "idFather=? AND isRead=1",
                            new String[] {id.toString()},
                            null,
                            null,
                            null,
                            null);
                    flux.setNumberOfReadArticles(c2.getCount());

                    // Ajout du flux à la liste
                    listeFlux.add(flux);
                } while (c.moveToNext());
            }
        }
        c.close();

        return listeFlux;
    }

/***************************************************************************//**
 * Retourne une liste de tous les flux dans la BDD.
 * 
 * @return  Liste d'interfaces.
 ******************************************************************************/
	public static List<Flux> getFluxFromDB() {

		final Cursor c = Constants.sqlHandler.query(
		        FluxDAO.nameOfTheAssociatedTable,
		        null,
		        null,
		        null,
		        null,
		        null,
		        null,
		        null);

		return getFluxFromDB(c);
	}

/***************************************************************************//**
 * Retourne un flux de la BDD à partir de son ID
 * 
 * @param id    Numéro d'identification du flux
 * 
 * @return      Interface du flux
 ******************************************************************************/
	public static Flux getFluxFromDB(final Long id) {

        final Cursor c = Constants.sqlHandler.query(
                FluxDAO.nameOfTheAssociatedTable,
                null,
                "id=?",
                new String[] {id.toString()},
                null,
                null,
                null,
                null);

        return getFluxFromDB(c).get(0);
	}

/***************************************************************************//**
 * Supprime un flux de la BDD.
 * 
 * @param flux  Flux à supprimer
 ******************************************************************************/
    public static void deleteFluxFromDB(final Flux flux) {

        if (flux.getArticles() != null) {
            ArticleDAO.deleteArticlesFromDB(flux.getArticles());
        }

        if (flux.getCloud() != null) {
            CloudDAO.deleteCloudFromDB(flux.getCloud());
        }

        if (flux.getImage() != null) {
            ImageDAO.deleteImageFromDB(flux.getImage());
        }
/*
        if (flux.getTextInput() != null) {
            TextInputDAO.deleteTextInputFromDB(flux.getTextInput());
        }
*/
        Constants.sqlHandler.delete(
                FluxDAO.nameOfTheAssociatedTable,
                "id=?",
                new String[] {flux.getId().toString()}
                );
    }

/***************************************************************************//**
 * Supprime une liste de flux de la BDD.
 * 
 * @param listeFlux Liste des flux à supprimer
 ******************************************************************************/
    public static void deleteFluxFromDB(final Flux[] listeFlux) {
        for (Flux flux : listeFlux) {
            deleteFluxFromDB(flux);
        }
    }
}
