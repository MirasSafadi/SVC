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
                    .setMessage("Please recheck the passwords and make sure they match")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        boolean isValid = true;
        if(!InputValidators.validate(InputValidators.EMAIL,email))
            isValid = false;
        if(!InputValidators.validate(InputValidators.NAME,full_name))
            isValid = false;
        if(!InputValidators.validate(InputValidators.PASSWORD,password1))
            isValid = false;

        if(!isValid){
            new AlertDialog.Builder(this)
                    .setTitle("Invalid input")
                    .setMessage("One or more of the fields is invalid")
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