/***************************************************************************//**
 * @file    CloudDAO.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-02-08
 * @version 0.1
 *
 * Interface pour les objets cloud
 ******************************************************************************/

package com.rss_pion.database.dao;

/*** INCLUDES *****************************************************************/

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

import com.rss_pion.beans.Cloud;
import com.rss_pion.configuration.Constants;

/*** MAIN CLASS ***************************************************************/

public class CloudDAO {

/*** ATTRIBUTES ***************************************************************/

    //! Table
    public static String nameOfTheAssociatedTable = "CLOUD_IT";

    //! Champs
    public static ArrayList<String[]> fieldsOfTheAssociatedTable;
    static {
        CloudDAO.fieldsOfTheAssociatedTable = new ArrayList<String[]>();
        CloudDAO.fieldsOfTheAssociatedTable.add(new String[] {
                "domain", "TEXT"});
        CloudDAO.fieldsOfTheAssociatedTable.add(new String[] {
                "path", "TEXT"});
        CloudDAO.fieldsOfTheAssociatedTable.add(new String[] {
                "port", "INTEGER"});
        CloudDAO.fieldsOfTheAssociatedTable.add(new String[] {
                "protocol", "TEXT"});
        CloudDAO.fieldsOfTheAssociatedTable.add(new String[] {
                "registerProcedure", "TEXT"});
    }

/*** METHODS ******************************************************************/

/***************************************************************************//**
 * Insère un cloud dans la BDD
 * 
 * @param cloud Cloud à insérer
 * 
 * @return      ID de l'entrée en BDD
 ******************************************************************************/
    public static Long insertCloudIntoDB(final Cloud cloud) {
        
        Long idCloud;
        ContentValues valuesMap;
        
        if (cloud == null) {
            return null;
        }

        idCloud = cloud.getId();
        
        if (idCloud == null) {

            // Préparation des champs
            valuesMap = new ContentValues();

            // Insertion de l'cloud
            idCloud = Constants.sqlHandler.insert(
                    CloudDAO.nameOfTheAssociatedTable,
                    "domain",
                    valuesMap);

            cloud.setId(idCloud);
        }

        // Préparation des champs
        valuesMap = new ContentValues();
        valuesMap.put("domain", cloud.getDomain());
        valuesMap.put("path", cloud.getPath());
        valuesMap.put("port", cloud.getPort());
        valuesMap.put("protocol", cloud.getProtocol());
        valuesMap.put("registerProcedure", cloud.getRegisterProcedure());

        Constants.sqlHandler.update(
                CloudDAO.nameOfTheAssociatedTable,
                valuesMap,
                "id=?",
                new String[] {idCloud.toString()});

        return idCloud;
    }

/***************************************************************************//**
 * Retourne un cloud de la BDD à partir de son ID
 * 
 * @param id    Numéro d'identification du cloud
 * 
 * @return      Cloud obtenu ou null
 ******************************************************************************/
    public static Cloud getCloudFromDB(final Long id) {

        final Cursor c;
        Cloud cloud;

        if (id == null) {
            return null;
        }

        // Requête
        c = Constants.sqlHandler.query(
                CloudDAO.nameOfTheAssociatedTable,
                null,
                "id=?",
                new String[] {id.toString()},
                null,
                null,
                null,
                null);

        if (c.moveToFirst()) {

            cloud = new Cloud();

            // Configuration de l'cloud à partir des données
            cloud.setId(id);
            cloud.setDomain(c.getString(c.getColumnIndex("domain")));
            cloud.setPath(c.getString(c.getColumnIndex("path")));
            cloud.setPort(c.getInt(c.getColumnIndex("port")));
            cloud.setProtocol(c.getString(c.getColumnIndex("protocol")));
            cloud.setRegisterProcedure(c.getString(
                    c.getColumnIndex("registerProcedure")));
        } else {
            cloud = null;
        }

        c.close();

        return cloud;
    }

/***************************************************************************//**
 * Supprime un cloud de la BDD.
 * 
 * @param cloud Cloud à supprimer
 ******************************************************************************/
    public static void deleteCloudFromDB(final Cloud cloud) {
        
        if (cloud == null) {
            return;
        }

        Constants.sqlHandler.delete(
                CloudDAO.nameOfTheAssociatedTable,
                "id=?",
                new String[] {cloud.getId().toString()}
                );
    }
}
