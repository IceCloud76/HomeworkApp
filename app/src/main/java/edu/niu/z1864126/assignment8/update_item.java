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

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ListIterator;

public class update_item extends AppCompatActivity {
    private ProjectDataBaseHelper myDb; //create a database helper
    private ArrayList<homeworkItem> arrayList; //create a arrayList of homeworkItems

    /*onCreate: Reloads app activity to prior saved state*/
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);
        myDb = new ProjectDataBaseHelper(this); //defines myDB
        displayHW(); //refreshes the screen
    }

    /*Displays the homework in radios that the user sees.
    If the any action is done by the user to the db, we call this to update the display*/
    public void displayHW(){

        LinearLayout linearLayout = findViewById(R.id.List); //find our linearlayout to deal with
        ((LinearLayout) linearLayout).removeAllViews(); //clears all children, as this acts kinda like a "screen refresh"

        arrayList = new ArrayList<>(myDb.retrieveItems()); //defines arrayList to temporarily hold a copy of tableone
        ListIterator<homeworkItem> listItr = arrayList.listIterator(); //defines a homeworkItem iterator to be used to loop through arrayList
        homeworkItem currentItem;

        while(listItr.hasNext()){ //while there is still items in the array

            RadioButton radioButton = new RadioButton(this); //create new radio to be displayed later

            currentItem = listItr.next(); //set currentItem so we always have a reference to our current item in the db
            radioButton.setId(currentItem.getID());
            radioButton.setText(currentItem.getDescOfTask());

            radioButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { //all this is to set up the radio properties that we normally would have done in XML
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { //a listener for when the check mark is changed

                    //This marks the start of our code which creates a popup witth two buttons and an edittext for the user to enter a new description
                    final EditText taskEditText = new EditText(update_item.this); //create an edit text

                    taskEditText.setText(radioButton.getText().toString()); //set the text to what was the old description of the item

                    AlertDialog dialog = new AlertDialog.Builder(update_item.this) //create the alert dialog
                            .setTitle("Update a task") //title
                            .setMessage("What should the new description be?") //message
                            .setView(taskEditText)
                            .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) { //this is what happens when the user clicks "update"
                                    String task = String.valueOf(taskEditText.getText()); //gets the string from the editText
                                    myDb.updateItem(radioButton.getId(), task); //update our db
                                    radioButton.setText(task); //set the new text to out current screen. no need to refresh everything if we can easily, and more quickly do it here
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .create();
                    dialog.show(); //show the dialog to the user
                    radioButton.setChecked(false); //set the radio button to false now that we've dealt with it
                }
            });

            // Add Checkbox to LinearLayout
            if (linearLayout != null) {
                linearLayout.addView(radioButton);
            }

        }

    }

    /*backToMain: Onclick method to be used with the back button; Returns the user to the
     * main activity */
    public void backToMain(View v){

        Intent returnResult = new Intent();
        setResult(Activity.RESULT_OK, returnResult);
        finish();
    }

    @Override
    public void onBackPressed(){ //is called if the back button on the nav bar is pressed. Same as backToMain()
        Intent returnResult = new Intent();
        setResult(Activity.RESULT_OK, returnResult);
        finish();
    }
}