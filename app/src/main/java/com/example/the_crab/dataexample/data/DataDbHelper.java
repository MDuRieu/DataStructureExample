package com.example.the_crab.dataexample.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.the_crab.dataexample.data.DataContract.Data;
/**
 * Created by the_crab on 5/04/16.
 *
 * This class doesn't really need much in it. If you start with a blank class and extend SQLiteOpenHelper,
 * Android Studio will prompt you to override the essential methods (ctrl + i) and to add a default constructor
 *
 * Note that this class also imports the Data class WITHIN the DataContract class. The Data class
 * just holds a bunch of definitions basically and helps keep things orderly. Importing this class
 * allows statements like Data.TABLE_NAME as seen in the onCreate method.
 *
 */
public class DataDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "exampleDatabase.db";

    public DataDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //This string is the meat of this whole class. It's in a very specific format because
        //SQL just takes a string statement to create the database and all its tables. If you die in
        //this string, you die in real life, so make sure you get it right.
        //The three rows here (_ID, COLUMN_WORD and COLUMN_MEANING)
        //are split up onto separate lines so it's easier to read. You need the _ID line as this is
        //a unique identifier in the database for that row. The other two columns have their names
        //defined and what type of data they hold. 'Not null' means that there must be some data for
        //each of those columns.
        final String SQL_CREATE_DATA_TABLE = "CREATE TABLE " + Data.TABLE_NAME + " (" +
                Data._ID + " INTEGER PRIMARY KEY," +  //Could have autoincrement here, but there is no order to the data, so it's ok to reuse IDs
                Data.COLUMN_WORD + " TEXT NOT NULL, " +
                Data.COLUMN_MEANING + " TEXT NOT NULL" + " );";

        //This statement just takes the above string and feeds it into the database to create the table
        db.execSQL(SQL_CREATE_DATA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //This method can be used to preserve user data on an upgrade
        //But we won't be needing it here. It's required to be implemented however.
    }
}
