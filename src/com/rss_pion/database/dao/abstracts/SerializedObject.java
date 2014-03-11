/***************************************************************************
 * @file SerializedObject.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ***************************************************************************/
package com.rss_pion.database.dao.abstracts;

// TODO: Auto-generated Javadoc
/**
 * The Class SerializedObject.
 */
public abstract class SerializedObject {

	/**
	 * Instantiates a new serialized object.
	 */
	public SerializedObject() {
	}

	/**
	 * Insert in the data base.
	 * 
	 * @return The long
	 * @throws IllegalAccessException : The illegal access exception
	 * @throws IllegalArgumentException : The illegal argument exception
	 */
	public abstract Long insertInTheDataBase(Object... objects)
			throws IllegalAccessException, IllegalArgumentException;

}
