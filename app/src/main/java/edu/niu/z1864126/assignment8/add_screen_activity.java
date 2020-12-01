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

public class add_screen_activity extends AppCompatActivity {

    /*onCreate: Reloads app activity to prior saved state*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_screen_activity);
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
        //Code will go here when database is finished
    }
}