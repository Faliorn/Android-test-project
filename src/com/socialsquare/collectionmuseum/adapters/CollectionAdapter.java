package com.socialsquare.collectionmuseum.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.socialsquare.collectionmuseum.R;
import com.socialsquare.collectionmuseum.datamodel.collections.Collection;

public class CollectionAdapter extends ArrayAdapter<Collection> {
	List<Collection> collections;
	Context context;

	public CollectionAdapter(Context context, int textViewResourceId,
			List<Collection> objects) {
		super(context, textViewResourceId, objects);
		this.collections = objects;
		this.context = context;
	}

	@Override
	public void add(Collection object) {
		super.add(object);
		collections.add(object);
	}

	@Override
	public void remove(Collection object) {
		super.remove(object);
		collections.add(object);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// return super.getView(position, convertView, parent);
		View v = convertView;
		if (convertView == null) {
			LayoutInflater l = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = l.inflate(R.layout.collection_listitem, null);
		}
		Collection c = collections.get(position);
		if (c != null) {
			TextView name = (TextView) v.findViewById(R.id.collection_name);
			TextView subtitle = (TextView) v
					.findViewById(R.id.collection_subtitle);
			name.setText(c.getName());
			subtitle.setText(c.getSubtitle());
		}
		return v;
	}
}
