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
import com.rss_pion.database.dao.CategoryFluxDAO;
import com.rss_pion.database.dao.abstracts.NeedTranslationToBeSerializedObject;

// TODO: Auto-generated Javadoc
/**
 * The Class Article.
 */
public class CategoryFlux extends NeedTranslationToBeSerializedObject {

	/** The category. */
	private String category;

	/** The id. */
	private Long id;

	/** The id father. */
	private Long idFather;

	/**
	 * Instantiates a new article.
	 */
	public CategoryFlux() {
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
	public CategoryFlux(final String category) {
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
		final CategoryFluxDAO categoryDAO = object instanceof CategoryFluxDAO ? (CategoryFluxDAO) object
				: null;
		if (categoryDAO != null) {
			this.id = categoryDAO.getId();
			this.category = categoryDAO.getCategory();
			this.idFather = categoryDAO.getIdFather();
		}
	}

	/***************************************************************************
	 * @see com.rss_pion.database.dao.abstracts.NeedTranslationToBeSerializedObject#translateObjectToDao(java.lang.Long[])
	 ***************************************************************************/
	@Override
	public Object translateObjectToDao(final Long... ids)
			throws IllegalAccessException, IllegalArgumentException {
		final CategoryFluxDAO categoryDAO = new CategoryFluxDAO();
		categoryDAO.setCategory(this.getCategory());
		categoryDAO.setId(SqlDbHelper
				.lastInsertId(CategoryFluxDAO.nameOfTheAssociatedTable) + 1);
		categoryDAO.setIdFather(ids[0]);
		return categoryDAO;
	}
}
