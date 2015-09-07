package com.training.highschool.sportsapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONObject;

import java.util.Arrays;

public class DialFragment extends DialogFragment implements View.OnClickListener{

    LoginButton loginButton;
    CallbackManager callbackManager;
    ProfilePictureView profilePictureView;
    Communicator comm;
    String id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        FacebookSdk.sdkInitialize(getActivity());
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View rootView = inflater.inflate(R.layout.modal_dialog, container, false);




        loginButton = (LoginButton) rootView.findViewById(R.id.login_button);
        profilePictureView = (ProfilePictureView) rootView.findViewById(R.id.profileImage);
        loginButton.setFragment(this);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                        Log.d("USERU ", jsonObject.toString());
                        Log.d("USERU: ", loginResult.getAccessToken().getUserId());
                        id = loginResult.getAccessToken().getUserId();
                        profilePictureView.setProfileId(loginResult.getAccessToken().getUserId());

                    }
                }).executeAsync();

                comm.respond(id);
               }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        comm = (Communicator) getActivity();

    }

    @Override
    public void onAttach(Activity activity) {
        comm = (Communicator) activity;
    }

    @Override
    public void onClick(View v) {
        comm.respond("button was clicked");
    }
}