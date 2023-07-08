package com.example.app;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MyFirstFragment extends Fragment {
    TextView result;
    String answer;
    String ButtonType;
    String blankString;

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
            ButtonType = bundle.getString("ButtonType");
            blankString=bundle.getString("empty");

            if (answer != null) {
                if (answer.equals(ButtonType)) {
                    result.setText("Awesome! Your answer is correct for "+answer);
                }
                 else {
                    result.setText("Incorrect! The answer is " + answer);
                }
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    result.setText("");
                }
            }, 3000);

  }


        return view;
    }
}
