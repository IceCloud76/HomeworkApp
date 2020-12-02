/******************************************************************************
 *                                                                           *
 *    Class Name: add_screen_activity.java                                   *
 *                                                                           *
 *           Purpose: Allows the user to enter new items into the list.      *
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

    ProjectDataBaseHelper myDb;
    ArrayList<homeworkItem> arrayList;

    /*onCreate: Reloads app activity to prior saved state*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_screen_activity);
        myDb = new ProjectDataBaseHelper(this);

        displayHW();
    }

    public void displayHW() {
        LinearLayout linearLayout = findViewById(R.id.listHolder);
        ((LinearLayout) linearLayout).removeAllViews();

        arrayList = new ArrayList<>(myDb.retrieveItems());
        ListIterator<homeworkItem> listItr = arrayList.listIterator();

        while(listItr.hasNext())
        {
            TextView textView = new TextView(this);
            textView.setText(listItr.next().getDescOfTask());

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
        setResult(Activity.RESULT_OK, returnResult);
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
            displayHW();
            Toast.makeText(getApplicationContext(), "Item added successfully.",Toast.LENGTH_SHORT).show();
        }
    }
}