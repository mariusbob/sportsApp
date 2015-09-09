package com.training.highschool.sportsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by ovi on 07/09/15.
 */


public class SignInFragment extends Fragment {

    private View myFragmentView;
    private Button signin;
    public static final String TAG = SignInFragment.class.getSimpleName();

    public static final String prefs = "preferences";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        myFragmentView = inflater.inflate(R.layout.fragment_signin, container, false);

        final EditText name = (EditText) myFragmentView.findViewById(R.id.editText2);
        final EditText email = (EditText) myFragmentView.findViewById(R.id.editText3);
        final EditText birthday = (EditText) myFragmentView.findViewById(R.id.editText4);
        final EditText pin = (EditText) myFragmentView.findViewById(R.id.editText);
//
        signin = (Button) myFragmentView.findViewById(R.id.signIn);
        //SharedPreferences preferences = this.getActivity().getSharedPreferences(prefs, Context.MODE_PRIVATE);
        //Editor edit = preferences.edit();
        signin.setOnClickListener(new View.OnClickListener(){

           @Override
            public void onClick(View v){

               String message_name = name.getText().toString();
               String message_email = email.getText().toString();
               String message_birthday = birthday.getText().toString();
               int message_pin = pin.getText().toString().hashCode();


               //SharedPreferences preferences = getActivity().getSharedPreferences(prefs, Context.MODE_PRIVATE);
               //Editor edit = preferences.edit();
              // edit.putString("userName", message_name);
               //edit.commit();

               if(message_name == null || message_email == null || message_birthday == null || message_pin==0)
                   Toast.makeText(getActivity().getApplicationContext(), "Please complete all fields before signing in.", Toast.LENGTH_LONG).show();
               else
               {
                   SharedPreferences preferences = getActivity().getSharedPreferences(prefs, Context.MODE_PRIVATE);
                   Editor edit = preferences.edit();
                   edit.putString("userName", message_name);
                   edit.putString("userEmail", message_email);
                   edit.putString("userBirthday", message_birthday);
                   edit.commit();
                   Toast.makeText(getActivity().getApplicationContext(), "Sign In succesful!", Toast.LENGTH_SHORT).show();
               }
               //Intent intent = new Intent(getActivity().getApplicationContext(), AboutFragment.class);
               //startActivity(intent);
            }
        });
        return myFragmentView;
    }
}