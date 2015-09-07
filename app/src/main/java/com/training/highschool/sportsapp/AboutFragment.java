package com.training.highschool.sportsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AboutFragment extends Fragment{

    private Button mShowDialogButton;
    TextView tvTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_about, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance){
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        mShowDialogButton = (Button) view.findViewById(R.id.btnShowDialog);

        mShowDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialFragment df = new DialFragment();
                android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
                df.show(fm, "");
            }
        });
    }

}
