/***************************************************************************//**
 * @file    ImageRSS.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-02-08
 * @version 1.0
 *
 * Image illustrant un flux.
 ******************************************************************************/

package com.rss_pion.beans;

/*** INCLUDES *****************************************************************/

import java.io.FileNotFoundException;

import com.rss_pion.configuration.Constants;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;

/*** MAIN CLASS ***************************************************************/

public class ImageRSS {

/*** ATTRIBUTES ***************************************************************/

    //! Numéro d'entrée dans la BDD
    private Long id;

    //! URL d'origine
    private String url;

    //! Chemin de stockage local
    private String path;

/*** METHODS ******************************************************************/

    public ImageRSS() {
        this.url = null;
        this.path = null;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return this.url;
    }

    public String getPath() {
        return this.path;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPath(String path) {
        this.path = path;
    }

/***************************************************************************//**
 * Retourne l'image sous forme affichable
 * 
 * @return  Surface d'image
 ******************************************************************************/
    public BitmapDrawable getDrawable() {
        Context context = Constants.adapterOfFlux.getContext();
        try {
            return new BitmapDrawable(
                    context.getResources(),
                    context.openFileInput(this.path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
