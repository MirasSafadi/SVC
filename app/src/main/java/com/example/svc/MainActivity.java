package com.example.svc;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import models.SVCDB;
import models.UserDAO;
import models.UserDTO;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_DATA = "com.example.svc.EXTRA_DATA";
    private SVCDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "hello ", Toast.LENGTH_LONG);
        db = new SVCDB(this);
//        db.removeUser("ranihassan@gmail.com");
    }


    public void login(View v){
        String email = ((TextInputEditText) findViewById(R.id.emailTF)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordTF)).getText().toString();
        if(UserDAO.login(new UserDTO(email,password,null,true),db)){
            Intent intent = new Intent(this,Home.class);
            intent.putExtra(EXTRA_DATA,"Welcome to SVC!");
            startActivity(intent);
        }else{
            new AlertDialog.Builder(this)
                    .setTitle("Wrong Credentials, Sorry :(")
                    .setMessage("Email or password are invalid")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
    }
    public void link(View v){
        Intent intent = new Intent(this,SignUp.class);
        startActivity(intent);
    }

}