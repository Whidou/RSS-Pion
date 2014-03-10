/***************************************************************************//**
 * @file    ImageGetterTask.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-02-02
 * @version 1.0
 *
 * Tâche d'obtention d'image depuis le réseau.
 ******************************************************************************/

package com.rss_pion.network;

/*** INCLUDES *****************************************************************/

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

/*** MAIN CLASS ***************************************************************/

public class ImageGetterTask extends AsyncTask<String, Void, Drawable>  {

/*** ATTRIBUTES ***************************************************************/

    Drawable drawable;

/*** METHODS ******************************************************************/

/***************************************************************************//**
 * Obtention d'une image depuis le réseau
 *
 * @param url   URL de l'image
 *
 * @return     Image obtenue
 ******************************************************************************/
    static protected BitmapDrawable getImage(String url) {

        URL urlObj;
        HttpURLConnection connection;
        InputStream inStr;
        BitmapDrawable image;

        // Validation de l'URL
        try {
            urlObj = new URL(url);
        } catch (MalformedURLException e) {
            return null;
        }

        // Connexion
        try {
            connection = (HttpURLConnection) urlObj.openConnection();
        } catch (IOException e) {
            return null;
        }

        // Réception
        try {
            inStr = connection.getInputStream();
        } catch (IOException e) {
            connection.disconnect();
            return null;
        }

        // Conversion de l'image en bitmap
        image = (BitmapDrawable) BitmapDrawable.createFromStream(inStr, "src");

        // Déconnexion
        connection.disconnect();

        return image;
    }

/***************************************************************************//**
 * Tâche d'obtention de l'image
 *
 * @see android.os.AsyncTask#doInBackground(Params...)
 ******************************************************************************/
    @Override
    protected BitmapDrawable doInBackground(String... params) {

        BitmapDrawable drawable;

        drawable = getImage(params[0]);

        drawable.setBounds(
                0,
                0,
                0 + drawable.getIntrinsicWidth(),
                0 + drawable.getIntrinsicHeight());

        return drawable;
    }
}
