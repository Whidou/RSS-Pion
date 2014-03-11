<<<<<<< HEAD
/***************************************************************************//**
 * @file    Flux.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    23 janv. 2014
 * @version 0.5
 *
 * Classe pour les objets flux RSS
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

    //! Adresse courriel du webmestre
	private String webMaster;

	//! Date de dernière publication
	private Long pubDate;

	//! Date de dernière génération
	private Long lastBuildDate;

	//! Articles
	private List<Category> categories = new ArrayList<Category>();

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

    //! ???
    private Integer ownRate;

    //! URL de l'image associée
    private String urlImage;

	//! Articles
	private List<Article> articles = new ArrayList<Article>();

/*** METHODS ******************************************************************/
	
	public Flux() {

		super();

		this.id = null;
        this.articles = new ArrayList<Article>();
        this.categories = new ArrayList<Category>();
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
			final Long lastBuildDate, final List<Category> categories,
			final String generator, final String docs, final Cloud cloud,
			final Integer ttl, final ImageRSS image, final String rating,
			final TextInput textInput, final List<Integer> skipHours,
			final List<String> skipDays, final Integer ownRate,
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

	public List<Category> getCategories() {
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

	    if (this.articles == null) {
	        return 0;
	    }

		return this.articles.size();
	}

	public Integer getNumberOfUnreadArticles() {

	    Integer nUnread = 0;

	    for (Article article : this.articles) {
	        if (article == null) {
	            continue;
	        }
	        if (!article.getIsRead()) {
	            nUnread++;
	        }
	    }

		return nUnread;
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

	public void setCategories(final List<Category> categories) {
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
        if (article != null) {
            article.setIdFlux(this.id);
            this.articles.add(article);
        }
    }

    public void addCategory(Category category) {
        if (category != null) {
            category.setIdParent(this.id);
            this.categories.add(category);
        }
    }

    public void addSkipDay(String day) {
        this.skipDays.add(day);
    }

    public void addSkipHour(int hour) {
        this.skipHours.add(hour);
    }
    
    @Override
    public String toString() {
        return "Flux {Titre:" + this.title + ", " +
                "Copyright:" + this.copyright + ", " +
                "Description:" + this.description + ", " +
                "Docs:" + this.docs + ", " +
                "Flux:" + this.feed + ", " +
                "Generateur:" + this.generator + ", " +
                "ID:" + this.id + ", " +
                "Langue:" + this.language + ", " +
                "MàJ:" + this.lastBuildDate + ", " +
                "Lien:" + this.link + ", " +
                "Éditeur:" + this.managingEditor + ", " +
                "Publication:" + this.pubDate + ", " +
                "Rating:" + this.rating + ", " +
                "TTL:" + this.ttl + ", " +
                "Image:" + this.urlImage + ", " +
                "Webmestre:" + this.webMaster + "}";
    }
}
=======
/***************************************************************************/
/**
 * @file    Flux.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    23 janv. 2014
 * @version 0.5
 *
 * Classe pour les objets flux RSS
 ******************************************************************************/

package com.rss_pion.beans;

/*** INCLUDES *****************************************************************/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rss_pion.configuration.Constants;

/*** MAIN CLASS ***************************************************************/

public class Flux {

	/*** ATTRIBUTES ***************************************************************/

	// ! Numéro d'entrée dans la BDD
	private Long id;

	// ! URL du flux RSS
	private String feed;

	// ! Titre
	private String title;

	// ! Lien vers le site web associé
	private String link;

	// ! Description
	private String description;

	// ! Langue
	private String language;

	// ! Copyright
	private String copyright;

	// ! Adresse courriel de l'éditeur
	private String managingEditor;

	// ! Adresse courriel du webmestre
	private String webMaster;

	// ! Date de dernière publication
	private Long pubDate;

	// ! Date de dernière génération
	private Long lastBuildDate;

	// ! Articles
	private List<Category> categories = new ArrayList<Category>();

	// ! Logiciel utilisé pour générer le flux
	private String generator;

	// ! Documentation associée
	private String docs;

	// ! Objet cloud associé
	private Cloud cloud;

	// ! Durée (en minutes) de validité
	private Integer ttl;

	// ! Objet Image associé
	private ImageRSS image;

	// ! Classement PICS
	private String rating;

	// ! Objet text input associé
	private TextInput textInput;

	// ! Heures de non-mise à jour
	private List<Integer> skipHours;

	// ! Jours de non-mise à jour
	private List<String> skipDays;

	// ! User rate (sorte de favoris pour classer les flux selon sa
	// propre pertinence)
	private Integer ownRate;

	// ! URL de l'image associée
	private String urlImage;

	// ! Articles
	private List<Article> articles = new ArrayList<Article>();

	/*** METHODS ******************************************************************/

	public Flux() {

		super();

		this.id = null;
		this.articles = new ArrayList<Article>();
		this.categories = new ArrayList<Category>();
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

	public Flux(final String feed) {

		this();
		this.feed = feed;
	}

	public Flux(final String feed, final String title, final String link,
			final String description, final String language,
			final String copyright, final String managingEditor,
			final String webMaster, final Long pubDate,
			final Long lastBuildDate, final List<Category> categories,
			final String generator, final String docs, final Cloud cloud,
			final Integer ttl, final ImageRSS image, final String rating,
			final TextInput textInput, final List<Integer> skipHours,
			final List<String> skipDays, final Integer ownRate,
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
		this.ownRate = ownRate;
		this.articles = articles;
		this.urlImage = urlImage;
	}

	public void addArticle(final Article article) {
		if (article != null) {
			article.setIdFlux(this.id);
			this.articles.add(article);
		}
	}

	public void addCategory(final Category category) {
		if (category != null) {
			category.setIdParent(this.id);
			this.categories.add(category);
		}
	}

	public void addSkipDay(final String day) {
		this.skipDays.add(day);
	}

	public void addSkipHour(final int hour) {
		this.skipHours.add(hour);
	}

	public List<Article> getArticles() {
		return this.articles;
	}

	public List<Category> getCategories() {
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

		if (this.articles == null) {
			return 0;
		}

		return this.articles.size();
	}

	public Integer getNumberOfUnreadArticles() {

		Integer nUnread = 0;

		for (final Article article : this.articles) {
			if (article == null) {
				continue;
			}
			if (!article.getIsRead()) {
				nUnread++;
			}
		}

		return nUnread;
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

	public void setCategories(final List<Category> categories) {
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

	public void toDetails() {
		GroupFluxDetails group;
		// ! URL du flux RSS
		group = new GroupFluxDetails("Feed");
		group.children.add(this.getFeed());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Titre
		group = new GroupFluxDetails("Title");
		group.children.add(this.getTitle());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Lien vers le site web associé
		group = new GroupFluxDetails("Link");
		group.children.add(this.getLink());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Description
		group = new GroupFluxDetails("Description");
		group.children.add(this.getDescription());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Langue
		group = new GroupFluxDetails("Language");
		group.children.add(this.getLanguage());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Copyright
		group = new GroupFluxDetails("Copyright");
		group.children.add(this.getCopyright());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Adresse courriel de l'éditeur
		group = new GroupFluxDetails("Managing Editor");
		group.children.add(this.getManagingEditor());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Adresse courriel du webmestre
		group = new GroupFluxDetails("Web Master");
		group.children.add(this.getWebMaster());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Date de dernière publication
		group = new GroupFluxDetails("Publication Date");
		try {
			group.children.add(this.getPubDate().toString());
		} catch (final Exception e) {
		}
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Date de dernière génération
		group = new GroupFluxDetails("Last Build Date");
		try {
			group.children.add(this.getLastBuildDate().toString());
		} catch (final Exception e) {
		}
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Articles
		group = new GroupFluxDetails("Categories");
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
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Logiciel utilisé pour générer le flux
		group = new GroupFluxDetails("Generator");
		group.children.add(this.getGenerator());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Documentation associée
		group = new GroupFluxDetails("Documentation");
		group.children.add(this.getDocs());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Objet cloud associé
		group = new GroupFluxDetails("Cloud");
		final ArrayList<String> cloud_str = new ArrayList<String>();
		try {
			cloud_str.add("Domain : " + this.getCloud().getDomain());
		} catch (final Exception e) {
		}
		try {
			cloud_str.add("Path : " + this.getCloud().getPath());
		} catch (final Exception e) {
		}
		try {
			cloud_str.add("Port : " + this.getCloud().getPort());
		} catch (final Exception e) {
		}
		try {
			cloud_str.add("Protocol : " + this.getCloud().getProtocol());
		} catch (final Exception e) {
		}
		try {
			cloud_str.add("Register procedure : "
					+ this.getCloud().getRegisterProcedure());
		} catch (final Exception e) {
		}
		final Iterator<String> itCloud_str = cloud_str.iterator();
		String cloud_str_tot = "";
		while (itCloud_str.hasNext()) {
			cloud_str_tot += itCloud_str.next();
			if (itCloud_str.hasNext()) {
				cloud_str_tot += "\n";
			}
		}
		group.children.add(cloud_str_tot);
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Durée (en minutes) de validité
		group = new GroupFluxDetails("Time To Live");
		try {
			group.children.add(this.getTtl().toString());
		} catch (final Exception e) {
		}
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Classement PICS
		group = new GroupFluxDetails("Rating");
		group.children.add(this.getRating());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Objet text input associé
		group = new GroupFluxDetails("Text Input");
		final ArrayList<String> textInput_str = new ArrayList<String>();
		try {
			textInput_str.add("Name : " + this.getTextInput().getName());
		} catch (final Exception e) {
		}
		try {
			textInput_str.add("Title : " + this.getTextInput().getTitle());
		} catch (final Exception e) {
		}
		try {
			textInput_str.add("Description : "
					+ this.getTextInput().getDescription());
		} catch (final Exception e) {
		}
		try {
			textInput_str.add("Link : " + this.getTextInput().getLink());
		} catch (final Exception e) {
		}
		final Iterator<String> itTextInput_str = textInput_str.iterator();
		String textInput_str_tot = "";
		while (itTextInput_str.hasNext()) {
			textInput_str_tot += itTextInput_str.next();
			if (itTextInput_str.hasNext()) {
				textInput_str_tot += "\n";
			}
		}
		group.children.add(textInput_str_tot);
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Heures de non-mise à jour
		group = new GroupFluxDetails("Skip Hours");
		try {
			final Iterator<Integer> itSH = this.getSkipHours().iterator();
			while (itSH.hasNext()) {
				group.children.add(itSH.next().toString());
			}
		} catch (final Exception e) {
		}
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Jours de non-mise à jour
		group = new GroupFluxDetails("Skip Days");
		try {
			final Iterator<String> itSD = this.getSkipDays().iterator();
			while (itSD.hasNext()) {
				group.children.add(itSD.next());
			}
		} catch (final Exception e) {
		}
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! Classement utilisateur (favoris)
		group = new GroupFluxDetails("User Rate");
		try {
			group.children.add(this.getOwnRate().toString());
		} catch (final Exception e) {
		}
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);

		// ! URL de l'image associée
		group = new GroupFluxDetails("URL Image");
		group.children.add(this.getUrlImage());
		Constants.groupsOfFluxDetails.append(
				Constants.groupsOfFluxDetails.size(), group);
	}

	@Override
	public String toString() {
		return "Flux {Titre:" + this.title + ", " + "Copyright:"
				+ this.copyright + ", " + "Description:" + this.description
				+ ", " + "Docs:" + this.docs + ", " + "Flux:" + this.feed
				+ ", " + "Generateur:" + this.generator + ", " + "ID:"
				+ this.id + ", " + "Langue:" + this.language + ", " + "MàJ:"
				+ this.lastBuildDate + ", " + "Lien:" + this.link + ", "
				+ "Éditeur:" + this.managingEditor + ", " + "Publication:"
				+ this.pubDate + ", " + "Rating:" + this.rating + ", " + "TTL:"
				+ this.ttl + ", " + "Image:" + this.urlImage + ", "
				+ "Webmestre:" + this.webMaster + "}";
	}
}
>>>>>>> 563c7cd8c587e12b5ac65dd8ae618e7b8ba1b06f
