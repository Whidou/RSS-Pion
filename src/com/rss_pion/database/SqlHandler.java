/***************************************************************************
 * @file SqlHandler.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ***************************************************************************/
package com.rss_pion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class SqlHandler.
 */
public class SqlHandler {

	/** The Constant DATABASE_NAME. */
	public static final String DATABASE_NAME = "RSS_PION_DB";

	/** The Constant DATABASE_VERSION. */
	public static final int DATABASE_VERSION = 1;

	/** The context. */
	Context context;

	/** The sql database. */
	SQLiteDatabase sqlDatabase;

	/** The db helper. */
	SqlDbHelper dbHelper;

	/**
	 * Instantiates a new sql handler.
	 * 
	 * @param context : The context
	 */
	public SqlHandler(final Context context) {
		this.dbHelper = new SqlDbHelper(context, SqlHandler.DATABASE_NAME,
				null, SqlHandler.DATABASE_VERSION);
		this.sqlDatabase = this.dbHelper.getWritableDatabase();
	}

	/**
	 * Delete dao.
	 * 
	 * @param table_name : The table_name
	 * @param id : The id
	 */
	public void deleteDAO(final String table_name, final Long id) {
		this.sqlDatabase.delete(table_name, "id = ?",
				new String[] { String.valueOf(id) });
	}

	/**
	 * Execute query.
	 * 
	 * @param query : The query
	 */
	public void executeQuery(final String query) {
		try {
			if (this.sqlDatabase.isOpen()) {
				this.sqlDatabase.close();
			}
			this.sqlDatabase = this.dbHelper.getWritableDatabase();
			this.sqlDatabase.execSQL(query);
		} catch (final Exception e) {
			Log.d("DB EXECUTE QUERY ERROR", "DATABASE ERROR " + e);
		}
	}

    public Long insert(String table,
            String nullColumnHack,
            ContentValues values) {
        if (this.sqlDatabase.isOpen()) {
            this.sqlDatabase.close();
        }
        this.sqlDatabase = this.dbHelper.getWritableDatabase();
        return this.sqlDatabase.insert(table, nullColumnHack, values);
    }

    public Integer update(String table,
            ContentValues values,
            String whereClause,
            String[] whereArgs) {
        if (this.sqlDatabase.isOpen()) {
            this.sqlDatabase.close();
        }
        this.sqlDatabase = this.dbHelper.getWritableDatabase();
        return this.sqlDatabase.update(table, values, whereClause, whereArgs);
    }

    public Cursor query(String table,
            String[] columns,
            String selection,
            String[] selectionArgs,
            String groupBy,
            String having,
            String orderBy,
            String limit) {
        if (this.sqlDatabase.isOpen()) {
            this.sqlDatabase.close();
        }
        this.sqlDatabase = this.dbHelper.getWritableDatabase();
        return this.sqlDatabase.query(table,
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
        if (this.sqlDatabase.isOpen()) {
            this.sqlDatabase.close();
        }
        this.sqlDatabase = this.dbHelper.getWritableDatabase();
        return this.sqlDatabase.delete(table, whereClause, whereArgs);
    }

	/**
	 * Insert dao.
	 * 
	 * @param table_name : The table_name
	 * @param createdContentValues : The created content values
	 * @return The long
	 */
	public long insertDAO(final String table_name,
			final ContentValues createdContentValues) {
		return this.sqlDatabase.insert(table_name, null, createdContentValues);
	}

	/**
	 * Select query.
	 * 
	 * @param query : The query
	 * @return The cursor
	 */
	public Cursor selectQuery(final String query) {
		Cursor c1 = null;
		try {
			if (this.sqlDatabase.isOpen()) {
				this.sqlDatabase.close();
			}
			this.sqlDatabase = this.dbHelper.getWritableDatabase();
			c1 = this.sqlDatabase.rawQuery(query, null);
		} catch (final Exception e) {
			Log.d("DB SELECT QUERY ERROR", "DATABASE ERROR " + e);
		}
		return c1;
	}
}
