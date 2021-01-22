package com.example.prakriti;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;


    /**
     * Created by Than Dana on 21/12/2019.
     **/

    public class DatabaseAdaptor extends SQLiteOpenHelper {

        public static String DATABASE_NAME = "constants_db";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_NAME = "constants_table";
        private static final String KEY_ID = "id";
        private static final String KEY_FIRSTNAME = "constant";
        private static final String KEY_SECONDNAME = "value";


        /* CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT); */

        private static final String CREATE_TABLE_COMMAND = "CREATE TABLE "
                + TABLE_NAME + "(" + KEY_FIRSTNAME + " TEXT,"+ KEY_SECONDNAME+ " TEXT);";

        public DatabaseAdaptor(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

            Log.d("table", CREATE_TABLE_COMMAND);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_COMMAND);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME+ "'");
            onCreate(db);
        }

        public long addConstant(String constant, String value) {
            SQLiteDatabase db = this.getWritableDatabase();
            // Creating content values
            ContentValues values = new ContentValues();
            values.put(KEY_FIRSTNAME, constant);
            values.put(KEY_SECONDNAME, value);
            // insert row in students table
            long insert = db.insert(TABLE_NAME, null, values);

            return insert;
        }


        public void truncatetable() {
            SQLiteDatabase db = this.getWritableDatabase();
            // Creating content values
            String truncate_command="delete from " +TABLE_NAME+";";
            Log.d("truncate", truncate_command);
            db.execSQL(truncate_command);

            String truncate_command2="vacuum;";
            Log.d("truncate2", truncate_command2);
            db.execSQL(truncate_command2);


        }




        public void insertConstant(String constant, String value) {
            SQLiteDatabase db = this.getWritableDatabase();
            String value_ext="\'"+value+"\'";
            String const_ext="\'"+constant+"\'";
            String insert_command="update " + TABLE_NAME+ " set value="+value_ext+" where constant="+const_ext+";";
            Log.d("insert command",insert_command);
            db.execSQL(insert_command);
        }



        public ArrayList<String> getAllConstants() {
            ArrayList<String> constantsArrayList = new ArrayList<String>();
            String name="";
            String selectQuery = "SELECT  * FROM " + TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (c.moveToFirst()) {

                do {
                    name = c.getString(c.getColumnIndex(KEY_FIRSTNAME));
                    constantsArrayList.add(name);
                } while (c.moveToNext());

                Log.d("array", constantsArrayList.toString());
            }
            return constantsArrayList;
        }


        public ArrayList<String> getAllValues() {
            ArrayList<String> constantsArrayList = new ArrayList<String>();
            String name="";
            String selectQuery = "SELECT  * FROM " + TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (c.moveToFirst()) {

                do {
                    name = c.getString(c.getColumnIndex(KEY_SECONDNAME));
                    constantsArrayList.add(name);
                } while (c.moveToNext());

                Log.d("array", constantsArrayList.toString());
            }
            return constantsArrayList;
        }



    }









