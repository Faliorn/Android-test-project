package com.socialsquare.collectionmuseum.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.socialsquare.collectionmuseum.R;
import com.socialsquare.collectionmuseum.datamodel.collections.CollectionCategory;

public class CollectionCategoryAdapter extends ArrayAdapter<CollectionCategory> {
	List<CollectionCategory> collectionCategories;
	Context context;

	public CollectionCategoryAdapter(Context context, int textViewResourceId,
			List<CollectionCategory> objects) {
		super(context, textViewResourceId, objects);
		this.collectionCategories = objects;
		this.context = context;
	}

	@Override
	public void add(CollectionCategory object) {
		super.add(object);
		collectionCategories.add(object);
	}

	@Override
	public void remove(CollectionCategory object) {
		super.remove(object);
		collectionCategories.add(object);
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
		CollectionCategory cc = collectionCategories.get(position);
		if (cc != null) {
			TextView description = (TextView) v
					.findViewById(R.id.collectioncategory_description);
			description.setText(cc.getDescription());
		}
		return v;
	}
}
