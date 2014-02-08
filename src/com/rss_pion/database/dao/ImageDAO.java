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
        ImageDAO.fieldsOfTheAssociatedTable.add(new String[] {
                "bitmap", "BLOB"});
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
		
		idImage = image.getId();
		
		if (idImage == null) {

            // Préparation des champs
            valuesMap = new ContentValues();

            // Insertion de l'image
            idImage = Constants.sqlHandler.insert(
                    ImageDAO.nameOfTheAssociatedTable,
                    "bitmap",
                    valuesMap);

            image.setId(idImage);
		}

        // Préparation des champs
        valuesMap = new ContentValues();
        valuesMap.put("bitmap", image.getBytes());

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
            image.setBytes(c.getString(c.getColumnIndex("bitmap")).getBytes());
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
        Constants.sqlHandler.delete(
                ImageDAO.nameOfTheAssociatedTable,
                "id=?",
                new String[] {image.getId().toString()});
    }
}
