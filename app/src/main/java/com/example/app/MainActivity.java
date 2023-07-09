package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            Toast.makeText(MainActivity.this,"Something",Toast.LENGTH_LONG).show();
        }
    }

    private TextView letterTextView, answerTextView;
    private char[] skyLetters = {'b', 'd', 'f', 'h', 'k', 'l', 't'};
    private char[] grassLetters = {'g', 'j', 'p', 'q', 'y'};
    private char[] rootLetters = {'a', 'c', 'e', 'i', 'm', 'n', 'o', 'r', 's', 'u', 'v', 'w', 'x', 'z'};
    private String answerString = "";
    static int count = 0;
    String ltr;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    String ans = "";
    String ButtonType = "";
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            navigationView = findViewById(R.id.nav_view);
            drawerLayout = findViewById(R.id.drawer);

            toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();


            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.playAgain:
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                            //drawerLayout.closeDrawer(GravityCompat.START);
                            break;

                   /* case R.id.showData :
                        Toast.makeText(getApplicationContext(),"Retur is Clicked",Toast.LENGTH_LONG).show();
                        //drawerLayout.closeDrawer(GravityCompat.START);
                        break;*/

                    }

                    return true;
                }
            });


            MyFirstFragment myFirstFragment = new MyFirstFragment();
            fragmentManager = getSupportFragmentManager();

            letterTextView = findViewById(R.id.letter_text_view);
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
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Buttons wala"+e.getMessage().toString(), Toast.LENGTH_LONG).show();
            System.out.println(e.getMessage().toString());

        }
    }

    private void displayAnswer(String answer, String ButtonType) {
        try {
            DBHandler databaseHelper = new DBHandler(MainActivity.this);

            // Save the answer in the database
            ltr = getRandomLetter();
            bundle.putString("Answer", answer);
            bundle.putString("ButtonType", ButtonType);
            databaseHelper.insertAnswer(answer, ButtonType, ltr);

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
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Display Answer wala"+e.getMessage().toString(), Toast.LENGTH_LONG).show();

        }
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
