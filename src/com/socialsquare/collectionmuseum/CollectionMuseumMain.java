package com.socialsquare.collectionmuseum;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.socialsquare.collectionmuseum.activities.CollectionActivity;
import com.socialsquare.collectionmuseum.adapters.CollectionAdapter;
import com.socialsquare.collectionmuseum.datamodel.collections.Collection;

public class CollectionMuseumMain extends Activity {
	private static final int MENU_NEW_COLLECTION = Menu.FIRST;
	private static final int MENU_ORGANIZE_COLLECTIONS = MENU_NEW_COLLECTION + 1;

	private static final int REQ_CODE_CREATE_COLLECTION = 0;
	// private static final int REQ_CODE_CHOOSE_ACTION = 1;
	protected static final int DIALOG_ACTION = 0;

	// Variables
	ListView mainList;
	List<Collection> allCollections;
	CollectionAdapter collectionAdapter;
	String selectedCollection;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mainList = (ListView) findViewById(R.id.mainList);
		populateCollectionList();
	}

	private void populateCollectionList() {
		allCollections = Collection.findAll();
		collectionAdapter = new CollectionAdapter(this, 0, allCollections);
		mainList.setAdapter(collectionAdapter);
		mainList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int arg2,
					long arg3) {
				selectedCollection = ((TextView) v
						.findViewById(R.id.collection_name)).getText()
						.toString();
				// Intent i = new Intent(parent.getContext(),
				// CollectionPopupActivity.class);
				// startActivityForResult(i, REQ_CODE_CHOOSE_ACTION);
				showDialog(DIALOG_ACTION);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Boolean result = super.onCreateOptionsMenu(menu);
		// MenuItem newCollection =
		menu.add(0, MENU_NEW_COLLECTION, Menu.NONE, R.string.menu_newCollection);
		menu.add(1, MENU_ORGANIZE_COLLECTIONS, Menu.NONE,
				R.string.menu_organizeCollections);
		return result;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Boolean result = super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case MENU_NEW_COLLECTION:
			Intent i = new Intent(this, CollectionActivity.class);
			startActivityForResult(i, REQ_CODE_CREATE_COLLECTION);
			break;
		}
		return result;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case REQ_CODE_CREATE_COLLECTION:
			if (resultCode == RESULT_OK) {
				allCollections.add(Collection.create(
						data.getStringExtra("name"),
						data.getStringExtra("longDescription")));
				collectionAdapter.notifyDataSetChanged();
			}
			break;
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_ACTION:
			return new AlertDialog.Builder(CollectionMuseumMain.this)
					.setTitle(selectedCollection)
					.setItems(R.array.collection_dialog,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {

									/* User clicked so do some stuff */
									String[] items = getResources()
											.getStringArray(
													R.array.collection_dialog);
									new AlertDialog.Builder(
											CollectionMuseumMain.this)
											.setMessage(
													"You selected: " + which
															+ " , "
															+ items[which])
											.show();
								}
							}).create();
		default:
			return null;
		}

	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		super.onPrepareDialog(id, dialog);
		switch (id) {
		case DIALOG_ACTION:
			dialog.setTitle(selectedCollection);
			break;
		}
	}
}