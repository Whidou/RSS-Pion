/***************************************************************************//**
 * @file    RSSParser.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-02-02
 * @version 1.3
 *
 * Interpréteur pour flux RSS.
 ******************************************************************************/

package com.rss_pion.parser;

/*** INCLUDES *****************************************************************/

import com.rss_pion.beans.Article;
import com.rss_pion.beans.Category;
import com.rss_pion.beans.Flux;
import com.rss_pion.beans.Guid;
import com.rss_pion.beans.TextInput;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/*** MAIN CLASS ***************************************************************/

public class RSSParser extends DefaultHandler {

/*** ATTRIBUTES ***************************************************************/

    //! Flux d'entrée du code XML
    private InputStream inStr;

    //! Flux RSS créé
    private Flux flux;

    //! Variable temporaire pour la définition des articles
    private Article article;

    //! Variable temporaire pour la définition des text inputs
    private TextInput textInput;

    //! Buffer de lecture
    private StringBuilder text;

    //! Format des dates
    DateFormat dateFormat;

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
        this.dateFormat = new SimpleDateFormat(
                "EEE, dd MMM yyyy kk:mm:ss zzz",
                Locale.US);
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
        
        Guid guid;
        String permaLink;

        // À l'ouverture d'une balise d'article
        if (qName.equalsIgnoreCase("item")) {

            // Création un nouvel article temporaire
            this.article = new Article();

            // Ajout de l'article au flux
            this.flux.addArticle(this.article);

        // À l'ouverture d'une balise de texr input
        } else if (qName.equalsIgnoreCase("textinput")) {

            // Création du text input
            this.textInput = new TextInput();

            // Ajout de l'input au flux
            this.flux.setTextInput(this.textInput);

        // À l'ouverture d'une balise de GUID d'article
        } else if (qName.equalsIgnoreCase("guid") && this.article != null) {

            // Création du GUID
            guid = new Guid();
            permaLink = attributes.getValue("isPermaLink");

            // (In)Validation du statut de permalien
            if (permaLink != null) {
                if (permaLink.equalsIgnoreCase("true")) {
                    guid.setPermaLink(true);
                }
            }

            this.article.setGuid(guid);
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

        if (this.article != null) {

            // Fin de l'article
            if (qName.equalsIgnoreCase("item")) {
                this.article = null;

            // Attributs de l'article
            } else if (qName.equalsIgnoreCase("author")) {
                this.article.setAuthor(value);
            } else if (qName.equalsIgnoreCase("category")) {
                this.article.addCategory(new Category(value));
            } else if (qName.equalsIgnoreCase("comments")) {
                this.article.setComments(value);
            } else if (qName.equalsIgnoreCase("description")) {
                this.article.setDescription(value);
            } else if (qName.equalsIgnoreCase("enclosure")) {
                this.article.setDescription(value);
            } else if (qName.equalsIgnoreCase("guid")) {
                this.article.getGuid().setValue(value);
            } else if (qName.equalsIgnoreCase("link")) {
                this.article.setLink(value);
            } else if (qName.equalsIgnoreCase("pubDate")) {
                try {
                    this.article.setPubDate(dateFormat.parse(value).getTime());
                } catch (ParseException e) {
                    this.article.setPubDate((new Date()).getTime());
                }
            } else if (qName.equalsIgnoreCase("source")) {
                this.article.setSource(value);
            } else if (qName.equalsIgnoreCase("title")) {
                this.article.setTitle(value);
            }
        } else if (this.textInput != null) {

            // Fin de l'input
            if (qName.equalsIgnoreCase("textinput")) {
                this.textInput = null;

            // Attributs de l'input
            } else if (qName.equalsIgnoreCase("description")) {
                this.textInput.setDescription(value);
            } else if (qName.equalsIgnoreCase("name")) {
                this.textInput.setName(value);
            } else if (qName.equalsIgnoreCase("link")) {
                this.textInput.setLink(value);
            } else if (qName.equalsIgnoreCase("title")) {
                this.textInput.setTitle(value);
            }
        } else {

            // Attributs du flux
            if (qName.equalsIgnoreCase("category")) {
                this.flux.addCategory(new Category(value));
            } else if (qName.equalsIgnoreCase("copyright")) {
                this.flux.setCopyright(value);
            } else if (qName.equalsIgnoreCase("day")) {
                this.flux.addSkipDay(value);
            } else if (qName.equalsIgnoreCase("docs")) {
                this.flux.setDocs(value);
            } else if (qName.equalsIgnoreCase("description")) {
                this.flux.setDescription(value);
            } else if (qName.equalsIgnoreCase("generator")) {
                this.flux.setGenerator(value);
            } else if (qName.equalsIgnoreCase("hour")) {
                this.flux.addSkipHour(Integer.parseInt(value));
            } else if (qName.equalsIgnoreCase("language")) {
                this.flux.setLanguage(value);
            } else if (qName.equalsIgnoreCase("lastbuilddate")) {
                try {
                    this.flux.setLastBuildDate(dateFormat.parse(value).getTime());
                } catch (ParseException e) {
                    this.flux.setLastBuildDate((new Date()).getTime());
                }
            } else if (qName.equalsIgnoreCase("link")) {
                this.flux.setLink(value);
            } else if (qName.equalsIgnoreCase("managingeditor")) {
                this.flux.setManagingEditor(value);
            } else if (qName.equalsIgnoreCase("pubdate")) {
                try {
                    this.flux.setPubDate(dateFormat.parse(value).getTime());
                } catch (ParseException e) {
                    this.flux.setPubDate((new Date()).getTime());
                }
            } else if (qName.equalsIgnoreCase("rating")) {
                this.flux.setRating(value);
            } else if (qName.equalsIgnoreCase("title")) {
                this.flux.setTitle(value);
            } else if (qName.equalsIgnoreCase("ttl")) {
                this.flux.setTtl(Integer.parseInt(value));

            // Attributs de l'image
            } else if (qName.equalsIgnoreCase("url")) {
                this.flux.setUrlImage(value);
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
