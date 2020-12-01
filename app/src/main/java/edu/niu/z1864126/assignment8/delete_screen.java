/******************************************************************************
 *                                                                           *
 *    Class Name: delete_screen.java                                         *
 *                                                                           *
 *           Purpose: Allows the user to delete items from the list.         *
 *                                                                           *
 *****************************************************************************/

package edu.niu.z1864126.assignment8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class delete_screen extends AppCompatActivity
{

    /*onCreate: Reloads app activity to prior saved state*/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_screen);
    }

    /*backToMain: Onclick method to be used with the back button; Returns the user to the
     * main activity */
    public void backToMain(View v)
    {
        finish();
    }
}