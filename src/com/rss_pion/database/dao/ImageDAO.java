/***************************************************************************//**
 * @file    ImageDAO.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    23 janv. 2014
 * @version 0.5
 *
 * Interface pour les objets image
 ******************************************************************************/

package com.rss_pion.database.dao;

/*** INCLUDES *****************************************************************/

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

import com.rss_pion.beans.ImageRSS;
import com.rss_pion.configuration.Constants;

/*** MAIN CLASS ***************************************************************/

public class ImageDAO {

/*** ATTRIBUTES ***************************************************************/

	//! Table
	public static String nameOfTheAssociatedTable = "IMAGE_IT";

	//! Champs
	public static ArrayList<String[]> fieldsOfTheAssociatedTable;
    static {
        ImageDAO.fieldsOfTheAssociatedTable = new ArrayList<String[]>();
        ImageDAO.fieldsOfTheAssociatedTable.add(new String[] {"url", "TEXT"});
        ImageDAO.fieldsOfTheAssociatedTable.add(new String[] {"path", "TEXT"});
    }

/*** METHODS ******************************************************************/

/***************************************************************************//**
 * Insère une image dans la BDD
 * 
 * @param image Image à insérer
 * 
 * @return      ID de l'entrée en BDD
 ******************************************************************************/
	public static Long insertImageIntoDB(final ImageRSS image) {
	    
	    Long idImage;
		ContentValues valuesMap;
        
        if (image == null) {
            return null;
        }
		
		idImage = image.getId();
		
		if (idImage == null) {

            // Préparation des champs
            valuesMap = new ContentValues();

            // Insertion de l'image
            idImage = Constants.sqlHandler.insert(
                    ImageDAO.nameOfTheAssociatedTable,
                    "url",
                    valuesMap);

            image.setId(idImage);
		}

        // Préparation des champs
        valuesMap = new ContentValues();
        valuesMap.put("url", image.getUrl());
        valuesMap.put("path", image.getPath());

		Constants.sqlHandler.update(
				ImageDAO.nameOfTheAssociatedTable,
				valuesMap,
                "id=?",
                new String[] {idImage.toString()});

		return idImage;
	}

/***************************************************************************//**
 * Retourne une image de la BDD à partir de son ID
 * 
 * @param id    Numéro d'identification de l'image
 * 
 * @return      Image obtenue ou null
 ******************************************************************************/
    public static ImageRSS getImageFromDB(final Long id) {

        final Cursor c;
        ImageRSS image;

        if (id == null) {
            return null;
        }

        // Requête
        c = Constants.sqlHandler.query(
                ImageDAO.nameOfTheAssociatedTable,
                null,
                "id=?",
                new String[] {id.toString()},
                null,
                null,
                null,
                null);

        if (c.moveToFirst()) {

            image = new ImageRSS();

            // Configuration de l'image à partir des données
            image.setId(id);
            image.setUrl(c.getString(c.getColumnIndex("url")));
            image.setPath(c.getString(c.getColumnIndex("path")));
        } else {
            image = null;
        }

        c.close();

        return image;
    }

/***************************************************************************//**
 * Supprime une image de la BDD.
 * 
 * @param image Image à supprimer
 ******************************************************************************/
    public static void deleteImageFromDB(final ImageRSS image) {

        if (image == null) {
            return;
        }

        Constants.sqlHandler.delete(
                ImageDAO.nameOfTheAssociatedTable,
                "id=?",
                new String[] {image.getId().toString()});
    }
}
