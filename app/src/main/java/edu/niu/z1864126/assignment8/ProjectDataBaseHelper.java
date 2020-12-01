package edu.niu.z1864126.assignment8;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProjectDataBaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "HomeworkList.db";
    public static final String TABLE_NAME = "tableone";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "DESCRIPTION";
    public static final String COL_3 = "COMPLETED";

    public ProjectDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , DESCRIPTION TEXT, COMPLETED BOOLEAN)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    SQLiteDatabase db = this.getWritableDatabase();

}
