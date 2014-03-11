package com.rss_pion.ui.adapter;

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

public class FluxDetailsExpandableListAdapter extends BaseExpandableListAdapter {

	private final SparseArray<GroupFluxDetails> groups;
	public LayoutInflater inflater;

	public FluxDetailsExpandableListAdapter(final Dialog dialog,
			final SparseArray<GroupFluxDetails> groups) {
		this.groups = groups;
		this.inflater = dialog.getLayoutInflater();
	}

	@Override
	public Object getChild(final int groupPosition, final int childPosition) {
		return this.groups.get(groupPosition).children.get(childPosition);
	}

	@Override
	public long getChildId(final int groupPosition, final int childPosition) {
		return 0;
	}

	@Override
	public int getChildrenCount(final int groupPosition) {
		return this.groups.get(groupPosition).children.size();
	}

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

	@Override
	public Object getGroup(final int groupPosition) {
		return this.groups.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.groups.size();
	}

	@Override
	public long getGroupId(final int groupPosition) {
		return 0;
	}

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

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(final int groupPosition,
			final int childPosition) {
		return false;
	}

	@Override
	public void onGroupCollapsed(final int groupPosition) {
		super.onGroupCollapsed(groupPosition);
	}

	@Override
	public void onGroupExpanded(final int groupPosition) {
		super.onGroupExpanded(groupPosition);
	}
}