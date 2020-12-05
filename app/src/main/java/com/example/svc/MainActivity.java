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

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class MainActivity extends AppCompatActivity {
    public static final String EMAIL = "com.example.svc.EMAIL";
    public static final String FULL_NAME = "com.example.svc.FULL_NAME";
    private SVCDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new SVCDB(this);
    }


    public void login(View v){
        String email = ((TextInputEditText) findViewById(R.id.emailTF)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordTF)).getText().toString();
        //input validation
        //-----------------------------------------------
        if(email.isEmpty() || password.isEmpty()){
            new AlertDialog.Builder(this)
                    .setTitle("Missing Fields")
                    .setMessage("Email and/or password is missing")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        //-----------------------------------------------
        UserDTO user;
        if((user = UserDAO.login(new UserDTO.Builder().
                                            setEmail(email).
                                            setPassword(password,true).
                                            build(),db)) != null){
            Intent intent = new Intent(this,Home.class);
            intent.putExtra(EMAIL,user.getEmail());
            intent.putExtra(FULL_NAME,user.getFull_name());
            startActivity(intent);
        }else{
            new AlertDialog.Builder(this)
                    .setTitle("Wrong Credentials, Sorry :(")
                    .setMessage("Email and/or password is invalid")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
    //takes the user to the registration page
    public void link(View v){
        Intent intent = new Intent(this,SignUp.class);
        startActivity(intent);
    }
    public void addVC(View v){
        Intent intent = new Intent(this,AddVC.class);
        startActivity(intent);
    }

}