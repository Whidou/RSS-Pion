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

import android.graphics.Bitmap;

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

    public Long getId() {
        return id;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
