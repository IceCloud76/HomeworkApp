/******************************************************************************
 *                                                                           *
 *    Class Name: add_screen_activity.java                                   *
 *                                                                           *
 *           Purpose: Allows the user to enter new items into the list.      *
 *           Created By Brendon Brewer and Eric Kirchman                     *
 *                                                                           *
 *****************************************************************************/

package edu.niu.z1864126.assignment8;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ListIterator;

public class add_screen_activity extends AppCompatActivity {

    private ProjectDataBaseHelper myDb; //Declaration of database reference
    private ArrayList<homeworkItem> arrayList; //Declaration of array list to hold homeworkItem objects

    /*onCreate: Reloads app activity to prior saved state*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_screen_activity);
        myDb = new ProjectDataBaseHelper(this); //Initialization of database reference

        displayHW();
    }

    /*displayHW: This function displays all items currently held in the database/list, alongside a
     * button. */
    public void displayHW() {

        //This code block creates reference to the linear layout used to hold the list
        //under the activity and clears it of all views
        LinearLayout linearLayout = findViewById(R.id.listHolder);
        ((LinearLayout) linearLayout).removeAllViews();

        //Fills the arrayList with homeworkItem objects created from database entries
        arrayList = new ArrayList<>(myDb.retrieveItems());

        //Creation of list iterator to access each homeworkItem object
        ListIterator<homeworkItem> listItr = arrayList.listIterator();


        //This loop is used to display the text of every list item
        while(listItr.hasNext())
        {
            //Create a new textview to hold a list item
            TextView textView = new TextView(this);

            //set the Textview's text to that of the current homework item
            textView.setText(listItr.next().getDescOfTask());

            //Set the textView to wrap with its content
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


            // Add textView to LinearLayout
            if (linearLayout != null) {
                linearLayout.addView(textView);
            }
        }
    }

    /*backToMain: Onclick method to be used with the back button; Returns the user to the
    * main activity */
    public void backToMain(View v)
    {
        Intent returnResult = new Intent();
        setResult(Activity.RESULT_OK, returnResult); //Set the result as OK
        finish(); //Return to the main activity
    }

    /*onBackPressed: Listener for when the android specific back button is pressed. Returns
     * the user back to the main activity. */
    @Override
    public void onBackPressed()
    {
        Intent returnResult = new Intent();
        setResult(Activity.RESULT_OK, returnResult); //Set the result as OK
        finish(); //Return to the main activity
    }

    /*addListItem: Adds an item to the user's list and the database*/
    public void addListItem(View v)
    {
        //Create reference to the user's entry field
        EditText newListEntry = findViewById(R.id.itemEntry);

        //The following if/else block ensures the user does not provide a blank entry
        if(newListEntry.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Blank entries are not allowed.",Toast.LENGTH_SHORT).show();
        }
        else
        {
            myDb.insertItem(newListEntry.getText().toString()); //Insert the user's item to the database
            displayHW(); //Update the displayed list
            Toast.makeText(getApplicationContext(), "Item added successfully.",Toast.LENGTH_SHORT).show();
        }
    }
}