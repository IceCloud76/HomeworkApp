/******************************************************************************
 *                                                                           *
 *    Class Name: delete_screen.java                                         *
 *                                                                           *
 *           Purpose: Allows the user to delete items from the list.         *
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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ListIterator;

public class delete_screen extends AppCompatActivity
{
    private ProjectDataBaseHelper myDb; // Declaration of variable to hold database reference
    private ArrayList<homeworkItem> arrayList; //Declaration of array list to hold homeworkItem objects

    /*onCreate: Reloads app activity to prior saved state*/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_screen);
        myDb = new ProjectDataBaseHelper(this); //Initialization of database reference

        displayHW();
    }

    /*displayHW: This function displays all items currently held in the database/list, alongside a
    * button. When the user presses the button next to a list item, the item is deleted from the
    * list and database.*/
    public void displayHW() {

        //This code block creates reference to the linear layout used to hold the list
        //under the activity and clears it of all views
        LinearLayout linearLayout = findViewById(R.id.List);
        ((LinearLayout) linearLayout).removeAllViews();

        //Fills the arrayList with homeworkItem objects created from database entries
        arrayList = new ArrayList<>(myDb.retrieveItems());

        //Creation of list iterator to access each homeworkItem object
        ListIterator<homeworkItem> listItr = arrayList.listIterator();

        //Variable to hold the current homeworkItem being worked on
        homeworkItem currentItem;

        /*This loop is used to display the text of every list item and have it alongside a radio
        /button. It also sets each button to delete an entry from the list/database when the user
         presses a button next to the list item*/
        while(listItr.hasNext())
        {
            //Creation of new button to interact with and hold list item text
            RadioButton radioButton = new RadioButton(this);

            //Get the current homeworkItem to display from the arrayList
            currentItem = listItr.next();

            //Set the buttons ID to that of the homeworkItem's ID
            radioButton.setId(currentItem.getID());

            //Set the buttons text to that of the current homework item's task
            radioButton.setText(currentItem.getDescOfTask());

            //Set the radioButton to wrap with its content
            radioButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            //Creation of button listener to delete an item from the list/database when the user
            //presses a button next to an item
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    myDb.deleteItem(radioButton.getId()); //Delete the item from the database
                    linearLayout.removeView(radioButton); //Remove the item from being displayed

                    //Inform the user the item has been deleted
                    String msg = "Item Deleted.";
                    Toast.makeText(delete_screen.this, msg, Toast.LENGTH_SHORT).show();
                }
            });

            // Add radioButton to LinearLayout
            if (linearLayout != null) {
                linearLayout.addView(radioButton);
            }

        }
    }

    /*backToMain: Onclick method to be used with the back button; Returns the user to the
     * main activity */
    public void backToMain(View v)
    {
        Intent returnResult = new Intent();
        setResult(Activity.RESULT_OK, returnResult); //Set the return result as OK
        finish(); //Return to main activity
    }


    /*onBackPressed: Listener for when the android specific back button is pressed. Returns
    * the user back to the main activity. */
    @Override
    public void onBackPressed()
    {
        Intent returnResult = new Intent();
        setResult(Activity.RESULT_OK, returnResult); //Set the return result as OK
        finish(); //Return to main activity
    }


}