package com.example.cpola.evapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.facebook.login.LoginManager;

public class Drawer_welcome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int INDEX_SIMPLE_LOGIN = 0;
    public static final int INDEX_CUSTOM_LOGIN = 1;

    private static final String STATE_SELECTED_FRAGMENT_INDEX = "selected_fragment_index";
    public static final String FRAGMENT_TAG = "fragment_tag";
    private android.support.v4.app.FragmentManager mFragmentManager;

    private TextView textNameDrawer;
    TextView welcome;
    //private String[] myStringArray = new String[]{"Welcome", Facebook.getProfile_global()};
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static FragmentManager fragmentManager;

    private final LatLng LOCATION_BURNABY = new LatLng(49.27645, -122.917587);
    private final LatLng LOCATION_SURRREY = new LatLng(49.187500, -122.849000);

    private GoogleMap map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String name="Prakash Gajera";

        //textNameDrawer=(TextView)findViewById(R.id.textNameDrawer);
        // textNameDrawer.setText(name);

        //toggleFragment(INDEX_SIMPLE_LOGIN);

        map  = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        map.addMarker(new MarkerOptions().position(LOCATION_SURRREY).title("Find me here!"));




        //mFragmentManager = getSupportFragmentManager();
        // toggleFragment(INDEX_SIMPLE_LOGIN);

        //fragmentManager = getSupportFragmentManager();
        // toggleFragment(INDEX_SIMPLE_LOGIN);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_welcome, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //textNameDrawer.setText(Facebook.getProfile_global());
        int id = item.getItemId();

         if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(this, SettingsActivity.class));

        } else if (id == R.id.nav_manage) {
            LoginManager.getInstance().logOut();
            startActivity(new Intent(this, MainActivity.class));
        } else if (id == R.id.nav_share) {


        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public class JSONTask extends AsyncTask<String,String, String>{


        @Override
        // se cambia String a List<MovieModel>
        protected String doInBackground(String... params) {

            return "carlos";
        }


        @Override
        //se cambia String result a List<MovieModel> result
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //textNameDrawer.setText("holaaa");
        }
    }

    private void toggleFragment(int index) {
        android.support.v4.app.Fragment fragment = mFragmentManager.findFragmentByTag(FRAGMENT_TAG);
        android.support.v4.app.FragmentTransaction transaction = mFragmentManager.beginTransaction();
        switch (index){
            case INDEX_SIMPLE_LOGIN:

                transaction.replace(android.R.id.content, new Facebook(), FRAGMENT_TAG);

                break;

        }
        transaction.commit();
    }

}
