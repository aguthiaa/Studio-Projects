package com.peter.spinnerexample.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="spinnerExample";
    private static final String TABLE_NAME="labels";
    private static final String COLUMN_ID="id";
    private static final String column_name ="name";

    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME , null,DATABASE_VERSION);
    }
    //creating tables

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + column_name + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_ITEM_TABLE);

    }

    //Upgrading database

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //Drop older table if it does not exist
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        //RECREATE THE TABLE AGAIN
        onCreate(sqLiteDatabase);
    }
    //Inserting new lable in labels table
    public  void insertLabel(String label){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(column_name, label);//column name, column value

        // Inserting Row
        db.insert(TABLE_NAME, null, values);//tableName, nullColumnHack, CotentValues
        db.close(); // Closing database connection
    }
    //Getting all labels
    //returning list of labels
    public CharSequence getAllLabels(){
        StringBuffer myb=new StringBuffer();
        //Select all queries
        String selectQuery="SELECT * FROM "+ TABLE_NAME;

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        //looping through rows and adding items to list
        if (cursor.moveToFirst()){
            do {
                myb.append(cursor.getString(cursor.getColumnIndex(column_name))+"\n");

            }while (cursor.moveToNext());

        }
        //closing connection
        cursor.close();
        //returning label
        return myb;
    }
//    public StringBuffer stringBuffer(){
//        SQLiteDatabase getLabelList=this.getWritableDatabase();
//        String myQuery=" SELECT * FROM "+TABLE_NAME;
//        Cursor cursor=getLabelList.rawQuery(myQuery,null);
//        StringBuffer sBuffer=new StringBuffer();
//        while (cursor.moveToNext()){
//            sBuffer.append(cursor.getString(cursor.getColumnIndex(column_name)) +"\n");
//        }
//        return sBuffer;
//
//    }
}
