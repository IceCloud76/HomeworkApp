package edu.niu.z1864126.assignment8;

import android.content.ContentValues;
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

    //get the all notes
    public ArrayList<homeworkItem> getNotes() {
        ArrayList<homeworkItem> arrayList = new ArrayList<>();

        // select all query
        String select_query= "SELECT *FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                homeworkItem noteModel = new homeworkItem();
                noteModel.setID(cursor.getInt(0));
                noteModel.setDescOfTask(cursor.getString(1));
                if (cursor.getInt(2) == 0) {
                    noteModel.setCompleted(false);
                }else{
                    noteModel.setCompleted(true);
                }
                //noteModel.setCompleted(cursor.getInt(2));
                arrayList.add(noteModel);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    //add the new note
    public void insertItem(String desc) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("DESCRIPTION", desc);
        //values.put("Description", des);

        //inserting new row
        sqLiteDatabase.insert(TABLE_NAME, null , values);
        //close database connection
        sqLiteDatabase.close();
    }

    SQLiteDatabase db = this.getWritableDatabase();

}
