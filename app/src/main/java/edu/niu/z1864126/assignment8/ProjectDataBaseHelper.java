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
    public static final String DATABASE_NAME = "HomeworkList.db"; //sets all out variable names to be able to quickly reference them, or change them here
    public static final String TABLE_NAME = "tableone";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "DESCRIPTION";
    public static final String COL_3 = "COMPLETED";

    public ProjectDataBaseHelper(@Nullable Context context) { //constructor
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //creates a db upon creation, or open the db upon creation of a ProjectDataBaseHelper object
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , DESCRIPTION TEXT, COMPLETED BOOLEAN)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //never actually used in our code, but might be helpful if we want to replace the db
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //get the all homeworkItems
    public ArrayList<homeworkItem> retrieveItems() {
        ArrayList<homeworkItem> arrayList = new ArrayList<>();

        // select all query from the db
        String select_query= "SELECT *FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to cursor list
        if (cursor.moveToFirst()) {
            do {
                homeworkItem homeworkModel = new homeworkItem();
                homeworkModel.setID(cursor.getInt(0));
                homeworkModel.setDescOfTask(cursor.getString(1));
                if (cursor.getInt(2) == 0) { //sets the item as true or false using an int comparison. There is no getBoolean()
                    homeworkModel.setCompleted(false);
                }else{
                    homeworkModel.setCompleted(true);
                }
                arrayList.add(homeworkModel); //add to the array
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    //add the new homeworkItem
    public void insertItem(String desc) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues(); //create a ContentValues object to insert into the db
        values.put("DESCRIPTION", desc); //this is the only two we need, as the id should be autoincremented and created automatically
        values.put("COMPLETED", false);

        //inserting new row
        sqLiteDatabase.insert(TABLE_NAME, null , values);
        //close database connection
        sqLiteDatabase.close();
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase(); //make db writable
        db.delete(TABLE_NAME,null,null);
        db.execSQL("delete from "+ TABLE_NAME);
        db.close(); //close db
    }

    public int deleteItem (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("tableone",
                "ID = ? ",
                new String[] { Integer.toString(id) }); //search db for an ID, and delete it. Return it's return value
    }
    //SQLiteDatabase db = this.getWritableDatabase();

    public boolean updateItem(Integer id, boolean done) { //takes an id and a booleon of whether or not the item is complete to update the item in the db
        SQLiteDatabase db = this.getWritableDatabase(); //make db writable
        ContentValues contentValues = new ContentValues(); //create a ContentValues object with attributes to update an item in the db
        contentValues.put("COMPLETED", done);
        db.update("tableone", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public boolean updateItem(Integer id, String newDescription) { //takes an id and a String for a new description to update an item in the db
        SQLiteDatabase db = this.getWritableDatabase(); //make db writable
        ContentValues contentValues = new ContentValues();  //create a ContentValues object with attributes to update an item in the db
        contentValues.put("DESCRIPTION", newDescription);
        db.update("tableone", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }


}
