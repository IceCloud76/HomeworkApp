/******************************************************************************
 *                                                                           *
 *    Class Name: update_item.java                                           *
 *                                                                           *
 *           Purpose: Allows the user to update items alread present in      *
 *           the list.                                                       *
 *                                                                           *
 *****************************************************************************/

package edu.niu.z1864126.assignment8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class update_item extends AppCompatActivity
{
    /*onCreate: Reloads app activity to prior saved state*/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);
    }

    /*backToMain: Onclick method to be used with the back button; Returns the user to the
     * main activity */
    public void backToMain(View v)
    {
        finish();
    }


    
}