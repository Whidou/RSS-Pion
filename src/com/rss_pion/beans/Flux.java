/***************************************************************************//**
 * @file    Flux.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    23 janv. 2014
 * @version 0.5
 *
 * Classe pour les objets FLux RSS
 ******************************************************************************/

package com.rss_pion.beans;

/*** INCLUDES *****************************************************************/

import java.util.ArrayList;
import java.util.List;

/*** MAIN CLASS ***************************************************************/

public class Flux {

/*** ATTRIBUTES ***************************************************************/

	//! Numéro d'entrée dans la BDD
	private Long id;

	//! URL du flux RSS
	private String feed;

	//! Titre
	private String title;

	//! Lien vers le site web associé
	private String link;

	//! Description
	private String description;

	//! Langue
	private String language;

	//! Copyright
	private String copyright;

	//! Adresse courriel de l'éditeur
	private String managingEditor;

    //! Adresse courriel du webmaster
	private String webMaster;

	//! Date de dernière publication
	private Long pubDate;

	//! Date de dernière génération
	private Long lastBuildDate;

	//! Articles
	private List<String> categories = new ArrayList<String>();

	//! Logiciel utilisé pour générer le flux
	private String generator;

	//! Documentation associée
	private String docs;

	//! Objet cloud associé
	private Cloud cloud;

	//! Durée (en minutes) de validité
	private Integer ttl;

	//! Objet Image associé
	private ImageRSS image;

	//! Classement PICS
	private String rating;

	//! Objet text input associé
	private TextInput textInput;

    //! Heures de non-mise à jour
    private List<Integer> skipHours;

    //! Jours de non-mise à jour
    private List<String> skipDays;

    //! Nombre d'articles lus
    private Integer numberOfReadArticles;

    //! Nombre d'articles
    private Integer numberOfArticles;

    //! ???
    private Integer ownRate;

    //! URL de l'image associée
    private String urlImage;

	//! Articles
	private List<Article> articles = new ArrayList<Article>();

/*** METHODS ******************************************************************/
	
	public Flux() {

		super();

        this.articles = new ArrayList<Article>();
        this.categories = new ArrayList<String>();
        this.cloud = null;
        this.copyright = "";
        this.description = "";
        this.docs = "";
        this.feed = "";
        this.generator = "";
        this.image = null;
        this.lastBuildDate = Long.valueOf(0);
        this.language = "";
        this.link = "";
        this.managingEditor = "";
        this.numberOfReadArticles = 0;
        this.numberOfArticles = 0;
        this.ownRate = 0;
        this.pubDate = Long.valueOf(0);
        this.skipDays = new ArrayList<String>();
        this.skipHours = new ArrayList<Integer>();
        this.rating = "";
        this.textInput = null;
        this.title = "";
        this.ttl = 0;
        this.urlImage = "";
        this.webMaster = "";
	}

	public Flux(final String feed, final String title, final String link,
			final String description, final String language,
			final String copyright, final String managingEditor,
			final String webMaster, final Long pubDate,
			final Long lastBuildDate, final List<String> categories,
			final String generator, final String docs, final Cloud cloud,
			final Integer ttl, final ImageRSS image, final String rating,
			final TextInput textInput, final List<Integer> skipHours,
			final List<String> skipDays, final Integer numberOfReadArticles,
			final Integer numberOfArticles, final Integer ownRate,
			final List<Article> articles, final String urlImage) {
		super();
		this.feed = feed;
		this.title = title;
		this.link = link;
		this.description = description;
		this.language = language;
		this.copyright = copyright;
		this.managingEditor = managingEditor;
		this.webMaster = webMaster;
		this.pubDate = pubDate;
		this.lastBuildDate = lastBuildDate;
		this.categories = categories;
		this.generator = generator;
		this.docs = docs;
		this.cloud = cloud;
		this.ttl = ttl;
		this.image = image;
		this.rating = rating;
		this.textInput = textInput;
		this.skipHours = skipHours;
		this.skipDays = skipDays;
		this.numberOfReadArticles = numberOfReadArticles;
		this.numberOfArticles = numberOfArticles;
		this.ownRate = ownRate;
		this.articles = articles;
		this.urlImage = urlImage;
	}

	public Flux(String feed) {

        this();
        this.feed = feed;
    }

	public List<Article> getArticles() {
		return this.articles;
	}

	public List<String> getCategories() {
		return this.categories;
	}

	public Cloud getCloud() {
		return this.cloud;
	}

	public String getCopyright() {
		return this.copyright;
	}

	public String getDescription() {
		return this.description;
	}

	public String getDocs() {
		return this.docs;
	}

	public String getFeed() {
		return this.feed;
	}

	public String getGenerator() {
		return this.generator;
	}

	public Long getId() {
		return this.id;
	}

	public ImageRSS getImage() {
		return this.image;
	}

	public String getLanguage() {
		return this.language;
	}

	public Long getLastBuildDate() {
		return this.lastBuildDate;
	}

	public String getLink() {
		return this.link;
	}

	public String getManagingEditor() {
		return this.managingEditor;
	}

	public Integer getNumberOfArticles() {
		return this.numberOfArticles;
	}

	public Integer getNumberOfReadArticles() {
		return this.numberOfReadArticles;
	}

	public Integer getOwnRate() {
		return this.ownRate;
	}

	public Long getPubDate() {
		return this.pubDate;
	}

	public String getRating() {
		return this.rating;
	}

	public List<String> getSkipDays() {
		return this.skipDays;
	}

	public List<Integer> getSkipHours() {
		return this.skipHours;
	}

	public TextInput getTextInput() {
		return this.textInput;
	}

	public String getTitle() {
		return this.title;
	}

	public Integer getTtl() {
		return this.ttl;
	}

	public String getUrlImage() {
		return this.urlImage;
	}

	public String getWebMaster() {
		return this.webMaster;
	}

	public void setArticles(final List<Article> articles) {
		this.articles = articles;
	}

	public void setCategories(final List<String> categories) {
		this.categories = categories;
	}

	public void setCloud(final Cloud cloud) {
		this.cloud = cloud;
	}

	public void setCopyright(final String copyright) {
		this.copyright = copyright;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setDocs(final String docs) {
		this.docs = docs;
	}

	public void setFeed(final String feed) {
		this.feed = feed;
	}

	public void setGenerator(final String generator) {
		this.generator = generator;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setImage(final ImageRSS image) {
		this.image = image;
	}

	public void setLanguage(final String language) {
		this.language = language;
	}

	public void setLastBuildDate(final Long lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	public void setLink(final String link) {
		this.link = link;
	}

	public void setManagingEditor(final String managingEditor) {
		this.managingEditor = managingEditor;
	}

	public void setNumberOfArticles(final Integer numberOfArticles) {
		this.numberOfArticles = numberOfArticles;
	}

	public void setNumberOfReadArticles(final Integer numberOfReadArticles) {
		this.numberOfReadArticles = numberOfReadArticles;
	}

	public void setOwnRate(final Integer ownRate) {
		this.ownRate = ownRate;
	}

	public void setPubDate(final Long pubDate) {
		this.pubDate = pubDate;
	}

	public void setRating(final String rating) {
		this.rating = rating;
	}

	public void setSkipDays(final List<String> skipDays) {
		this.skipDays = skipDays;
	}

	public void setSkipHours(final List<Integer> skipHours) {
		this.skipHours = skipHours;
	}

	public void setTextInput(final TextInput textInput) {
		this.textInput = textInput;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setTtl(final Integer ttl) {
		this.ttl = ttl;
	}

	public void setUrlImage(final String urlImage) {
		this.urlImage = urlImage;
	}

	public void setWebMaster(final String webMaster) {
		this.webMaster = webMaster;
	}

    public void addArticle(Article article) {
        this.articles.add(article);
    }

    public void addCategory(String category) {
        this.categories.add(category);
    }

    public void addSkipDay(String day) {
        this.skipDays.add(day);
    }

    public void addSkipHour(int hour) {
        this.skipHours.add(hour);
        
    }
}
