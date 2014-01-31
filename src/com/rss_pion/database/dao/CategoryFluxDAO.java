/***************************************************************************
 * @file CategoryFluxDAO.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ***************************************************************************/
package com.rss_pion.database.dao;

import java.util.ArrayList;
import java.util.Iterator;

import android.database.Cursor;
import android.util.Log;

import com.rss_pion.configuration.Constants;
import com.rss_pion.database.SqlDbHelper;
import com.rss_pion.database.dao.abstracts.SerializedObject;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoryFluxDAO.
 */
public class CategoryFluxDAO extends SerializedObject {

	/** The name of the associated table. */
	public static String nameOfTheAssociatedTable = "CATEGORY_FLUX_IT";

	/** The fields of the associated table. */
	public static ArrayList<String[]> fieldsOfTheAssociatedTable;
	static {
		CategoryFluxDAO.fieldsOfTheAssociatedTable = new ArrayList<String[]>();
		CategoryFluxDAO.fieldsOfTheAssociatedTable.add(new String[] {
				"category", "TEXT NOT NULL" });
		CategoryFluxDAO.fieldsOfTheAssociatedTable.add(new String[] {
				"idFather", "INTEGER NOT NULL" });
	}

	/**
	 * Delete category in the data base.
	 * 
	 * @param id : The id
	 */
	public static void deleteCategoryInTheDataBase(final Long id) {
		final String query = "SELECT * FROM "
				+ CategoryFluxDAO.nameOfTheAssociatedTable + " WHERE id = "
				+ id;
		final Cursor c1 = Constants.sqlHandler.selectQuery(query);
		if ((c1 != null) && (c1.getCount() != 0)) {
			if (c1.moveToFirst()) {
				do {
					Constants.sqlHandler.deleteDAO(
							CategoryFluxDAO.nameOfTheAssociatedTable, id);
				} while (c1.moveToNext());
			}
		}
		c1.close();
	}

	/** The category. */
	private String category;

	/** The id. */
	private Long id;

	/** The id father. */
	private Long idFather;

	/**
	 * Instantiates a new category dao.
	 */
	public CategoryFluxDAO() {
		super();
	}

	/**
	 * Gets the category.
	 * 
	 * @return The category
	 */
	public String getCategory() {
		return this.category;
	}

	/**
	 * Gets the id.
	 * 
	 * @return The id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Gets the id father.
	 * 
	 * @return The id father
	 */
	public Long getIdFather() {
		return this.idFather;
	}

	/***************************************************************************
	 * @see com.rss_pion.database.dao.abstracts.SerializedObject#insertInTheDataBase()
	 ***************************************************************************/
	@Override
	public Long insertInTheDataBase(final Object... objects)
			throws IllegalAccessException, IllegalArgumentException {
		String names = "";
		final Iterator<String[]> it = CategoryFluxDAO.fieldsOfTheAssociatedTable
				.iterator();
		while (it.hasNext()) {
			names += it.next()[0] + (it.hasNext() ? ", " : ")");
		}
		Constants.sqlHandler.executeQuery("INSERT INTO "
				+ CategoryFluxDAO.nameOfTheAssociatedTable + " (" + names
				+ " VALUES ('" + this.getCategory() + "', "
				+ this.getIdFather() + ");");
		Log.d("CATEGORY FLUX ADDED", this.toString());
		return SqlDbHelper
				.lastInsertId(CategoryFluxDAO.nameOfTheAssociatedTable);
	}

	/**
	 * Sets the category.
	 * 
	 * @param category : The new category
	 */
	public void setCategory(final String category) {
		this.category = category;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id : The new id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Sets the id father.
	 * 
	 * @param idFather : The new id father
	 */
	public void setIdFather(final Long idFather) {
		this.idFather = idFather;
	}

	/***************************************************************************
	 * @see java.lang.Object#toString()
	 ***************************************************************************/
	@Override
	public String toString() {
		return "CategoryFluxDAO [category=" + this.category + ", id=" + this.id
				+ ", idFather=" + this.idFather + "]";
	}
}
