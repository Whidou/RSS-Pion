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

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.rss_pion.beans.Article;
import com.rss_pion.beans.Flux;
import com.rss_pion.beans.ImageRSS;
import com.rss_pion.configuration.Constants;
import com.rss_pion.database.dao.FluxDAO;
import com.rss_pion.rss.RSSParser;

/*** MAIN CLASS ***************************************************************/

public class NetworkUpdateTask extends AsyncTask<Void, Integer, Void> {

    NotificationManager notiManager;
    Notification noti;

private ImageRSS getImage(String url) {

    URL urlObj;
    HttpURLConnection connection;
    InputStream inStr;
    Bitmap image;

    try {
        urlObj = new URL(url);
    } catch (MalformedURLException e) {
        return null;
    }

    try {
        connection = (HttpURLConnection) urlObj.openConnection();
    } catch (IOException e) {
        return null;
    }

    try {
        inStr = connection.getInputStream();
    } catch (IOException e) {
        connection.disconnect();
        return null;
    }

    // Conversion de l'image en bitmap
    image = BitmapFactory.decodeStream(inStr);
    
    connection.disconnect();

    return new ImageRSS(image);
}

/***************************************************************************//**
 * Obtention d'un flux depuis le réseau
 * 
 * @param feed  URL du flux
 * 
 * @return      Flux obtenu
 ******************************************************************************/
    private Flux getFlux(String feed) throws IOException {

        Flux flux;
        URL url;
        HttpURLConnection urlConnection;
        InputStream inStr;
        RSSParser rssParser;

        // Téléchargement du flux
        try {
            url = new URL(feed);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }

        urlConnection = (HttpURLConnection) url.openConnection();

        inStr = urlConnection.getInputStream();

        // Analyse du flux
        rssParser = new RSSParser(inStr);
        rssParser.parse();

        flux = rssParser.getFlux();

        urlConnection.disconnect();

        // Téléchargement de l'éventuelle image associée
        flux.setImage(this.getImage(flux.getUrlImage()));

        return flux;
    }

/***************************************************************************//**
 * Mise à jour d'un flux depuis le réseau
 * 
 * @param flux  Object flux à mettre à jour
 ******************************************************************************/
    private void updateFlux(Flux flux) throws IOException {

        Flux update;
        Long lastUpdateTimestamp = flux.getPubDate();

        // Obtention de la dernière version du flux
        update = this.getFlux(flux.getFeed());

        if (update == null) {
            return;
        }

        // Vérification de la date de dernière mise à jour
        if (update.getPubDate() <= lastUpdateTimestamp) {
            return;
        }

        // Mise à jour des attributs du flux
        flux.setCategories(update.getCategories());
        flux.setCloud(update.getCloud());
        flux.setCopyright(update.getCopyright());
        flux.setDescription(update.getDescription());
        flux.setDocs(update.getDocs());
        flux.setGenerator(update.getGenerator());
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

        FluxDAO.insertFluxIntoDB(flux);
    }
    
    @SuppressWarnings("deprecation")
    @Override
    protected void onPreExecute () {

        Context context;

        Log.e("NetworkUpdateTask", "Starting update.");
        
        context = Constants.adapterOfFlux.getContext();

        this.noti = new Notification.Builder(context)
                .setContentTitle("RSS Pion")
                .setContentText("Updating the RSS feeds...")
                .setSmallIcon(android.R.drawable.stat_notify_sync)
                .setOngoing(true)
                .getNotification();

        this.notiManager = (NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE);

        this.notiManager.notify("NetworkUpdate", 0, this.noti);
    }

    @Override
    protected Void doInBackground(Void... params) {

        int i, nFlux;

        i = 0;
        nFlux = Constants.listOfFlux.size();

        for (Flux flux : Constants.listOfFlux) {
            try {
                this.updateFlux(flux);
            } catch (IOException e) {
                e.printStackTrace();
            }
            publishProgress((int) ((i / (float) nFlux) * 100));
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        
        Constants.listOfFlux.clear();

        for (Flux flux : FluxDAO.getFluxFromDB()) {
            if (flux != null) {
                Constants.listOfFlux.add(flux);
            }
        }

        Constants.adapterOfFlux.notifyDataSetChanged();
        
        this.notiManager.cancel("NetworkUpdate", 0);

        Log.e("NetworkUpdateTask", "Ending update.");
    }
}
