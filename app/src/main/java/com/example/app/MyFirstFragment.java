package com.example.app;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MyFirstFragment extends Fragment {
    TextView result;
    String answer;

    public MyFirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_first, container, false);
        result = view.findViewById(R.id.result);

        Bundle bundle = getArguments();
        if (bundle != null) {
            answer = bundle.getString("Answer");

            if (answer.equals("Sky Letter")) {
                result.setText("Awesome! Your answer is correct for Sky Letter");
            } else
                result.setText("Incorrect! The answer is " + answer);
            }


        return view;
    }
}
