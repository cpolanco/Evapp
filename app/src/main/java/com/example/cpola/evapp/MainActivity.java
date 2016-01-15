package com.example.cpola.evapp;

import android.app.*;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v4.app.*;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.AccessToken;

import static com.example.cpola.evapp.Facebook.getProfile_global;
import static com.example.cpola.evapp.Facebook.isFacebookLoggedIn;
import static com.example.cpola.evapp.Facebook.profile_global;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {

    Button bLogReg, bStart;
    EditText etName, etAge, etUsername,etPassword;
    UserLocalStore userLocalStore;
    boolean loggedIn=false;
    private TextView userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etAge = (EditText)findViewById(R.id.etAge);
        etName = (EditText)findViewById(R.id.etName);
        etUsername = (EditText)findViewById(R.id.etUsername);
        bLogReg = (Button) findViewById(R.id.bLogReg);
        bStart = (Button) findViewById(R.id.bStart);
        userDetails = (TextView) findViewById(R.id.userDetails);

        bLogReg.setOnClickListener(this);
        bStart.setOnClickListener(this);


        userLocalStore = new UserLocalStore(this);
        try{
            loggedIn = isFacebookLoggedIn();
        }catch (Exception e){
            loggedIn=false;
        }



        if( loggedIn == true){
            bLogReg.setVisibility(View.GONE);

            //profile_global.getProfilePictureUri(200, 200);

        }
        else {

        }

    }



    @Override
    protected void onStart() {
        super.onStart();
        try{
            loggedIn = isFacebookLoggedIn();
        }catch (Exception e){
            loggedIn=false;
        }



        if( loggedIn == true){
            bLogReg.setVisibility(View.GONE);
            //String name =profile_global.getFirstName();
            userDetails.setText("Welcome ");

        }
        else {

        }

    }

    public boolean isClass(String className) {
        try  {
            Class.forName(className);
            return true;
        }  catch (final ClassNotFoundException e) {
            return false;
        }
    }

    private boolean authenticate(){
        return userLocalStore.getUserLoggedIn();

    }

    private void displayUserDetails(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bLogReg:

                startActivity(new Intent(this, Login.class));
                // toggleFragment(INDEX_SIMPLE_LOGIN);
                break;
            case R.id.bStart:
                startActivity(new Intent(this, Drawer_welcome.class));
                // toggleFragment(INDEX_SIMPLE_LOGIN);
                // Intent intent = new Intent(getApplicationContext(), Facebook.class);
                //startActivity(intent);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}