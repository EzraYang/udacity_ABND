package com.example.android.pets;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.pets.data.PetContract.PetEntry;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    PetCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });


        // attach an CursorAdapter of null database to the listView
        ListView petListView = (ListView) findViewById(R.id.list);
        mAdapter = new PetCursorAdapter(getBaseContext(), null);
        petListView.setAdapter(mAdapter);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        View emptyView = findViewById(R.id.empty_view);
        petListView.setEmptyView(emptyView);

        // when a pet is clicked, sent the uri of that pet in database
        // in an Intent to open EditorActivity
        petListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uriOfClickedPet = ContentUris.withAppendedId(PetEntry.CONTENT_URI, id);
                Intent openEditorAct = new Intent(CatalogActivity.this, EditorActivity.class);
                openEditorAct.setData(uriOfClickedPet);
                startActivity(openEditorAct);
            }
        });



        getLoaderManager().initLoader(0, null, this);

//        displayDatabaseInfo();

    }

    @Override
    protected void onStart() {
        super.onStart();
//        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
//    private void displayDatabaseInfo() {
//
//        String [] projection = {
//                PetEntry._ID,
//                PetEntry.COLUMN_PET_NAME,
//                PetEntry.COLUMN_PET_BREED,
//                PetEntry.COLUMN_PET_GENDER,
//                PetEntry.COLUMN_PET_WEIGHT
//        };
//
//        // Perform this raw SQL query "SELECT * FROM pets"
//        // to get a Cursor that contains all rows from the pets table.
//        // Cursor cursor = db.rawQuery("SELECT * FROM " + PetEntry.TABLE_NAME, null);
////        Cursor cursor = db.query(PetEntry.TABLE_NAME, null, null, null, null, null, null);
//        Cursor cursor = getContentResolver().query(PetEntry.CONTENT_URI , projection, null, null, null);
//
//        mAdapter.changeCursor(cursor);
////        try {
////            // Display the number of rows in the Cursor (which reflects the number of rows in the
////            // pets table in the database).
////            TextView displayView = (TextView) findViewById(R.id.text_view_pet);
////            displayView.setText("Number of rows in pets database table: " + cursor.getCount() + "\n\n");
////
//////            displayView.append(PetEntry._ID + " - " +
//////                    PetEntry.COLUMN_PET_NAME + "\n");
////
////            int idColumnIndex = cursor.getColumnIndex(PetEntry._ID);
////            int nameColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_NAME);
////            int breedColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_BREED);
////            int genderColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_GENDER);
////            int weightColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_WEIGHT);
////
////            while (cursor.moveToNext()){
////                int currentId = cursor.getInt(idColumnIndex);
////                String currentName = cursor.getString(nameColumnIndex);
////                String currentBreed = cursor.getString(breedColumnIndex);
////                int currentGender = cursor.getInt(genderColumnIndex);
////                int currentWeight = cursor.getInt(weightColumnIndex);
////
////                displayView.append("\n" + currentId + " - " +
////                        currentName + " - " +
////                        currentBreed + " - " +
////                        currentGender + " - " +
////                        currentWeight );
////            }
////        } finally {
////            // Always close the cursor when you're done reading from it. This releases all its
////            // resources and makes it invalid.
////            cursor.close();
////        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                // ContentValues made of data of a fake pet
                ContentValues values = new ContentValues();
                values.put(PetEntry.COLUMN_PET_NAME, "Toto");
                values.put(PetEntry.COLUMN_PET_BREED, "Terrier");
                values.put(PetEntry.COLUMN_PET_GENDER, PetEntry.GENDER_MALE);
                values.put(PetEntry.COLUMN_PET_WEIGHT, 7);

                Uri  newRowUri = getContentResolver().insert(PetEntry.CONTENT_URI, values);
                Log.i("CatalogActivity", "New row uri is " + newRowUri);

//                change to use a cursorLoader
//                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                getContentResolver().delete(PetEntry.CONTENT_URI, null, null);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String [] projection = {
                PetEntry._ID,
                PetEntry.COLUMN_PET_NAME,
                PetEntry.COLUMN_PET_BREED,
//                PetEntry.COLUMN_PET_GENDER,
//                PetEntry.COLUMN_PET_WEIGHT
        };

        return new CursorLoader(
                getBaseContext(),
                PetEntry.CONTENT_URI,   // an uri reffering to table pets
                projection,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null) {
            mAdapter.swapCursor(data);
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);

    }

}