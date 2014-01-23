/***************************************************************************
 * @file TextInputDAO.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ***************************************************************************/
package com.rss_pion.database.dao;

/*** INCLUDES *****************************************************************/

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
 * The Class TextInputDAO.
 */

public class TextInputDAO extends SerializedObject {

	/** The name of the associated table. */
	public static String nameOfTheAssociatedTable = "TEXTINPUT_IT";

	/** The fields of the associated table. */
	public static ArrayList<String[]> fieldsOfTheAssociatedTable;
	static {
		TextInputDAO.fieldsOfTheAssociatedTable = new ArrayList<String[]>();
		TextInputDAO.fieldsOfTheAssociatedTable.add(new String[] { "author",
				"TEXT NOT NULL" });
		TextInputDAO.fieldsOfTheAssociatedTable.add(new String[] { "category",
				"TEXT NOT NULL" });
		TextInputDAO.fieldsOfTheAssociatedTable.add(new String[] { "comments",
				"TEXT NOT NULL" });
		TextInputDAO.fieldsOfTheAssociatedTable.add(new String[] {
				"description", "TEXT NOT NULL" });
		TextInputDAO.fieldsOfTheAssociatedTable.add(new String[] {
				"idEnclosure", "INTEGER NOT NULL" });
		TextInputDAO.fieldsOfTheAssociatedTable.add(new String[] { "idGuid",
				"INTEGER NOT NULL" });
		TextInputDAO.fieldsOfTheAssociatedTable.add(new String[] { "link",
				"TEXT NOT NULL" });
		TextInputDAO.fieldsOfTheAssociatedTable.add(new String[] { "pubDate",
				"TEXT NOT NULL" });
		TextInputDAO.fieldsOfTheAssociatedTable.add(new String[] { "source",
				"TEXT NOT NULL" });
		TextInputDAO.fieldsOfTheAssociatedTable.add(new String[] { "title",
				"TEXT NOT NULL" });
	}

	/**
	 * Delete text input in the data base.
	 *
	 * @param id : The id
	 */
	public static void deleteTextInputInTheDataBase(final Long id) {
		final String query = "SELECT * FROM "
				+ TextInputDAO.nameOfTheAssociatedTable + " WHERE id = " + id;
		final Cursor c1 = Constants.sqlHandler.selectQuery(query);
		final TextInputDAO textInput = new TextInputDAO();
		if ((c1 != null) && (c1.getCount() != 0)) {
			if (c1.moveToFirst()) {
				do {
					textInput.setIdEnclosure(Long.parseLong(c1.getString(c1
							.getColumnIndex("idEnclosure"))));
					textInput.setIdGuid(Long.parseLong(c1.getString(c1
							.getColumnIndex("idGuid"))));
					Constants.sqlHandler.deleteDAO(
							Enclosure.nameOfTheAssociatedTable,
							textInput.getIdEnclosure());
					Constants.sqlHandler.deleteDAO(
							TextInputDAO.nameOfTheAssociatedTable,
							textInput.getIdGuid());
					Constants.sqlHandler.deleteDAO(
							TextInputDAO.nameOfTheAssociatedTable, id);
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
				+ ArticleDAO.nameOfTheAssociatedTable + ";";
		final Cursor c1 = Constants.sqlHandler.selectQuery(query);
		final ArrayList<FluxDAO> flux_list = new ArrayList<FluxDAO>();
		if ((c1 != null) && (c1.getCount() != 0)) {
			if (c1.moveToFirst()) {
				do {
					final FluxDAO fluxDAO = new FluxDAO();
					fluxDAO.setCategory(c1.getString(c1
							.getColumnIndex("category")));
					fluxDAO.setIdCloud(Long.parseLong(c1.getString(c1
							.getColumnIndex("cloud"))));
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
							.getColumnIndex("image"))));
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
							.getColumnIndex("textInput"))));
					fluxDAO.setTitle(c1.getString(c1.getColumnIndex("title")));
					fluxDAO.setTtl(Integer.parseInt(c1.getString(c1
							.getColumnIndex("ttl"))));
					fluxDAO.setWebMaster(c1.getString(c1
							.getColumnIndex("webMaster")));
					flux_list.add(fluxDAO);
				} while (c1.moveToNext());
			}
		}
		c1.close();
		return flux_list;
	}

	/** The title. */
	private String title;

	/** The link. */
	private String link;

	/** The description. */
	private String description;

	/** The author. */
	private String author;

	/** The category. */
	private String category;

	/** The comments. */
	private String comments;

	/** The id enclosure. */
	private Long idEnclosure;

	/** The id guid. */
	private Long idGuid;

	/** The pub date. */
	private String pubDate;

	/** The source. */
	private String source;

	/**
	 * Instantiates a new text input dao.
	 */
	public TextInputDAO() {
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
	 * Gets the id enclosure.
	 *
	 * @return The id enclosure
	 */
	public Long getIdEnclosure() {
		return this.idEnclosure;
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

	/***************************************************************************
	 * @see com.rss_pion.database.dao.abstracts.SerializedObject#insertInTheDataBase()
	 ***************************************************************************/
	@Override
	public Long insertInTheDataBase() throws IllegalAccessException,
			IllegalArgumentException {
		String names = "";
		final Iterator<String[]> it = TextInputDAO.fieldsOfTheAssociatedTable
				.iterator();
		while (it.hasNext()) {
			names += it.next()[0] + (it.hasNext() ? ", " : ")");
		}
		Constants.sqlHandler.executeQuery("INSERT INTO "
				+ TextInputDAO.nameOfTheAssociatedTable + " (" + names
				+ " VALUES ('" + this.getAuthor() + "', '" + this.getCategory()
				+ "', '" + this.getComments() + "', '" + this.getDescription()
				+ "', " + this.getIdEnclosure() + ", " + this.getIdGuid()
				+ ", '" + this.getLink() + "', '" + this.getPubDate() + "', '"
				+ this.getSource() + "', '" + this.getTitle() + "');");
		return SqlDbHelper.lastInsertId(TextInputDAO.nameOfTheAssociatedTable);
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
	 * Sets the id enclosure.
	 *
	 * @param idEnclosure : The new id enclosure
	 */
	public void setIdEnclosure(final Long idEnclosure) {
		this.idEnclosure = idEnclosure;
	}

	/**
	 * Sets the id guid.
	 *
	 * @param idGuid : The new id guid
	 */
	public void setIdGuid(final Long idGuid) {
		this.idGuid = idGuid;
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
}
