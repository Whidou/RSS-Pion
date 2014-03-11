/***************************************************************************//**
 * @file    SqlHandler.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-01-23
 * @version 1.1
 *
 * Gestionnaire base de données
 ******************************************************************************/

package com.rss_pion.database;

/*** INCLUDES *****************************************************************/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*** MAIN CLASS ***************************************************************/

public class SqlHandler {

/*** ATTRIBUTES ***************************************************************/

	//! Nom de la BDD
	public static final String DATABASE_NAME = "RSS_PION_DB";

	//! Version
	public static final int DATABASE_VERSION = 1;

	//! Contexte d'exécution
	Context context;

	//! Assistant BDD
	SqlDbHelper dbHelper;

/*** METHODS ******************************************************************/

	public SqlHandler(final Context context) {
		this.dbHelper = new SqlDbHelper(
		        context,
		        SqlHandler.DATABASE_NAME,
				null, SqlHandler.DATABASE_VERSION);
	}

	public Long insert(String table,
            String nullColumnHack,
            ContentValues values) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        return db.insert(table, nullColumnHack, values);
    }

    public Integer update(String table,
            ContentValues values,
            String whereClause,
            String[] whereArgs) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        return db.update(table, values, whereClause, whereArgs);
    }

    public Cursor query(String table,
            String[] columns,
            String selection,
            String[] selectionArgs,
            String groupBy,
            String having,
            String orderBy,
            String limit) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        return db.query(table,
                columns,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderBy,
                limit);
    }

    public int delete(String table,
            String whereClause,
            String[] whereArgs) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        return db.delete(table, whereClause, whereArgs);
    }
}
