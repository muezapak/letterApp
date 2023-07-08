package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView letterTextView, answerTextView;
    private char[] skyLetters = {'b', 'd', 'f', 'h', 'k', 'l', 't'};
    private char[] grassLetters = {'g', 'j', 'p', 'q', 'y'};
    private char[] rootLetters = {'a', 'c', 'e', 'i', 'm', 'n', 'o', 'r', 's', 'u', 'v', 'w', 'x', 'z'};
    private String answerString = "";
    static int count=0;
    String ltr;


    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    String ans="";
    String ButtonType="";
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyFirstFragment myFirstFragment = new MyFirstFragment();
        fragmentManager = getSupportFragmentManager();

        letterTextView = findViewById(R.id.letter_text_view);
        ltr=letterTextView.getText().toString();
        letterTextView.setText(getRandomLetter());


        Button skyButton = findViewById(R.id.sky_button);
        skyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < 5) {
                    count++;
                    ButtonType = "Sky Letter";
                    ans = answerString;
                    displayAnswer(ans, ButtonType);
                } else {
                    Toast.makeText(MainActivity.this, "Check Your result", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button grassButton = findViewById(R.id.grass_button);
        grassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < 5) {
                    count++;
                    ButtonType = "Grass Letter";
                    ans = answerString;
                    displayAnswer(ans, ButtonType);
                } else {
                    Toast.makeText(MainActivity.this, "Check Your result", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button rootButton = findViewById(R.id.root_button);
        rootButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < 5) {
                    count++;
                    ButtonType = "Root Letter";
                    ans = answerString;
                    displayAnswer(ans, ButtonType);
                } else {
                    Toast.makeText(MainActivity.this, "Check Your result", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void displayAnswer(String answer,String ButtonType) {
        DBHandler databaseHelper = new DBHandler(MainActivity.this);

        // Save the answer in the database

        bundle.putString("Answer", answer);
        bundle.putString("ButtonType", ButtonType);
        databaseHelper.insertAnswer( answer,ButtonType,  ltr);

        MyFirstFragment myFirstFragment = new MyFirstFragment();

        myFirstFragment.setArguments(bundle);

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, myFirstFragment);
        fragmentTransaction.commit();

        // Wait for 5 seconds and create a new question
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                letterTextView.setText(getRandomLetter());




            }
        }, 3000); // 5000 milliseconds = 5 seconds
    }

    private String getRandomLetter() {
        Random random = new Random();
        int category = random.nextInt(3);
        char letter;
        switch (category) {
            case 0:
                letter = skyLetters[random.nextInt(skyLetters.length)];
                answerString = "Sky Letter";
                break;
            case 1:
                letter = grassLetters[random.nextInt(grassLetters.length)];
                answerString = "Grass Letter";
                break;
            default:
                letter = rootLetters[random.nextInt(rootLetters.length)];
                answerString = "Root Letter";
                break;
        }
        return String.valueOf(letter);
    }
}
