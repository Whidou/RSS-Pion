/***************************************************************************//**
 * @file    RSSParser.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-01-26
 * @version 0.1
 *
 * Interpréteur pour flux RSS.
 ******************************************************************************/

package com.rss_pion.rss;

/*** INCLUDES *****************************************************************/

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.rss_pion.beans.Article;
import com.rss_pion.beans.Flux;

/*** MAIN CLASS ***************************************************************/

public class RSSParser extends DefaultHandler {

/*** ATTRIBUTES ***************************************************************/

    //! Flux d'entrée du code XML
    private InputStream inStr;

    //! Flux RSS créé
    private Flux flux;

    //! Variable temporaire pour la définition des articles
    private Article article;

    //! Buffer de lecture
    private StringBuilder text;

/*** METHODS ******************************************************************/

/***************************************************************************//**
 * Constructeur
 * 
 * @param stream    Flux d'entrée du code XML (en provenance du net)
 ******************************************************************************/
    public RSSParser(InputStream stream) {
        this.inStr = stream;
        this.flux = new Flux();
        this.article = null;
        this.text = new StringBuilder();
    }

/***************************************************************************//**
 * Lance l'analyse du flux
 ******************************************************************************/
    public void parse() throws IOException {

        SAXParserFactory parserFactory;
        SAXParser parser;

        // Création du créateur de parseur
        parserFactory = SAXParserFactory.newInstance();

        // Création du parseur
        try {
            parser = parserFactory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return;
        } catch (SAXException e) {
            e.printStackTrace();
            return;
        }

        // Analyse du flux
        try {
            parser.parse(this.inStr, this);
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

/***************************************************************************//**
 * Retourne le flux obtenu.
 * 
 * @return  Flux obtenu
 ******************************************************************************/
    public Flux getFlux() {
        return (this.flux);
    }

/***************************************************************************//**
 * Callback aux balises ouvrantes
 * 
 * @param uri           
 * @param localName     
 * @param qName         Nom de la balise.
 * @param attributes    Attributs associés.
 ******************************************************************************/
    @Override
    public void startElement(   String uri,
                                String localName,
                                String qName,
                                Attributes attributes) {

        // À l'ouverture d'une balise d'article
        if (qName.equalsIgnoreCase("item")) {

            // Création un nouvel article temporaire
            this.article = new Article();

            // Ajout de l'article au flux
            this.flux.addArticle(this.article);
        }
    }

/***************************************************************************//**
 * Callback aux balises fermantes
 * 
 * @param uri           
 * @param localName     
 * @param qName         Nom de la balise.
 ******************************************************************************/
    @Override
    public void endElement(String uri, String localName, String qName) {
        
        String value = this.text.toString().trim();

        // Fin de l'article
        if (qName.equalsIgnoreCase("item")) {
            this.article = null;
        }

        else if (qName.equalsIgnoreCase("title")) {
            if (this.article != null) {
                this.article.setTitle(value);
            }
            else {
                this.flux.setTitle(value);
            }
        }

        else if (qName.equalsIgnoreCase("link")) {
            if (this.article != null) {
                this.article.setLink(value);
            }
            else {
                this.flux.setLink(value);
            }
        }

        else if (qName.equalsIgnoreCase("description")) {
            if (this.article != null) {
                this.article.setDescription(value);
            }
            else {
                this.flux.setDescription(value);
            }
        }

        else if (qName.equalsIgnoreCase("category")) {
            if (this.article != null) {
                this.article.addCategory(value);
            }
            this.flux.addCategory(value);
        }

        else if (qName.equalsIgnoreCase("url")) {
            this.flux.setUrlImage(value);
        }

        else if (qName.equalsIgnoreCase("language")) {
            this.flux.setLanguage(value);
        }

        else if (qName.equalsIgnoreCase("generator")) {
            this.flux.setGenerator(value);
        }

        else if (qName.equalsIgnoreCase("copyright")) {
            this.flux.setCopyright(value);
        }

        else if (qName.equalsIgnoreCase("pubDate")) {
            if (this.article != null) {
                this.article.setPubDate(value);
            }
        }

        // Vidage du buffer
        this.text.setLength(0);
    }


/***************************************************************************//**
 * Callback à l'arrivée de caractères par le flux
 * 
 * @param ch        Données
 * @param start     Position
 * @param length    Longueur
 ******************************************************************************/
    @Override
    public void characters(char[] ch, int start, int length) {
        this.text.append(ch, start, length);
    }
}