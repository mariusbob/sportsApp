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
import android.widget.Toast;

public class AboutFragment extends Fragment {

    private Button mShowDialog;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);

        Context context = this.getActivity().getApplicationContext();
        final Dialog dialog = new Dialog(context, R.style.CustomDialogTheme);

        mShowDialog = (Button) rootView.findViewById(R.id.btnShowDialog);

        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.setContentView(R.layout.modal_dialog);
                dialog.show();
            }
        });

        return rootView;
    }
}
