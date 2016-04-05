package com.example.the_crab.dataexample.data;

import android.net.Uri;
import android.provider.BaseColumns;


/**
 * Created by the_crab on 5/04/16.
 */
public class DataContract {

    //Authority of the Data Provider
    public static final String CONTENT_AUTHORITY = "com.example.the_crab.dataexample";

    //The content URI
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_DATA = "data";

    public static final class Data implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_DATA).build();

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_DATA;

        //  public static final String CONTENT_TYPE =
        //           ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;
        //  public static final String CONTENT_ITEM_TYPE =
        //           ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;

        public static final String TABLE_NAME = "data";

        //Single strings for the data
        public static final String COLUMN_WORD = "word";
        public static final String COLUMN_MEANING =  "meaning";


       // public static Uri buildDataUri(long id){
       //     return ContentUris.withAppendedId(CONTENT_URI, id);
        }
}
