/***************************************************************************//**
 * @file    Flux.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    23 janv. 2014
 * @version 1.0
 *
 * Classe pour les objets flux RSS
 ******************************************************************************/

package com.rss_pion.beans;

/*** MAIN CLASS ***************************************************************/

public class Category {

/*** ATTRIBUTES ***************************************************************/

    //! Numéro d'entrée dans la BDD
    Long id;

    //! ID de l'objet lié
    Long idParent;

    //! Nom
    String name;

/*** METHODS ******************************************************************/

    public Category() {
        super();
        this.id = null;
        this.idParent = null;
        this.name = "";
    }

    public Category(String name) {
        this();
        this.name = name;
    }

    public Category(Long id, Long idParent, String name) {
        super();
        this.id = id;
        this.idParent = idParent;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Long getIdParent() {
        return idParent;
    }

    public String getName() {
        return name;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setIdParent(final Long idParent) {
        this.idParent = idParent;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
