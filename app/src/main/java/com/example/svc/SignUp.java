package com.example.svc;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import models.SVCDB;
import models.UserDAO;
import models.UserDTO;
import security.InputValidators;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class SignUp extends AppCompatActivity {
    private SVCDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        db = new SVCDB(this);
    }

    public void signUp(View v){
        String email = ((TextInputEditText) findViewById(R.id.emailTR)).getText().toString();
        String full_name = ((TextInputEditText) findViewById(R.id.fullNameTR)).getText().toString();
        String password1 = ((EditText) findViewById(R.id.passwordTR)).getText().toString();
        String password2 = ((EditText) findViewById(R.id.cPasswordTR)).getText().toString();

        //validate input
        //---------------------------------------------------------------------

        if(email.isEmpty() || full_name.isEmpty() || password1.isEmpty() || password2.isEmpty()){
            new AlertDialog.Builder(this)
                    .setTitle("Invalid input")
                    .setMessage("One or more of the fields is missing")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }

        if(!password1.equals(password2)) {
            new AlertDialog.Builder(this)
                    .setTitle("Passwords do not match")
                    .setMessage("Please re-check the password fields.")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        if(!InputValidators.validate(InputValidators.EMAIL,email)) {
            new AlertDialog.Builder(this)
                    .setTitle("Invalid input")
                    .setMessage("Email field is invalid")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        if(!InputValidators.validate(InputValidators.NAME,full_name)) {
            new AlertDialog.Builder(this)
                    .setTitle("Invalid input")
                    .setMessage("Name must contain only english characters")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        if(!InputValidators.validate(InputValidators.PASSWORD,password1))
           {
            new AlertDialog.Builder(this)
                    .setTitle("Invalid input")
                    .setMessage("Password must be at least 8 characters long and contain at least 1 digit, 1 small case letter, and 1 upper case letter.")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        //----------------------------------------------------------------------


        if(UserDAO.signUp(new UserDTO.Builder().
                                        setEmail(email).
                                        setFull_name(full_name).
                                        setPassword(password1,true)
                                        .build(),db)){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }else{
            new AlertDialog.Builder(this)
                    .setTitle("You already have an account!")
                    .setMessage("Please go to the login page and login!")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
    }
}