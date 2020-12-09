package com.atoproduction.ls11facebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private CallbackManager callbackManager;
    private LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.login_button);


        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            Log.d("thinhvh", "isLoggedIn: true");
            Profile profile = Profile.getCurrentProfile();
            String userID = accessToken.getUserId();
            Log.d("thinhvh", "getMiddleName: " + profile.getMiddleName());
            Log.d("thinhvh", "getFirstName: " + profile.getFirstName());
            Log.d("thinhvh", "getName: " + profile.getName());
            Log.d("thinhvh", "userID: " + userID);


        } else {
            Log.d("thinhvh", "isLoggedIn: false");
            callbackManager = CallbackManager.Factory.create();
            loginButton.setReadPermissions("email");
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    // App code
                    Log.d("thinhvh", "getUserId: " + loginResult.getAccessToken().getUserId());
                    Profile profile = Profile.getCurrentProfile();
                    String userID = loginResult.getAccessToken().getUserId();
                    Log.d("thinhvh", "getMiddleName: " + profile.getMiddleName());
                    Log.d("thinhvh", "getFirstName: " + profile.getFirstName());
                    Log.d("thinhvh", "getName: " + profile.getName());
                }

                @Override
                public void onCancel() {
                    // App code
                    Log.d("thinhvh", "onCancel: ");
                }

                @Override
                public void onError(FacebookException exception) {
                    // App code
                    Log.d("thinhvh", "onError: ");
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

//    private void nhuShit() {
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    getPackageName(),
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        }
//        catch (PackageManager.NameNotFoundException e) {
//
//        }
//        catch (NoSuchAlgorithmException e) {
//
//        }
//    }
}