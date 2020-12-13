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

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class AddVC extends AppCompatActivity {
    private SVCDB db;
    private String owner_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vc);
        db = new SVCDB(this);
        //get the email of the user from intent
        Intent intent = getIntent();
        owner_email = intent.getStringExtra(Home.USER_EMAIL);
    }

    public void addVc(View v){
        String full_name = ((EditText) findViewById(R.id.nameTF)).getText().toString();
        String mobile = ((EditText) findViewById(R.id.mobileTF)).getText().toString();
        String company = ((EditText) findViewById(R.id.companyTF)).getText().toString();
        String telephone = ((EditText) findViewById(R.id.telephoneTF)).getText().toString();
        String email = ((EditText) findViewById(R.id.emailET)).getText().toString();
        String fax = ((EditText) findViewById(R.id.faxTF)).getText().toString();
        String position_title = ((EditText) findViewById(R.id.positionTF)).getText().toString();
        String website = ((EditText) findViewById(R.id.websiteTF)).getText().toString();
        String address = ((EditText) findViewById(R.id.addressTF)).getText().toString();


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


        if(!isValid) {
            new AlertDialog.Builder(this)
                    .setTitle("Invalid Input")
                    .setMessage("One or more of the fields is invalid")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        if(VisitCardDAO.addVC(new VisitCardDTO.Builder()
               .setOwner(owner_email)
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
           //add a condition in the DB that checks if a visit card for this user with THE SAME VALUES FOR ALL FIELDS
            new AlertDialog.Builder(this)
                   .setTitle("You already have this visit card!")
                    .setMessage("Please add another visit card with another ID!")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
    }
}
