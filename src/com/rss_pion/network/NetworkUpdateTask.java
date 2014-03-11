/***************************************************************************//**
 * @file    NetworkUpdateTask.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-02-02
 * @version 1.1
 *
 * Tâche de synchronisation réseau.
 ******************************************************************************/

package com.rss_pion.network;

/*** INCLUDES *****************************************************************/

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.rss_pion.beans.Article;
import com.rss_pion.beans.Flux;
import com.rss_pion.beans.ImageRSS;
import com.rss_pion.configuration.Constants;
import com.rss_pion.database.dao.FluxDAO;
import com.rss_pion.parser.RSSParser;

/*** MAIN CLASS ***************************************************************/

public class NetworkUpdateTask extends AsyncTask<Void, Integer, Void> {

/*** ATTRIBUTES ***************************************************************/

    //! Gestionnaire de notifications
    NotificationManager notiManager;

    //! Notification de travail
    Notification noti;

/*** METHODS ******************************************************************/

/***************************************************************************//**
 * Obtention d'une image depuis le réseau
 *
 * @param url   URL de l'image
 *
 * @return     Image obtenue
 ******************************************************************************/
    static protected ImageRSS getImage(String url) {

        URL urlObj;
        HttpURLConnection connection;
        InputStream inStr;
        FileOutputStream outStr;
        Context context;
        String path;
        ImageRSS image;
        byte[] buffer;
        int read;

        // Validation de l'URL
        try {
            urlObj = new URL(url);
        } catch (MalformedURLException e) {
            return null;
        }
        
        Log.d("NUT", "URL: "+url);

        // Obtention du contexte
        context = Constants.adapterOfFlux.getContext();

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

        // Création du nom de fichier
        path = "image_"+urlObj.hashCode()+urlObj.getFile().replace("/", "_");

        // Sauvegarde de l'image
        try {
            outStr = context.openFileOutput(path, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        buffer = new byte[1024];
        do {
            try {
                read = inStr.read(buffer, 0, 1024);
                outStr.write(buffer, 0, 1024);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        } while (read >= 1024);
        try {
            outStr.close();
            inStr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Log.d("NUT", "Image sauvegardée: "+path);

        // Déconnexion
        connection.disconnect();

        // Création de l'image
        image = new ImageRSS();
        image.setUrl(url);
        image.setPath(path);
        
        Log.d("NUT", "Image: "+image);

        return image;
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

        // Validation URL
        try {
            url = new URL(feed);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }

        // Connexion
        urlConnection = (HttpURLConnection) url.openConnection();

        // Réception
        inStr = urlConnection.getInputStream();

        // Analyse du flux RSS
        rssParser = new RSSParser(inStr);
        rssParser.parse();

        // Obtention du flux RSS analysé
        flux = rssParser.getFlux();

        // Déconnexion
        urlConnection.disconnect();

        // Téléchargement de l'éventuelle image associée
        flux.setImage(getImage(flux.getUrlImage()));
        Log.d("NUT", "Image: "+flux.getImage());

        return flux;
    }

/***************************************************************************//**
 * Mise à jour d'un flux depuis le réseau
 *
 * @param flux  Object flux à mettre à jour
 ******************************************************************************/
    private void updateFlux(Flux flux) throws IOException {

        Flux update;
        Long lastBuildDate, newBuildDate, articlePubDate;

        // Obtention de la dernière version du flux
        update = this.getFlux(flux.getFeed());

        // En cas d'erreur, pas de modification
        if (update == null) {
            Log.e("NetworkUpdateTask", "Erreur récupération flux.");
            return;
        }

        lastBuildDate = flux.getLastBuildDate();
        newBuildDate = update.getLastBuildDate();
        if (newBuildDate == 0) {
            newBuildDate = (new Date()).getTime();
        }

        if (newBuildDate <= lastBuildDate) {
            return;
        }

        // Mise à jour des attributs du flux
        flux.setCategories(update.getCategories());
        flux.setCloud(update.getCloud());
        flux.setCopyright(update.getCopyright());
        flux.setDescription(update.getDescription());
        flux.setDocs(update.getDocs());
        flux.setGenerator(update.getGenerator());
        flux.setImage(update.getImage());
        Log.d("NUTUF", "Image: "+flux.getImage());
        flux.setLanguage(update.getLanguage());
        flux.setLastBuildDate(newBuildDate);
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
            articlePubDate = article.getPubDate();
            if (articlePubDate == 0) {
                articlePubDate = newBuildDate;
            }
            if (articlePubDate > lastBuildDate) {
                flux.addArticle(article);
            }
        }

        // Mise à jour de la BDD
        FluxDAO.insertFluxIntoDB(flux);
    }

/***************************************************************************//**
 * @see android.os.AsyncTask#onPreExecute()
 ******************************************************************************/
    @SuppressWarnings("deprecation")
    @Override
    protected void onPreExecute () {

        Context context;

        Log.d("NetworkUpdateTask", "Starting update.");

        // Obtention du conexte
        context = Constants.adapterOfFlux.getContext();

        // Création de la notification
        // (ancienne méthode pour compatibilité avec les vielles APIs)
        this.noti = new Notification.Builder(context)
                .setContentTitle("RSS Pion")
                .setContentText("Updating the RSS feeds...")
                .setSmallIcon(android.R.drawable.stat_notify_sync)
                .setOngoing(true)
                .getNotification();

        // Obtention du gestionnaire de notifications depuis le contexte
        this.notiManager = (NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE);

        // Affichage de la notification
        this.notiManager.notify("NetworkUpdate", 0, this.noti);
    }

/***************************************************************************//**
 * Tâche de mise à jour des flux RSS
 *
 * @see android.os.AsyncTask#doInBackground(Params...)
 ******************************************************************************/
    @Override
    protected Void doInBackground(Void... params) {

        int i, nFlux;

        i = 0;
        nFlux = Constants.listOfFlux.size();

        // Mise à jour de chaque flux un par un
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

/***************************************************************************//**
 * @see android.os.AsyncTask#onPostExecute(Result)
 ******************************************************************************/
    @Override
    protected void onPostExecute(Void result) {

        // Reconstruction de la liste des flux à partir de la BDD
        Constants.listOfFlux.clear();
        for (Flux flux : FluxDAO.getFluxFromDB()) {
            if (flux != null) {
                Constants.listOfFlux.add(flux);
            }
        }

        // Mise à jour de l'affichage
        Constants.adapterOfFlux.notifyDataSetChanged();

        // Suppression de la notification
        this.notiManager.cancel("NetworkUpdate", 0);

        Log.d("NetworkUpdateTask", "Ending update.");
    }
}
