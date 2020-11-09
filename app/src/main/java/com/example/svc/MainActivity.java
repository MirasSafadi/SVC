package com.example.svc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import models.User;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_DATA = "com.example.svc.EXTRA_DATA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View v){
        String email = ((TextInputEditText) findViewById(R.id.emailTF)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordTF)).getText().toString();
        if(User.login(email,password,getApplicationContext())){
            Intent intent = new Intent(this,Home.class);
            intent.putExtra(EXTRA_DATA,"Welcome to SVC!");
            startActivity(intent);
        }else{
            new AlertDialog.Builder(this)
                    .setTitle("Wrong Credentials, Sorry :(")
                    .setMessage("Email or password is incorrect!")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
    }
}