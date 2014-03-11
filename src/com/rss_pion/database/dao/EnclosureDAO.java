/***************************************************************************/
/**
 * @file    EnclosureDAO.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-02-08
 * @version 0.1
 *
 * Interface pour les pièces jointes
 ******************************************************************************/

package com.rss_pion.database.dao;

/*** INCLUDES *****************************************************************/

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

import com.rss_pion.beans.Enclosure;
import com.rss_pion.configuration.Constants;

/*** MAIN CLASS ***************************************************************/

public class EnclosureDAO {

	/*** ATTRIBUTES ***************************************************************/

	// ! Table
	public static String nameOfTheAssociatedTable = "ENCLOSURE_IT";

	// ! Champs
	public static ArrayList<String[]> fieldsOfTheAssociatedTable;
	static {
		EnclosureDAO.fieldsOfTheAssociatedTable = new ArrayList<String[]>();
		EnclosureDAO.fieldsOfTheAssociatedTable.add(new String[] { "length",
				"INTEGER" });
		EnclosureDAO.fieldsOfTheAssociatedTable.add(new String[] { "type",
				"TEXT" });
		EnclosureDAO.fieldsOfTheAssociatedTable.add(new String[] { "url",
				"TEXT" });
	}

	/*** METHODS ******************************************************************/

	/***************************************************************************/
	/**
	 * Supprime une pièce jointe de la BDD.
	 * 
	 * @param enclosure Pièce jointe à supprimer
	 ******************************************************************************/
	public static void deleteEnclosureFromDB(final Enclosure enclosure) {

		if (enclosure == null) {
			return;
		}

		Constants.sqlHandler.delete(EnclosureDAO.nameOfTheAssociatedTable,
				"id=?", new String[] { enclosure.getId().toString() });
	}

	/***************************************************************************/
	/**
	 * Retourne une pièce jointe de la BDD à partir de son ID
	 * 
	 * @param id Numéro d'identification de la pièce jointe
	 * @return Pièce jointe obtenue ou null
	 ******************************************************************************/
	public static Enclosure getEnclosureFromDB(final Long id) {

		final Cursor c;
		Enclosure enclosure;

		if (id == null) {
			return null;
		}

		// Requête
		c = Constants.sqlHandler.query(EnclosureDAO.nameOfTheAssociatedTable,
				null, "id=?", new String[] { id.toString() }, null, null, null,
				null);

		if (c.moveToFirst()) {

			enclosure = new Enclosure();

			// Configuration de l'enclosure à partir des données
			enclosure.setId(id);
			enclosure.setLength(c.getLong(c.getColumnIndex("length")));
			enclosure.setType(c.getString(c.getColumnIndex("type")));
			enclosure.setUrl(c.getString(c.getColumnIndex("url")));
		} else {
			enclosure = null;
		}

		c.close();

		return enclosure;
	}

	/***************************************************************************/
	/**
	 * Insère une pièce jointe dans la BDD
	 * 
	 * @param enclosure Pièce jointe à insérer
	 * @return ID de l'entrée en BDD
	 ******************************************************************************/
	public static Long insertEnclosureIntoDB(final Enclosure enclosure) {

		Long idEnclosure;
		ContentValues valuesMap;

		if (enclosure == null) {
			return null;
		}

		idEnclosure = enclosure.getId();

		if (idEnclosure == null) {

			// Préparation des champs
			valuesMap = new ContentValues();

			// Insertion de l'enclosure
			idEnclosure = Constants.sqlHandler.insert(
					EnclosureDAO.nameOfTheAssociatedTable, "url", valuesMap);

			enclosure.setId(idEnclosure);
		}

		// Préparation des champs
		valuesMap = new ContentValues();
		valuesMap.put("length", enclosure.getLength());
		valuesMap.put("type", enclosure.getType());
		valuesMap.put("url", enclosure.getUrl());

		Constants.sqlHandler.update(EnclosureDAO.nameOfTheAssociatedTable,
				valuesMap, "id=?", new String[] { idEnclosure.toString() });

		return idEnclosure;
	}
}
