/***************************************************************************//**
 * @file    FluxActivity.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-01-23
 * @version 0.4
 *
 * Activité d'affichage des flux.
 ******************************************************************************/

package com.rss_pion.network;

/*** INCLUDES *****************************************************************/

import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.rss_pion.beans.Flux;
import com.rss_pion.rss.RSSParser;

/*** MAIN CLASS ***************************************************************/

public class Network {

/***************************************************************************//**
 * Obtention d'un flux depuis le réseau
 * 
 * @param feed  URL du flux
 * 
 * @return      Flux obtenu
 ******************************************************************************/
        public static Flux getFlux(String feed) throws IOException {

            Flux flux;
            URL url;
            HttpURLConnection urlConnection;
            InputStream inStr;
            RSSParser rssParser;
            Bitmap image;

            // Téléchargement du flux
            try {
                url = new URL(feed);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            urlConnection = (HttpURLConnection) url.openConnection();

            try {
                inStr = urlConnection.getInputStream();
            } finally {
                urlConnection.disconnect();
            }

            // Analyse du flux
            rssParser = new RSSParser(inStr);
            rssParser.parse();

            flux = rssParser.getFlux();

            // Téléchargement de l'éventuelle image associée
            try {
                url = new URL(flux.getUrlImage());
            } catch (MalformedURLException e) {
                return flux;
            }

            urlConnection = (HttpURLConnection) url.openConnection();

            try {
                inStr = urlConnection.getInputStream();
            } finally {
                urlConnection.disconnect();
            }

            // Conversion de l'image en bitmap
            image = BitmapFactory.decodeStream(inStr);
            
            flux.setImage(image);

            return flux;
        }
}
