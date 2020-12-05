package com.example.svc;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import models.SVCDB;
import models.VisitCardDAO;
import models.VisitCardDTO;

public class EditVC extends AppCompatActivity {
    private SVCDB db;
    private VisitCardDTO vc;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_v_c);
        db = new SVCDB(this);
        Intent intent = getIntent();
        vc = VisitCardDTO.stringToVisitCard(intent.getStringExtra(ViewVisitCard.VC_DATA_EDIT));
        EditText email = (EditText) findViewById(R.id.eemailET);
        email.setText(vc.getEmail());

        EditText full_name = (EditText) findViewById(R.id.enameTF);
        full_name.setText(vc.getFull_name());

        EditText position_title = (EditText) findViewById(R.id.epositionTF);
        position_title.setText(vc.getPosition_title());

        EditText company = (EditText) findViewById(R.id.ecompanyTF);
        company.setText(vc.getCompany());

        EditText address = (EditText) findViewById(R.id.eaddressTF);
        address.setText(vc.getAddress());

        EditText telephone = (EditText) findViewById(R.id.etelephoneTF);
        telephone.setText(vc.getTelephone());

        EditText fax = (EditText) findViewById(R.id.efaxTF);
        fax.setText(vc.getFax());

        EditText mobile = (EditText) findViewById(R.id.emobileTF);
        mobile.setText(vc.getMobile());

        EditText website = (EditText) findViewById(R.id.ewebsiteTF);
        website.setText(vc.getWebsite());

    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void editVC(View v){
        String full_name = ((EditText) findViewById(R.id.nameTF)).getText().toString();
        String mobile = ((EditText) findViewById(R.id.mobileTF)).getText().toString();
        String company = ((EditText) findViewById(R.id.companyTF)).getText().toString();
        String telephone = ((EditText) findViewById(R.id.telephoneTF)).getText().toString();
        String email = ((EditText) findViewById(R.id.emailET)).getText().toString();
        String fax = ((EditText) findViewById(R.id.faxTF)).getText().toString();
        String position_title = ((EditText) findViewById(R.id.positionTF)).getText().toString();
        String website = ((EditText) findViewById(R.id.websiteTF)).getText().toString();
        String address = ((EditText) findViewById(R.id.addressTF)).getText().toString();

        if(VisitCardDAO.editVC(new VisitCardDTO.Builder()
                .setEmail(email)
                .setFull_name(full_name)
                .setPosition_title(position_title)
                .setCompany(company)
                .setAddress(address)
                .setTelephone(telephone)
                .setFax(fax)
                .setMobile(mobile)
                .setWebsite(website)
                .build(), db)){
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
