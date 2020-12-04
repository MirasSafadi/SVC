package com.example.svc;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import models.SVCDB;
import models.VisitCardDAO;
import models.VisitCardDTO;

public class EditVC extends AppCompatActivity {
    private SVCDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_v_c);
        db = new SVCDB(this);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void editVC(View v){
        String id = ((TextInputEditText) findViewById(R.id.EIdTR)).getText().toString();
        String owner = ((TextInputEditText) findViewById(R.id.EownerTR)).getText().toString();
        String full_name = ((TextInputEditText) findViewById(R.id.EfullNameTR)).getText().toString();
        String email = ((TextInputEditText) findViewById(R.id.EemailTR)).getText().toString();
        String phone_number = ((TextInputEditText) findViewById(R.id.EnumberTR)).getText().toString();
        String job = ((TextInputEditText) findViewById(R.id.EjobTR)).getText().toString();
        String company = ((TextInputEditText) findViewById(R.id.EcompanyTR)).getText().toString();
        String address = ((TextInputEditText) findViewById(R.id.EaddressTR)).getText().toString();
        if(VisitCardDAO.editVC(new VisitCardDTO(Integer.parseInt(id), owner,full_name, email,phone_number,job,company,address), db)){
            Intent intent = new Intent(this,Home.class);
            startActivity(intent);
        }else{
            new AlertDialog.Builder(this)
                    .setTitle("You already have this visit card!")
                    .setMessage("Please add another visit card with another ID!")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
    }
}