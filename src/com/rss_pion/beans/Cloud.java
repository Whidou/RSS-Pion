/***************************************************************************//**
 * @file    Cloud.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    23 janv. 2014
 * @version 0.5
 *
 * Objet cloud
 ******************************************************************************/

package com.rss_pion.beans;

/*** MAIN CLASS ***************************************************************/

public class Cloud {

/*** ATTRIBUTES ***************************************************************/

    //! Numéro d'entrée dans la BDD
    private Long id;

	//! Domaine
	private String domain;

	//! Adresse
	private String path;

	//! Port
	private Integer port;

	//! Protocole
	private String protocol;

	//! Procédure d'enregistrement
	private String registerProcedure;

/*** METHODS ******************************************************************/

	public Cloud() {

	    super();

	    this.id = null;
        this.domain = "";
        this.port = null;
        this.path = "";
        this.registerProcedure = "";
        this.protocol = "";
	}

	public Cloud(final String domain, final Integer port, final String path,
			final String registerProcedure, final String protocol) {
		super();
		this.domain = domain;
		this.port = port;
		this.path = path;
		this.registerProcedure = registerProcedure;
		this.protocol = protocol;
	}

    public String getDomain() {
        return this.domain;
    }

    public Long getId() {
        return this.id;
    }

	public String getPath() {
		return this.path;
	}

	public Integer getPort() {
		return this.port;
	}

	public String getProtocol() {
		return this.protocol;
	}

	public String getRegisterProcedure() {
		return this.registerProcedure;
	}

    public void setDomain(final String domain) {
        this.domain = domain;
    }

    public void setId(final Long id) {
        this.id = id;
    }

	public void setPath(final String path) {
		this.path = path;
	}

	public void setPort(final Integer port) {
		this.port = port;
	}

	public void setProtocol(final String protocol) {
		this.protocol = protocol;
	}

	public void setRegisterProcedure(final String registerProcedure) {
		this.registerProcedure = registerProcedure;
	}

/***************************************************************************//**
 * @see java.lang.Object#toString()
 ******************************************************************************/
	@Override
	public String toString() {
		return "Cloud [domain=" + this.domain + ", port=" + this.port
				+ ", path=" + this.path + ", registerProcedure="
				+ this.registerProcedure + ", protocol=" + this.protocol + "]";
	}
}
