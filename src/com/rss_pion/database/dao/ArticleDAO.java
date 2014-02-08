/***************************************************************************//**
 * @file    ArticleDAO.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-02-08
 * @version 0.5
 *
 * Interface BDD pour les articles
 ******************************************************************************/

package com.rss_pion.database.dao;

/*** INCLUDES *****************************************************************/

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.util.Log;

import com.rss_pion.beans.Article;

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
		        "idFather", "INTEGER"});
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
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] {
		        "userRate", "INTEGER"});
	}

/*** METHODS ******************************************************************/

	public static Long insertIntoDB(final Article article) {
		Log.d("ARTICLE ADDED", article.toString());
		return null;
	}

    protected static List<Article> getArticlesFromDB(final Cursor c) {
        return null;
    }

    public static List<Article> getArticlesFromDB(final Long id) {
        return null;
    }

    public static Article getArticleFromDB(final Long id) {
        return null;
    }

    public static void deleteArticleFromDB(final Article article) {
        return;
    }

    public static void deleteArticlesFromDB(final List<Article> articles) {
        return;
    }
}
