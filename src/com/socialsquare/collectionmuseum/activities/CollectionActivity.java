package com.socialsquare.collectionmuseum.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.socialsquare.collectionmuseum.R;
import com.socialsquare.collectionmuseum.datamodel.collections.CollectionCategory;

public class CollectionActivity extends Activity {

	Button ok;
	EditText name;
	EditText longDescription;
	Spinner categories;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collection_activity);
		ok = (Button) findViewById(R.id.ok);
		name = (EditText) findViewById(R.id.collection_name);
		longDescription = (EditText) findViewById(R.id.collection_longDescription);
		ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Collection c = new Collection();
				Intent i = new Intent();
				i.putExtra("name", name.getText().toString());
				i.putExtra("longDescription", longDescription.getText()
						.toString());
				setResult(RESULT_OK, i);
				finish();
			}
		});
		loadCategories();
	}

	private void loadCategories() {
		ArrayAdapter<CharSequence> adapterForSpinner = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_spinner_item);
		adapterForSpinner
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		List<CollectionCategory> allCategories = CollectionCategory.findAll();
		categories = (Spinner) findViewById(R.id.collection_category);
		categories.setAdapter(adapterForSpinner);
		for (CollectionCategory collectionCategory : allCategories) {
			adapterForSpinner.add(collectionCategory.getDescription());
		}
	}

}
