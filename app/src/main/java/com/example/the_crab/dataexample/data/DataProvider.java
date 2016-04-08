package com.example.the_crab.dataexample.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by the_crab on 5/04/16.
 *
 * If you start with a blank class and extend ContentProvider, Android Studio will prompt you to
 * implement the required methods OnCreate, query, getType, insert, delete, update. These are the
 * common methods you can do to your database. Each one takes an address (URI) and some arguments
 * of one form or another.
 *
 * ********************************************
 * THE MOST CONFUSING THING ABOUT DATABASES IN ANDROID IS that you have to register this
 * content provider in the manifest!!! If you don't, you will lose your mind trying to figure out
 * what's wrong. Also, you NEVER INSTANTIATE OR CALL THIS CLASS DIRECTLY.
 *
 * You get cursor objects in your other classes (Like MainActivityFragment in this app) by calling
 * things like  Cursor cursor  = getContext().getContentResolver().query(URI and arguments in here)
 *
 * The content resolver knows where to go because we told it that this content provider exists in
 * the manifest! This goes inside the application tag
 *   <provider
 *   android:authorities="com.example.the_crab.dataexample"
 *   android:name=".data.DataProvider" />
 * ********************************************
 *
 */
public class DataProvider extends ContentProvider{

    //Declare an instance of the database helper for use by methods in this class
    private DataDbHelper mOpenHelper;

    static final int DATA = 100;

    //The URI Matcher used by this provider
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    @Override
    public boolean onCreate() {


        //Initialise instance variable
        mOpenHelper = new DataDbHelper(getContext());
        return true; //Return true to tell Android that the content provider was created successfully
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        //Declare the cursor to be returned by the query method
        Cursor retCursor;
        //This statement queries the database using the parameters input from when the content provider
        //was called. You can use a switch statement based on the URI passed to determine which arguments
        //you want to actually pass. This stops rogue developers from making calls on your database that
        //weren't intended.
        switch (sUriMatcher.match(uri)) {
            case DATA:{
                retCursor = mOpenHelper.getReadableDatabase().query(
                        DataContract.Data.TABLE_NAME,
                        null,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);


        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }


    @Nullable
    @Override
    //Not necessary for this example app but good to have an idea how it works
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match){
            case DATA:
                //This returns the content type because it returns multiple items
                //(what happens when there's only one DB entry?)
                return DataContract.Data.CONTENT_TYPE;

            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);

        }

    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //Get an instance of the database to write to
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        long _id = db.insert(DataContract.Data.TABLE_NAME, null, values);

        Uri returnUri = DataContract.Data.buildDataUri(_id);


        getContext().getContentResolver().notifyChange(uri, null); //Notifies any registered observers of changes
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    static UriMatcher buildUriMatcher(){
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = DataContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, DataContract.PATH_DATA, DATA);

        return matcher;
    }

}
