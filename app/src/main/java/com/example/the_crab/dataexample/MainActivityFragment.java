package com.example.the_crab.dataexample;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.the_crab.dataexample.data.DataAdapter;
import com.example.the_crab.dataexample.data.DataContract;
import com.example.the_crab.dataexample.data.DummyData;

import java.util.ArrayList;
import java.util.List;


public class MainActivityFragment extends Fragment {

    //Temporary list while RecyclerView is developed
    private List<DummyData> dataList = new ArrayList<>();

    //Instance of the DataAdapter, is bound to the RecyclerView
    private DataAdapter mAdapter;

    //The RecyclerView, instantiated here to have several methods called on it later
    private RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Adapter is instantiated and passed the dataList in this case since the Loader
        //isn't implemented
        mAdapter = new DataAdapter(dataList);
        //Scrap this method once Loader implemented
        populateData();

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
            values.put(DataContract.Data.COLUMN_WORD, "Aschgieger");
            values.put(DataContract.Data.COLUMN_MEANING, "Ass Violin");
            Uri insertUri = getContext().getContentResolver().insert(DataContract.Data.CONTENT_URI, values);
            System.out.println(DataContract.Data.CONTENT_URI.toString());
            //  System.out.println(insertUri.toString());
        }
        cursor = getContext().getContentResolver().query(DataContract.Data.CONTENT_URI, null, null, null, null);

       while(cursor.moveToNext()) {
           System.out.println("Cursor result:" + cursor.getString(cursor.getColumnIndex("word")));
           System.out.println("Cursor result:" + cursor.getString(cursor.getColumnIndex("meaning")));
       }

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

    public void populateData(){
        DummyData data = new DummyData("Treppenwitz", "The things you should have said but only occur to you when it is too late, such as all the witty one-liners you only think of after you have left the party.");
        dataList.add(data);
        data = new DummyData("Torschlusspanik", "The fear, usually as one gets older, that time is running out and important opportunities are slipping away");
        dataList.add(data);
        data = new DummyData("Backpfeifengesicht", "A face that cries out for a fist in it");
        dataList.add(data);
        data = new DummyData("Erklärungsnot", "The state of requiring a credible explanation at very short notice, such as being discovered by your spouse in the company of an attractive young acquaintance, having claimed to be working late.");
        dataList.add(data);
        mAdapter.notifyDataSetChanged();

    }
}
