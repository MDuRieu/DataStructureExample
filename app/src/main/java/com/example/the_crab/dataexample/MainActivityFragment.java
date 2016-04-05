package com.example.the_crab.dataexample;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.the_crab.dataexample.data.DataContract;


public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ContentValues values = new ContentValues();
        values.put(DataContract.Data.COLUMN_WORD, "Aschgieger");
        values.put(DataContract.Data.COLUMN_MEANING, "Ass Violin");
        Uri insertUri = getContext().getContentResolver().insert(DataContract.Data.CONTENT_URI, values);
        System.out.println(DataContract.Data.CONTENT_URI.toString());
      //  System.out.println(insertUri.toString());

        Cursor cursor = getContext().getContentResolver().query(DataContract.Data.CONTENT_URI, null, null, null, null);
        cursor.moveToFirst();
        System.out.println("Cursor result:" + cursor.getString(cursor.getColumnIndex("word")));
        System.out.println("Cursor result:" + cursor.getString(cursor.getColumnIndex("meaning")));

        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
