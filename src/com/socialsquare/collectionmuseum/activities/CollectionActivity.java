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

	/**
	 * Request codes for this activity
	 */
	public static final int REQ_CODE_CREATE_COLLECTION = 0;
	public static final int REQ_CODE_EDIT_COLLECTION = 1;
	public static final int REQ_CODE_DELETE_COLLECTION = 2;

	Button ok;
	Button cancel;
	EditText name;
	EditText longDescription;
	Spinner categories;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collection_activity);
		ok = (Button) findViewById(R.id.ok);
		cancel = (Button) findViewById(R.id.cancel);
		name = (EditText) findViewById(R.id.collection_name);
		longDescription = (EditText) findViewById(R.id.collection_longDescription);
		loadCategories();
		configureLayout();
	}

	protected void configureLayout() {
		int requestCode = getIntent().getIntExtra("requestCode",
				REQ_CODE_CREATE_COLLECTION);
		switch (requestCode) {
		case REQ_CODE_EDIT_COLLECTION:
			ok.setText(R.string.save);
			break;
		case REQ_CODE_DELETE_COLLECTION:
			ok.setText(R.string.delete);
			break;
		}
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
		cancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
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
