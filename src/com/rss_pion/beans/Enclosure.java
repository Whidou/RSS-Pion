/***************************************************************************
 * @file Enclosure.java
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
 * The Class Enclosure.
 */
public class Enclosure extends SerializedObject {

	/** The name of the associated table. */
	public static String nameOfTheAssociatedTable = "ENCLOSURE_IT";

	/** The fields of the associated table. */
	public static ArrayList<String[]> fieldsOfTheAssociatedTable;
	static {
		Enclosure.fieldsOfTheAssociatedTable = new ArrayList<String[]>();
		Enclosure.fieldsOfTheAssociatedTable.add(new String[] { "length",
				"INTEGER NOT NULL" });
		Enclosure.fieldsOfTheAssociatedTable.add(new String[] { "type",
				"TEXT NOT NULL" });
		Enclosure.fieldsOfTheAssociatedTable.add(new String[] { "url",
				"TEXT NOT NULL" });
	}

	/** The url. */
	private String url;

	/** The length. */
	private Long length;

	/** The type. */
	private String type;

	/**
	 * Instantiates a new enclosure.
	 */
	public Enclosure() {
		super();
	}

	/**
	 * Instantiates a new enclosure.
	 * 
	 * @param url : The url
	 * @param length : The length
	 * @param type : The type
	 */
	public Enclosure(final String url, final Long length, final String type) {
		super();
		this.url = url;
		this.length = length;
		this.type = type;
	}

	/**
	 * Gets the length.
	 * 
	 * @return The length
	 */
	public Long getLength() {
		return this.length;
	}

	/**
	 * Gets the type.
	 * 
	 * @return The type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Gets the url.
	 * 
	 * @return The url
	 */
	public String getUrl() {
		return this.url;
	}

	/***************************************************************************
	 * @see com.rss_pion.database.dao.abstracts.SerializedObject#insertInTheDataBase()
	 ***************************************************************************/
	@Override
	public Long insertInTheDataBase(final Object... objects)
			throws IllegalAccessException, IllegalArgumentException {
		String names = "";
		final Iterator<String[]> it = Enclosure.fieldsOfTheAssociatedTable
				.iterator();
		while (it.hasNext()) {
			names += it.next()[0] + (it.hasNext() ? ", " : ")");
		}
		Constants.sqlHandler.executeQuery("INSERT INTO "
				+ Enclosure.nameOfTheAssociatedTable + " (" + names
				+ " VALUES (" + this.getLength() + ", '" + this.getType()
				+ "', '" + this.getUrl() + "');");
		Log.d("ENCLOSURE ADDED", this.toString());
		return SqlDbHelper.lastInsertId(Enclosure.nameOfTheAssociatedTable);
	}

	/**
	 * Sets the length.
	 * 
	 * @param length : The new length
	 */
	public void setLength(final Long length) {
		this.length = length;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type : The new type
	 */
	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * Sets the url.
	 * 
	 * @param url : The new url
	 */
	public void setUrl(final String url) {
		this.url = url;
	}

	/***************************************************************************
	 * @see java.lang.Object#toString()
	 ***************************************************************************/
	@Override
	public String toString() {
		return "Enclosure [url=" + this.url + ", length=" + this.length
				+ ", type=" + this.type + "]";
	}
}
