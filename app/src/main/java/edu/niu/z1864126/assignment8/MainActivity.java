package edu.niu.z1864126.assignment8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
{
    private int requestcode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_res, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem choice)
    {
        switch (choice.getItemId())
        {
            case R.id.Add:
                Intent addActivitySwitch = new Intent(this, add_screen_activity.class);
                startActivityForResult(addActivitySwitch, requestcode);
            case R.id.Delete:
            default:
                return super.onOptionsItemSelected(choice);
        }
    }
}