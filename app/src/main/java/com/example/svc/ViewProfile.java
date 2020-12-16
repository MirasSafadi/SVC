package com.example.svc;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import Utils.Constants;
import models.SVCDB;
import models.UserDTO;

public class ViewProfile extends AppCompatActivity {
    private SVCDB db;
    private UserDTO user;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        db = new SVCDB(this);
        Intent intent = getIntent();

        user = UserDTO.stringToUser(intent.getStringExtra(Constants.USER));

        EditText full_name = (EditText) findViewById(R.id.fullNameinfoTR);
        full_name.setText(user.getFull_name());

        EditText email = (EditText) findViewById(R.id.emailinfoTR);
        email.setText(user.getEmail());

    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void editInfo(View v){
        Intent intent = new Intent(this,ResetPassword.class);
        intent.putExtra(Constants.USER,user.toString());
        startActivity(intent);
    }
}