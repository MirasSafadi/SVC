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
import security.InputValidators;

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
        
        EditText owner = (EditText) findViewById(R.id.eownerTF);
        owner.setText(vc.getOwner());

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
        String full_name = ((EditText) findViewById(R.id.enameTF)).getText().toString();
        String mobile = ((EditText) findViewById(R.id.emobileTF)).getText().toString();
        String company = ((EditText) findViewById(R.id.ecompanyTF)).getText().toString();
        String telephone = ((EditText) findViewById(R.id.etelephoneTF)).getText().toString();
        String email = ((EditText) findViewById(R.id.eemailET)).getText().toString();
        String fax = ((EditText) findViewById(R.id.efaxTF)).getText().toString();
        String position_title = ((EditText) findViewById(R.id.epositionTF)).getText().toString();
        String website = ((EditText) findViewById(R.id.ewebsiteTF)).getText().toString();
        String address = ((EditText) findViewById(R.id.eaddressTF)).getText().toString();

        //check if fields are not empty and validate them with regex if so...
        boolean isValid = true;
        if(!full_name.isEmpty() && !InputValidators.validate(InputValidators.NAME,full_name))
            isValid = false;
        if(!mobile.isEmpty() && !InputValidators.validate(InputValidators.MOBILE,mobile))
            isValid = false;
        if(!telephone.isEmpty() && !InputValidators.validate(InputValidators.TELEPHONE,telephone))
            isValid = false;
        if(!email.isEmpty() && !InputValidators.validate(InputValidators.EMAIL,email))
            isValid = false;
        if(!fax.isEmpty() && !InputValidators.validate(InputValidators.FAX,fax))
            isValid = false;
        if(!website.isEmpty() && !InputValidators.validate(InputValidators.WEBSITE,website))
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

        if(VisitCardDAO.editVC(new VisitCardDTO.Builder()
                .setEmail(email)
                .setOwner(vc.getOwner())
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
                    .setTitle("This visit card hasn't updated.")
                    .setMessage("Please try again!")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
    }
}
