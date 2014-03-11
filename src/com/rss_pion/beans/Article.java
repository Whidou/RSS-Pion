<<<<<<< HEAD
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

import android.text.Spanned;
import android.text.SpannedString;

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
	}

	public Article(final Long idFlux, final Boolean isRead,
			final String title, final String link, final String description,
			final String author, final List<Category> categories,
			final String comments, final Enclosure enclosure, final Guid guid,
			final Long pubDate, final String source) {
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
        return new SpannedString(this.description.replaceAll("<.*>", ""));
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

    public void addCategory(final Category category) {
        if (category != null) {
            category.setIdParent(this.id);
            this.categories.add(category);
        }
    }
}
=======
/***************************************************************************/
/**
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
import java.util.Iterator;
import java.util.List;

import android.text.Html;
import android.text.Spanned;

import com.rss_pion.configuration.Constants;
import com.rss_pion.parser.URLImageParser;

/*** MAIN CLASS ***************************************************************/

public class Article {

	/*** ATTRIBUTES ***************************************************************/

	// ! Auteur
	private String author;

	// ! Catégorie
	private List<Category> categories;

	// ! Commentaires
	private String comments;

	// ! Description
	private String description;

	// ! Pièce Jointe
	private Enclosure enclosure;

	// ! GUID
	private Guid guid;

	// ! Numéro d'entrée dans la BDD
	private Long id;

	// ! Numéro d'entrée du flux associé
	private Long idFlux;

	// ! Lu
	private Boolean isRead;

	// ! URL
	private String link;

	// ! Date de publication
	private Long pubDate;

	// ! Source
	private String source;

	// ! Titre
	private String title;

	// ! ???
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

	public Article(final Long idFlux, final Boolean isRead, final String title,
			final String link, final String description, final String author,
			final List<Category> categories, final String comments,
			final Enclosure enclosure, final Guid guid, final Long pubDate,
			final String source, final Integer userRate) {
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

	public void addCategory(final Category category) {
		if (category != null) {
			category.setIdParent(this.id);
			this.categories.add(category);
		}
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

	public Enclosure getEnclosure() {
		return this.enclosure;
	}

	public Guid getGuid() {
		return this.guid;
	}

	public Spanned getHtmlDescription() {
		return Html.fromHtml(this.description, new URLImageParser(), null);
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

	public void toDetails() {
		GroupArticleDetails group;

		// ! Auteur
		group = new GroupArticleDetails("Author");
		group.children.add(this.getAuthor());
		Constants.groupsOfArticleDetails.append(
				Constants.groupsOfArticleDetails.size(), group);

		// ! Catégorie
		group = new GroupArticleDetails("Categories");
		try {
			final Iterator<Category> itC = this.getCategories().iterator();
			while (itC.hasNext()) {
				try {
					group.children.add(itC.next().getName());
				} catch (final Exception e) {
				}
			}
		} catch (final Exception e) {
		}
		Constants.groupsOfArticleDetails.append(
				Constants.groupsOfArticleDetails.size(), group);

		// ! Commentaires
		group = new GroupArticleDetails("Comments");
		group.children.add(this.getComments());
		Constants.groupsOfArticleDetails.append(
				Constants.groupsOfArticleDetails.size(), group);

		// ! Description
		group = new GroupArticleDetails("Description");
		group.children.add(this.getDescription());
		Constants.groupsOfArticleDetails.append(
				Constants.groupsOfArticleDetails.size(), group);

		// ! Pièce Jointe
		group = new GroupArticleDetails("Enclosure");
		final ArrayList<String> enclosure_str = new ArrayList<String>();
		try {
			enclosure_str.add("Type : " + this.getEnclosure().getType());
		} catch (final Exception e) {
		}
		try {
			enclosure_str.add("URL : " + this.getEnclosure().getUrl());
		} catch (final Exception e) {
		}
		try {
			enclosure_str.add("Length : " + this.getEnclosure().getLength());
		} catch (final Exception e) {
		}
		final Iterator<String> itEnclosure_str = enclosure_str.iterator();
		String enclosure_str_tot = "";
		while (itEnclosure_str.hasNext()) {
			enclosure_str_tot += itEnclosure_str.next();
			if (itEnclosure_str.hasNext()) {
				enclosure_str_tot += "\n";
			}
		}
		group.children.add(enclosure_str_tot);
		Constants.groupsOfArticleDetails.append(
				Constants.groupsOfArticleDetails.size(), group);

		// ! GUID
		group = new GroupArticleDetails("Guid");
		final ArrayList<String> guid_str = new ArrayList<String>();
		try {
			guid_str.add("Value : " + this.getGuid().getValue());
		} catch (final Exception e) {
		}
		try {
			guid_str.add("Is permanent link : "
					+ this.getGuid().getIsPermaLink());
		} catch (final Exception e) {
		}
		final Iterator<String> itGuid_str = guid_str.iterator();
		String guid_str_tot = "";
		while (itGuid_str.hasNext()) {
			guid_str_tot += itGuid_str.next();
			if (itGuid_str.hasNext()) {
				guid_str_tot += "\n";
			}
		}
		group.children.add(guid_str_tot);
		Constants.groupsOfArticleDetails.append(
				Constants.groupsOfArticleDetails.size(), group);

		// ! Lu
		group = new GroupArticleDetails("Is read");
		try {
			group.children.add(this.getIsRead().toString());
		} catch (final Exception e) {
		}
		Constants.groupsOfArticleDetails.append(
				Constants.groupsOfArticleDetails.size(), group);

		// ! URL
		group = new GroupArticleDetails("Link");
		group.children.add(this.getLink());
		Constants.groupsOfArticleDetails.append(
				Constants.groupsOfArticleDetails.size(), group);

		// ! Date de publication
		group = new GroupArticleDetails("Publication Date");
		try {
			group.children.add(this.getPubDate().toString());
		} catch (final Exception e) {
		}
		Constants.groupsOfArticleDetails.append(
				Constants.groupsOfArticleDetails.size(), group);

		// ! Source
		group = new GroupArticleDetails("Source");
		group.children.add(this.getSource());
		Constants.groupsOfArticleDetails.append(
				Constants.groupsOfArticleDetails.size(), group);

		// ! Titre
		group = new GroupArticleDetails("Title");
		group.children.add(this.getTitle());
		Constants.groupsOfArticleDetails.append(
				Constants.groupsOfArticleDetails.size(), group);

		// ! User rate (sorte de favoris pour classer les articles selon sa
		// propre pertinence)
		group = new GroupArticleDetails("User Rate");
		try {
			group.children.add(this.getUserRate().toString());
		} catch (final Exception e) {
		}
		Constants.groupsOfArticleDetails.append(
				Constants.groupsOfArticleDetails.size(), group);
	}
}
>>>>>>> 563c7cd8c587e12b5ac65dd8ae618e7b8ba1b06f
