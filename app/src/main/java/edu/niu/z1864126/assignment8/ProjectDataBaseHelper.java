/******************************************************************************
 *                                                                           *
 *    Class Name: ProjectDataBaseHelper.java                                 *
 *                                                                           *
 *           Purpose: helps the devs to access database.                     *
 *           Created By Brendon Brewer and Eric Kirchman                     *
 *                                                                           *
 *****************************************************************************/
package edu.niu.z1864126.assignment8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    public ArrayList<homeworkItem> retrieveItems() {
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
        values.put("COMPLETED", false);

        //inserting new row
        sqLiteDatabase.insert(TABLE_NAME, null , values);
        //close database connection
        sqLiteDatabase.close();
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }

    public int deleteItem (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("tableone",
                "ID = ? ",
                new String[] { Integer.toString(id) });
    }
    //SQLiteDatabase db = this.getWritableDatabase();

    public boolean updateItem(Integer id, boolean done) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("COMPLETED", done);
        db.update("tableone", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public boolean updateItem(Integer id, String newDescription) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DESCRIPTION", newDescription);
        db.update("tableone", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }


}
