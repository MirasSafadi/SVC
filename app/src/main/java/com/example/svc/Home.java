package com.example.svc;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class Home extends AppCompatActivity {
    String email;
    String full_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        email = intent.getStringExtra(MainActivity.EMAIL);
        full_name = intent.getStringExtra(MainActivity.FULL_NAME);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.welcomeTV);
        textView.setText("Welcome, " + full_name);

    }
}