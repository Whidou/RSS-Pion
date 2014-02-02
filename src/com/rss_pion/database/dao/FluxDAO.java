/***************************************************************************//**
 * @file FluxDAO.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * Interface BDD pour les objets flux
 ******************************************************************************/

package com.rss_pion.database.dao;

/*** INCLUDES *****************************************************************/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.ContentValues;
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

/*** MAIN CLASS ***************************************************************/

public class FluxDAO extends SerializedObject {

/*** STATIC ATTRIBUTES ********************************************************/

	//! Table
	public static String nameOfTheAssociatedTable = "FLUX_IT";

	//! Champs
	public static ArrayList<String[]> fieldsOfTheAssociatedTable;
	static {
		FluxDAO.fieldsOfTheAssociatedTable = new ArrayList<String[]>();
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "copyright",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "description",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "docs",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "feed",
				"TEXT NOT NULL" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "generator",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "idCloud",
				"INTEGER" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "idImage",
				"INTEGER" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "idTextInput",
				"INTEGER" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "language",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "lastBuildDate",
				"INTEGER" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "link",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "managingEditor",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] {
				"numberOfArticles", "INTEGER" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] {
				"numberOfReadArticles", "INTEGER" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "ownRate",
				"INTEGER" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "pubDate",
				"INTEGER" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "rating",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "skipDays",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "skipHours",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "title",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "ttl",
				"INTEGER" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "webMaster",
				"TEXT" });
		FluxDAO.fieldsOfTheAssociatedTable.add(new String[] { "urlImage",
				"TEXT" });
	}

/*** STATIC METHODS ***********************************************************/

/***************************************************************************//**
 * Supprime les articles de la BDD
 * 
 * @param idFather  Numéro d'identification du père
 ******************************************************************************/
	public static void deleteArticlesInTheDataBase(final Long idFather) {

		final String query = "SELECT * FROM " +
				ArticleDAO.nameOfTheAssociatedTable + " WHERE idFather = " +
				idFather;
		final Cursor c1 = Constants.sqlHandler.selectQuery(query);

		if ((c1 != null) && (c1.getCount() != 0)) {
			if (c1.moveToFirst()) {
				do {

					final ArticleDAO article = new ArticleDAO();

					article.setIdEnclosure(c1.getLong(c1
							.getColumnIndex("enclosure")));
					article.setIdGuid(c1.getLong(c1
							.getColumnIndex("guid")));
                    article.setIdGuid(c1.getLong(c1
                            .getColumnIndex("id")));

					// Suppression de la pièce jointe
					Constants.sqlHandler.deleteDAO(
							Enclosure.nameOfTheAssociatedTable,
							article.getIdEnclosure());

                    // Suppression du GUID
					Constants.sqlHandler.deleteDAO(
							Guid.nameOfTheAssociatedTable,
							article.getIdGuid());

                    // Suppression de l'article
					Constants.sqlHandler.deleteDAO(
							ArticleDAO.nameOfTheAssociatedTable,
							article.getId());
				} while (c1.moveToNext());
			}
		}
		c1.close();
	}

/***************************************************************************//**
 * Supprime un flux de la BDD
 * 
 * @param id    Numéro d'identification du flux
 ******************************************************************************/
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
					fluxDAO.setIdTextInput(Long.parseLong(c1.getString(c1
							.getColumnIndex("idTextInput"))));

					// Suppression du cloud
					Constants.sqlHandler.deleteDAO(
							Cloud.nameOfTheAssociatedTable,
							fluxDAO.getIdCloud());

                    // Suppression de l'image
					Constants.sqlHandler.deleteDAO(
							ImageDAO.nameOfTheAssociatedTable,
							fluxDAO.getIdImage());

                    // Suppression du text input
					TextInputDAO.deleteTextInputInTheDataBase(fluxDAO
							.getIdTextInput());

					// Suppression des articles
					FluxDAO.deleteArticlesInTheDataBase(id);

                    // Suppression des catégories
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
								Constants.sqlHandler.deleteDAO(
								        CategoryFluxDAO.nameOfTheAssociatedTable,
								        category.getId());
							} while (cCategories.moveToNext());
						}
					}
					cCategories.close();

                    // Suppression du flux
					Constants.sqlHandler.deleteDAO(
							FluxDAO.nameOfTheAssociatedTable,
							id);
				} while (c1.moveToNext());
			}
		}
		c1.close();
	}
    public static List<Flux> getFluxFromDB(Cursor c) {

        final List<Flux> listeFlux = new ArrayList<Flux>();
        Cursor c2;
        Flux flux;
        Long id;
        List<String> days = new ArrayList<String>();
        List<String> categories = new ArrayList<String>();
        List<Integer> hours = new ArrayList<Integer>();

        if ((c != null) && (c.getCount() != 0)) {
            if (c.moveToFirst()) {
                do {

                    flux = new Flux();
                    
                    id = c.getLong(c.getColumnIndex("id"));

                    // Configuration du flux à partir des données
                    flux.setId(id);
                    flux.setCopyright(c.getString(
                            c.getColumnIndex("copyright")));
                    flux.setDescription(c.getString(
                            c.getColumnIndex("description")));
                    flux.setDocs(c.getString(c.getColumnIndex("docs")));
                    flux.setFeed(c.getString(c.getColumnIndex("feed")));
                    flux.setGenerator(c.getString(
                            c.getColumnIndex("generator")));
                    flux.setLanguage(c.getString(
                            c.getColumnIndex("language")));
                    flux.setLastBuildDate(c.getLong(
                            c.getColumnIndex("lastBuildDate")));
                    flux.setLink(c.getString(c.getColumnIndex("link")));
                    flux.setManagingEditor(c.getString(
                            c.getColumnIndex("managingEditor")));
                    flux.setOwnRate(c.getInt(c.getColumnIndex("ownRate")));
                    flux.setPubDate(c.getLong(c.getColumnIndex("pubDate")));
                    flux.setRating(c.getString(c.getColumnIndex("rating")));
                    flux.setTitle(c.getString(c.getColumnIndex("title")));
                    flux.setTtl(c.getInt(c.getColumnIndex("ttl")));
                    flux.setWebMaster(c.getString(
                            c.getColumnIndex("webMaster")));
                    flux.setUrlImage(c.getString(
                            c.getColumnIndex("urlImage")));

                    // Séparation des jours
                    for (String day : c.getString(
                            c.getColumnIndex("skipDays")).split("/")) {
                        days.add(day);
                    }
                    flux.setSkipDays(days);

                    // Séparation des catégories
                    for (String category : c.getString(
                            c.getColumnIndex("skipDays")).split("/")) {
                        categories.add(category);
                    }
                    flux.setCategories(categories);

                    // Séparation des heures
                    for (String hour : c.getString(
                            c.getColumnIndex("skipHours")).split("/")) {
                        try {
                            hours.add(Integer.parseInt(hour));
                        } catch (NumberFormatException e) {
                            // Les heures mal formattées sont ignorées
                        }
                    }
                    flux.setSkipHours(hours);

                    flux.setCloud(new Cloud(c.getLong(
                            c.getColumnIndex("idCloud"))));
/*
                    flux.setImage(new ImageDAO(c.getLong(
                            c.getColumnIndex("idImage"))));
                    flux.setTextInput(new TextInput(c.getLong(
                            c.getColumnIndex("idTextInput"))));
*/

                    // Comptage des articles
                    c2 = Constants.sqlHandler.query(
                            ArticleDAO.nameOfTheAssociatedTable,
                            null,
                            "idFather=?",
                            new String[] {id.toString()},
                            null,
                            null,
                            null,
                            null);
                    flux.setNumberOfArticles(c2.getCount());

                    // Comptage des articles lus
                    c2 = Constants.sqlHandler.query(
                            ArticleDAO.nameOfTheAssociatedTable,
                            null,
                            "idFather=? AND isRead=1",
                            new String[] {id.toString()},
                            null,
                            null,
                            null,
                            null);
                    flux.setNumberOfReadArticles(c2.getCount());

                    // Ajout du flux à la liste
                    listeFlux.add(flux);
                } while (c.moveToNext());
            }
        }
        c.close();

        return listeFlux;
    }

/***************************************************************************//**
 * Retourne une liste d'interfaces, une pour chaque flux dans la BDD.
 * 
 * @return  Liste d'interfaces.
 ******************************************************************************/
	public static List<Flux> getFluxFromDB() {

		final Cursor c = Constants.sqlHandler.query(
		        FluxDAO.nameOfTheAssociatedTable,
		        null,
		        null,
		        null,
		        null,
		        null,
		        null,
		        null);

		return getFluxFromDB(c);
	}

/***************************************************************************//**
 * Retourne l'interface d'un flux à partir de son ID
 * 
 * @param id    Numéro d'identification du flux
 * 
 * @return      Interface du flux
 ******************************************************************************/
	public static Flux getFluxFromDB(final Long id) {

        final Cursor c = Constants.sqlHandler.query(
                FluxDAO.nameOfTheAssociatedTable,
                null,
                "id=?",
                new String[] {id.toString()},
                null,
                null,
                null,
                null);

        return getFluxFromDB(c).get(0);
	}

/*** ATTRIBUTES ***************************************************************/

    //! Numéro d'indentification
    private Long id;

    //! URL
    private String feed;

    //! Titre
    private String title;

    //! Lien
    private String link;

    //! Description
    private String description;

    //! Langue
    private String language;

    //! Copyright
    private String copyright;

    //! Courriel de l'éditeur
    private String managingEditor;

    //! Courriel du webmestre
    private String webMaster;

    //! Date de dernière publication
    private Long pubDate;

    //! Date de dernière génération
    private Long lastBuildDate;

    //! Catégories
    private List<CategoryFlux> categories;

    //! Logiciel ayant généré le flux
    private String generator;

    //! URL vers les documentations associées
    private String docs;

    //! Numéro d'identification de l'objet cloud associé
    private Long idCoud;

    //! Durée (en minutes) de validité
    private Integer ttl;

    //! Numéro d'identification de l'objet image associé
    private Long idImage;

    //! Classement PICS
    private String rating;

    //! Numéro d'identification de l'objet text input associé
    private Long idTextInput;

    //! Heures de non-mise à jour
    private String skipHours;

    //! Jours de non-mise à jour
    private String skipDays;

    //! Nombre d'articles lus
    private Integer numberOfReadArticles;

    //! Nombre d'articles
    private Integer numberOfArticles;

    //! ???
    private Integer ownRate;

    //! URL de l'image associée
    private String urlImage;

    //! Valeurs
    private ContentValues valuesMap;

/*** METHODS ******************************************************************/

	public FluxDAO() {
		super();
	}

	public FluxDAO(final String feed, final String title, final String link,
			final String description, final String language,
			final String copyright, final String managingEditor,
			final String webMaster, final Long pubDate,
			final Long lastBuildDate, final List<CategoryFlux> categories,
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
		
		this.valuesMap = new ContentValues();
		this.valuesMap.put("copyright", copyright);
        this.valuesMap.put("description", description);
        this.valuesMap.put("docs", docs);
        this.valuesMap.put("feed", feed);
        this.valuesMap.put("generator", generator);
        this.valuesMap.put("idCloud", cloud);
        this.valuesMap.put("idImage", image);
        this.valuesMap.put("idTextInput", textInput);
        this.valuesMap.put("language", language);
        this.valuesMap.put("lastBuildDate", lastBuildDate);
        this.valuesMap.put("link", link);
        this.valuesMap.put("managingEditor", managingEditor);
        this.valuesMap.put("numberOfArticles", numberOfArticles);
        this.valuesMap.put("numberOfReadArticles", numberOfReadArticles);
        this.valuesMap.put("ownRate", ownRate);
        this.valuesMap.put("pubDate", pubDate);
        this.valuesMap.put("rating", rating);
        this.valuesMap.put("skipDays", skipDays);
        this.valuesMap.put("skipHours", skipHours);
        this.valuesMap.put("title", title);
        this.valuesMap.put("ttl", ttl);
        this.valuesMap.put("webMaster", webMaster);
        this.valuesMap.put("urlImage", urlImage);
	}

/***************************************************************************//**
 * Retourne une liste d'interfaces, une pour chaque article du flux
 * 
 * @return  Liste d'interfaces d'articles
 ******************************************************************************/
    public ArrayList<ArticleDAO> getArticlesDAO() {

        final String query = "SELECT * FROM " +
                ArticleDAO.nameOfTheAssociatedTable +
                " WHERE idFather = " +
                this.getId() +
                ";";
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

    public TextInputDAO getTextInputDAO() {
        final String query = "SELECT * FROM "
                + TextInputDAO.nameOfTheAssociatedTable + " WHERE id = "
                + this.idTextInput;
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

/***************************************************************************
 * @see com.rss_pion.database.dao.abstracts.SerializedObject#insertInTheDataBase()
 ***************************************************************************/
    @Override
    public Long insertInTheDataBase(final Object... objects)
            throws IllegalAccessException, IllegalArgumentException {
/*
        String names = "";

        final Iterator<String[]> it = FluxDAO.fieldsOfTheAssociatedTable
                .iterator();
        while (it.hasNext()) {
            names += it.next()[0] + (it.hasNext() ? ", " : ")");
        }
*/
        Constants.sqlHandler.insert(FluxDAO.nameOfTheAssociatedTable,
                null,
                this.valuesMap);
/*
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
*/
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
/*
        final Iterator<String> itCategory = ((Flux) objects[0])
                .getCategories().iterator();
        while (itCategory.hasNext()) {
            final CategoryFluxDAO categoryDAO = (CategoryFluxDAO) itCategory
                    .next().translateObjectToDao(id);
            categoryDAO.insertInTheDataBase();
        }
*/
        Log.d("FLUX ADDED", this.toString());
        return id;
    }

    public List<CategoryFlux> getCategories() {
        return this.categories;
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

    public Long getIdCloud() {
        return this.idCoud;
    }

    public Long getIdImage() {
        return this.idImage;
    }

    public Long getIdTextInput() {
        return this.idTextInput;
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

    public String getSkipDays() {
        return this.skipDays;
    }

    public String getSkipHours() {
        return this.skipHours;
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

    public void setCategories(final List<CategoryFlux> categories) {
        this.categories = categories;
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

    public void setIdCloud(final Long idCloud) {
        this.idCoud = idCloud;
    }

    public void setIdImage(final long l) {
        this.idImage = l;
    }

    public void setIdTextInput(final Long idTextInput) {
        this.idTextInput = idTextInput;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public void setLastBuildDate(final Long lastBuildDate2) {
        this.lastBuildDate = lastBuildDate2;
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

    public void setPubDate(final Long pubDate2) {
        this.pubDate = pubDate2;
    }

    public void setRating(final String rating) {
        this.rating = rating;
    }

    public void setSkipDays(final String skipDays) {
        this.skipDays = skipDays;
    }

    public void setSkipHours(final String skipHours) {
        this.skipHours = skipHours;
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

/***************************************************************************//**
 * @see java.lang.Object#toString()
 ******************************************************************************/
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
