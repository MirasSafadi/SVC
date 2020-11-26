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

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class AddVC extends AppCompatActivity {
    private SVCDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_v_c);
        db = new SVCDB(this);
    }

    public void addVc(View v){
        String id = ((TextInputEditText) findViewById(R.id.AIdTR)).getText().toString();
        if(VisitCardDAO.addVC(new VisitCardDTO(Integer.parseInt(id), null, null, null,null,null,null,null), db)){
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
