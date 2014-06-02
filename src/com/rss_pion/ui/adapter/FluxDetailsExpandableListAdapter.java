/***************************************************************************//**
 * @file    ArticleDetailsExpandableAdapter.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-01-23
 * @version 1.0
 *
 * Adaptateur d'affichage pour les détails des articles
 ******************************************************************************/

package com.rss_pion.ui.adapter;

/*** INCLUDES *****************************************************************/

import android.app.Dialog;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.rss_pion.R;
import com.rss_pion.beans.GroupFluxDetails;

/*** MAIN CLASS ***************************************************************/

public class FluxDetailsExpandableListAdapter extends
        BaseExpandableListAdapter {

/*** ATTRIBUTES ***************************************************************/

    //! Groupes à afficher
	private final SparseArray<GroupFluxDetails> groups;

    //! Chargeur d'agencement
	public LayoutInflater inflater;

/*** METHODS ******************************************************************/

	public FluxDetailsExpandableListAdapter(final Dialog dialog,
			final SparseArray<GroupFluxDetails> groups) {
		this.groups = groups;
		this.inflater = dialog.getLayoutInflater();
	}

/***************************************************************************//**
 * @see android.widget.BaseExpandableListAdapter#getChild(int, int)
 ******************************************************************************/
	@Override
	public Object getChild(final int groupPosition, final int childPosition) {
		return this.groups.get(groupPosition).children.get(childPosition);
	}

/***************************************************************************//**
 * @see android.widget.BaseExpandableListAdapter#getChildId(int, int)
 ******************************************************************************/
	@Override
	public long getChildId(final int groupPosition, final int childPosition) {
		return 0;
	}

/***************************************************************************//**
 * @see android.widget.BaseExpandableListAdapter#getChildrenCount(int)
 ******************************************************************************/
	@Override
	public int getChildrenCount(final int groupPosition) {
		return this.groups.get(groupPosition).children.size();
	}

/***************************************************************************//**
 * @see android.widget.BaseExpandableListAdapter#getChildView(
 * int, int, boolean, View, ViewGroup)
 ******************************************************************************/
	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			final boolean isLastChild, View convertView, final ViewGroup parent) {
		final String children = (String) this.getChild(groupPosition,
				childPosition);
		TextView text = null;
		if (convertView == null) {
			convertView = this.inflater.inflate(R.layout.listrow_details, null);
		}
		text = (TextView) convertView.findViewById(R.id.textView1);
		text.setText(children);
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
			}
		});
		return convertView;
	}

/***************************************************************************//**
 * @see android.widget.BaseExpandableListAdapter#getGroup(int)
 ******************************************************************************/
	@Override
	public Object getGroup(final int groupPosition) {
		return this.groups.get(groupPosition);
	}

/***************************************************************************//**
 * @see android.widget.BaseExpandableListAdapter#getGroupCount()
 ******************************************************************************/
	@Override
	public int getGroupCount() {
		return this.groups.size();
	}

/***************************************************************************//**
 * @see android.widget.BaseExpandableListAdapter#getGroupId(int)
 ******************************************************************************/
	@Override
	public long getGroupId(final int groupPosition) {
		return 0;
	}

/***************************************************************************//**
 * @see android.widget.BaseExpandableListAdapter#getGroupView(
 * int, boolean, View, ViewGroup)
 ******************************************************************************/
	@Override
	public View getGroupView(final int groupPosition, final boolean isExpanded,
			View convertView, final ViewGroup parent) {
		if (convertView == null) {
			convertView = this.inflater.inflate(R.layout.listrow_group, null);
		}
		final GroupFluxDetails group = (GroupFluxDetails) this
				.getGroup(groupPosition);
		((CheckedTextView) convertView).setText(group.string);
		((CheckedTextView) convertView).setChecked(isExpanded);
		return convertView;
	}

/***************************************************************************//**
 * @see android.widget.BaseExpandableListAdapter#hasStableIds()
 ******************************************************************************/
	@Override
	public boolean hasStableIds() {
		return false;
	}

/***************************************************************************//**
 * @see android.widget.BaseExpandableListAdapter#isChildSelectable(int, int)
 ******************************************************************************/
	@Override
	public boolean isChildSelectable(final int groupPosition,
			final int childPosition) {
		return false;
	}

/***************************************************************************//**
 * @see android.widget.BaseExpandableListAdapter#onGroupCollapsed(int)
 ******************************************************************************/
	@Override
	public void onGroupCollapsed(final int groupPosition) {
		super.onGroupCollapsed(groupPosition);
	}

/***************************************************************************//**
 * @see android.widget.BaseExpandableListAdapter#onGroupExpanded(int)
 ******************************************************************************/
	@Override
	public void onGroupExpanded(final int groupPosition) {
		super.onGroupExpanded(groupPosition);
	}
}