/***************************************************************************
 * @file    ImageRSS.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-02-08
 * @version 0.1
 *
 * Image illustrant un flux ou un article.
 ***************************************************************************/

package com.rss_pion.beans;

/*** INCLUDES *****************************************************************/

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/*** MAIN CLASS ***************************************************************/

public class ImageRSS {

/*** ATTRIBUTES ***************************************************************/

    //! Numéro d'entrée dans la BDD
    private Long id;

    //! Image
    private Bitmap bitmap;

/*** METHODS ******************************************************************/

    public ImageRSS() {
        this.bitmap = null;
    }

    public ImageRSS(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public byte[] getBytes() {

        final ByteArrayOutputStream stream;

        stream = new ByteArrayOutputStream();
        this.bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);

        return stream.toByteArray();
    }

    public Long getId() {
        return id;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setBytes(byte[] bytes) {

        final ByteArrayInputStream stream;

        stream = new ByteArrayInputStream(bytes);
        this.bitmap = BitmapFactory.decodeStream(stream);
    }

    public void setId(Long id) {
        this.id = id;
    }
}
