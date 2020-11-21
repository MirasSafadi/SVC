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

import models.UserDAO;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class AddVC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_v_c);
    }

    public void addVc(View v){
        String email = ((TextInputEditText) findViewById(R.id.emailTR)).getText().toString();
        String cPassword = ((EditText) findViewById(R.id.cPasswordTR)).getText().toString();
//        if(UserDAO.signUp(email,cPassword)){
//            Intent intent = new Intent(this,MainActivity.class);
//            startActivity(intent);
//        }else{
//            new AlertDialog.Builder(this)
//                    .setTitle("Wrong Credentials, Sorry :(")
//                    .setMessage("Email is already exist or password is not ma!")
//                    .setNeutralButton("Close", null)
//                    .setIcon(android.R.drawable.ic_dialog_alert)
//                    .show();
//
//        }
    }
}
