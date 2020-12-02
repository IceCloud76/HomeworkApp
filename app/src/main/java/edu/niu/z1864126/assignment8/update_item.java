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
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ListIterator;

public class update_item extends AppCompatActivity
{
    ProjectDataBaseHelper myDb;
    ArrayList<homeworkItem> arrayList;

    /*onCreate: Reloads app activity to prior saved state*/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d("mytag", " we made it to onCreate");
        super.onCreate(savedInstanceState);
        Log.d("mytag", " we made it to onCreate2");
        setContentView(R.layout.activity_update_item);
        Log.d("mytag", " we made it to onCreate3");
        myDb = new ProjectDataBaseHelper(this);
        Log.d("mytag", " we made it to onCreate4");
        displayHW();
        Log.d("mytag", " we made it to onCreateX");
    }

    public void displayHW() {

        LinearLayout linearLayout = findViewById(R.id.List);
        ((LinearLayout) linearLayout).removeAllViews();


        arrayList = new ArrayList<>(myDb.retrieveItems());
        ListIterator<homeworkItem> listItr = arrayList.listIterator();
        homeworkItem currentItem;

        Log.d("mytag", " before hasNext()");
        while(listItr.hasNext())
        {

            RadioButton radioButton = new RadioButton(this);
            Log.d("mytag", " After create radiobutton");

            currentItem = listItr.next();
            radioButton.setId(currentItem.getID());
            radioButton.setText(currentItem.getDescOfTask());

            radioButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    myDb.deleteItem(radioButton.getId());
                    linearLayout.removeView(radioButton);
                    String msg = "Item Deleted.";
                    Toast.makeText(update_item.this, msg, Toast.LENGTH_SHORT).show();
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
    public void backToMain(View v)
    {
        Intent returnResult = new Intent();
        setResult(Activity.RESULT_OK, returnResult);
        finish();
    }

    @Override
    public void onBackPressed()
    {
        Intent returnResult = new Intent();
        setResult(Activity.RESULT_OK, returnResult);
        finish();
    }
    
}