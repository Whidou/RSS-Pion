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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.rss_pion.database.dao.ArticleDAO;
import com.rss_pion.database.dao.CategoryFluxDAO;
import com.rss_pion.database.dao.FluxDAO;
import com.rss_pion.database.dao.ImageDAO;
import com.rss_pion.database.dao.TextInputDAO;
import com.rss_pion.database.dao.abstracts.NeedTranslationToBeSerializedObject;

// TODO: Auto-generated Javadoc
/**
 * The Class Flux.
 */
public class Flux extends NeedTranslationToBeSerializedObject {

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
	private String pubDate;

	/** The last build date. */
	private String lastBuildDate;

	/** The articles. */
	private List<CategoryFlux> categories = new ArrayList<CategoryFlux>();

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
			final String webMaster, final String pubDate,
			final String lastBuildDate, final List<CategoryFlux> categories,
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

	/**
	 * Gets the articles.
	 * 
	 * @return The articles
	 */
	public List<Article> getArticles() {
		return this.articles;
	}

	/**
	 * Gets the category.
	 * 
	 * @return The category
	 */
	public List<CategoryFlux> getCategories() {
		return this.categories;
	}

	/**
	 * Gets the cloud.
	 * 
	 * @return The cloud
	 */
	public Cloud getCloud() {
		return this.cloud;
	}

	/**
	 * Gets the copyright.
	 * 
	 * @return The copyright
	 */
	public String getCopyright() {
		return this.copyright;
	}

	/**
	 * Gets the description.
	 * 
	 * @return The description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Gets the docs.
	 * 
	 * @return The docs
	 */
	public String getDocs() {
		return this.docs;
	}

	/**
	 * Gets the feed.
	 * 
	 * @return The feed
	 */
	public String getFeed() {
		return this.feed;
	}

	/**
	 * Gets the generator.
	 * 
	 * @return The generator
	 */
	public String getGenerator() {
		return this.generator;
	}

	/**
	 * Gets the id.
	 * 
	 * @return The id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Gets the image.
	 * 
	 * @return The image
	 */
	public ImageDAO getImage() {
		return this.image;
	}

	/**
	 * Gets the language.
	 * 
	 * @return The language
	 */
	public String getLanguage() {
		return this.language;
	}

	/**
	 * Gets the last build date.
	 * 
	 * @return The last build date
	 */
	public String getLastBuildDate() {
		return this.lastBuildDate;
	}

	/**
	 * Gets the link.
	 * 
	 * @return The link
	 */
	public String getLink() {
		return this.link;
	}

	/**
	 * Gets the managing editor.
	 * 
	 * @return The managing editor
	 */
	public String getManagingEditor() {
		return this.managingEditor;
	}

	/**
	 * Gets the number of articles.
	 * 
	 * @return The number of articles
	 */
	public Integer getNumberOfArticles() {
		return this.numberOfArticles;
	}

	/**
	 * Gets the number of read articles.
	 * 
	 * @return The number of read articles
	 */
	public Integer getNumberOfReadArticles() {
		return this.numberOfReadArticles;
	}

	/**
	 * Gets the own rate.
	 * 
	 * @return The own rate
	 */
	public Integer getOwnRate() {
		return this.ownRate;
	}

	/**
	 * Gets the pub date.
	 * 
	 * @return The pub date
	 */
	public String getPubDate() {
		return this.pubDate;
	}

	/**
	 * Gets the rating.
	 * 
	 * @return The rating
	 */
	public String getRating() {
		return this.rating;
	}

	/**
	 * Gets the skip days.
	 * 
	 * @return The skip days
	 */
	public List<String> getSkipDays() {
		return this.skipDays;
	}

	/**
	 * Gets the skip hours.
	 * 
	 * @return The skip hours
	 */
	public List<Integer> getSkipHours() {
		return this.skipHours;
	}

	/**
	 * Gets the text input.
	 * 
	 * @return The text input
	 */
	public TextInput getTextInput() {
		return this.textInput;
	}

	/**
	 * Gets the title.
	 * 
	 * @return The title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Gets the ttl.
	 * 
	 * @return The ttl
	 */
	public Integer getTtl() {
		return this.ttl;
	}

	/***************************************************************************
	 * @return The urlImage (String)
	 ***************************************************************************/
	public String getUrlImage() {
		return this.urlImage;
	}

	/**
	 * Gets the web master.
	 * 
	 * @return The web master
	 */
	public String getWebMaster() {
		return this.webMaster;
	}

	/**
	 * Sets the articles.
	 * 
	 * @param articles : The new articles
	 */
	public void setArticles(final List<Article> articles) {
		this.articles = articles;
	}

	/**
	 * Sets the category.
	 * 
	 * @param category : The new category
	 */
	public void setCategories(final List<CategoryFlux> categories) {
		this.categories = categories;
	}

	/**
	 * Sets the cloud.
	 * 
	 * @param cloud : The new cloud
	 */
	public void setCloud(final Cloud cloud) {
		this.cloud = cloud;
	}

	/**
	 * Sets the copyright.
	 * 
	 * @param copyright : The new copyright
	 */
	public void setCopyright(final String copyright) {
		this.copyright = copyright;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description : The new description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Sets the docs.
	 * 
	 * @param docs : The new docs
	 */
	public void setDocs(final String docs) {
		this.docs = docs;
	}

	/**
	 * Sets the feed.
	 * 
	 * @param feed : The new feed
	 */
	public void setFeed(final String feed) {
		this.feed = feed;
	}

	/**
	 * Sets the generator.
	 * 
	 * @param generator : The new generator
	 */
	public void setGenerator(final String generator) {
		this.generator = generator;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id : The new id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Sets the image.
	 * 
	 * @param image : The new image
	 */
	public void setImage(final ImageDAO image) {
		this.image = image;
	}

	/**
	 * Sets the language.
	 * 
	 * @param language : The new language
	 */
	public void setLanguage(final String language) {
		this.language = language;
	}

	/**
	 * Sets the last build date.
	 * 
	 * @param lastBuildDate : The new last build date
	 */
	public void setLastBuildDate(final String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	/**
	 * Sets the link.
	 * 
	 * @param link : The new link
	 */
	public void setLink(final String link) {
		this.link = link;
	}

	/**
	 * Sets the managing editor.
	 * 
	 * @param managingEditor : The new managing editor
	 */
	public void setManagingEditor(final String managingEditor) {
		this.managingEditor = managingEditor;
	}

	/**
	 * Sets the number of articles.
	 * 
	 * @param numberOfArticles : The new number of articles
	 */
	public void setNumberOfArticles(final Integer numberOfArticles) {
		this.numberOfArticles = numberOfArticles;
	}

	/**
	 * Sets the number of read articles.
	 * 
	 * @param numberOfReadArticles : The new number of read articles
	 */
	public void setNumberOfReadArticles(final Integer numberOfReadArticles) {
		this.numberOfReadArticles = numberOfReadArticles;
	}

	/**
	 * Sets the own rate.
	 * 
	 * @param ownRate : The new own rate
	 */
	public void setOwnRate(final Integer ownRate) {
		this.ownRate = ownRate;
	}

	/**
	 * Sets the pub date.
	 * 
	 * @param pubDate : The new pub date
	 */
	public void setPubDate(final String pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * Sets the rating.
	 * 
	 * @param rating : The new rating
	 */
	public void setRating(final String rating) {
		this.rating = rating;
	}

	/**
	 * Sets the skip days.
	 * 
	 * @param skipDays : The new skip days
	 */
	public void setSkipDays(final List<String> skipDays) {
		this.skipDays = skipDays;
	}

	/**
	 * Sets the skip hours.
	 * 
	 * @param skipHours : The new skip hours
	 */
	public void setSkipHours(final List<Integer> skipHours) {
		this.skipHours = skipHours;
	}

	/**
	 * Sets the text input.
	 * 
	 * @param textInput : The new text input
	 */
	public void setTextInput(final TextInput textInput) {
		this.textInput = textInput;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title : The new title
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * Sets the ttl.
	 * 
	 * @param ttl : The new ttl
	 */
	public void setTtl(final Integer ttl) {
		this.ttl = ttl;
	}

	/**
	 * Sets the url image.
	 * 
	 * @param ttl : The url image
	 */
	public void setUrlImage(final String urlImage) {
		this.urlImage = urlImage;
	}

	/**
	 * Sets the web master.
	 * 
	 * @param webMaster : The new web master
	 */
	public void setWebMaster(final String webMaster) {
		this.webMaster = webMaster;
	}

	/***************************************************************************
	 * @see com.rss_pion.database.dao.abstracts.NeedTranslationToBeSerializedObject#translateDaoToObject(java.lang.Object)
	 ***************************************************************************/
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
			    hoursList.add(Integer.parseInt(strHour));
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

	/***************************************************************************
	 * @see com.rss_pion.database.dao.abstracts.NeedTranslationToBeSerializedObject#translateObjectToDao(java.lang.Long[])
	 ***************************************************************************/
	@Override
	public Object translateObjectToDao(final Long... ids)
			throws IllegalAccessException, IllegalArgumentException {
		final FluxDAO fluxDAO = new FluxDAO();
		fluxDAO.setIdCloud(this.getCloud().insertInTheDataBase());
		fluxDAO.setCopyright(this.getCopyright());
		fluxDAO.setDescription(this.getDescription());
		fluxDAO.setDocs(this.getDocs());
		fluxDAO.setFeed(this.getFeed());
		fluxDAO.setGenerator(this.getGenerator());
		fluxDAO.setIdImage(this.getImage().insertInTheDataBase());
		fluxDAO.setLanguage(this.getLanguage());
		fluxDAO.setLastBuildDate(this.getLastBuildDate());
		fluxDAO.setLink(this.getLink());
		fluxDAO.setManagingEditor(this.getManagingEditor());
		fluxDAO.setNumberOfArticles(this.getNumberOfArticles());
		fluxDAO.setNumberOfReadArticles(this.getNumberOfReadArticles());
		fluxDAO.setOwnRate(this.getOwnRate());
		fluxDAO.setPubDate(this.getPubDate());
		fluxDAO.setRating(this.getRating());
		fluxDAO.setSkipDays(this.getSkipDays().toString()
				.replace("[", "").replace("]", "").replace(", ", "/"));
		fluxDAO.setSkipHours(this.getSkipHours().toString()
				.replace("[", "").replace("]", "").replace(", ", "/"));
		final TextInputDAO textInputDAO = (TextInputDAO) this.getTextInput()
				.translateObjectToDao();
		fluxDAO.setIdTextInput(textInputDAO.insertInTheDataBase());
		fluxDAO.setTitle(this.getTitle());
		fluxDAO.setTtl(this.getTtl());
		fluxDAO.setWebMaster(this.getWebMaster());
		fluxDAO.setUrlImage(this.getUrlImage());
		return fluxDAO;
	}

    public void addArticle(Article article) {
        this.articles.add(article);
    }

    public void addCategory(CategoryFlux category) {
        this.categories.add(category);
    }

    public void addSkipDay(String day) {
        this.skipDays.add(day);
    }

    public void addSkipHour(int hour) {
        this.skipHours.add(hour);
        
    }
}
