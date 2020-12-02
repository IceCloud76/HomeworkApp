/******************************************************************************
 *                                                                           *
 *    Class Name: SplashScreen.java                                          *
 *                                                                           *
 *           Purpose: Displays the app's splash screen                       *
 *           Created By Brendon Brewer and Eric Kirchman                     *
 *                                                                           *
 *****************************************************************************/

package edu.niu.z1864126.assignment8;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {


    // Splash screen timer
        private int SPLASH_TIME_OUT = 3000;

        /*onCreate: Reloads app activity to prior saved state*/
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            try
            {
                this.getSupportActionBar().hide();
            }
            catch (NullPointerException e){}


            setContentView(R.layout.activity_splash);

            new Handler().postDelayed(new Runnable() {

                /*
                 * Showing splash screen with a timer. This will be useful when you
                 * want to show case your app logo / company
                 */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);

                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }

    }

