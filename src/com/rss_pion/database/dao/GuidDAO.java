/***************************************************************************//**
 * @file    GuidDAO.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-02-08
 * @version 0.1
 *
 * Interface pour les objets guid
 ******************************************************************************/

package com.rss_pion.database.dao;

/*** INCLUDES *****************************************************************/

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

import com.rss_pion.beans.Guid;
import com.rss_pion.configuration.Constants;

/*** MAIN CLASS ***************************************************************/

public class GuidDAO {

/*** ATTRIBUTES ***************************************************************/

    //! Table
    public static String nameOfTheAssociatedTable = "GUID_IT";

    //! Champs
    public static ArrayList<String[]> fieldsOfTheAssociatedTable;
    static {
        GuidDAO.fieldsOfTheAssociatedTable = new ArrayList<String[]>();
        GuidDAO.fieldsOfTheAssociatedTable.add(new String[] {
                "isPermaLink", "INTEGER"});
        GuidDAO.fieldsOfTheAssociatedTable.add(new String[] {
                "value", "TEXT"});
    }

/*** METHODS ******************************************************************/

/***************************************************************************//**
 * Insère un guid dans la BDD
 * 
 * @param guid  Guid à insérer
 * 
 * @return      ID de l'entrée en BDD
 ******************************************************************************/
    public static Long insertGuidIntoDB(final Guid guid) {
        
        Long idGuid;
        ContentValues valuesMap;

        if (guid == null) {
            return null;
        }
        
        idGuid = guid.getId();
        
        if (idGuid == null) {

            // Préparation des champs
            valuesMap = new ContentValues();

            // Insertion de l'guid
            idGuid = Constants.sqlHandler.insert(
                    GuidDAO.nameOfTheAssociatedTable,
                    "value",
                    valuesMap);

            guid.setId(idGuid);
        }

        // Préparation des champs
        valuesMap = new ContentValues();
        valuesMap.put("isPermaLink", guid.isPermaLink());
        valuesMap.put("value", guid.getValue());

        Constants.sqlHandler.update(
                GuidDAO.nameOfTheAssociatedTable,
                valuesMap,
                "id=?",
                new String[] {idGuid.toString()});

        return idGuid;
    }

/***************************************************************************//**
 * Retourne un guid de la BDD à partir de son ID
 * 
 * @param id    Numéro d'identification du guid
 * 
 * @return      Guid obtenu ou null
 ******************************************************************************/
    public static Guid getGuidFromDB(final Long id) {

        final Cursor c;
        Guid guid;

        if (id == null) {
            return null;
        }

        // Requête
        c = Constants.sqlHandler.query(
                GuidDAO.nameOfTheAssociatedTable,
                null,
                "id=?",
                new String[] {id.toString()},
                null,
                null,
                null,
                null);

        if (c.moveToFirst()) {

            guid = new Guid();

            // Configuration de l'guid à partir des données
            guid.setId(id);
            guid.setPermaLink(
                    (c.getInt(c.getColumnIndex("isPermaLink"))!= 0)?true:false);
            guid.setValue(c.getString(c.getColumnIndex("value")));
        } else {
            guid = null;
        }

        c.close();

        return guid;
    }

/***************************************************************************//**
 * Supprime un guid de la BDD.
 * 
 * @param guid Guid à supprimer
 ******************************************************************************/
    public static void deleteGuidFromDB(final Guid guid) {
        
        if (guid == null) {
            return;
        }

        Constants.sqlHandler.delete(
                GuidDAO.nameOfTheAssociatedTable,
                "id=?",
                new String[] {guid.getId().toString()});
    }
}
