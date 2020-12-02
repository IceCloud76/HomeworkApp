/******************************************************************************
 *                                                                           *
 *    Class Name: delete_screen.java                                         *
 *                                                                           *
 *           Purpose: Allows the user to delete items from the list.         *
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
    ProjectDataBaseHelper myDb;
    ArrayList<homeworkItem> arrayList;

    /*onCreate: Reloads app activity to prior saved state*/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_screen);
        myDb = new ProjectDataBaseHelper(this);

        displayHW();
    }

    public void displayHW() {
        LinearLayout linearLayout = findViewById(R.id.List);
        ((LinearLayout) linearLayout).removeAllViews();

        arrayList = new ArrayList<>(myDb.retrieveItems());
        ListIterator<homeworkItem> listItr = arrayList.listIterator();
        homeworkItem currentItem;

        while(listItr.hasNext())
        {
            RadioButton radioButton = new RadioButton(this);
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