/***************************************************************************//**
 * @file    Article.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-02-08
 * @version 0.5
 *
 * Classe pour les objets articles
 ******************************************************************************/

package com.rss_pion.beans;

/*** INCLUDES *****************************************************************/

import java.util.ArrayList;
import java.util.List;

import android.text.Html;
import android.text.Spanned;

/*** MAIN CLASS ***************************************************************/

public class Article {

/*** ATTRIBUTES ***************************************************************/

	//! Auteur
	private String author;

	//! Catégorie
	private List<Category> categories;

	//! Commentaires
	private String comments;

	//! Description
	private String description;

	//! Pièce Jointe
	private Enclosure enclosure;

	//! GUID
	private Guid guid;

	//! Numéro d'entrée dans la BDD
	private Long id;

	//! Numéro d'entrée du flux associé
	private Long idFlux;

	//! Lu
	private Boolean isRead;

	//! URL
	private String link;

	//! Date de publication
	private Long pubDate;

	//! Source
	private String source;

	//! Titre
	private String title;

	//! ???
	private Integer userRate;

/*** METHODS ******************************************************************/

	public Article() {
		super();
        this.idFlux = null;
        this.isRead = false;
        this.title = "";
        this.link = "";
        this.description = "";
        this.author = "";
        this.categories = new ArrayList<Category>();
        this.comments = "";
        this.enclosure = null;
        this.guid = null;
        this.pubDate = Long.valueOf(0);
        this.source = "";
        this.userRate = null;
	}

	public Article(final Long idFlux, final Boolean isRead,
			final String title, final String link, final String description,
			final String author, final List<Category> categories,
			final String comments, final Enclosure enclosure, final Guid guid,
			final Long pubDate, final String source, final Integer userRate) {
		super();
		this.idFlux = idFlux;
		this.isRead = isRead;
		this.title = title;
		this.link = link;
		this.description = description;
		this.author = author;
		this.categories = categories;
		this.comments = comments;
		this.enclosure = enclosure;
		this.guid = guid;
		this.pubDate = pubDate;
		this.source = source;
		this.userRate = userRate;
	}

	public String getAuthor() {
		return this.author;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public String getComments() {
		return this.comments;
	}

	public String getDescription() {
		return this.description;
	}

    public Spanned getHtmlDescription() {
        return Html.fromHtml(this.description, null, null);
    }

	public Enclosure getEnclosure() {
		return this.enclosure;
	}

	public Guid getGuid() {
		return this.guid;
	}

	public Long getId() {
		return this.id;
	}

	public Long getIdFlux() {
		return this.idFlux;
	}

	public Boolean getIsRead() {
		return this.isRead;
	}

	public String getLink() {
		return this.link;
	}

	public Long getPubDate() {
		return this.pubDate;
	}

	public String getSource() {
		return this.source;
	}

	public String getTitle() {
		return this.title;
	}

	public Integer getUserRate() {
		return this.userRate;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public void setCategories(final List<Category> categories) {
		this.categories = categories;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setEnclosure(final Enclosure enclosure) {
		this.enclosure = enclosure;
	}

	public void setGuid(final Guid guid) {
		this.guid = guid;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setIdFlux(final Long idFlux) {
		this.idFlux = idFlux;
	}

	public void setIsRead(final Boolean isRead) {
		this.isRead = isRead;
	}

	public void setLink(final String link) {
		this.link = link;
	}

	public void setPubDate(final Long pubDate) {
		this.pubDate = pubDate;
	}

	public void setSource(final String source) {
		this.source = source;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setUserRate(final Integer userRate) {
		this.userRate = userRate;
	}

    public void addCategory(final Category category) {
        if (category != null) {
            category.setIdParent(this.id);
            this.categories.add(category);
        }
    }
}
