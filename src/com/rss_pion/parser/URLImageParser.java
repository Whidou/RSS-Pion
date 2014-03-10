/***************************************************************************//**
 * @file    URLImageParser.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-02-02
 * @version 1.0
 *
 * Traitement des images dans les textes HTML
 ******************************************************************************/

package com.rss_pion.parser;

/*** INCLUDES *****************************************************************/

import java.util.concurrent.ExecutionException;

import com.rss_pion.network.ImageGetterTask;

import android.graphics.drawable.Drawable;
import android.text.Html.ImageGetter;

/*** MAIN CLASS ***************************************************************/

public class URLImageParser implements ImageGetter {

/*** METHODS ******************************************************************/

/***************************************************************************//**
 * @see android.text.Html.ImageGetter#getDrawable(java.lang.String)
 ******************************************************************************/
    public Drawable getDrawable(String source) {

        Drawable drawable;
        ImageGetterTask asyncTask;

        drawable = null;

        // Obtention de l'image
        asyncTask = new ImageGetterTask();
        asyncTask.execute(source);

        try {
            drawable = asyncTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return drawable;
    }
}
