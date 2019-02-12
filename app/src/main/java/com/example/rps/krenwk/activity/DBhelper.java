package com.example.rps.krenwk.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBhelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "krenwk";
    private static final String TABLE_NAME = "krenwkpro";

    private static final String _ID = "_id";
    private static final String NAMAE = "namae";
    private static final String NOMORPHONE = "nomorphone";
    private static final String ALAMATMAIL = "alamatmail";
    private static final String PROFILEPIC = "profilepic";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";


    //Constructor
    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Query to create table
        //String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + NOMORPHONE + " TEXT," + ALAMATMAIL + " TEXT" + ")";
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + _ID + " TEXT,"
                + NAMAE + " TEXT,"
                + NOMORPHONE + " TEXT,"
                + ALAMATMAIL + " TEXT,"
                + LATITUDE + " TEXT,"
                + LONGITUDE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    //onUpgrade executes once databse version changed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    //method to add data to database
    void addUser(Users user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(_ID, user.get_Id());
        values.put(NAMAE, user.getNamae());
        values.put(NOMORPHONE, user.getNomorphone());
        values.put(ALAMATMAIL, user.getAlamatmail());
        values.put(LATITUDE, user.getLatitude());
        values.put(LONGITUDE, user.getLongitude());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //method to return List of rows from table in database
    public List<Users> getAllUsers() {
        List<Users> contactList = new ArrayList<Users>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;  //retrieve data from the database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Users user = new Users();
                user.set_Id(cursor.getString(0));
                user.setNamae(cursor.getString(1));
                user.setNomorphone(cursor.getString(2));
                user.setAlamatmail(cursor.getString(3));
                user.setLatitude(cursor.getString(4));
                user.setLongitude(cursor.getString(5));
                contactList.add(user);
            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public List<UsersVideodemand> getAllVideo(){
        List<UsersVideodemand> videoDetail = new ArrayList<UsersVideodemand>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectquery = "SELECT * FROM " + "krenwkvideo" ;
        Cursor cursor = db.rawQuery(selectquery, null);
        if(cursor.moveToFirst()){
            do{
                UsersVideodemand video = new UsersVideodemand();
                video.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("title")));
                video.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("description")));
                video.setThumbnail(cursor.getString(cursor.getColumnIndexOrThrow("thumbnail")));
                video.setUrl(cursor.getString(cursor.getColumnIndexOrThrow("url")));
                video.setCreatetime(cursor.getString(cursor.getColumnIndexOrThrow("createtime")));
                videoDetail.add(video);
            }
            while (cursor.moveToNext());
        }

        return videoDetail;
    }
}


