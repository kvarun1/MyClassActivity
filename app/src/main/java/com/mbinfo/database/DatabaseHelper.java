package com.mbinfo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.mbinfo.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABSE_VERSION = "1";
    private static final String DATABSE_NAME = "UserDb.db";
    public static final String TABLE_NAME = "User";
    // column name
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String USER_PASSWORD = "user_password";
    private static final String USER_PHONE = "user_phone";
 /*   // create table
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_NAME + " TEXT,"
            + USER_PASSWORD + " TEXT," + USER_PHONE + " TEXT" + ")";*/
    // TABLE CREATION STATEMENT
    private static final String CREATE_USER_TABLE = "create table "
            + TABLE_NAME + "(" + USER_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_NAME + " TEXT NOT NULL, "
            + USER_PASSWORD + " TEXT NOT NULL, "
           + USER_PHONE + " TEXT NOT NULL)";
    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABSE_NAME,null, Integer.parseInt(DATABSE_VERSION));
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        onCreate(sqLiteDatabase);

    }

    // create user
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, user.getUsername());
        values.put(USER_PASSWORD, user.getPassword());
        values.put(USER_PHONE, user.getPhone());
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                USER_ID,
                USER_NAME,
                USER_PASSWORD,
                USER_PHONE
        };
        // sorting orders
        String sortOrder =
                USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(USER_ID))));
                user.setUsername(cursor.getString(cursor.getColumnIndex(USER_NAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(USER_PASSWORD)));
                user.setPhone(cursor.getString(cursor.getColumnIndex(USER_PHONE)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user list
        return userList;
    }
    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, user.getUsername());
        values.put(USER_PASSWORD, user.getPassword());
        values.put(USER_PHONE, user.getPhone());
        // updating row
        db.update(TABLE_NAME, values, USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_NAME, USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
    /**
     * This method to check user exist or not
     *
     * @param username
     * @return true/false
     *
     */

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String username, String password) {
        // array of columns to fetch
        String[] columns = {
                USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = USER_NAME + " = ?" + " AND " + USER_PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {username, password};
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
}
