/***************************************************************************
 * @file ImageDAO.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ***************************************************************************/
package com.rss_pion.database.dao;

import android.content.ContentValues;
import android.graphics.Bitmap;

import com.rss_pion.configuration.Constants;
import com.rss_pion.configuration.Tools;
import com.rss_pion.database.dao.abstracts.SerializedObject;

// TODO: Auto-generated Javadoc
/**
 * The Class ImageDAO.
 */
public class ImageDAO extends SerializedObject {

	/** The name of the associated table. */
	public static String nameOfTheAssociatedTable = "IMAGE_IT";

	/** The fields of the associated table. */
	public static String[] fieldsOfTheAssociatedTable = { "bitmap",
			"BLOB NOT NULL" };

	/** The bitmap. */
	private Bitmap bitmap;

	/**
	 * Instantiates a new image dao.
	 */
	public ImageDAO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new image dao.
	 */
	public ImageDAO(final Bitmap image) {
		this.bitmap = image;
	}

	/**
	 * Gets the bitmap.
	 * 
	 * @return The bitmap
	 */
	public Bitmap getBitmap() {
		return this.bitmap;
	}

	/***************************************************************************
	 * @see com.rss_pion.database.dao.abstracts.SerializedObject#insertInTheDataBase()
	 ***************************************************************************/
	@Override
	public Long insertInTheDataBase(final Object... objects)
			throws IllegalAccessException, IllegalArgumentException {
		final ContentValues cv = (new ContentValues());
		cv.put(ImageDAO.fieldsOfTheAssociatedTable[0],
				Tools.getBytes(this.getBitmap()));
		return Constants.sqlHandler.insertDAO(
				ImageDAO.nameOfTheAssociatedTable, cv);
	}

	/**
	 * Sets the image.
	 * 
	 * @param image : The new image
	 */
	public void setImage(final Bitmap image) {
		this.bitmap = image;
	}
}
