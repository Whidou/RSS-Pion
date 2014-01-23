/***************************************************************************
 * @file Tools.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ***************************************************************************/
package com.rss_pion.configuration;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class Tools.
 */
public class Tools {

	/**
	 * Gets the bytes.
	 * 
	 * @param bitmap : The bitmap
	 * @return The bytes
	 */
	public static byte[] getBytes(final Bitmap bitmap) {
		final ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.PNG, 0, stream);
		return stream.toByteArray();
	}

	/**
	 * Instantiates a new tools.
	 */
	public Tools() {
		// TODO Auto-generated constructor stub
	}
}
