/***************************************************************************
 * @file Article.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ***************************************************************************/
package com.rss_pion.beans;

import com.rss_pion.database.SqlDbHelper;
import com.rss_pion.database.dao.CategoryArticleDAO;
import com.rss_pion.database.dao.abstracts.NeedTranslationToBeSerializedObject;

// TODO: Auto-generated Javadoc
/**
 * The Class Article.
 */
public class CategoryArticle extends NeedTranslationToBeSerializedObject {

	/** The category. */
	private String category;

	/** The id. */
	private Long id;

	/** The id father. */
	private Long idFather;

	/** The id grand father. */
	private Long idGrandfather;

	/**
	 * Instantiates a new article.
	 */
	public CategoryArticle() {
		super();
	}

	/**
	 * Instantiates a new article.
	 * 
	 * @param idFather : The id father
	 * @param isRead : The is read
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
	 * @param userRate : The user rate
	 */
	public CategoryArticle(final String category) {
		super();
		this.category = category;
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
	 * Gets the id.
	 * 
	 * @return The id
	 */
	public Long getId() {
		return this.id;
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
	 * Gets the id grandfather.
	 * 
	 * @return The id grandfather
	 */
	public Long getIdGrandfather() {
		return this.idGrandfather;
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
	 * Sets the id.
	 * 
	 * @param id : The new id
	 */
	public void setId(final Long id) {
		this.id = id;
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
	 * Sets the id grandfather.
	 * 
	 * @param idGrandfather : The new id grandfather
	 */
	public void setIdGrandfather(final Long idGrandfather) {
		this.idGrandfather = idGrandfather;
	}

	/***************************************************************************
	 * @see java.lang.Object#toString()
	 ***************************************************************************/
	@Override
	public String toString() {
		return this.category + " ";
	}

	/***************************************************************************
	 * @see com.rss_pion.database.dao.abstracts.NeedTranslationToBeSerializedObject#translateDaoToObject(java.lang.Object)
	 ***************************************************************************/
	@Override
	public void translateDaoToObject(final Object object) {
		final CategoryArticleDAO categoryDAO = object instanceof CategoryArticleDAO ? (CategoryArticleDAO) object
				: null;
		if (categoryDAO != null) {
			this.id = categoryDAO.getId();
			this.category = categoryDAO.getCategory();
			this.idFather = categoryDAO.getIdFather();
			this.idGrandfather = categoryDAO.getIdGrandfather();
		}
	}

	/***************************************************************************
	 * @see com.rss_pion.database.dao.abstracts.NeedTranslationToBeSerializedObject#translateObjectToDao(java.lang.Long[])
	 ***************************************************************************/
	@Override
	public Object translateObjectToDao(final Long... ids)
			throws IllegalAccessException, IllegalArgumentException {
		final CategoryArticleDAO categoryDAO = new CategoryArticleDAO();
		categoryDAO.setCategory(this.getCategory());
		categoryDAO.setId(SqlDbHelper
				.lastInsertId(CategoryArticleDAO.nameOfTheAssociatedTable) + 1);
		categoryDAO.setIdFather(ids[0]);
		categoryDAO.setIdGrandfather(ids[1]);
		return categoryDAO;
	}
}
