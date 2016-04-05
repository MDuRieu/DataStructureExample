package com.example.the_crab.dataexample.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
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
    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
