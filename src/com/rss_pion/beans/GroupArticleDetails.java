package com.rss_pion.beans;

import java.util.ArrayList;
import java.util.List;

public class GroupArticleDetails {

	public String string;
	public final List<String> children = new ArrayList<String>();

	public GroupArticleDetails(final String string) {
		this.string = string;
	}

}