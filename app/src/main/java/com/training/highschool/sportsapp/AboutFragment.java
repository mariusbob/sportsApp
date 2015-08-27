package com.training.highschool.sportsapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;

public class AboutFragment extends Fragment {

    private Button mShowDialogButton;
    LoginButton loginButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_about, container, false);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());



        Context context = this.getActivity().getApplicationContext();
        final Dialog dialog = new Dialog(context, R.style.CustomDialogTheme);

        mShowDialogButton = (Button) rootView.findViewById(R.id.btnShowDialog);

        mShowDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.setContentView(R.layout.modal_dialog);
                dialog.show();


            }
        });
        loginButton = (LoginButton) dialog.findViewById(R.id.login_button);


        return rootView;
    }
}
