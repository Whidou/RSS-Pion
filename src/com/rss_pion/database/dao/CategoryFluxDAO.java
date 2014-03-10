/***************************************************************************//**
 * @file    CategoryFluxDAO.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-02-08
 * @version 0.1
 *
 * Interface pour les objets category
 ******************************************************************************/

package com.rss_pion.database.dao;

/*** INCLUDES *****************************************************************/

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.rss_pion.beans.Category;
import com.rss_pion.configuration.Constants;

/*** MAIN CLASS ***************************************************************/

public abstract class CategoryFluxDAO {

/*** ATTRIBUTES ***************************************************************/
    
    //! Table
    public static String nameOfTheAssociatedTable = "CATEGORY_FLUX_IT";

    //! Champs
    public static ArrayList<String[]> fieldsOfTheAssociatedTable;
    static {
        fieldsOfTheAssociatedTable = new ArrayList<String[]>();
        fieldsOfTheAssociatedTable.add(new String[] {
                "name", "TEXT" });
        fieldsOfTheAssociatedTable.add(new String[] {
                "idParent", "INTEGER" });
    }

/*** METHODS ******************************************************************/

/***************************************************************************//**
 * Insère un category dans la BDD
 * 
 * @param category  Category à insérer
 * 
 * @return      ID de l'entrée en BDD
 ******************************************************************************/
    public static Long insertCategoryIntoDB(final Category category) {

        Long idCategory;
        ContentValues valuesMap;

        if (category == null) {
            return null;
        }

        idCategory = category.getId();
        
        if (idCategory == null) {

            // Préparation des champs
            valuesMap = new ContentValues();

            // Insertion de l'category
            idCategory = Constants.sqlHandler.insert(
                    CategoryFluxDAO.nameOfTheAssociatedTable,
                    "name",
                    valuesMap);

            category.setId(idCategory);
        }

        // Préparation des champs
        valuesMap = new ContentValues();
        valuesMap.put("idParent", category.getIdParent());
        valuesMap.put("name", category.getName());

        Constants.sqlHandler.update(
                CategoryFluxDAO.nameOfTheAssociatedTable,
                valuesMap,
                "id=?",
                new String[] {idCategory.toString()});

        return idCategory;
    }

/***************************************************************************//**
 * Insère une liste de catégories dans la BDD
 * 
 * @param categories    Catégories à insérer
 * 
 * @return              ID des entrées en BDD
 ******************************************************************************/
    public static List<Long> insertCategoriesIntoDB(
            final List<Category> categories) {

        List<Long> ids;
        Long id;

        ids = new ArrayList<Long>();

        for (Category category : categories) {

            id = insertCategoryIntoDB(category);

            if (id != null) {
                ids.add(id);
            }
        }

        return ids;
    }

/***************************************************************************//**
 * Retourne une liste de catégories à partir d'un curseur.
 * 
 * @param c Curseur obtenu par requête sur la BDD
 * 
 * @return  Liste de catégories
 ******************************************************************************/
    protected static List<Category> getCategoriesFromDB(final Cursor c) {

        final List<Category> listeCategories = new ArrayList<Category>();
        Category category;
        Long id;

        if (c == null) {
            return listeCategories;
        }

        if (!c.moveToFirst()) {

            listeCategories.add(null);
            c.close();

            return listeCategories;
        }

        do {
            category = new Category();

            id = c.getLong(c.getColumnIndex("id"));
    
            // Configuration de la catégorie à partir des données
            category.setId(id);
            category.setIdParent(c.getLong(c.getColumnIndex("idParent")));
            category.setName(c.getString(c.getColumnIndex("name")));

            // Ajout de la catégorie à la liste
            listeCategories.add(category);
        } while (c.moveToNext());

        c.close();

        return listeCategories;
    }

/***************************************************************************//**
 * Retourne une liste de toutes les catégories d'un article ou flux.
 * 
 * @param id    ID du parent
 * 
 * @return      Liste de catégories
 ******************************************************************************/
    public static List<Category> getCategoriesFromDB(final Long id) {

        final Cursor c;

        if (id == null) {
            return new ArrayList<Category>();
        }

        c = Constants.sqlHandler.query(
                CategoryFluxDAO.nameOfTheAssociatedTable,
                null,
                "idParent=?",
                new String[] {id.toString()},
                null,
                null,
                null,
                null);

        return getCategoriesFromDB(c);
    }

/***************************************************************************//**
 * Retourne un category de la BDD à partir de son ID
 * 
 * @param id    Numéro d'identification de la catégorie
 * 
 * @return      Catégorie obtenue ou null
 ******************************************************************************/
    public static Category getCategoryFromDB(final Long id) {

        final Cursor c;
        List<Category> categories;

        if (id == null) {
            return null;
        }

        c = Constants.sqlHandler.query(
                CategoryFluxDAO.nameOfTheAssociatedTable,
                null,
                "id=?",
                new String[] {id.toString()},
                null,
                null,
                null,
                null);

        categories = getCategoriesFromDB(c);

        if (categories.size() < 1) {
            return null;
        }

        return categories.get(0);
    }

/***************************************************************************//**
 * Supprime une catégorie de la BDD.
 * 
 * @param category  Catégorie à supprimer
 ******************************************************************************/
    public static void deleteCategoryFromDB(final Category category) {

        if (category == null) {
            return;
        }

        Constants.sqlHandler.delete(
                CategoryFluxDAO.nameOfTheAssociatedTable,
                "id=?",
                new String[] {category.getId().toString()});
    }

/***************************************************************************//**
 * Supprime une liste de catégories de la BDD.
 * 
 * @param categories    Liste des catégories à supprimer
 ******************************************************************************/
    public static void deleteCategoriesFromDB(final List<Category> categories) {
        for (Category category : categories) {
            deleteCategoryFromDB(category);
        }
    }
}
