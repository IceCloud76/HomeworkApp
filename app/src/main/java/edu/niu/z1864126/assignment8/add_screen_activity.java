/******************************************************************************
 *                                                                           *
 *    Class Name: add_screen_activity.java                                   *
 *                                                                           *
 *           Purpose: Allows the user to enter new items into the list.      *
 *                                                                           *
 *****************************************************************************/

package edu.niu.z1864126.assignment8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class add_screen_activity extends AppCompatActivity {

    ProjectDataBaseHelper myDb;

    /*onCreate: Reloads app activity to prior saved state*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_screen_activity);
        myDb = new ProjectDataBaseHelper(this);
    }

    /*backToMain: Onclick method to be used with the back button; Returns the user to the
    * main activity */
    public void backToMain(View v)
    {
        finish();
    }

    /*addListItem: Adds an item to the user's list*/
    public void addListItem(View v)
    {
        EditText newListEntry = findViewById(R.id.itemEntry);

        if(newListEntry.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Blank entries are not allowed.",Toast.LENGTH_SHORT).show();
        }
        else
        {
            myDb.insertItem(newListEntry.getText().toString());
            Toast.makeText(getApplicationContext(), "Item added successfully.",Toast.LENGTH_SHORT).show();
        }
    }
}