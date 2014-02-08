/***************************************************************************//**
 * @file    Guid.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-08-02
 * @version 0.5
 *
 * Classe pour les objets GUID
 *****************************************************************************/

package com.rss_pion.beans;

/*** MAIN CLASS ***************************************************************/

public class Guid {

/*** ATTRIBUTES ***************************************************************/

    //! Numéro d'entrée dans la BDD
    private Long id;

	//! Définit si cd GUID est ou non un lien permanent
	private boolean isPermaLink;

	//! Valeur
	private String value;

/*** METHODS ******************************************************************/

	public Guid() {
		super();
		this.isPermaLink = false;
		this.value = "";
	}

	public Guid(final boolean isPermaLink, final String value) {
		super();
		this.isPermaLink = isPermaLink;
		this.value = value;
	}

	public Long getId() {
        return id;
    }

    public boolean isPermaLink() {
		return this.isPermaLink;
	}

	public String getValue() {
		return this.value;
	}

    public void setId(Long id) {
        this.id = id;
    }

	public void setPermaLink(final boolean isPermaLink) {
		this.isPermaLink = isPermaLink;
	}

	public void setValue(final String value) {
		this.value = value;
	}

/***************************************************************************//**
 * @see java.lang.Object#toString()
 ******************************************************************************/
	@Override
	public String toString() {
		return "Guid [isPermaLink=" + this.isPermaLink + ", value="
				+ this.value + "]";
	}
}
