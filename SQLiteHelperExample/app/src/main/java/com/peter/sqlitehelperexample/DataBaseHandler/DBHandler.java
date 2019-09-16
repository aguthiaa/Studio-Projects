package com.peter.sqlitehelperexample.DataBaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="contactsManager";
    private static final String TABLE_CONTACTS="contacts";
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_PHONE_NUMBER="phoneNumber";


    public DBHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    //Creating tables


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE="CREATE TABLE "+TABLE_CONTACTS+"("+KEY_ID+"INTEGER PRIMARY KEY,"+KEY_NAME+"TEXT,"+KEY_PHONE_NUMBER+"TEXT"+")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    //upgrading the database

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //drop Old database if exists and create a new one
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_CONTACTS);

        //creating the database again
        onCreate(sqLiteDatabase);
    }

    //code to add new contact

    public void addContact(Contact contact){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_NAME,contact.getName());
        contentValues.put(KEY_PHONE_NUMBER,contact.getPhoneNumber());

        //Inserting content values in a row
        sqLiteDatabase.insert(TABLE_CONTACTS,null,contentValues);
        //closing database
        sqLiteDatabase.close();
    }
    //code to get the single contact
   public Contact getContact(int id){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();

       Cursor cursor=sqLiteDatabase.query(TABLE_CONTACTS, new String[]{KEY_ID,KEY_NAME,KEY_PHONE_NUMBER},KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);
       if (cursor !=null){
           cursor.moveToFirst();
       }
       Contact contact=new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
       return contact;
   }
   //Code to get all elements in a listview
    public List<Contact> getAllContacts(){
        List<Contact>contactList=new ArrayList<Contact>();

        //select all query
        String selectQuery="SELECT * FROM "+TABLE_CONTACTS;
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        //looping through all rows and adding to list
        if (cursor.moveToFirst()){
            do {
                Contact contact=new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                //adding contact to list
                contactList.add(contact);

            }while (cursor.moveToNext());
        }


        //return contactList
        return contactList;
    }

    //code to update the single contact
    public int updateContact(Contact contact){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_NAME,contact.getName());
        contentValues.put(KEY_PHONE_NUMBER,contact.getPhoneNumber());

        return sqLiteDatabase.update(TABLE_CONTACTS,contentValues,KEY_ID+"=?",new String[]{String.valueOf(contact.getId())});
    }

    //Deleting single contact
    public void deleteContact(Contact contact){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_CONTACTS,KEY_ID+"=?",new String[]{String.valueOf(contact.getId())});
        sqLiteDatabase.close();
    }
    //getting contacts count
    public int getContactsCount(){
        String countQuery="SELECT * FROM "+TABLE_CONTACTS;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(countQuery,null);
        cursor.close();
        return cursor.getCount();
    }


}
