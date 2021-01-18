package com.counttoast.votingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "voting.db";
    public static final String TABLE_NAME = "customer";
    public static final String TABLE_NAME1 = "candidates";
    public static final String Col_1 = "fname";
    public static final String Col_2 = "lname";
    public static final String Col_3 = "email";
    public static final String Col_4 = "password";
    public static final String Col_5 = "phone";
    public static final String Colm_1 = "groups";
    public static final String Colm_2 = "result";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + "(fname TEXT,lname TEXT,email TEXT,password TEXT PRIMARY KEY ,phone TEXT)");
        db.execSQL("create table " + TABLE_NAME1 + "(groups TEXT,result INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);
    }

    public boolean insertData(String fname, String lname, String email, String password, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_1, fname);
        contentValues.put(Col_2, lname);
        contentValues.put(Col_3, email);
        contentValues.put(Col_4, password);
        contentValues.put(Col_5, phone);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        if (result == -1)
            return false;
        else
            return true;


    }

    public boolean insertCandidate(String group, int result) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();

        Values.put(Colm_1, group);
        Values.put(Colm_2, result);
        long result1 = db.insert(TABLE_NAME1, null, Values);
        db.close();
        if (result1 == -1)
            return false;
        else
            return true;
    }

    public boolean checkuser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from customer where email = ? and password = ? ", new String[]{email, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public int previousBal(String activeuser) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select result from " + TABLE_NAME1 + " where groups=? order by t_id desc limit 1", new String[]{activeuser});
        int result = cursor.getInt(Integer.parseInt("result"));
        result = result + 1;
        SQLiteDatabase db1 = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME1 + " SET result = " + result + " WHERE groups = " + activeuser;
        db.beginTransaction();
        SQLiteStatement stmt = db.compileStatement(sql);
        try {
            stmt.execute();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return result;
    }


}
