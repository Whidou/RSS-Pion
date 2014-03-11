/***************************************************************************//**
 * @file    ArticleDAO.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-02-08
 * @version 0.6
 *
 * Interface BDD pour les articles
 ******************************************************************************/

package com.rss_pion.database.dao;

/*** INCLUDES *****************************************************************/

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.rss_pion.beans.Article;
import com.rss_pion.configuration.Constants;

/*** MAIN CLASS ***************************************************************/

public class ArticleDAO {

/*** ATTRIBUTES ***************************************************************/

	//! Table
	public static String nameOfTheAssociatedTable = "ARTICLE_IT";

	//! Champs
	public static ArrayList<String[]> fieldsOfTheAssociatedTable;
	static {
		ArticleDAO.fieldsOfTheAssociatedTable = new ArrayList<String[]>();
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] {
		        "author", "TEXT"});
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] {
		        "comments", "TEXT"});
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] {
		        "description", "TEXT"});
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] {
		        "idEnclosure", "INTEGER"});
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] {
		        "idFlux", "INTEGER"});
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] {
		        "idGuid", "INTEGER"});
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] {
		        "isRead", "INTEGER"});
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] {
		        "link", "TEXT"});
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] {
		        "pubDate", "TEXT"});
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] {
		        "source", "TEXT"});
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] {
		        "title", "TEXT"});
	}

/*** METHODS ******************************************************************/

/***************************************************************************//**
 * Insère un article dans la BDD
 * 
 * @param article   Article à insérer
 * 
 * @return          ID de l'entrée en BDD
 ******************************************************************************/
	public static Long insertArticleIntoDB(final Article article) {

        ContentValues valuesMap;
        Long idArticle;

        if (article == null) {
            return null;
        }

        idArticle = article.getId();

        // Si le article n'existe pas dans la BDD
        if (idArticle == null) {

            // Préparation des champs du article
            valuesMap = new ContentValues();
            valuesMap.put("idFlux", article.getIdFlux());

            // Insertion du article
            idArticle = Constants.sqlHandler.insert(
                    ArticleDAO.nameOfTheAssociatedTable,
                    "idFlux",
                    valuesMap);

            article.setId(idArticle);
        }

        // Préparation des champs du article
        valuesMap = new ContentValues();
        valuesMap.put("author", article.getAuthor());
        valuesMap.put("comments", article.getComments());
        valuesMap.put("description", article.getDescription());
        valuesMap.put("idFlux", article.getIdFlux());
        valuesMap.put("isRead", article.getIsRead());
        valuesMap.put("link", article.getLink());
        valuesMap.put("pubDate", article.getPubDate());
        valuesMap.put("source", article.getSource());
        valuesMap.put("title", article.getTitle());

        // Insertion des objets associés
        valuesMap.put(
                "idEnclosure",
                EnclosureDAO.insertEnclosureIntoDB(article.getEnclosure()));
        valuesMap.put(
                "idGuid",
                GuidDAO.insertGuidIntoDB(article.getGuid()));

        // Update de l'article
        Constants.sqlHandler.update(
                ArticleDAO.nameOfTheAssociatedTable,
                valuesMap,
                "id=?",
                new String[] {idArticle.toString()});

        return idArticle;
	}

/***************************************************************************//**
 * Insère une liste d'articles dans la BDD
 * 
 * @param articles  Articles à insérer
 * 
 * @return          ID des entrées en BDD
 ******************************************************************************/
    public static List<Long> insertArticlesIntoDB(
            final List<Article> articles) {

        List<Long> ids = new ArrayList<Long>();

        for (Article article : articles) {
            ids.add(insertArticleIntoDB(article));
        }

        return ids;
    }

/***************************************************************************//**
 * Retourne une liste d'articles à partir d'un curseur.
 * 
 * @param c Curseur obtenu par requête sur la BDD
 * 
 * @return  Liste d'articles
 ******************************************************************************/
    protected static List<Article> getArticlesFromDB(final Cursor c) {

        final List<Article> listeArticles = new ArrayList<Article>();
        Article article;
        Long id;

        if (c == null) {
            return listeArticles;
        }

        if (!c.moveToFirst()) {

            listeArticles.add(null);
            c.close();

            return listeArticles;
        }

        do {

            article = new Article();

            id = c.getLong(c.getColumnIndex("id"));

            // Configuration de l'article à partir des données
            article.setId(id);
            article.setAuthor(c.getString(c.getColumnIndex("author")));
            article.setComments(c.getString(c.getColumnIndex("comments")));
            article.setDescription(
                    c.getString(c.getColumnIndex("description")));
            article.setIdFlux(c.getLong(c.getColumnIndex("idFlux")));
            article.setIsRead(
                    (c.getInt(c.getColumnIndex("isRead")) != 0) ? true : false);
            article.setLink(c.getString(c.getColumnIndex("link")));
            article.setPubDate(c.getLong(c.getColumnIndex("pubDate")));
            article.setSource(c.getString(c.getColumnIndex("source")));
            article.setTitle(c.getString(c.getColumnIndex("title")));

            // Obtention des objets associés
            article.setEnclosure(EnclosureDAO.getEnclosureFromDB(
                    c.getLong(c.getColumnIndex("idEnclosure"))));
            article.setGuid(GuidDAO.getGuidFromDB(
                    c.getLong(c.getColumnIndex("idGuid"))));
            article.setCategories(CategoryArticleDAO.getCategoriesFromDB(id));

            // Ajout du article à la liste
            listeArticles.add(article);
        } while (c.moveToNext());

        c.close();

        return listeArticles;
    }

/***************************************************************************//**
 * Retourne une liste de tous articles d'un flux.
 * 
 * @param id    ID du flux
 * 
 * @return      Liste d'articles
 ******************************************************************************/
    public static List<Article> getArticlesFromDB(final Long id) {

        Cursor c;

        if (id == null) {
            return new ArrayList<Article>();
        }

        c = Constants.sqlHandler.query(
                ArticleDAO.nameOfTheAssociatedTable,
                null,
                "idFlux=?",
                new String[] {id.toString()},
                null,
                null,
                null,
                null);

        return getArticlesFromDB(c);
    }

/***************************************************************************//**
 * Retourne un article de la BDD à partir de son ID
 * 
 * @param id    Numéro d'identification de l'article
 * 
 * @return      Article demandé ou null si cette ID n'est pas dans la BDD
 ******************************************************************************/
    public static Article getArticleFromDB(final Long id) {

        final Cursor c;
        List<Article> articles;

        if (id == null) {
            return null;
        }

        c = Constants.sqlHandler.query(
                ArticleDAO.nameOfTheAssociatedTable,
                null,
                "id=?",
                new String[] {id.toString()},
                null,
                null,
                null,
                null);

        articles = getArticlesFromDB(c);

        if (articles.size() < 1) {
            return null;
        }

        return articles.get(0);
    }

/***************************************************************************//**
 * Supprime un article de la BDD.
 * 
 * @param article   Article à supprimer
 ******************************************************************************/
    public static void deleteArticleFromDB(final Article article) {
        
        if (article == null) {
            return;
        }

        // Suppression des objets associés
        EnclosureDAO.deleteEnclosureFromDB(article.getEnclosure());
        GuidDAO.deleteGuidFromDB(article.getGuid());
        CategoryArticleDAO.deleteCategoriesFromDB(article.getCategories());

        // Suppression du flux
        Constants.sqlHandler.delete(
                ArticleDAO.nameOfTheAssociatedTable,
                "id=?",
                new String[] {article.getId().toString()});
    }

/***************************************************************************//**
 * Supprime une liste d'articles de la BDD.
 * 
 * @param articles  Liste des articles à supprimer
 ******************************************************************************/
    public static void deleteArticlesFromDB(final List<Article> articles) {
        for (Article article : articles) {
            deleteArticleFromDB(article);
        }
    }
}
