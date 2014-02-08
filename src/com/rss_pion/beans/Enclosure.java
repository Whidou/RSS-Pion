/***************************************************************************//**
 * @file    Enclosure.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-02-08
 * @version 0.5
 *
 * Classe pour les pièces jointes
 ******************************************************************************/

package com.rss_pion.beans;

/*** MAIN CLASS ***************************************************************/

public class Enclosure {

/*** ATTRIBUTES ***************************************************************/

    //! Numéro d'entrée dans la BDD
    private Long id;

    //! Taille
    private Long length;

	//! Type
	private String type;

	//! URL
	private String url;

/*** METHODS ******************************************************************/

	public Enclosure() {

		super();

		this.id = null;
		this.length = Long.valueOf(0);
		this.type = "";
		this.url = "";
	}

	public Enclosure(
	        final Long id,
	        final Long length,
	        final String type,
	        final String url) {

		super();

        this.id = id;
		this.length = length;
		this.type = type;
		this.url = url;
	}

	public Long getId() {
        return id;
    }

    public Long getLength() {
		return this.length;
	}

	public String getType() {
		return this.type;
	}

	public String getUrl() {
		return this.url;
	}

    public void setId(Long id) {
        this.id = id;
    }

	public void setLength(final Long length) {
		this.length = length;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

/***************************************************************************//**
 * @see java.lang.Object#toString()
 ******************************************************************************/
	@Override
	public String toString() {
		return "Enclosure [url=" + this.url + ", length=" + this.length
				+ ", type=" + this.type + "]";
	}
}
