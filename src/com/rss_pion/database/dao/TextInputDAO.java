/***************************************************************************//**
 * @file    TextInputDAO.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-02-08
 * @version 0.5
 *
 * Interface pour les objets text input
 ******************************************************************************/

package com.rss_pion.database.dao;

/*** INCLUDES *****************************************************************/

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

import com.rss_pion.beans.TextInput;
import com.rss_pion.configuration.Constants;

/*** MAIN CLASS ***************************************************************/

public class TextInputDAO {

/*** ATTRIBUTES ***************************************************************/

    //! Table
    public static String nameOfTheAssociatedTable = "TEXTINPUT_IT";

    //! Champs
    public static ArrayList<String[]> fieldsOfTheAssociatedTable;
    static {
        TextInputDAO.fieldsOfTheAssociatedTable = new ArrayList<String[]>();
        TextInputDAO.fieldsOfTheAssociatedTable.add(new String[] {
                "description", "TEXT NOT NULL" });
        TextInputDAO.fieldsOfTheAssociatedTable.add(new String[] {
                "link", "TEXT NOT NULL" });
        TextInputDAO.fieldsOfTheAssociatedTable.add(new String[] {
                "name", "TEXT NOT NULL" });
        TextInputDAO.fieldsOfTheAssociatedTable.add(new String[] {
                "title", "TEXT NOT NULL" });
    }

/*** METHODS ******************************************************************/

/***************************************************************************//**
 * Insère un text input dans la BDD
 * 
 * @param textInput text input à insérer
 * 
 * @return          ID de l'entrée en BDD
 ******************************************************************************/
    public static Long insertTextInputIntoDB(final TextInput textInput) {
        
        Long idTextInput;
        ContentValues valuesMap;
        
        idTextInput = textInput.getId();
        
        if (idTextInput == null) {

            // Préparation des champs
            valuesMap = new ContentValues();

            // Insertion de l'textInput
            idTextInput = Constants.sqlHandler.insert(
                    TextInputDAO.nameOfTheAssociatedTable,
                    "name",
                    valuesMap);

            textInput.setId(idTextInput);
        }

        // Préparation des champs
        valuesMap = new ContentValues();
        valuesMap.put("description", textInput.getDescription());
        valuesMap.put("link", textInput.getLink());
        valuesMap.put("name", textInput.getName());
        valuesMap.put("title", textInput.getTitle());

        Constants.sqlHandler.update(
                TextInputDAO.nameOfTheAssociatedTable,
                valuesMap,
                "id=?",
                new String[] {idTextInput.toString()});

        return idTextInput;
    }

/***************************************************************************//**
 * Retourne un text input de la BDD à partir de son ID
 * 
 * @param id    Numéro d'identification du text input
 * 
 * @return      Text input obtenu ou null
 ******************************************************************************/
    public static TextInput getTextInputFromDB(final Long id) {

        final Cursor c;
        TextInput textInput;

        // Requête
        c = Constants.sqlHandler.query(
                TextInputDAO.nameOfTheAssociatedTable,
                null,
                "id=?",
                new String[] {id.toString()},
                null,
                null,
                null,
                null);

        if (c.moveToFirst()) {

            textInput = new TextInput();

            // Configuration de l'textInput à partir des données
            textInput.setId(id);
            textInput.setDescription(
                    c.getString(c.getColumnIndex("description")));
            textInput.setLink(c.getString(c.getColumnIndex("link")));
            textInput.setName(c.getString(c.getColumnIndex("name")));
            textInput.setTitle(c.getString(c.getColumnIndex("title")));
        } else {
            textInput = null;
        }

        c.close();

        return textInput;
    }

/***************************************************************************//**
 * Supprime un text input de la BDD.
 * 
 * @param textInput Text input à supprimer
 ******************************************************************************/
    public static void deleteTextInputFromDB(final TextInput textInput) {
        Constants.sqlHandler.delete(
                TextInputDAO.nameOfTheAssociatedTable,
                "id=?",
                new String[] {textInput.getId().toString()}
                );
    }
}
