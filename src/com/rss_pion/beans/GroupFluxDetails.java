package com.rss_pion.beans;

import java.util.ArrayList;
import java.util.List;

public class GroupFluxDetails {

	public String string;
	public final List<String> children = new ArrayList<String>();

	public GroupFluxDetails(final String string) {
		this.string = string;
	}

}