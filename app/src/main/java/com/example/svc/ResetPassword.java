package com.example.svc;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import Utils.Constants;
import models.SVCDB;
import models.UserDAO;
import models.UserDTO;
import security.InputValidators;

public class ResetPassword extends AppCompatActivity {
    private UserDTO user;
    private SVCDB db;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        db = new SVCDB(this);
        Intent intent = getIntent();

        user = UserDTO.stringToUser(intent.getStringExtra(Constants.USER));

    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void editPassword(View v){
        String password1 = ((EditText) findViewById(R.id.passwordeditTR)).getText().toString();
        String password2 = ((EditText) findViewById(R.id.passwordconfirmTR)).getText().toString();

        //validate input
        //---------------------------------------------------------------------

        if(password1.isEmpty() || password2.isEmpty()){
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

        if(UserDAO.resetPassword(new UserDTO.Builder().
                setEmail(user.getEmail()).
                setFull_name(user.getFull_name()).
                setPassword(password1,true)
                .build(),db)) {
            Intent intent = new Intent(this, Home.class);
            intent.putExtra(Constants.USER,user.toString());
            startActivity(intent);
        }
    }
}