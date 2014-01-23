/***************************************************************************
 * @file ArticleDAO.java
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

import android.database.Cursor;

import com.rss_pion.beans.Enclosure;
import com.rss_pion.beans.Guid;
import com.rss_pion.configuration.Constants;
import com.rss_pion.database.SqlDbHelper;
import com.rss_pion.database.dao.abstracts.SerializedObject;

// TODO: Auto-generated Javadoc
/**
 * The Class ArticleDAO.
 */
public class ArticleDAO extends SerializedObject {

	/** The name of the associated table. */
	public static String nameOfTheAssociatedTable = "ARTICLE_IT";

	/** The fields of the associated table. */
	public static ArrayList<String[]> fieldsOfTheAssociatedTable;
	static {
		ArticleDAO.fieldsOfTheAssociatedTable = new ArrayList<String[]>();
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] { "author",
				"TEXT NOT NULL" });
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] { "category",
				"TEXT NOT NULL" });
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] { "comments",
				"TEXT NOT NULL" });
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] { "description",
				"TEXT NOT NULL" });
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] { "idEnclosure",
				"INTEGER NOT NULL" });
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] { "idFather",
				"INTEGER NOT NULL" });
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] { "idGuid",
				"INTEGER NOT NULL" });
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] { "isRead",
				"INTEGER NOT NULL" });
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] { "link",
				"TEXT NOT NULL" });
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] { "pubDate",
				"TEXT NOT NULL" });
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] { "source",
				"TEXT NOT NULL" });
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] { "title",
				"TEXT NOT NULL" });
		ArticleDAO.fieldsOfTheAssociatedTable.add(new String[] { "userRate",
				"INTEGER NOT NULL" });
	}

	/**
	 * Delete article in the data base.
	 * 
	 * @param id : The id
	 */
	public static void deleteArticleInTheDataBase(final Long id) {
		final String query = "SELECT * FROM "
				+ ArticleDAO.nameOfTheAssociatedTable + " WHERE id = " + id;
		final Cursor c1 = Constants.sqlHandler.selectQuery(query);
		if ((c1 != null) && (c1.getCount() != 0)) {
			if (c1.moveToFirst()) {
				do {
					final ArticleDAO article = new ArticleDAO();
					article.setIdEnclosure(Long.parseLong(c1.getString(c1
							.getColumnIndex("idEnclosure"))));
					article.setIdGuid(Long.parseLong(c1.getString(c1
							.getColumnIndex("idGuid"))));
					Constants.sqlHandler.deleteDAO(
							Enclosure.nameOfTheAssociatedTable,
							article.getIdEnclosure());
					Constants.sqlHandler.deleteDAO(
							Guid.nameOfTheAssociatedTable, article.getIdGuid());
					Constants.sqlHandler.deleteDAO(
							ArticleDAO.nameOfTheAssociatedTable, id);
				} while (c1.moveToNext());
			}
		}
		c1.close();
	}

	/** The author. */
	private String author;

	/** The category. */
	private String category;

	/** The comments. */
	private String comments;

	/** The description. */
	private String description;

	/** The id enclosure. */
	private Long idEnclosure;

	/** The id guid. */
	private Long idGuid;

	/** The id. */
	private Long id;

	/** The id father. */
	private Long idFather;

	/** The is read. */
	private Integer isRead;

	/** The link. */
	private String link;

	/** The pub date. */
	private String pubDate;

	/** The source. */
	private String source;

	/** The title. */
	private String title;

	/** The user rate. */
	private Integer userRate;

	/**
	 * Instantiates a new article dao.
	 */
	public ArticleDAO() {
		super();
	}

	/**
	 * Gets the author.
	 * 
	 * @return The author
	 */
	public String getAuthor() {
		return this.author;
	}

	/**
	 * Gets the category.
	 * 
	 * @return The category
	 */
	public String getCategory() {
		return this.category;
	}

	/**
	 * Gets the comments.
	 * 
	 * @return The comments
	 */
	public String getComments() {
		return this.comments;
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
	 * Gets the enclosure.
	 * 
	 * @return The enclosure
	 */
	public Enclosure getEnclosure() {
		final String query = "SELECT * FROM "
				+ Enclosure.nameOfTheAssociatedTable + " WHERE id = "
				+ this.getIdEnclosure();
		final Cursor c1 = Constants.sqlHandler.selectQuery(query);
		final Enclosure enclosure = new Enclosure();
		if ((c1 != null) && (c1.getCount() != 0)) {
			if (c1.moveToFirst()) {
				do {
					enclosure.setLength(Long.parseLong(c1.getString(c1
							.getColumnIndex("length"))));
					enclosure.setType(c1.getString(c1.getColumnIndex("type")));
					enclosure.setUrl(c1.getString(c1.getColumnIndex("url")));
				} while (c1.moveToNext());
			}
		}
		c1.close();
		return enclosure;
	}

	/**
	 * Gets the guid.
	 * 
	 * @return The guid
	 */
	public Guid getGuid() {
		final String query = "SELECT * FROM " + Guid.nameOfTheAssociatedTable
				+ " WHERE id = " + this.getIdGuid();
		final Cursor c1 = Constants.sqlHandler.selectQuery(query);
		final Guid guid = new Guid();
		if ((c1 != null) && (c1.getCount() != 0)) {
			if (c1.moveToFirst()) {
				do {
					guid.setIsPermaLink(Integer.parseInt(c1.getString(c1
							.getColumnIndex("isPermaLink"))));
					guid.setValue(c1.getString(c1.getColumnIndex("value")));
				} while (c1.moveToNext());
			}
		}
		c1.close();
		return guid;
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
	 * Gets the id enclosure.
	 * 
	 * @return The id enclosure
	 */
	public Long getIdEnclosure() {
		return this.idEnclosure;
	}

	/**
	 * Gets the id father.
	 * 
	 * @return The id father
	 */
	public Long getIdFather() {
		return this.idFather;
	}

	/**
	 * Gets the id guid.
	 * 
	 * @return The id guid
	 */
	public Long getIdGuid() {
		return this.idGuid;
	}

	/**
	 * Gets the checks if is read.
	 * 
	 * @return The checks if is read
	 */
	public Integer getIsRead() {
		return this.isRead;
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
	 * Gets the pub date.
	 * 
	 * @return The pub date
	 */
	public String getPubDate() {
		return this.pubDate;
	}

	/**
	 * Gets the source.
	 * 
	 * @return The source
	 */
	public String getSource() {
		return this.source;
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
	 * Gets the user rate.
	 * 
	 * @return The user rate
	 */
	public Integer getUserRate() {
		return this.userRate;
	}

	/***************************************************************************
	 * @see com.rss_pion.database.dao.abstracts.SerializedObject#insertInTheDataBase()
	 ***************************************************************************/
	@Override
	public Long insertInTheDataBase() throws IllegalAccessException,
			IllegalArgumentException {
		String names = "";
		final Iterator<String[]> it = ArticleDAO.fieldsOfTheAssociatedTable
				.iterator();
		while (it.hasNext()) {
			names += it.next()[0] + (it.hasNext() ? ", " : ")");
		}
		Constants.sqlHandler.executeQuery("INSERT INTO "
				+ ArticleDAO.nameOfTheAssociatedTable + " (" + names
				+ " VALUES ('" + this.getAuthor() + "', '" + this.getCategory()
				+ "', '" + this.getComments() + "', '" + this.getDescription()
				+ "', " + this.getIdEnclosure() + ", " + this.getIdFather()
				+ ", " + this.getIdGuid() + ", " + this.getIsRead() + ", '"
				+ this.getLink() + "', '" + this.getPubDate() + "', '"
				+ this.getSource() + "', '" + this.getTitle() + "', "
				+ this.getUserRate() + ");");
		return SqlDbHelper.lastInsertId(ArticleDAO.nameOfTheAssociatedTable);
	}

	/**
	 * Sets the author.
	 * 
	 * @param author : The new author
	 */
	public void setAuthor(final String author) {
		this.author = author;
	}

	/**
	 * Sets the category.
	 * 
	 * @param category : The new category
	 */
	public void setCategory(final String category) {
		this.category = category;
	}

	/**
	 * Sets the comments.
	 * 
	 * @param comments : The new comments
	 */
	public void setComments(final String comments) {
		this.comments = comments;
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
	 * Sets the id.
	 * 
	 * @param id : The new id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Sets the id enclosure.
	 * 
	 * @param enclosure : The new id enclosure
	 */
	public void setIdEnclosure(final Long enclosure) {
		this.idEnclosure = enclosure;
	}

	/**
	 * Sets the id father.
	 * 
	 * @param idFather : The new id father
	 */
	public void setIdFather(final Long idFather) {
		this.idFather = idFather;
	}

	/**
	 * Sets the id guid.
	 * 
	 * @param guid : The new id guid
	 */
	public void setIdGuid(final Long guid) {
		this.idGuid = guid;
	}

	/**
	 * Sets the checks if is read.
	 * 
	 * @param isRead : The new checks if is read
	 */
	public void setIsRead(final Integer isRead) {
		this.isRead = isRead;
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
	 * Sets the pub date.
	 * 
	 * @param pubDate : The new pub date
	 */
	public void setPubDate(final String pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * Sets the source.
	 * 
	 * @param source : The new source
	 */
	public void setSource(final String source) {
		this.source = source;
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
	 * Sets the user rate.
	 * 
	 * @param userRate : The new user rate
	 */
	public void setUserRate(final Integer userRate) {
		this.userRate = userRate;
	}
}
