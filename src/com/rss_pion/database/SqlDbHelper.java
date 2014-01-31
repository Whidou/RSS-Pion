/***************************************************************************
 * @file SqlDbHelper.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ***************************************************************************/
package com.rss_pion.database;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.rss_pion.beans.Cloud;
import com.rss_pion.beans.Enclosure;
import com.rss_pion.beans.Guid;
import com.rss_pion.configuration.Constants;
import com.rss_pion.database.dao.ArticleDAO;
import com.rss_pion.database.dao.CategoryArticleDAO;
import com.rss_pion.database.dao.CategoryFluxDAO;
import com.rss_pion.database.dao.FluxDAO;
import com.rss_pion.database.dao.ImageDAO;
import com.rss_pion.database.dao.TextInputDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class SqlDbHelper.
 */
public class SqlDbHelper extends SQLiteOpenHelper {

	/**
	 * Last insert id.
	 * 
	 * @param table_name : The table_name
	 * @return The long
	 */
	public static Long lastInsertId(final String table_name) {
		final String query = "SELECT ROWID FROM " + table_name
				+ " ORDER BY ROWID DESC LIMIT 1;";
		final Cursor c = Constants.sqlHandler.selectQuery(query);
		if ((c != null) && c.moveToFirst()) {
			return c.getLong(0);
		} else {
			return 0l;
		}
	}

	/**
	 * Instantiates a new sql db helper.
	 * 
	 * @param context : The context
	 * @param name : The name
	 * @param factory : The factory
	 * @param version : The version
	 */
	public SqlDbHelper(final Context context, final String name,
			final CursorFactory factory, final int version) {
		super(context, name, factory, version);
	}

	/**
	 * Creates the table.
	 * 
	 * @param table_name : The table_name
	 * @param fields : The fields
	 * @return The string
	 */
	private String createTable(final String table_name,
			final ArrayList<String[]> fields) {
		String out = "CREATE TABLE " + table_name
				+ " (id INTEGER PRIMARY KEY AUTOINCREMENT, ";
		final Iterator<String[]> it = fields.iterator();
		while (it.hasNext()) {
			final String[] field = it.next();
			if (!field[0].equals("id")) {
				out += field[0] + " " + field[1] + (it.hasNext() ? ", " : ");");
			}
		}
		return out;
	}

	/**
	 * Drop table.
	 * 
	 * @param table_name : The table_name
	 * @return The string
	 */
	private String dropTable(final String table_name) {
		return "DROP TABLE IF EXISTS " + table_name + ";";
	}

	/***************************************************************************
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 ***************************************************************************/
	@Override
	public void onCreate(final SQLiteDatabase db) {
		final ArrayList<String[]> arrayList = new ArrayList<String[]>();
		arrayList.add(ImageDAO.fieldsOfTheAssociatedTable);
		db.execSQL(this.createTable(ImageDAO.nameOfTheAssociatedTable,
				arrayList));
		db.execSQL(this.createTable(FluxDAO.nameOfTheAssociatedTable,
				FluxDAO.fieldsOfTheAssociatedTable));
		db.execSQL(this.createTable(ArticleDAO.nameOfTheAssociatedTable,
				ArticleDAO.fieldsOfTheAssociatedTable));
		db.execSQL(this.createTable(Cloud.nameOfTheAssociatedTable,
				Cloud.fieldsOfTheAssociatedTable));
		db.execSQL(this.createTable(Enclosure.nameOfTheAssociatedTable,
				Enclosure.fieldsOfTheAssociatedTable));
		db.execSQL(this.createTable(Guid.nameOfTheAssociatedTable,
				Guid.fieldsOfTheAssociatedTable));
		db.execSQL(this.createTable(TextInputDAO.nameOfTheAssociatedTable,
				TextInputDAO.fieldsOfTheAssociatedTable));
		db.execSQL(this.createTable(
				CategoryArticleDAO.nameOfTheAssociatedTable,
				CategoryArticleDAO.fieldsOfTheAssociatedTable));
		db.execSQL(this.createTable(CategoryFluxDAO.nameOfTheAssociatedTable,
				CategoryFluxDAO.fieldsOfTheAssociatedTable));
	}

	/***************************************************************************
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase,
	 *      int, int)
	 ***************************************************************************/
	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion,
			final int newVersion) {
		db.execSQL(this.dropTable(ImageDAO.nameOfTheAssociatedTable));
		db.execSQL(this.dropTable(FluxDAO.nameOfTheAssociatedTable));
		db.execSQL(this.dropTable(ArticleDAO.nameOfTheAssociatedTable));
		db.execSQL(this.dropTable(Cloud.nameOfTheAssociatedTable));
		db.execSQL(this.dropTable(Enclosure.nameOfTheAssociatedTable));
		db.execSQL(this.dropTable(Guid.nameOfTheAssociatedTable));
		db.execSQL(this.dropTable(TextInputDAO.nameOfTheAssociatedTable));
		db.execSQL(this.dropTable(CategoryArticleDAO.nameOfTheAssociatedTable));
		db.execSQL(this.dropTable(CategoryFluxDAO.nameOfTheAssociatedTable));
		this.onCreate(db);
	}
}