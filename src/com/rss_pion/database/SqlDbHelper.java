/***************************************************************************//**
 * @file    SqlDbHelper.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-01-23
 * @version 1.0
 *
 * Assistant BDD
 ******************************************************************************/

package com.rss_pion.database;

/*** INCLUDES *****************************************************************/

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.rss_pion.database.dao.ArticleDAO;
import com.rss_pion.database.dao.CategoryArticleDAO;
import com.rss_pion.database.dao.CategoryFluxDAO;
import com.rss_pion.database.dao.CloudDAO;
import com.rss_pion.database.dao.EnclosureDAO;
import com.rss_pion.database.dao.FluxDAO;
import com.rss_pion.database.dao.GuidDAO;
import com.rss_pion.database.dao.ImageDAO;
import com.rss_pion.database.dao.TextInputDAO;

/*** MAIN CLASS ***************************************************************/

public class SqlDbHelper extends SQLiteOpenHelper {

	public SqlDbHelper(final Context context, final String name,
			final CursorFactory factory, final int version) {
		super(context, name, factory, version);
	}

/***************************************************************************//**
 * Crée une requête d'ajout de nouvelle table à la BDD
 * 
 * @param tableName     Nom
 * @param fields        Champs
 * 
 * @return              Requête
 ******************************************************************************/
	private String createTable(final String tableName,
			final ArrayList<String[]> fields) {

		String out =
		        "CREATE TABLE " +
		        tableName +
				" (id INTEGER PRIMARY KEY AUTOINCREMENT, ";
		final Iterator<String[]> it = fields.iterator();

		while (it.hasNext()) {
			final String[] field = it.next();
			if (!field[0].equals("id")) {
				out += field[0] + " " + field[1] + (it.hasNext() ? ", " : ");");
			}
		}

		return out;
	}

/***************************************************************************//**
 * Crée une requête de suppression de table de la BDD
 * 
 * @param tableName     Nom
 * 
 * @return              Requête
 ******************************************************************************/
	private String dropTable(final String tableName) {
		return "DROP TABLE IF EXISTS " + tableName + ";";
	}

/***************************************************************************//**
 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(
 * android.database.sqlite.SQLiteDatabase)
 ******************************************************************************/
	@Override
	public void onCreate(final SQLiteDatabase db) {
		db.execSQL(this.createTable(
		        FluxDAO.nameOfTheAssociatedTable,
				FluxDAO.fieldsOfTheAssociatedTable));
		db.execSQL(this.createTable(
		        ArticleDAO.nameOfTheAssociatedTable,
				ArticleDAO.fieldsOfTheAssociatedTable));
        db.execSQL(this.createTable(
                CloudDAO.nameOfTheAssociatedTable,
                CloudDAO.fieldsOfTheAssociatedTable));
        db.execSQL(this.createTable(
                ImageDAO.nameOfTheAssociatedTable,
                ImageDAO.fieldsOfTheAssociatedTable));
		db.execSQL(this.createTable(
		        EnclosureDAO.nameOfTheAssociatedTable,
				EnclosureDAO.fieldsOfTheAssociatedTable));
		db.execSQL(this.createTable(
		        GuidDAO.nameOfTheAssociatedTable,
				GuidDAO.fieldsOfTheAssociatedTable));
		db.execSQL(this.createTable(
		        TextInputDAO.nameOfTheAssociatedTable,
				TextInputDAO.fieldsOfTheAssociatedTable));
		db.execSQL(this.createTable(
				CategoryArticleDAO.nameOfTheAssociatedTable,
				CategoryArticleDAO.fieldsOfTheAssociatedTable));
		db.execSQL(this.createTable(
		        CategoryFluxDAO.nameOfTheAssociatedTable,
				CategoryFluxDAO.fieldsOfTheAssociatedTable));
	}

/***************************************************************************
 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(
 * android.database.sqlite.SQLiteDatabase,int, int)
 ***************************************************************************/
	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion,
			final int newVersion) {
		db.execSQL(this.dropTable(ImageDAO.nameOfTheAssociatedTable));
		db.execSQL(this.dropTable(FluxDAO.nameOfTheAssociatedTable));
		db.execSQL(this.dropTable(ArticleDAO.nameOfTheAssociatedTable));
		db.execSQL(this.dropTable(CloudDAO.nameOfTheAssociatedTable));
		db.execSQL(this.dropTable(EnclosureDAO.nameOfTheAssociatedTable));
		db.execSQL(this.dropTable(GuidDAO.nameOfTheAssociatedTable));
		db.execSQL(this.dropTable(TextInputDAO.nameOfTheAssociatedTable));
		db.execSQL(this.dropTable(CategoryArticleDAO.nameOfTheAssociatedTable));
		db.execSQL(this.dropTable(CategoryFluxDAO.nameOfTheAssociatedTable));
		this.onCreate(db);
	}
}