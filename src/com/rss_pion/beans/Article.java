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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rss_pion.database.SqlDbHelper;
import com.rss_pion.database.dao.ArticleDAO;
import com.rss_pion.database.dao.CategoryArticleDAO;
import com.rss_pion.database.dao.abstracts.NeedTranslationToBeSerializedObject;

// TODO: Auto-generated Javadoc
/**
 * The Class Article.
 */
public class Article extends NeedTranslationToBeSerializedObject {

	/** The author. */
	private String author;

	/** The category. */
	private List<CategoryArticle> categories;

	/** The comments. */
	private String comments;

	/** The description. */
	private String description;

	/** The enclosure. */
	private Enclosure enclosure;

	/** The guid. */
	private Guid guid;

	/** The id. */
	private Long id;

	/** The id father. */
	private Long idFather;

	/** The is read. */
	private Boolean isRead;

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
	 * Instantiates a new article.
	 */
	public Article() {
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
	public Article(final Long idFather, final Boolean isRead,
			final String title, final String link, final String description,
			final String author, final List<CategoryArticle> categories,
			final String comments, final Enclosure enclosure, final Guid guid,
			final String pubDate, final String source, final Integer userRate) {
		super();
		this.idFather = idFather;
		this.isRead = isRead;
		this.title = title;
		this.link = link;
		this.description = description;
		this.author = author;
		this.categories = categories;
		this.comments = comments;
		this.enclosure = enclosure;
		this.guid = guid;
		this.pubDate = pubDate;
		this.source = source;
		this.userRate = userRate;
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
	public List<CategoryArticle> getCategories() {
		return this.categories;
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
	 * Gets the checks if is read.
	 * 
	 * @return The checks if is read
	 */
	public Boolean getIsRead() {
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
	public void setCategories(final List<CategoryArticle> categories) {
		this.categories = categories;
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
	 * Sets the checks if is read.
	 * 
	 * @param isRead : The new checks if is read
	 */
	public void setIsRead(final Boolean isRead) {
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

	/***************************************************************************
	 * @see com.rss_pion.database.dao.abstracts.NeedTranslationToBeSerializedObject#translateDaoToObject(java.lang.Object)
	 ***************************************************************************/
	@Override
	public void translateDaoToObject(final Object object) {
		final ArticleDAO articleDAO = object instanceof ArticleDAO ? (ArticleDAO) object
				: null;
		if (articleDAO != null) {
			this.id = articleDAO.getId();
			this.author = articleDAO.getAuthor();
			this.comments = articleDAO.getComments();
			this.description = articleDAO.getDescription();
			this.enclosure = articleDAO.getEnclosure();
			this.guid = articleDAO.getGuid();
			this.idFather = articleDAO.getIdFather();
			this.isRead = articleDAO.getIsRead() == 1;
			this.link = articleDAO.getLink();
			this.pubDate = articleDAO.getPubDate();
			this.source = articleDAO.getSource();
			this.title = articleDAO.getTitle();
			this.userRate = articleDAO.getUserRate();
			this.categories = new ArrayList<CategoryArticle>();
			final Iterator<CategoryArticleDAO> itc = articleDAO
					.getCategoriesDAO().iterator();
			while (itc.hasNext()) {
				final CategoryArticle category = new CategoryArticle();
				category.translateDaoToObject(itc.next());
				this.categories.add(category);
			}
		}
	}

	/***************************************************************************
	 * @see com.rss_pion.database.dao.abstracts.NeedTranslationToBeSerializedObject#translateObjectToDao(java.lang.Long[])
	 ***************************************************************************/
	@Override
	public Object translateObjectToDao(final Long... ids)
			throws IllegalAccessException, IllegalArgumentException {
		final ArticleDAO articleDAO = new ArticleDAO();
		articleDAO.setAuthor(this.getAuthor());
		articleDAO.setComments(this.getComments());
		articleDAO.setDescription(this.getDescription());
		articleDAO.setIdEnclosure(this.getEnclosure().insertInTheDataBase());
		articleDAO.setIdGuid(this.getGuid().insertInTheDataBase());
		articleDAO.setId(SqlDbHelper
				.lastInsertId(ArticleDAO.nameOfTheAssociatedTable) + 1);
		articleDAO.setIdFather(ids[0]);
		articleDAO.setIsRead(this.getIsRead() ? 1 : 0);
		articleDAO.setLink(this.getLink());
		articleDAO.setPubDate(this.getPubDate());
		articleDAO.setSource(this.getSource());
		articleDAO.setTitle(this.getTitle());
		articleDAO.setUserRate(this.getUserRate());
		return articleDAO;
	}
}
