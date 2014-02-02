/***************************************************************************
 * @file Tests.java
 * @author PERROCHAUD Clément
 * @author TOMA Hadrien
 * @date 23 janv. 2014
 * @version 0.4
 *
 * @brief
 ***************************************************************************/
package com.rss_pion.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Application;

import com.rss_pion.beans.Article;
import com.rss_pion.beans.CategoryArticle;
import com.rss_pion.beans.CategoryFlux;
import com.rss_pion.beans.Cloud;
import com.rss_pion.beans.Enclosure;
import com.rss_pion.beans.Flux;
import com.rss_pion.beans.Guid;
import com.rss_pion.beans.TextInput;
import com.rss_pion.database.dao.ImageDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class Tests.
 */
public class Tests extends Application {

	/** The Constant flux_1_article_3_enclosure. */
	public static final Enclosure flux_1_article_3_enclosure = new Enclosure(
			"flux_1_article_3_enclosure_url", 1l,
			"flux_1_article_3_enclosure_type");

	/** The Constant flux_1_article_3_guid. */
	public static final Guid flux_1_article_3_guid = new Guid(true,
			"flux_1_article_3_guid_value");

	public static final CategoryArticle flux_1_article_3_categorie_1 = new CategoryArticle(
			"musique");
	public static final CategoryArticle flux_1_article_3_categorie_2 = new CategoryArticle(
			"art");

	/** The Constant flux_1_article_3. */
	public static final Article flux_1_article_3 = new Article(1l, true,
			"flux_1_article_3_title", "flux_1_article_3_link",
			"flux_1_article_3_description", "flux_1_article_3_author",
			Arrays.asList(new CategoryArticle[] {
					Tests.flux_1_article_3_categorie_1,
					Tests.flux_1_article_3_categorie_2 }),
			"flux_1_article_3_comments", Tests.flux_1_article_3_enclosure,
			Tests.flux_1_article_3_guid, Long.valueOf(0),
			"flux_1_article_3_source", 1);

	/** The Constant flux_1_article_2_enclosure. */
	public static final Enclosure flux_1_article_2_enclosure = new Enclosure(
			"flux_1_article_2_enclosure_url", 1l,
			"flux_1_article_2_enclosure_type");

	/** The Constant flux_1_article_2_guid. */
	public static final Guid flux_1_article_2_guid = new Guid(true,
			"flux_1_article_2_guid_value");

	public static final CategoryArticle flux_1_article_2_categorie_1 = new CategoryArticle(
			"divers");
	public static final CategoryArticle flux_1_article_2_categorie_2 = new CategoryArticle(
			"perso");

	/** The Constant flux_1_article_2. */
	public static final Article flux_1_article_2 = new Article(1l, true,
			"flux_1_article_2_title", "flux_1_article_2_link",
			"flux_1_article_2_description", "flux_1_article_2_author",
			Arrays.asList(new CategoryArticle[] {
					Tests.flux_1_article_2_categorie_1,
					Tests.flux_1_article_2_categorie_2 }),
			"flux_1_article_2_comments", Tests.flux_1_article_2_enclosure,
			Tests.flux_1_article_2_guid, Long.valueOf(1000),
			"flux_1_article_2_source", 1);

	/** The Constant flux_1_article_1_enclosure. */
	public static final Enclosure flux_1_article_1_enclosure = new Enclosure(
			"flux_1_article_1_enclosure_url", 1l,
			"flux_1_article_1_enclosure_type");

	/** The Constant flux_1_article_1_guid. */
	public static final Guid flux_1_article_1_guid = new Guid(false,
			"flux_1_article_1_guid_value");

	public static final CategoryArticle flux_1_article_1_categorie_1 = new CategoryArticle(
			"cuisine");
	public static final CategoryArticle flux_1_article_1_categorie_2 = new CategoryArticle(
			"sport");

	/** The Constant flux_1_article_1. */
	public static final Article flux_1_article_1 = new Article(1l, true,
			"flux_1_article_1_title", "flux_1_article_1_link",
			"flux_1_article_1_description", "flux_1_article_1_author",
			Arrays.asList(new CategoryArticle[] {
					Tests.flux_1_article_1_categorie_1,
					Tests.flux_1_article_1_categorie_2 }),
			"flux_1_article_1_comments", Tests.flux_1_article_1_enclosure,
			Tests.flux_1_article_1_guid, Long.valueOf(2000),
			"flux_1_article_1_source", 1);

	/** The Constant flux_1_skipDays. */
	public static final List<String> flux_1_skipDays = new ArrayList<String>();

	/** The Constant flux_1_skipHours. */
	public static final List<Integer> flux_1_skipHours = new ArrayList<Integer>();

	/** The Constant flux_1_textInput_enclosure. */
	public static final Enclosure flux_1_textInput_enclosure = new Enclosure(
			"flux_1_textInput_enclosure_url", 1l,
			"flux_1_textInput_enclosure_type");

	/** The Constant flux_1_textInput_guid. */
	public static final Guid flux_1_textInput_guid = new Guid(false,
			"flux_1_textInput_guid_value");

	/** The Constant flux_1_textInput. */
	public static final TextInput flux_1_textInput = new TextInput(
			"flux_1_textInput_title", "flux_1_textInput_link",
			"flux_1_textInput_description", "flux_1_textInput_name");

	/** The flux_1_image. */
	public static ImageDAO flux_1_image = null;

	/** The Constant flux_1_cloud. */
	public static final Cloud flux_1_cloud = new Cloud("flux_1_cloud_domain",
			1, "flux_1_cloud_path", "flux_1_cloud_registerProcedure",
			"flux_1_cloud_protocol");

	public static final CategoryFlux flux_1_categorie_1 = new CategoryFlux(
			"sciences");
	public static final CategoryFlux flux_1_categorie_2 = new CategoryFlux(
			"nature");

	public static final String flux_1_urlImage = "flux_1_urlImage";

	/** The Constant flux_1. */
	public static final Flux flux_1 = new Flux("flux_1_feed", "flux_1_title",
			"flux_1_link", "flux_1_description", "flux_1_language",
			"flux_1_copyright", "flux_1_managingEditor", "flux_1_webMaster",
			Long.valueOf(3000), Long.valueOf(3500),
			Arrays.asList(new CategoryFlux[] { Tests.flux_1_categorie_1,
					Tests.flux_1_categorie_2 }), "flux_1_generator",
			"flux_1_docs", Tests.flux_1_cloud, 1, Tests.flux_1_image,
			"flux_1_rating", Tests.flux_1_textInput, Tests.flux_1_skipHours,
			Tests.flux_1_skipDays, 1, 1, 1, Arrays.asList(new Article[] {
					Tests.flux_1_article_1, Tests.flux_1_article_2,
					Tests.flux_1_article_3 }), Tests.flux_1_urlImage);
	//
	// /** The Constant flux_2_article_3_enclosure. */
	// public static final Enclosure flux_2_article_3_enclosure = new Enclosure(
	// "flux_2_article_3_enclosure_url", 1l,
	// "flux_2_article_3_enclosure_type");
	//
	// /** The Constant flux_2_article_3_guid. */
	// public static final Guid flux_2_article_3_guid = new Guid(1,
	// "flux_2_article_3_guid_value");
	//
	// public static final ArrayList<CategoryFlux> flux_2_article_3_categories =
	// new
	// ArrayList<CategoryFlux>(
	// Arrays.asList(new CategoryFlux(1l, "sciences"), new CategoryFlux(1l,
	// "nature")));
	//
	// /** The Constant flux_2_article_3. */
	// public static final Article flux_2_article_3 = new Article(1l, true,
	// "flux_2_article_3_title", "flux_2_article_3_link",
	// "flux_2_article_3_description", "flux_2_article_3_author",
	// Tests.flux_2_article_3_categories, "flux_2_article_3_comments",
	// Tests.flux_2_article_3_enclosure, Tests.flux_2_article_3_guid,
	// "flux_2_article_3_pubDate", "flux_2_article_3_source", 1);
	//
	// /** The Constant flux_2_article_2_enclosure. */
	// public static final Enclosure flux_2_article_2_enclosure = new Enclosure(
	// "flux_2_article_2_enclosure_url", 1l,
	// "flux_2_article_2_enclosure_type");
	//
	// /** The Constant flux_2_article_2_guid. */
	// public static final Guid flux_2_article_2_guid = new Guid(1,
	// "flux_2_article_2_guid_value");
	//
	// public static final ArrayList<CategoryFlux> flux_2_article_2_categories =
	// new
	// ArrayList<CategoryFlux>(
	// Arrays.asList(new CategoryFlux(1l, "sciences"), new CategoryFlux(1l,
	// "nature")));
	//
	// /** The Constant flux_2_article_2. */
	// public static final Article flux_2_article_2 = new Article(1l, true,
	// "flux_2_article_2_title", "flux_2_article_2_link",
	// "flux_2_article_2_description", "flux_2_article_2_author",
	// Tests.flux_2_article_2_categories, "flux_2_article_2_comments",
	// Tests.flux_2_article_2_enclosure, Tests.flux_2_article_2_guid,
	// "flux_2_article_2_pubDate", "flux_2_article_2_source", 1);
	//
	// /** The Constant flux_2_article_1_enclosure. */
	// public static final Enclosure flux_2_article_1_enclosure = new Enclosure(
	// "flux_2_article_1_enclosure_url", 1l,
	// "flux_2_article_1_enclosure_type");
	//
	// /** The Constant flux_2_article_1_guid. */
	// public static final Guid flux_2_article_1_guid = new Guid(1,
	// "flux_2_article_1_guid_value");
	//
	// public static final ArrayList<CategoryFlux> flux_2_article_1_categories =
	// new
	// ArrayList<CategoryFlux>(
	// Arrays.asList(new CategoryFlux(1l, "sciences"), new CategoryFlux(1l,
	// "nature")));
	//
	// /** The Constant flux_2_article_1. */
	// public static final Article flux_2_article_1 = new Article(1l, true,
	// "flux_2_article_1_title", "flux_2_article_1_link",
	// "flux_2_article_1_description", "flux_2_article_1_author",
	// Tests.flux_2_article_1_categories, "flux_2_article_1_comments",
	// Tests.flux_2_article_1_enclosure, Tests.flux_2_article_1_guid,
	// "flux_2_article_1_pubDate", "flux_2_article_1_source", 1);
	//
	// /** The Constant flux_2_skipDays. */
	// public static final String[] flux_2_skipDays = { "flux_2_skipDays_1",
	// "flux_2_skipDays_2", "flux_2_skipDays_3" };
	//
	// /** The Constant flux_2_skipHours. */
	// public static final String[] flux_2_skipHours = { "flux_2_skipHours_1",
	// "flux_2_skipHours_2", "flux_2_skipHours_3" };
	//
	// /** The Constant flux_2_textInput_enclosure. */
	// public static final Enclosure flux_2_textInput_enclosure = new Enclosure(
	// "flux_2_textInput_enclosure_url", 1l,
	// "flux_2_textInput_enclosure_type");
	//
	// /** The Constant flux_2_textInput_guid. */
	// public static final Guid flux_2_textInput_guid = new Guid(1,
	// "flux_2_textInput_guid_value");
	//
	// /** The Constant flux_2_textInput. */
	// public static final TextInput flux_2_textInput = new TextInput(
	// "flux_2_textInput_title", "flux_2_textInput_link",
	// "flux_2_textInput_description", "flux_2_textInput_author",
	// "flux_2_textInput_category", "flux_2_textInput_comments",
	// Tests.flux_2_textInput_enclosure, Tests.flux_2_textInput_guid,
	// "flux_2_textInput_pubDate", "flux_2_textInput_source");
	//
	// /** The flux_2_image. */
	// public static ImageDAO flux_2_image = null;
	//
	// /** The Constant flux_2_cloud. */
	// public static final Cloud flux_2_cloud = new Cloud("flux_2_cloud_domain",
	// 1, "flux_2_cloud_path", "flux_2_cloud_registerProcedure",
	// "flux_2_cloud_protocol");
	//
	// public static final ArrayList<CategoryFlux> flux_2_categories = new
	// ArrayList<CategoryFlux>(
	// Arrays.asList(new CategoryFlux(1l, "sciences"), new CategoryFlux(1l,
	// "nature")));
	//
	// /** The Constant flux_2. */
	// public static final Flux flux_2 = new Flux("flux_2_feed", "flux_2_title",
	// "flux_2_link", "flux_2_description", "flux_2_language",
	// "flux_2_copyright", "flux_2_managingEditor", "flux_2_webMaster",
	// "flux_2_pubDate", "flux_2_lastBuildDate", Tests.flux_2_categories,
	// "flux_2_generator", "flux_2_docs", Tests.flux_2_cloud, 1,
	// Tests.flux_2_image, "flux_2_rating", Tests.flux_2_textInput,
	// Tests.flux_2_skipHours, Tests.flux_2_skipDays, 1, 1, 1,
	// Arrays.asList(new Article[] { Tests.flux_2_article_1,
	// Tests.flux_2_article_2, Tests.flux_2_article_3 }));
}