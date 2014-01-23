/***************************************************************************
 * @file NeedTranslationToBeSerializedObject.java
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
 * The Class NeedTranslationToBeSerializedObject.
 */
public abstract class NeedTranslationToBeSerializedObject {

	/**
	 * Instantiates a new need translation to be serialized object.
	 */
	public NeedTranslationToBeSerializedObject() {
	}

	/**
	 * Translate dao to object.
	 *
	 * @param object : The object
	 */
	public abstract void translateDaoToObject(Object object);

	/**
	 * Translate object to dao.
	 *
	 * @param ids : The ids
	 * @return The object
	 * @throws IllegalAccessException : The illegal access exception
	 * @throws IllegalArgumentException : The illegal argument exception
	 */
	public abstract Object translateObjectToDao(Long... ids)
			throws IllegalAccessException, IllegalArgumentException;

}
