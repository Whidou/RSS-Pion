/***************************************************************************
 * @file Flux.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ***************************************************************************/
package com.rss_pion.beans;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;

import com.rss_pion.configuration.Constants;
import com.rss_pion.database.dao.FluxDAO;
import com.rss_pion.database.dao.ImageDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class Flux.
 */
public class Flux {

	/** The id. */
	private Long id;

	/** The feed. */
	private String feed;

	/** The title. */
	private String title;

	/** The link. */
	private String link;

	/** The description. */
	private String description;

	/** The language. */
	private String language;

	/** The copyright. */
	private String copyright;

	/** The managing editor. */
	private String managingEditor;

	/** The web master. */
	private String webMaster;

	/** The pub date. */
	private Long pubDate;

	/** The last build date. */
	private Long lastBuildDate;

	/** The articles. */
	private List<String> categories = new ArrayList<String>();

	/** The generator. */
	private String generator;

	/** The docs. */
	private String docs;

	/** The cloud. */
	private Cloud cloud;

	/** The ttl. */
	private Integer ttl;

	/** The image. */
	private ImageDAO image;

	/** The rating. */
	private String rating;

	/** The text input. */
	private TextInput textInput;

	/** The skip hours. */
	private List<Integer> skipHours;

	/** The skip days. */
	private List<String> skipDays;

	/** The number of read articles. */
	private Integer numberOfReadArticles;

	/** The number of articles. */
	private Integer numberOfArticles;

	/** The own rate. */
	private Integer ownRate;

	/** The url image. */
	private String urlImage;

	/** The articles. */
	private List<Article> articles = new ArrayList<Article>();

	/**
	 * Instantiates a new flux.
	 */
	public Flux() {
		super();
	}

	/**
	 * Instantiates a new flux.
	 * 
	 * @param feed : The feed
	 * @param title : The title
	 * @param link : The link
	 * @param description : The description
	 * @param language : The language
	 * @param copyright : The copyright
	 * @param managingEditor : The managing editor
	 * @param webMaster : The web master
	 * @param pubDate : The pub date
	 * @param lastBuildDate : The last build date
	 * @param category : The category
	 * @param generator : The generator
	 * @param docs : The docs
	 * @param cloud : The cloud
	 * @param ttl : The ttl
	 * @param image : The image
	 * @param rating : The rating
	 * @param textInput : The text input
	 * @param skipHours : The skip hours
	 * @param skipDays : The skip days
	 * @param numberOfReadArticles : The number of read articles
	 * @param numberOfArticles : The number of articles
	 * @param ownRate : The own rate
	 * @param articles : The articles
	 */
	public Flux(final String feed, final String title, final String link,
			final String description, final String language,
			final String copyright, final String managingEditor,
			final String webMaster, final Long pubDate,
			final Long lastBuildDate, final List<String> categories,
			final String generator, final String docs, final Cloud cloud,
			final Integer ttl, final ImageDAO image, final String rating,
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

        super();

        this.feed = feed;
        this.lastBuildDate = Long.valueOf(0);

        this.title = "";
        this.link = "";
        this.description = "";
        this.language = "";
        this.copyright = "";
        this.managingEditor = "";
        this.webMaster = "";
        this.pubDate = Long.valueOf(0);
        this.categories = new ArrayList<String>();
        this.generator = "";
        this.docs = "";
        this.cloud =null;
        this.ttl = 0;
        this.image = null;
        this.rating = "";
        this.textInput = null;
        this.skipHours = new ArrayList<Integer>();
        this.skipDays = new ArrayList<String>();
        this.numberOfReadArticles = 0;
        this.numberOfArticles = 0;
        this.ownRate = 0;
        this.articles = null;
        this.urlImage = "";
    }
	
	public Long insertIntoDB() {

	    ContentValues valuesMap;
	    Long idLinkedObject;
	    String strValue;

	    // Si le flux n'existe pas dans la BDD
	    if (this.id == null) {

	        // Préparation des champs du flux
	        valuesMap = new ContentValues();
	        valuesMap.put("feed", this.feed);

            // Insertion du flux
	        this.id = Constants.sqlHandler.insert(
	                FluxDAO.nameOfTheAssociatedTable,
	                null,
	                valuesMap);
	    }

        // Préparation des champs du flux
	    valuesMap = new ContentValues();
        valuesMap.put("copyright", this.copyright);
        valuesMap.put("description", this.description);
        valuesMap.put("docs", this.docs);
        valuesMap.put("generator", this.generator);
        valuesMap.put("language", this.language);
        valuesMap.put("lastBuildDate", this.lastBuildDate);
        valuesMap.put("link", this.link);
        valuesMap.put("managingEditor", this.managingEditor);
        valuesMap.put("numberOfArticles", this.numberOfArticles);
        valuesMap.put("numberOfReadArticles", this.numberOfReadArticles);
        valuesMap.put("ownRate", this.ownRate);
        valuesMap.put("pubDate", this.pubDate);
        valuesMap.put("rating", this.rating);
        valuesMap.put("title", this.title);
        valuesMap.put("ttl", this.ttl);
        valuesMap.put("webMaster", this.webMaster);
        valuesMap.put("urlImage", this.urlImage);

        // Concaténation des jours
        if (this.skipDays != null) {
            strValue = new String();
            for (String day : this.skipDays){
                strValue.concat(day);
            }
            valuesMap.put("skipDays", strValue);
        }

        // Concaténation des heures
        if (this.skipHours != null) {
            strValue = new String();
            for (Integer hour : this.skipHours){
                strValue.concat(hour.toString());
            }
            valuesMap.put("skipHours", strValue);
        }

        if (this.cloud != null) {
            idLinkedObject = this.cloud.insertIntoDB();
            valuesMap.put("idCloud", idLinkedObject);
        } else {
            valuesMap.put("idCloud", (Long) null);
        }
/*
        if (this.image != null) {
            idLinkedObject = this.image.insertIntoDB();
            valuesMap.put("idImage", idLinkedObject);
        } else {
            valuesMap.put("idImage", (Long) null);
        }

        if (this.image != null) {
            idLinkedObject = this.textInput.insertIntoDB();
            valuesMap.put("idTextInput", idLinkedObject);
        } else {
            valuesMap.put("idTextInput", (Long) null);
        }
*/
        // Update du flux
        Constants.sqlHandler.update(FluxDAO.nameOfTheAssociatedTable,
                valuesMap, 
                "id=?",
                new String[] {this.id.toString()});

        return this.id;
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

	public ImageDAO getImage() {
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

	public void setImage(final ImageDAO image) {
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

	/***************************************************************************
	 * @see com.rss_pion.database.dao.abstracts.NeedTranslationToBeSerializedObject#translateDaoToObject(java.lang.Object)
	 ***************************************************************************/
/*
	@Override
	public void translateDaoToObject(final Object object) {
	    List<Integer> hoursList = new ArrayList<Integer>();
		final FluxDAO fluxDAO = object instanceof FluxDAO ? (FluxDAO) object
				: null;
		if (fluxDAO != null) {
			final Iterator<ArticleDAO> ita = fluxDAO.getArticlesDAO()
					.iterator();
			while (ita.hasNext()) {
				final Article article = new Article();
				article.translateDaoToObject(ita.next());
				this.articles.add(article);
			}
			final Iterator<CategoryFluxDAO> itc = fluxDAO.getCategoriesDAO()
					.iterator();
			while (itc.hasNext()) {
				final CategoryFlux category = new CategoryFlux();
				category.translateDaoToObject(itc.next());
				this.categories.add(category);
			}
			this.cloud = fluxDAO.getCloud();
			this.copyright = fluxDAO.getCopyright();
			this.description = fluxDAO.getDescription();
			this.docs = fluxDAO.getDocs();
			this.feed = fluxDAO.getFeed();
			this.generator = fluxDAO.getGenerator();
			this.id = fluxDAO.getId();
			this.image = fluxDAO.getImage();
			this.language = fluxDAO.getLanguage();
			this.lastBuildDate = fluxDAO.getLastBuildDate();
			this.link = fluxDAO.getLink();
			this.managingEditor = fluxDAO.getManagingEditor();
			this.numberOfArticles = fluxDAO.getNumberOfArticles();
			this.numberOfReadArticles = fluxDAO.getNumberOfReadArticles();
			this.ownRate = fluxDAO.getOwnRate();
			this.pubDate = fluxDAO.getPubDate();
			this.rating = fluxDAO.getRating();
			this.skipDays = Arrays.asList(fluxDAO.getSkipDays().split("/"));
			for (String strHour : fluxDAO.getSkipHours().split("/")) {
			    try {
			        hoursList.add(Integer.parseInt(strHour));
			    } catch (NumberFormatException e) {
			        // Si le string n'est pas un nombre, il est ignoré.
			    }
			}
			this.skipHours = hoursList;
			final TextInput textInput = new TextInput();
			textInput.translateDaoToObject(fluxDAO.getTextInputDAO());
			this.textInput = textInput;
			this.title = fluxDAO.getTitle();
			this.ttl = fluxDAO.getTtl();
			this.webMaster = fluxDAO.getWebMaster();
			this.urlImage = fluxDAO.getUrlImage();
		}
	}
*/
	/***************************************************************************
	 * @see com.rss_pion.database.dao.abstracts.NeedTranslationToBeSerializedObject#translateObjectToDao(java.lang.Long[])
	 ***************************************************************************/
/*
	@Override
	public Object translateObjectToDao(final Long... ids)
			throws IllegalAccessException, IllegalArgumentException {

		final FluxDAO fluxDAO = new FluxDAO();

		if (this.cloud != null) {
		    fluxDAO.setIdCloud(this.cloud.insertInTheDataBase());
		}
        fluxDAO.setCopyright(this.copyright);
        fluxDAO.setDescription(this.description);
		fluxDAO.setDocs(this.docs);
		fluxDAO.setFeed(this.feed);
		fluxDAO.setGenerator(this.generator);
        if (this.image != null) {
            fluxDAO.setIdImage(this.image.insertInTheDataBase());
        }
		fluxDAO.setLanguage(this.getLanguage());
        if (this.lastBuildDate != null) {
            fluxDAO.setLastBuildDate(this.lastBuildDate);
        }
		fluxDAO.setLink(this.link);
		fluxDAO.setManagingEditor(this.managingEditor);
		fluxDAO.setNumberOfArticles(this.numberOfArticles);
		fluxDAO.setNumberOfReadArticles(this.numberOfReadArticles);
		fluxDAO.setOwnRate(this.ownRate);
        if (this.pubDate != null) {
            fluxDAO.setPubDate(this.pubDate);
        }
		fluxDAO.setRating(this.rating);
        if (this.skipDays != null) {
    		fluxDAO.setSkipDays(this.skipDays.toString()
    				.replace("[", "").replace("]", "").replace(", ", "/"));
        }
        if (this.skipHours != null) {
            fluxDAO.setSkipHours(this.skipHours.toString()
				.replace("[", "").replace("]", "").replace(", ", "/"));
        }
        if (this.textInput != null) {
    		final TextInputDAO textInputDAO = (TextInputDAO) this.getTextInput()
    				.translateObjectToDao();
    		fluxDAO.setIdTextInput(textInputDAO.insertInTheDataBase());
        }
		fluxDAO.setTitle(this.title);
		fluxDAO.setTtl(this.ttl);
		fluxDAO.setWebMaster(this.webMaster);
		fluxDAO.setUrlImage(this.urlImage);

		return fluxDAO;
	}
*/
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
