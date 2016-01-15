package com.example.cpola.evapp;
import android.app.Application;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
// Add this to the header of your file:
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class Facebook extends android.support.v4.app.Fragment {

    public static Profile profile_global;
    private TextView userDetails2;

    private CallbackManager callbackManager;
    private AccessTokenTracker mtokenTracker;
    private ProfileTracker mProfileTracler;


    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accesToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            setProfile_global(profile);
            displayWelcomeMessage(profile);
            startActivity(new Intent(getActivity(), Drawer_welcome.class));




        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        mtokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            }
        };


        mProfileTracler = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                displayWelcomeMessage(newProfile);

            }
        };
        mtokenTracker.startTracking();
        mProfileTracler.startTracking();

    }

    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_facebook,container,false);
    }

    public  void displayWelcomeMessage(Profile profile){
        if (profile != null){
            userDetails2.setText("Welcome " + profile.getName());



        }


    }


    public static boolean isFacebookLoggedIn(){
        return AccessToken.getCurrentAccessToken() != null;
    }

    public void setProfile_global(Profile profile_global) {
        this.profile_global = profile_global;
    }

    public static String getProfile_global() {
        return profile_global.getName();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginButton loginButton = (LoginButton)view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, callback);
        userDetails2= (TextView) view.findViewById(R.id.userDetails2);

    }

    @Override
    public void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        displayWelcomeMessage(profile);
    }

    @Override
    public void onStop() {
        super.onStop();
        mProfileTracler.stopTracking();
        mtokenTracker.stopTracking();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
