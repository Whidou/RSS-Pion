/***************************************************************************//**
 * @file    FluxActivity.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-02-02
 * @version 1.0
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

import com.rss_pion.beans.Article;
import com.rss_pion.beans.Flux;
import com.rss_pion.database.dao.ImageDAO;
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
        
        flux.setImage(new ImageDAO(image));

        return flux;
    }

/***************************************************************************//**
 * Mise à jour d'un flux depuis le réseau
 * 
 * @param flux  Object flux à mettre à jour
 ******************************************************************************/
    public static void updateFlux(Flux flux) throws IOException {

        Flux update;
        Long lastUpdateTimestamp = flux.getLastBuildDate();

        // Obtention de la dernière version du flux
        update = Network.getFlux(flux.getFeed());

        // Vérification de la date de dernière mise à jour
        if (update.getLastBuildDate() <= lastUpdateTimestamp) {
            return;
        }

        // Mise à jour des attributs du flux
        flux.setCategories(update.getCategories());
        flux.setCloud(update.getCloud());
        flux.setCopyright(update.getCopyright());
        flux.setDescription(update.getDescription());
        flux.setDocs(update.getDocs());
        flux.setGenerator(update.getGenerator());
        flux.setId(update.getId());
        flux.setImage(update.getImage());
        flux.setLanguage(update.getLanguage());
        flux.setLastBuildDate(lastUpdateTimestamp);
        flux.setLink(update.getLink());
        flux.setManagingEditor(update.getManagingEditor());
        flux.setPubDate(update.getPubDate());
        flux.setRating(update.getRating());
        flux.setSkipDays(update.getSkipDays());
        flux.setSkipHours(update.getSkipHours());
        flux.setTextInput(update.getTextInput());
        flux.setTitle(update.getTitle());
        flux.setTtl(update.getTtl());
        flux.setUrlImage(update.getUrlImage());
        flux.setWebMaster(update.getWebMaster());

        // Ajout des nouveaux articles
        for (Article article : update.getArticles()) {
            if (article.getPubDate() > lastUpdateTimestamp) {
                flux.addArticle(article);
            }
        }
    }
}
