/***************************************************************************
 * @file Guid.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ***************************************************************************/
package com.rss_pion.beans;

import java.util.ArrayList;
import java.util.Iterator;

import android.util.Log;

import com.rss_pion.configuration.Constants;
import com.rss_pion.database.SqlDbHelper;
import com.rss_pion.database.dao.abstracts.SerializedObject;

// TODO: Auto-generated Javadoc
/**
 * The Class Guid.
 */
public class Guid extends SerializedObject {

	/** The name of the associated table. */
	public static String nameOfTheAssociatedTable = "GUID_IT";

	/** The fields of the associated table. */
	public static ArrayList<String[]> fieldsOfTheAssociatedTable;
	static {
		Guid.fieldsOfTheAssociatedTable = new ArrayList<String[]>();
		Guid.fieldsOfTheAssociatedTable.add(new String[] { "isPermaLink",
				"INTEGER NOT NULL" });
		Guid.fieldsOfTheAssociatedTable.add(new String[] { "value",
				"TEXT NOT NULL" });
	}

	/** The is perma link. */
	private boolean isPermaLink;

	/** The value. */
	private String value;

	/**
	 * Instantiates a new guid.
	 */
	public Guid() {
		super();
	}

	/**
	 * Instantiates a new guid.
	 * 
	 * @param isPermaLink : The is perma link
	 * @param value : The value
	 */
	public Guid(final boolean isPermaLink, final String value) {
		super();
		this.isPermaLink = isPermaLink;
		this.value = value;
	}

	/**
	 * Gets the checks if is perma link.
	 * 
	 * @return The checks if is perma link
	 */
	public boolean getIsPermaLink() {
		return this.isPermaLink;
	}

	/**
	 * Gets the value.
	 * 
	 * @return The value
	 */
	public String getValue() {
		return this.value;
	}

	/***************************************************************************
	 * @see com.rss_pion.database.dao.abstracts.SerializedObject#insertInTheDataBase()
	 ***************************************************************************/
	@Override
	public Long insertInTheDataBase(final Object... objects)
			throws IllegalAccessException, IllegalArgumentException {
		String names = "";
		final Iterator<String[]> it = Guid.fieldsOfTheAssociatedTable
				.iterator();
		while (it.hasNext()) {
			names += it.next()[0] + (it.hasNext() ? ", " : ")");
		}
		Constants.sqlHandler.executeQuery("INSERT INTO "
				+ Guid.nameOfTheAssociatedTable + " (" + names + " VALUES ("
				+ this.getIsPermaLink() + ", '" + this.getValue() + "');");
		Log.d("GUID ADDED", this.toString());
		return SqlDbHelper.lastInsertId(Guid.nameOfTheAssociatedTable);
	}

	/**
	 * Sets the checks if is perma link.
	 * 
	 * @param isPermaLink : The new checks if is perma link
	 */
	public void setIsPermaLink(final boolean isPermaLink) {
		this.isPermaLink = isPermaLink;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value : The new value
	 */
	public void setValue(final String value) {
		this.value = value;
	}

	/***************************************************************************
	 * @see java.lang.Object#toString()
	 ***************************************************************************/
	@Override
	public String toString() {
		return "Guid [isPermaLink=" + this.isPermaLink + ", value="
				+ this.value + "]";
	}
}
