/***************************************************************************//**
 * @file TextInput.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ******************************************************************************/

package com.rss_pion.beans;

/*** MAIN CLASS ***************************************************************/

public class TextInput {

/*** ATTRIBUTES ***************************************************************/

    //! Numéro d'entrée dans la BDD
    private Long id;

	//! Titre
	private String title;

	//! Description
	private String description;

	//! Nom
	private String name;

	//! Lien
	private String link;

/*** METHODS ******************************************************************/

	public TextInput() {

		super();

		this.title = "";
		this.link = "";
		this.description = "";
		this.name = "";
	}

	public TextInput(final String title, final String link,
			final String description, final String name) {

		super();

        this.title = title;
        this.link = link;
        this.description = description;
        this.name = name;
	}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
