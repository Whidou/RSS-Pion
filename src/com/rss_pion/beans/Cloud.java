/***************************************************************************
 * @file Cloud.java
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

import android.util.Log;

import com.rss_pion.configuration.Constants;
import com.rss_pion.database.SqlDbHelper;
import com.rss_pion.database.dao.abstracts.SerializedObject;

// TODO: Auto-generated Javadoc
/**
 * The Class Cloud.
 */
public class Cloud extends SerializedObject {

	/** The name of the associated table. */
	public static String nameOfTheAssociatedTable = "CLOUD_IT";

	/** The fields of the associated table. */
	public static ArrayList<String[]> fieldsOfTheAssociatedTable;
	static {
		Cloud.fieldsOfTheAssociatedTable = new ArrayList<String[]>();
		Cloud.fieldsOfTheAssociatedTable.add(new String[] { "domain",
				"TEXT NOT NULL" });
		Cloud.fieldsOfTheAssociatedTable.add(new String[] { "path",
				"TEXT NOT NULL" });
		Cloud.fieldsOfTheAssociatedTable.add(new String[] { "port",
				"INTEGER NOT NULL" });
		Cloud.fieldsOfTheAssociatedTable.add(new String[] { "protocol",
				"TEXT NOT NULL" });
		Cloud.fieldsOfTheAssociatedTable.add(new String[] {
				"registerProcedure", "TEXT NOT NULL" });
	}

	/** The domain. */
	private String domain;

	/** The port. */
	private Integer port;

	/** The path. */
	private String path;

	/** The register procedure. */
	private String registerProcedure;

	/** The protocol. */
	private String protocol;

	/**
	 * Instantiates a new cloud.
	 */
	public Cloud() {
		super();
	}

	/**
	 * Instantiates a new cloud.
	 * 
	 * @param domain : The domain
	 * @param port : The port
	 * @param path : The path
	 * @param registerProcedure : The register procedure
	 * @param protocol : The protocol
	 */
	public Cloud(final String domain, final Integer port, final String path,
			final String registerProcedure, final String protocol) {
		super();
		this.domain = domain;
		this.port = port;
		this.path = path;
		this.registerProcedure = registerProcedure;
		this.protocol = protocol;
	}

	/**
	 * Gets the domain.
	 * 
	 * @return The domain
	 */
	public String getDomain() {
		return this.domain;
	}

	/**
	 * Gets the path.
	 * 
	 * @return The path
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * Gets the port.
	 * 
	 * @return The port
	 */
	public Integer getPort() {
		return this.port;
	}

	/**
	 * Gets the protocol.
	 * 
	 * @return The protocol
	 */
	public String getProtocol() {
		return this.protocol;
	}

	/**
	 * Gets the register procedure.
	 * 
	 * @return The register procedure
	 */
	public String getRegisterProcedure() {
		return this.registerProcedure;
	}

	/***************************************************************************
	 * @see com.rss_pion.database.dao.abstracts.SerializedObject#insertInTheDataBase()
	 ***************************************************************************/
	@Override
	public Long insertInTheDataBase(final Object... objects)
			throws IllegalAccessException, IllegalArgumentException {
		String names = "";
		final Iterator<String[]> it = Cloud.fieldsOfTheAssociatedTable
				.iterator();
		while (it.hasNext()) {
			names += it.next()[0] + (it.hasNext() ? ", " : ")");
		}
		Constants.sqlHandler.executeQuery("INSERT INTO "
				+ Cloud.nameOfTheAssociatedTable + " (" + names + " VALUES ('"
				+ this.getDomain() + "', '" + this.getPath() + "', "
				+ this.getPort() + ", '" + this.getProtocol() + "', '"
				+ this.getRegisterProcedure() + "');");
		Log.d("CLOUD ADDED", this.toString());
		return SqlDbHelper.lastInsertId(Cloud.nameOfTheAssociatedTable);
	}

	/**
	 * Sets the domain.
	 * 
	 * @param domain : The new domain
	 */
	public void setDomain(final String domain) {
		this.domain = domain;
	}

	/**
	 * Sets the path.
	 * 
	 * @param path : The new path
	 */
	public void setPath(final String path) {
		this.path = path;
	}

	/**
	 * Sets the port.
	 * 
	 * @param port : The new port
	 */
	public void setPort(final Integer port) {
		this.port = port;
	}

	/**
	 * Sets the protocol.
	 * 
	 * @param protocol : The new protocol
	 */
	public void setProtocol(final String protocol) {
		this.protocol = protocol;
	}

	/**
	 * Sets the register procedure.
	 * 
	 * @param registerProcedure : The new register procedure
	 */
	public void setRegisterProcedure(final String registerProcedure) {
		this.registerProcedure = registerProcedure;
	}

	/***************************************************************************
	 * @see java.lang.Object#toString()
	 ***************************************************************************/
	@Override
	public String toString() {
		return "Cloud [domain=" + this.domain + ", port=" + this.port
				+ ", path=" + this.path + ", registerProcedure="
				+ this.registerProcedure + ", protocol=" + this.protocol + "]";
	}
}
