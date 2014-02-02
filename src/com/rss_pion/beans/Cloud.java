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

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.rss_pion.configuration.Constants;
import com.rss_pion.database.SqlDbHelper;
import com.rss_pion.database.dao.FluxDAO;
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
				"TEXT" });
		Cloud.fieldsOfTheAssociatedTable.add(new String[] { "path",
				"TEXT" });
		Cloud.fieldsOfTheAssociatedTable.add(new String[] { "port",
				"INTEGER" });
		Cloud.fieldsOfTheAssociatedTable.add(new String[] { "protocol",
				"TEXT" });
		Cloud.fieldsOfTheAssociatedTable.add(new String[] {
				"registerProcedure", "TEXT" });
	}

    private Long id;

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
    public Cloud(final Long id) {

        super();

        final Cursor c = Constants.sqlHandler.query(
                FluxDAO.nameOfTheAssociatedTable,
                null,
                "id=?",
                new String[] {id.toString()},
                null,
                null,
                null,
                null);

            if ((c == null) || (c.getCount() == 0)) {
                return;
            }
            c.moveToFirst();
    
            this.domain = c.getString(c.getColumnIndex("domain"));
            this.port = c.getInt(c.getColumnIndex("port"));
            this.path = c.getString(c.getColumnIndex("path"));
            this.registerProcedure = c.getString(
                    c.getColumnIndex("registerProcedure"));
            this.protocol = c.getString(c.getColumnIndex("protocol"));
    }

	public String getDomain() {
		return this.domain;
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

    public Long insertIntoDB() {

        ContentValues valuesMap;

        // Si le cloud n'existe pas dans la BDD
        if (this.id == null) {

            // Préparation des champs
            valuesMap = new ContentValues();

            // Insertion
            this.id = Constants.sqlHandler.insert(
                    FluxDAO.nameOfTheAssociatedTable,
                    "domain",
                    valuesMap);
        }

        // Préparation des champs
        valuesMap = new ContentValues();
        valuesMap.put("domain", this.domain);
        valuesMap.put("port", this.port);
        valuesMap.put("path", this.path);
        valuesMap.put("protocol", this.protocol);
        valuesMap.put("registerProcedure", this.registerProcedure);

        // Update
        Constants.sqlHandler.update(FluxDAO.nameOfTheAssociatedTable,
                valuesMap, 
                "id=?",
                new String[] {this.id.toString()});

        return this.id;
    }

	public void setDomain(final String domain) {
		this.domain = domain;
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
