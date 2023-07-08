package com.example.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "AppDB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "AppTable";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_C_ANSWER = "CAnswer";
    private static final String COLUMN_U_ANSWER = "UAnswer";
    private static final String COLUMN_Alphabet = "Alphabet";

    public DBHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_C_ANSWER + " TEXT," +
                COLUMN_U_ANSWER + " TEXT," +
                COLUMN_Alphabet + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertAnswer(String answer, String buttonType, String ltr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_C_ANSWER, answer);
        values.put(COLUMN_U_ANSWER, buttonType);
        values.put(COLUMN_Alphabet,ltr);
        long result = db.insert(TABLE_NAME, null, values);
        db.close();

    }
}
