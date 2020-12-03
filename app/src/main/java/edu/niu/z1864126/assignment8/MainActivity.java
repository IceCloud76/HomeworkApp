/**********************************************************
 *
 *  CSCI 322            Assignment 6               Fall 2020
 *
 *                  mainActivity.java
 *
 *      Developers: Eric Kirchman and Brendon Brewer
 *      Due Date: 12/4/20
 *
 *      Purpose: Create Homework App ustilizing a SQLite database
 *      App should be able to add, remove, and modify entries, and also be
 *      able to mark tasks as complete
 *
 * *********************************************************/
package edu.niu.z1864126.assignment8;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    private ProjectDataBaseHelper myDb;
    private ArrayList<homeworkItem> arrayList;


    /*onCreate: Reloads app activity to prior saved state*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new ProjectDataBaseHelper(this);

        //myDb.insertItem("Math hw"); //USED FOR TESTING ONLY

        displayHW();
    }

    /*Displays the homework in check boxes that the user sees.
    If the any action is done by the user to the db, we call this to update the display*/
    public void displayHW() {
        LinearLayout linearLayout = findViewById(R.id.HomeworkView);
        ((LinearLayout) linearLayout).removeAllViews(); //removes all previous views/children to refresh the screen from scratch

        arrayList = new ArrayList<>(myDb.retrieveItems()); //create an arrayList to loop through
        ListIterator<homeworkItem> listItr = arrayList.listIterator(); //create an iterator to use to loop through the table
        homeworkItem currentItem;

        while(listItr.hasNext()){ //while we have another item in the db

            CheckBox checkBox = new CheckBox(this); //create a new checkbox to be assigned new properties

            currentItem = listItr.next(); //set currentItem so we always have a reference to our current item in the db
            checkBox.setId(currentItem.getID());
            checkBox.setText(currentItem.getDescOfTask());
            if(currentItem.isCompleted()){ //if the current item in the database is marked as complete, we also check the box in the app
                checkBox.setChecked(true);
            }else{
                checkBox.setChecked(false);
            }

            checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { //all this is to set up the checkbox properties that we normally would have done in XML
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { //a listener for when the check mark is changed
                    String msg = "Marked as " + (isChecked ? "complete" : "uncomplete");
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

                    if(checkBox.isChecked()) { //if the user has checked the box, we mark the item as complete
                        myDb.updateItem(checkBox.getId(), true);
                    }else{
                        myDb.updateItem(checkBox.getId(), false);
                    }
                }
            });

            // Add Checkbox to LinearLayout
            if (linearLayout != null) {
                linearLayout.addView(checkBox);
            }

        }
    }

    /*onCreateOptionsMenu: Creates menu in main activity from menu_res file*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_res, menu);
        return true;
    }

    /*onOptionsItemSelected: This method takes in the menu option the user selected and brings
    * them to either the "Add item to list" activity or the "Delete item from list" activity;
    * Alternatively, if the user picks to start over, all list options are deleted*/
    @Override
    public boolean onOptionsItemSelected(MenuItem choice){
        int requestcode = 0; //Request code to provide when starting new activity

        Intent addDeleteUpdate; //Declaration of intent object to hold activity to transition to

        switch (choice.getItemId()){ //Switch that Selects the activity to launch or action to take

            case R.id.Add: //User's menu choice is to add to the list; Starts the add activity
                addDeleteUpdate = new Intent(this, add_screen_activity.class);
                startActivityForResult(addDeleteUpdate, requestcode);
                return true;
            case R.id.Delete: //User's menu choice is to delete from the list; Starts the delete activity
                addDeleteUpdate = new Intent(this, delete_screen.class);
                startActivityForResult(addDeleteUpdate, requestcode);
                return true;
            case R.id.Start_Over: //User's menu choice is to start over and delete all list items
                myDb.deleteAll();
                displayHW();
                return true;
            case R.id.Update_Item: //User's menu choice is to update an item in the list
                addDeleteUpdate = new Intent(this, update_item.class);
                Log.d("mytag", " we passed intent");
                startActivityForResult(addDeleteUpdate, requestcode);
                return true;
            default: //Default case
                return super.onOptionsItemSelected(choice);
        }

    }
    /* returns the user back to the main screen gracefully, by updating the screen when the user returns*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnResult){

        super.onActivityResult(requestCode, resultCode, returnResult);

        if(resultCode == Activity.RESULT_OK){ //checks if the activity returned OK

            displayHW();
        }
    }
}