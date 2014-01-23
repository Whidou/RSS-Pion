/***************************************************************************
 * @file TextInput.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ***************************************************************************/
package com.rss_pion.beans;

import com.rss_pion.database.dao.TextInputDAO;
import com.rss_pion.database.dao.abstracts.NeedTranslationToBeSerializedObject;

// TODO: Auto-generated Javadoc
/**
 * The Class TextInput.
 */
public class TextInput extends NeedTranslationToBeSerializedObject {

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

	/** The enclosure. */
	private Enclosure enclosure;

	/** The guid. */
	private Guid guid;

	/** The pub date. */
	private String pubDate;

	/** The source. */
	private String source;

	/**
	 * Instantiates a new text input.
	 */
	public TextInput() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new text input.
	 *
	 * @param title : The title
	 * @param link : The link
	 * @param description : The description
	 * @param author : The author
	 * @param category : The category
	 * @param comments : The comments
	 * @param enclosure : The enclosure
	 * @param guid : The guid
	 * @param pubDate : The pub date
	 * @param source : The source
	 */
	public TextInput(final String title, final String link,
			final String description, final String author,
			final String category, final String comments,
			final Enclosure enclosure, final Guid guid, final String pubDate,
			final String source) {
		super();
		this.title = title;
		this.link = link;
		this.description = description;
		this.author = author;
		this.category = category;
		this.comments = comments;
		this.enclosure = enclosure;
		this.guid = guid;
		this.pubDate = pubDate;
		this.source = source;
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
		return this.enclosure;
	}

	/**
	 * Gets the guid.
	 *
	 * @return The guid
	 */
	public Guid getGuid() {
		return this.guid;
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
	 * Sets the enclosure.
	 *
	 * @param enclosure : The new enclosure
	 */
	public void setEnclosure(final Enclosure enclosure) {
		this.enclosure = enclosure;
	}

	/**
	 * Sets the guid.
	 *
	 * @param guid : The new guid
	 */
	public void setGuid(final Guid guid) {
		this.guid = guid;
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

	/***************************************************************************
	 * @see com.rss_pion.database.dao.abstracts.NeedTranslationToBeSerializedObject#translateDaoToObject(java.lang.Object)
	 ***************************************************************************/
	@Override
	public void translateDaoToObject(final Object object) {
		final TextInputDAO textInputDAO = object instanceof TextInputDAO ? (TextInputDAO) object
				: null;
		if (textInputDAO != null) {
			this.author = textInputDAO.getAuthor();
			this.category = textInputDAO.getCategory();
			this.comments = textInputDAO.getComments();
			this.description = textInputDAO.getDescription();
			this.enclosure = textInputDAO.getEnclosure();
			this.guid = textInputDAO.getGuid();
			this.link = textInputDAO.getLink();
			this.pubDate = textInputDAO.getPubDate();
			this.source = textInputDAO.getSource();
			this.title = textInputDAO.getTitle();
		}
	}

	/***************************************************************************
	 * @see com.rss_pion.database.dao.abstracts.NeedTranslationToBeSerializedObject#translateObjectToDao(java.lang.Long[])
	 ***************************************************************************/
	@Override
	public Object translateObjectToDao(final Long... ids)
			throws IllegalAccessException, IllegalArgumentException {
		final TextInputDAO textInputDAO = new TextInputDAO();
		textInputDAO.setAuthor(this.getAuthor());
		textInputDAO.setCategory(this.getCategory());
		textInputDAO.setComments(this.getComments());
		textInputDAO.setDescription(this.getDescription());
		textInputDAO.setIdEnclosure(this.getEnclosure().insertInTheDataBase());
		textInputDAO.setIdGuid(this.getGuid().insertInTheDataBase());
		textInputDAO.setLink(this.getLink());
		textInputDAO.setPubDate(this.getPubDate());
		textInputDAO.setSource(this.getSource());
		textInputDAO.setTitle(this.getTitle());
		return textInputDAO;
	}
}
