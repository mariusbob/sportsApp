package com.training.highschool.sportsapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by ovi on 03/09/15.
 */
public class AboutFragment extends Fragment {

    public static final String prefs = "preferences";
    private View AboutView;
    private Button logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getActivity().setContentView(R.layout.fragment_about)
        // Inflate the layout for this fragment
        AboutView = inflater.inflate(R.layout.fragment_about, container, false);

        final TextView text = (TextView) AboutView.findViewById(R.id.textView);
        final TextView text1 = (TextView) AboutView.findViewById(R.id.textView2);
        final TextView text2 = (TextView) AboutView.findViewById(R.id.textView3);
        SharedPreferences preferences = this.getActivity().getSharedPreferences(prefs, Context.MODE_PRIVATE);


        String username = preferences.getString("userName", "You are not logged in.");
        text.setText("  Name: "+username);

        String useremail = preferences.getString("userEmail"," ");
        text1.setText("  E-Mail: "+useremail);

        String birthday = preferences.getString("userBirthday"," ");
        text2.setText("  Birthday: "+birthday);

        logout = (Button) AboutView.findViewById(R.id.logOut);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("You are not logged in.");
                text1.setText("");
                text2.setText("");
            }
        });

        return AboutView;
    }

}
