package com.example.svc;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import models.User;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
    public void signUp(View v){
        String email = ((TextInputEditText) findViewById(R.id.emailTR)).getText().toString();
        String cPassword = ((EditText) findViewById(R.id.cPasswordTR)).getText().toString();
        if(User.signUp(email,cPassword,getApplicationContext())){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }else{
            new AlertDialog.Builder(this)
                    .setTitle("Wrong Credentials, Sorry :(")
                    .setMessage("Email is already exist or password is not ma!")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
    }
}