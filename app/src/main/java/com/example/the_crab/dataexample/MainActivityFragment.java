package com.example.the_crab.dataexample;

//import android.app.LoaderManager;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.the_crab.dataexample.data.DataAdapter;
import com.example.the_crab.dataexample.data.DataContract;

//import android.content.CursorLoader;
//import android.content.Loader;


public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    //Instance of the DataAdapter, is bound to the RecyclerView
    private DataAdapter adapter;

    //The RecyclerView, instantiated here to have several methods called on it later
    private RecyclerView recyclerView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(0, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Adapter is instantiated
        adapter = new DataAdapter();


    }
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //Get a cursor and see if there's any dummy data in the database. This is just for
        // testing so we have something to show by default
        Cursor cursor = getContext().getContentResolver().query(DataContract.Data.CONTENT_URI, null, null, null, null);

        if (!cursor.moveToFirst()) {
            ContentValues values = new ContentValues();
            values.put(DataContract.Data.COLUMN_WORD, "Treppenwitz");
            values.put(DataContract.Data.COLUMN_MEANING, "The things you should have said but only occur to you when it is too late, such as all the witty one-liners you only think of after you have left the party.");
            Uri insertUri = getContext().getContentResolver().insert(DataContract.Data.CONTENT_URI, values);
            values.put(DataContract.Data.COLUMN_WORD, "Torschlusspanik");
            values.put(DataContract.Data.COLUMN_MEANING, "The fear, usually as one gets older, that time is running out and important opportunities are slipping away");
            insertUri = getContext().getContentResolver().insert(DataContract.Data.CONTENT_URI, values);
            values.put(DataContract.Data.COLUMN_WORD, "Backpfeifengesicht");
            values.put(DataContract.Data.COLUMN_MEANING, "A face that cries out for a fist in it");
            insertUri = getContext().getContentResolver().insert(DataContract.Data.CONTENT_URI, values);
            values.put(DataContract.Data.COLUMN_WORD, "Erklärungsnot");
            values.put(DataContract.Data.COLUMN_MEANING, "The state of requiring a credible explanation at very short notice, such as being discovered by your spouse in the company of an attractive young acquaintance, having claimed to be working late.");
             insertUri = getContext().getContentResolver().insert(DataContract.Data.CONTENT_URI, values);

        }
        Cursor tempCursor = getContext().getContentResolver().query(DataContract.Data.CONTENT_URI, null, null, null, null);

        while (tempCursor.moveToNext()) {
            System.out.println("Cursor result:" + tempCursor.getString(cursor.getColumnIndex("word")));
            System.out.println("Cursor result:" + tempCursor.getString(cursor.getColumnIndex("meaning")));
        }
            recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);

            return rootView;
        }



    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),(DataContract.Data.CONTENT_URI), null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    adapter.swapCursor(null);
    }
}
