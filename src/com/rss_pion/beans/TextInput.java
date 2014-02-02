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

	/** The description. */
	private String description;

	/** The author. */
	private String name;

	/** The link. */
	private String link;

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
			final String description, final String name) {
		super();
		this.title = title;
		this.link = link;
		this.description = description;
		this.name = name;
	}

	/**
	 * Gets the author.
	 *
	 * @return The author
	 */
	public String getName() {
		return this.name;
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
	 * Gets the link.
	 *
	 * @return The link
	 */
	public String getLink() {
		return this.link;
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
	public void setName(final String name) {
		this.name = name;
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
	 * Sets the link.
	 *
	 * @param link : The new link
	 */
	public void setLink(final String link) {
		this.link = link;
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
			this.name = textInputDAO.getName();
			this.description = textInputDAO.getDescription();
			this.link = textInputDAO.getLink();
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
		textInputDAO.setName(this.getName());
		textInputDAO.setDescription(this.getDescription());
		textInputDAO.setLink(this.getLink());
		textInputDAO.setTitle(this.getTitle());
		return textInputDAO;
	}
}
