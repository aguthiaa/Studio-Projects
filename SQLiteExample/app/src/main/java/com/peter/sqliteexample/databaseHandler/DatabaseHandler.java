package com.peter.sqliteexample.databaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="contactsManager";
    private static final String TABLE_CONTACTS="contacts";
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_PHONE_NUMBER="phone_number";
    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    //Creating tables

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CTREATE_CONTACTS_TABLE="CREATE TABLE "+TABLE_CONTACTS+"("+KEY_ID+"INTEGER PRIMARY KEY,"+KEY_NAME+"TEXT,"+KEY_PHONE_NUMBER+"TEXT"+")";
            sqLiteDatabase.execSQL(CTREATE_CONTACTS_TABLE);
    }
    //upgrading database

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_CONTACTS);
        //create table again
        onCreate(sqLiteDatabase);

    }
    //Code to add new contact
    public void addContact(Contact contact){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_NAME,contact.get_name());
        contentValues.put(KEY_PHONE_NUMBER,contact.get_phoneNumber());

        //inserting row
        sqLiteDatabase.insert(TABLE_CONTACTS,null,contentValues);
        //closing database connections
        sqLiteDatabase.close();
    }


    public List<String> getContacts(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }


}
