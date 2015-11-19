package com.example.carlos.carlostests;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    Button bLogReg;
    EditText etName, etAge, etUsername,etPassword;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAge = (EditText)findViewById(R.id.etAge);
        etName = (EditText)findViewById(R.id.etName);
        etUsername = (EditText)findViewById(R.id.etUsername);
        bLogReg = (Button) findViewById(R.id.bLogReg);
        bLogReg.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (authenticate() == true) {
            displayUserDetails();

            }
        }


    private boolean authenticate(){
        return userLocalStore.getUserLoggedIn();

    }

    private void displayUserDetails(){
        User user = userLocalStore.getLoggedInUser();
        etUsername.setText(user.username);
        etName.setText(user.name);
        etAge.setText(user.age + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bLogReg:

                startActivity(new Intent(this, Login.class));
                break;
        }
    }
}
