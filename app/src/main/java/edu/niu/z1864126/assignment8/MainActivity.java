package edu.niu.z1864126.assignment8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity
{
    ProjectDataBaseHelper myDb;
    ArrayList<homeworkItem> arrayList;
    RecyclerView recyclerView;

    private int requestcode = 0; //Request code to provide when starting new activity

    /*onCreate: Reloads app activity to prior saved state*/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new ProjectDataBaseHelper(this);

        //myDb.insertItem("Math hw"); //USED FOR TESTING ONLY

        displayHW();
    }



    public void displayHW() {
        arrayList = new ArrayList<>(myDb.retrieveItems());
        ListIterator<homeworkItem> listItr = arrayList.listIterator();
        int count = 1;
        LinearLayout linearLayout = findViewById(R.id.HomeworkView);

        while(listItr.hasNext())
        {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(listItr.next().getDescOfTask());

            checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    String msg = "You have " + (isChecked ? "checked" : "unchecked") + " this Check it Checkbox.";
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_res, menu);
        return true;
    }

    /*onOptionsItemSelected: This method takes in the menu option the user selected and brings
    * them to either the "Add item to list" activity or the "Delete item from list" activity;
    * Alternatively, if the user picks to start over, all list options are deleted*/
    @Override
    public boolean onOptionsItemSelected(MenuItem choice)
    {
        Intent addDeleteUpdate; //Declaration of intent object to hold activity to transition to

        switch (choice.getItemId()) //Switch that Selects the activity to launch or action to take
        {
            case R.id.Add: //User's menu choice is to add to the list; Starts the add activity
                addDeleteUpdate = new Intent(this, add_screen_activity.class);
                startActivityForResult(addDeleteUpdate, requestcode);
                return true;
            case R.id.Delete: //User's menu choice is to delete from the list; Starts the delete activity
                addDeleteUpdate = new Intent(this, delete_screen.class);
                startActivityForResult(addDeleteUpdate, requestcode);
                return true;
            case R.id.Start_Over: //User's menu choice is to start over and delete all list items
                return true;
            case R.id.Update_Item: //User's menu choice is to update an item in the list
                addDeleteUpdate = new Intent(this, update_item.class);
                startActivityForResult(addDeleteUpdate, requestcode);
                return true;
            default: //Default case
                return super.onOptionsItemSelected(choice);
        }
    }
}