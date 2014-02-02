/***************************************************************************
 * @file FluxDAO.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ***************************************************************************/
package com.rss_pion.database.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.rss_pion.beans.Article;
import com.rss_pion.beans.CategoryFlux;
import com.rss_pion.beans.Cloud;
import com.rss_pion.beans.Enclosure;
import com.rss_pion.beans.Flux;
import com.rss_pion.beans.Guid;
import com.rss_pion.configuration.Constants;
import com.rss_pion.database.SqlDbHelper;
import com.rss_pion.database.dao.abstracts.SerializedObject;

// TODO: Auto-generated Javadoc
/**
 * The Class FluxDAO.
 */
public class FluxDAO extends SerializedObject {

	/** The name of the associated table. */
	public static String nameOfTheAssociatedTable = "FLUX_IT";

	/** The fields of the associated table. */
	public static ArrayList<String[]> fieldsOfTheAssociatedTable;
	static {
		FluxDAO.fieldsOfTheAssociatedTable = new ArrayList<String[]>();
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "copyright",
				"TEXT NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "description",
				"TEXT NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "docs",
				"TEXT NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "feed",
				"TEXT NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "generator",
				"TEXT NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "idCloud",
				"INTEGER NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "idImage",
				"INTEGER NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "idTextInput",
				"INTEGER NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "language",
				"TEXT NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "lastBuildDate",
				"TEXT NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "link",
				"TEXT NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "managingEditor",
				"TEXT NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] {
				"numberOfArticles", "INTEGER NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] {
				"numberOfReadArticles", "INTEGER NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "ownRate",
				"INTEGER NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "pubDate",
				"TEXT NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "rating",
				"TEXT NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "skipDays",
				"TEXT NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "skipHours",
				"TEXT NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "title",
				"TEXT NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "ttl",
				"INTEGER NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "webMaster",
				"TEXT NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "urlImage",
				"TEXT NOT NULL" });
	}

	/**
	 * Delete articles in the data base.
	 * 
	 * @param idFather : The id father
	 */
	public static void deleteArticlesInTheDataBase(final Long idFather) {
		final String query = "SELECT * FROM "
				+ ArticleDAO.nameOfTheAssociatedTable + " WHERE idFather = "
				+ idFather;
		final Cursor c1 = Constants.sqlHandler.selectQuery(query);
		if ((c1 != null) && (c1.getCount() != 0)) {
			if (c1.moveToFirst()) {
				do {
					final ArticleDAO article = new ArticleDAO();
					article.setIdEnclosure(Long.parseLong(c1.getString(c1
							.getColumnIndex("enclosure"))));
					article.setIdGuid(Long.parseLong(c1.getString(c1
							.getColumnIndex("guid"))));
					article.setId(Long.parseLong(c1.getString(c1
							.getColumnIndex("id"))));
					Constants.sqlHandler.deleteDAO(
							Enclosure.nameOfTheAssociatedTable,
							article.getIdEnclosure());
					Constants.sqlHandler.deleteDAO(
							Guid.nameOfTheAssociatedTable, article.getIdGuid());
					Constants.sqlHandler.deleteDAO(
							ArticleDAO.nameOfTheAssociatedTable,
							article.getId());
				} while (c1.moveToNext());
			}
		}
		c1.close();
	}

	/**
	 * Delete flux in the data base.
	 * 
	 * @param id : The id
	 */
	public static void deleteFluxInTheDataBase(final Long id) {
		final String query = "SELECT * FROM "
				+ FluxDAO.nameOfTheAssociatedTable + " WHERE id = " + id;
		final Cursor c1 = Constants.sqlHandler.selectQuery(query);
		if ((c1 != null) && (c1.getCount() != 0)) {
			if (c1.moveToFirst()) {
				do {
					final FluxDAO fluxDAO = new FluxDAO();
					fluxDAO.setIdCloud(Long.parseLong(c1.getString(c1
							.getColumnIndex("idCloud"))));
					fluxDAO.setIdImage(Long.parseLong(c1.getString(c1
							.getColumnIndex("idImage"))));
					fluxDAO.setId(Long.parseLong(c1.getString(c1
							.getColumnIndex("id"))));
					fluxDAO.setIdTextInput(Long.parseLong(c1.getString(c1
							.getColumnIndex("idTextInput"))));
					Constants.sqlHandler.deleteDAO(
							Cloud.nameOfTheAssociatedTable,
							fluxDAO.getIdCloud());
					Constants.sqlHandler.deleteDAO(
							ImageDAO.nameOfTheAssociatedTable,
							fluxDAO.getIdImage());
					TextInputDAO.deleteTextInputInTheDataBase(fluxDAO
							.getIdTextInput());
					final String queryArticles = "SELECT * FROM "
							+ ArticleDAO.nameOfTheAssociatedTable
							+ " WHERE idFather = " + id;
					final Cursor cArticles = Constants.sqlHandler
							.selectQuery(queryArticles);
					if ((cArticles != null) && (cArticles.getCount() != 0)) {
						if (cArticles.moveToFirst()) {
							do {
								final ArticleDAO article = new ArticleDAO();
								article.setIdEnclosure(Long.parseLong(cArticles
										.getString(cArticles
												.getColumnIndex("idEnclosure"))));
								article.setIdGuid(Long.parseLong(cArticles
										.getString(cArticles
												.getColumnIndex("idGuid"))));
								article.setId(Long.parseLong(cArticles
										.getString(cArticles
												.getColumnIndex("id"))));
								Constants.sqlHandler.deleteDAO(
										Enclosure.nameOfTheAssociatedTable,
										article.getIdEnclosure());
								Constants.sqlHandler.deleteDAO(
										Guid.nameOfTheAssociatedTable,
										article.getIdGuid());
								Constants.sqlHandler.deleteDAO(
										ArticleDAO.nameOfTheAssociatedTable,
										article.getId());
								ArticleDAO.deleteArticleInTheDataBase(article
										.getId());
							} while (cArticles.moveToNext());
						}
					}
					cArticles.close();
					final String queryCategories = "SELECT * FROM "
							+ CategoryFluxDAO.nameOfTheAssociatedTable
							+ " WHERE idFather = " + id;
					final Cursor cCategories = Constants.sqlHandler
							.selectQuery(queryCategories);
					if ((cCategories != null) && (cCategories.getCount() != 0)) {
						if (cCategories.moveToFirst()) {
							do {
								final CategoryFluxDAO category = new CategoryFluxDAO();
								category.setId(Long.parseLong(cCategories
										.getString(cCategories
												.getColumnIndex("id"))));
								Constants.sqlHandler
										.deleteDAO(
												CategoryFluxDAO.nameOfTheAssociatedTable,
												category.getId());
							} while (cCategories.moveToNext());
						}
					}
					cCategories.close();
					Constants.sqlHandler.deleteDAO(
							FluxDAO.nameOfTheAssociatedTable, id);
				} while (c1.moveToNext());
			}
		}
		c1.close();
	}

	/**
	 * Gets the flux dao.
	 * 
	 * @return The flux dao
	 */
	public static ArrayList<FluxDAO> getFluxDAO() {
		final String query = "SELECT * FROM "
				+ FluxDAO.nameOfTheAssociatedTable + ";";
		final Cursor c1 = Constants.sqlHandler.selectQuery(query);
		final ArrayList<FluxDAO> flux_list = new ArrayList<FluxDAO>();
		if ((c1 != null) && (c1.getCount() != 0)) {
			if (c1.moveToFirst()) {
				do {
					final FluxDAO fluxDAO = new FluxDAO();
					fluxDAO.setIdCloud(Long.parseLong(c1.getString(c1
							.getColumnIndex("idCloud"))));
					fluxDAO.setCopyright(c1.getString(c1
							.getColumnIndex("copyright")));
					fluxDAO.setDescription(c1.getString(c1
							.getColumnIndex("description")));
					fluxDAO.setDocs(c1.getString(c1.getColumnIndex("docs")));
					fluxDAO.setFeed(c1.getString(c1.getColumnIndex("feed")));
					fluxDAO.setGenerator(c1.getString(c1
							.getColumnIndex("generator")));
					fluxDAO.setId(Long.parseLong(c1.getString(c1
							.getColumnIndex("id"))));
					fluxDAO.setIdImage(Long.parseLong(c1.getString(c1
							.getColumnIndex("idImage"))));
					fluxDAO.setLanguage(c1.getString(c1
							.getColumnIndex("language")));
					fluxDAO.setLastBuildDate(c1.getString(c1
							.getColumnIndex("lastBuildDate")));
					fluxDAO.setLink(c1.getString(c1.getColumnIndex("link")));
					fluxDAO.setManagingEditor(c1.getString(c1
							.getColumnIndex("managingEditor")));
					fluxDAO.setNumberOfArticles(Integer.parseInt(c1
							.getString(c1.getColumnIndex("numberOfArticles"))));
					fluxDAO.setNumberOfReadArticles(Integer.parseInt(c1
							.getString(c1
									.getColumnIndex("numberOfReadArticles"))));
					fluxDAO.setOwnRate(Integer.parseInt(c1.getString(c1
							.getColumnIndex("ownRate"))));
					fluxDAO.setPubDate(c1.getString(c1
							.getColumnIndex("pubDate")));
					fluxDAO.setRating(c1.getString(c1.getColumnIndex("rating")));
					fluxDAO.setSkipDays(c1.getString(c1
							.getColumnIndex("skipDays")));
					fluxDAO.setSkipHours(c1.getString(c1
							.getColumnIndex("skipHours")));
					fluxDAO.setIdTextInput(Long.parseLong(c1.getString(c1
							.getColumnIndex("idTextInput"))));
					fluxDAO.setTitle(c1.getString(c1.getColumnIndex("title")));
					fluxDAO.setTtl(Integer.parseInt(c1.getString(c1
							.getColumnIndex("ttl"))));
					fluxDAO.setWebMaster(c1.getString(c1
							.getColumnIndex("webMaster")));
					fluxDAO.setUrlImage(c1.getString(c1
							.getColumnIndex("urlImage")));
					flux_list.add(fluxDAO);
				} while (c1.moveToNext());
			}
		}
		c1.close();
		return flux_list;
	}

	/**
	 * Gets the flux dao.
	 * 
	 * @param id : The id
	 * @return The flux dao
	 */
	public static FluxDAO getFluxDAO(final Long id) {
		final String query = "SELECT * FROM "
				+ FluxDAO.nameOfTheAssociatedTable + " WHERE id = " + id + ";";
		final Cursor c1 = Constants.sqlHandler.selectQuery(query);
		final FluxDAO fluxDAO = new FluxDAO();
		if ((c1 != null) && (c1.getCount() != 0)) {
			if (c1.moveToFirst()) {
				do {
					fluxDAO.setIdCloud(Long.parseLong(c1.getString(c1
							.getColumnIndex("idCloud"))));
					fluxDAO.setCopyright(c1.getString(c1
							.getColumnIndex("copyright")));
					fluxDAO.setDescription(c1.getString(c1
							.getColumnIndex("description")));
					fluxDAO.setDocs(c1.getString(c1.getColumnIndex("docs")));
					fluxDAO.setFeed(c1.getString(c1.getColumnIndex("feed")));
					fluxDAO.setGenerator(c1.getString(c1
							.getColumnIndex("generator")));
					fluxDAO.setId(Long.parseLong(c1.getString(c1
							.getColumnIndex("id"))));
					fluxDAO.setIdImage(Long.parseLong(c1.getString(c1
							.getColumnIndex("idImage"))));
					fluxDAO.setLanguage(c1.getString(c1
							.getColumnIndex("language")));
					fluxDAO.setLastBuildDate(c1.getString(c1
							.getColumnIndex("lastBuildDate")));
					fluxDAO.setLink(c1.getString(c1.getColumnIndex("link")));
					fluxDAO.setManagingEditor(c1.getString(c1
							.getColumnIndex("managingEditor")));
					fluxDAO.setNumberOfArticles(Integer.parseInt(c1
							.getString(c1.getColumnIndex("numberOfArticles"))));
					fluxDAO.setNumberOfReadArticles(Integer.parseInt(c1
							.getString(c1
									.getColumnIndex("numberOfReadArticles"))));
					fluxDAO.setOwnRate(Integer.parseInt(c1.getString(c1
							.getColumnIndex("ownRate"))));
					fluxDAO.setPubDate(c1.getString(c1
							.getColumnIndex("pubDate")));
					fluxDAO.setRating(c1.getString(c1.getColumnIndex("rating")));
					fluxDAO.setSkipDays(c1.getString(c1
							.getColumnIndex("skipDays")));
					fluxDAO.setSkipHours(c1.getString(c1
							.getColumnIndex("skipHours")));
					fluxDAO.setIdTextInput(Long.parseLong(c1.getString(c1
							.getColumnIndex("idTextInput"))));
					fluxDAO.setTitle(c1.getString(c1.getColumnIndex("title")));
					fluxDAO.setTtl(Integer.parseInt(c1.getString(c1
							.getColumnIndex("ttl"))));
					fluxDAO.setWebMaster(c1.getString(c1
							.getColumnIndex("webMaster")));
					fluxDAO.setUrlImage(c1.getString(c1
							.getColumnIndex("urlImage")));
				} while (c1.moveToNext());
			}
		}
		c1.close();
		return fluxDAO;
	}

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

	/** The category. */
	private List<CategoryFlux> categories;

	/** The generator. */
	private String generator;

	/** The docs. */
	private String docs;

	/** The id coud. */
	private Long idCoud;

	/** The ttl. */
	private Integer ttl;

	/** The id image. */
	private Long idImage;

	/** The rating. */
	private String rating;

	/** The id text input. */
	private Long idTextInput;

	/** The skip hours. */
	private String skipHours;

	/** The skip days. */
	private String skipDays;

	/** The number of read articles. */
	private Integer numberOfReadArticles;

	/** The number of articles. */
	private Integer numberOfArticles;

	/** The own rate. */
	private Integer ownRate;

	/** The url image. */
	private String urlImage;

	/**
	 * Instantiates a new flux dao.
	 */
	public FluxDAO() {
		super();
	}

	/**
	 * Instantiates a new flux dao.
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
	 */
	public FluxDAO(final String feed, final String title, final String link,
			final String description, final String language,
			final String copyright, final String managingEditor,
			final String webMaster, final String pubDate,
			final String lastBuildDate, final List<CategoryFlux> categories,
			final String generator, final String docs, final Long cloud,
			final Integer ttl, final Long image, final String rating,
			final Long textInput, final String skipHours,
			final String skipDays, final Integer numberOfReadArticles,
			final Integer numberOfArticles, final Integer ownRate,
			final String urlImage) {
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
		this.idCoud = cloud;
		this.ttl = ttl;
		this.idImage = image;
		this.rating = rating;
		this.idTextInput = textInput;
		this.skipHours = skipHours;
		this.skipDays = skipDays;
		this.numberOfReadArticles = numberOfReadArticles;
		this.numberOfArticles = numberOfArticles;
		this.ownRate = ownRate;
		this.urlImage = urlImage;
	}

	/**
	 * Gets the articles dao.
	 * 
	 * @return The articles dao
	 */
	public ArrayList<ArticleDAO> getArticlesDAO() {
		final String query = "SELECT * FROM "
				+ ArticleDAO.nameOfTheAssociatedTable + " WHERE idFather = "
				+ this.getId() + ";";
		final Cursor c1 = Constants.sqlHandler.selectQuery(query);
		final ArrayList<ArticleDAO> articles = new ArrayList<ArticleDAO>();
		if ((c1 != null) && (c1.getCount() != 0)) {
			if (c1.moveToFirst()) {
				do {
					final ArticleDAO article = new ArticleDAO();
					article.setId(Long.parseLong(c1.getString(c1
							.getColumnIndex("id"))));
					article.setComments(c1.getString(c1
							.getColumnIndex("comments")));
					article.setDescription(c1.getString(c1
							.getColumnIndex("description")));
					article.setIdEnclosure(Long.parseLong(c1.getString(c1
							.getColumnIndex("idEnclosure"))));
					article.setIdGuid(Long.parseLong(c1.getString(c1
							.getColumnIndex("idGuid"))));
					article.setIdFather(Long.parseLong(c1.getString(c1
							.getColumnIndex("idFather"))));
					article.setIsRead(Integer.parseInt(c1.getString(c1
							.getColumnIndex("isRead"))));
					article.setLink(c1.getString(c1.getColumnIndex("link")));
					article.setPubDate(c1.getString(c1
							.getColumnIndex("pubDate")));
					article.setSource(c1.getString(c1.getColumnIndex("source")));
					article.setTitle(c1.getString(c1.getColumnIndex("title")));
					article.setUserRate(Integer.parseInt(c1.getString(c1
							.getColumnIndex("userRate"))));
					articles.add(article);
				} while (c1.moveToNext());
			}
		}
		c1.close();
		return articles;
	}

	/**
	 * Gets the categories.
	 * 
	 * @return The category
	 */
	public List<CategoryFlux> getCategories() {
		return this.categories;
	}

	public ArrayList<CategoryFluxDAO> getCategoriesDAO() {
		final String query = "SELECT * FROM "
				+ CategoryFluxDAO.nameOfTheAssociatedTable
				+ " WHERE idFather = " + this.getId() + ";";
		final Cursor c1 = Constants.sqlHandler.selectQuery(query);
		final ArrayList<CategoryFluxDAO> categories = new ArrayList<CategoryFluxDAO>();
		if ((c1 != null) && (c1.getCount() != 0)) {
			if (c1.moveToFirst()) {
				do {
					final CategoryFluxDAO category = new CategoryFluxDAO();
					category.setId(Long.parseLong(c1.getString(c1
							.getColumnIndex("id"))));
					category.setCategory(c1.getString(c1
							.getColumnIndex("category")));
					category.setIdFather(Long.parseLong(c1.getString(c1
							.getColumnIndex("idFather"))));
					categories.add(category);
				} while (c1.moveToNext());
			}
		}
		c1.close();
		return categories;
	}

	/**
	 * Gets the cloud.
	 * 
	 * @return The cloud
	 */
	public Cloud getCloud() {
		final String query = "SELECT * FROM " + Cloud.nameOfTheAssociatedTable
				+ " WHERE id = " + this.getIdCloud();
		final Cursor c1 = Constants.sqlHandler.selectQuery(query);
		final Cloud cloud = new Cloud();
		if ((c1 != null) && (c1.getCount() != 0)) {
			if (c1.moveToFirst()) {
				do {
					cloud.setDomain(c1.getString(c1.getColumnIndex("domain")));
					cloud.setPath(c1.getString(c1.getColumnIndex("path")));
					cloud.setPort(Integer.parseInt(c1.getString(c1
							.getColumnIndex("port"))));
					cloud.setProtocol(c1.getString(c1
							.getColumnIndex("protocol")));
					cloud.setRegisterProcedure(c1.getString(c1
							.getColumnIndex("registerProcedure")));
				} while (c1.moveToNext());
			}
		}
		c1.close();
		return cloud;
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
	 * Gets the id cloud.
	 * 
	 * @return The id cloud
	 */
	public Long getIdCloud() {
		return this.idCoud;
	}

	/**
	 * Gets the id image.
	 * 
	 * @return The id image
	 */
	public Long getIdImage() {
		return this.idImage;
	}

	/**
	 * Gets the id text input.
	 * 
	 * @return The id text input
	 */
	public Long getIdTextInput() {
		return this.idTextInput;
	}

	/**
	 * Gets the image.
	 * 
	 * @return The image
	 */
	public ImageDAO getImage() {
		final String query = "SELECT * FROM "
				+ ImageDAO.nameOfTheAssociatedTable + " WHERE id = "
				+ this.getIdImage() + ";";
		final Cursor c1 = Constants.sqlHandler.selectQuery(query);
		final ImageDAO imageDAO = new ImageDAO();
		if ((c1 != null) && (c1.getCount() != 0)) {
			if (c1.moveToFirst()) {
				do {
					final byte[] imageByte = c1
							.getBlob(c1
									.getColumnIndex(ImageDAO.fieldsOfTheAssociatedTable[0]));
					imageDAO.setImage(BitmapFactory.decodeByteArray(imageByte,
							0, imageByte.length));
				} while (c1.moveToNext());
			}
		}
		c1.close();
		return imageDAO;
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
	public String getSkipDays() {
		return this.skipDays;
	}

	/**
	 * Gets the skip hours.
	 * 
	 * @return The skip hours
	 */
	public String getSkipHours() {
		return this.skipHours;
	}

	/**
	 * Gets the text input dao.
	 * 
	 * @return The text input dao
	 */
	public TextInputDAO getTextInputDAO() {
		final String query = "SELECT * FROM "
				+ TextInputDAO.nameOfTheAssociatedTable + " WHERE id = "
				+ this.getIdTextInput();
		final Cursor c1 = Constants.sqlHandler.selectQuery(query);
		final TextInputDAO textInput = new TextInputDAO();
		if ((c1 != null) && (c1.getCount() != 0)) {
			if (c1.moveToFirst()) {
				do {
					textInput.setName(c1.getString(c1
							.getColumnIndex("name")));
					textInput.setDescription(c1.getString(c1
							.getColumnIndex("description")));
					textInput.setLink(c1.getString(c1.getColumnIndex("link")));
					textInput
							.setTitle(c1.getString(c1.getColumnIndex("title")));
				} while (c1.moveToNext());
			}
		}
		c1.close();
		return textInput;
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

	/**
	 * Gets the url image.
	 * 
	 * @return The url image
	 */
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

	/***************************************************************************
	 * @see com.rss_pion.database.dao.abstracts.SerializedObject#insertInTheDataBase()
	 ***************************************************************************/
	@Override
	public Long insertInTheDataBase(final Object... objects)
			throws IllegalAccessException, IllegalArgumentException {
		String names = "";
		final Iterator<String[]> it = FluxDAO.fieldsOfTheAssociatedTable
				.iterator();
		while (it.hasNext()) {
			names += it.next()[0] + (it.hasNext() ? ", " : ")");
		}
		Constants.sqlHandler.executeQuery("INSERT INTO "
				+ FluxDAO.nameOfTheAssociatedTable + " (" + names
				+ " VALUES ('" + this.getCopyright() + "', '"
				+ this.getDescription() + "', '" + this.getDocs() + "', '"
				+ this.getFeed() + "', '" + this.getGenerator() + "', "
				+ this.getIdCloud() + ", " + this.getIdImage() + ", "
				+ this.getIdTextInput() + ", '" + this.getLanguage() + "', '"
				+ this.getLastBuildDate() + "', '" + this.getLink() + "', '"
				+ this.getManagingEditor() + "', " + this.getNumberOfArticles()
				+ ", " + this.getNumberOfReadArticles() + ", "
				+ this.getOwnRate() + ", '" + this.getPubDate() + "', '"
				+ this.getRating() + "', '" + this.getSkipDays() + "', '"
				+ this.getSkipHours() + "', '" + this.getTitle() + "', '"
				+ this.getTtl() + "', '" + this.getWebMaster() + "', '"
				+ this.getUrlImage() + "');");
		final Long id = SqlDbHelper
				.lastInsertId(FluxDAO.nameOfTheAssociatedTable);
		final Iterator<Article> itArticle = ((Flux) objects[0]).getArticles()
				.iterator();
		Article article;
		while (itArticle.hasNext()) {
			article = itArticle.next();
			final ArticleDAO articleDAO = (ArticleDAO) article
					.translateObjectToDao(id);
			articleDAO.insertInTheDataBase(article);
		}
		final Iterator<CategoryFlux> itCategory = ((Flux) objects[0])
				.getCategories().iterator();
		while (itCategory.hasNext()) {
			final CategoryFluxDAO categoryDAO = (CategoryFluxDAO) itCategory
					.next().translateObjectToDao(id);
			categoryDAO.insertInTheDataBase();
		}
		Log.d("FLUX ADDED", this.toString());
		return id;
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
	 * Sets the id cloud.
	 * 
	 * @param idCloud : The new id cloud
	 */
	public void setIdCloud(final Long idCloud) {
		this.idCoud = idCloud;
	}

	/**
	 * Sets the id image.
	 * 
	 * @param l : The new id image
	 */
	public void setIdImage(final long l) {
		this.idImage = l;
	}

	/**
	 * Sets the id text input.
	 * 
	 * @param idTextInput : The new id text input
	 */
	public void setIdTextInput(final Long idTextInput) {
		this.idTextInput = idTextInput;
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
	public void setSkipDays(final String skipDays) {
		this.skipDays = skipDays;
	}

	/**
	 * Sets the skip hours.
	 * 
	 * @param skipHours : The new skip hours
	 */
	public void setSkipHours(final String skipHours) {
		this.skipHours = skipHours;
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
	 * @param The url image
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
	 * @see java.lang.Object#toString()
	 ***************************************************************************/
	@Override
	public String toString() {
		return "FluxDAO [id=" + this.id + ", feed=" + this.feed + ", title="
				+ this.title + ", link=" + this.link + ", description="
				+ this.description + ", language=" + this.language
				+ ", copyright=" + this.copyright + ", managingEditor="
				+ this.managingEditor + ", webMaster=" + this.webMaster
				+ ", pubDate=" + this.pubDate + ", lastBuildDate="
				+ this.lastBuildDate + ", categories=" + this.categories
				+ ", generator=" + this.generator + ", docs=" + this.docs
				+ ", idCoud=" + this.idCoud + ", ttl=" + this.ttl
				+ ", idImage=" + this.idImage + ", rating=" + this.rating
				+ ", idTextInput=" + this.idTextInput + ", skipHours="
				+ this.skipHours + ", skipDays=" + this.skipDays
				+ ", numberOfReadArticles=" + this.numberOfReadArticles
				+ ", numberOfArticles=" + this.numberOfArticles + ", ownRate="
				+ this.ownRate + ", urlImage=" + this.urlImage + "]";
	}
}
