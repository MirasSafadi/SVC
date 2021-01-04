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
import models.UserDTO;
import models.VisitCardDAO;
import models.VisitCardDTO;
import security.InputValidators;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class EditVC extends AppCompatActivity {
    private SVCDB db;
    private VisitCardDTO vc;
    private UserDTO user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_v_c);
        db = new SVCDB(this);
        Intent intent = getIntent();

        vc = VisitCardDTO.stringToVisitCard(intent.getStringExtra(Constants.VC_DATA));
        user = UserDTO.stringToUser(intent.getStringExtra(Constants.USER));


        EditText email = (EditText) findViewById(R.id.eemailET);
        email.setText(vc.getEmail());

        EditText prefix = (EditText) findViewById(R.id.eprenameTF);
        prefix.setText(vc.getPrefix());

        EditText first_name = (EditText) findViewById(R.id.efnameTF);
        first_name.setText(vc.getFirst_name());

        EditText middle_name = (EditText) findViewById(R.id.emnameTF);
        middle_name.setText(vc.getMiddle_name());

        EditText last_name = (EditText) findViewById(R.id.elnameTF);
        last_name.setText(vc.getLast_name());

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
    public void editVC(View v){
        String prefix = ((EditText) findViewById(R.id.eprenameTF)).getText().toString();
        String first_name = ((EditText) findViewById(R.id.efnameTF)).getText().toString();
        String middle_name = ((EditText) findViewById(R.id.emnameTF)).getText().toString();
        String last_name = ((EditText) findViewById(R.id.elnameTF)).getText().toString();
        String mobile = ((EditText) findViewById(R.id.emobileTF)).getText().toString();
        String company = ((EditText) findViewById(R.id.ecompanyTF)).getText().toString();
        String telephone = ((EditText) findViewById(R.id.etelephoneTF)).getText().toString();
        String email = ((EditText) findViewById(R.id.eemailET)).getText().toString();
        String fax = ((EditText) findViewById(R.id.efaxTF)).getText().toString();
        String position_title = ((EditText) findViewById(R.id.epositionTF)).getText().toString();
        String website = ((EditText) findViewById(R.id.ewebsiteTF)).getText().toString();
        String address = ((EditText) findViewById(R.id.eaddressTF)).getText().toString();

        //check if fields are not empty and validate them with regex if so...
        if(!first_name.isEmpty() && !InputValidators.validate(InputValidators.NAME,first_name))
        {
            new AlertDialog.Builder(this)
                    .setTitle("Invalid input")
                    .setMessage("First name must contain only english characters")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }

        if(!middle_name.isEmpty() && !InputValidators.validate(InputValidators.NAME,middle_name))
        {
            new AlertDialog.Builder(this)
                    .setTitle("Invalid input")
                    .setMessage("Middle name must contain only english characters")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }

        if(!last_name.isEmpty() && !InputValidators.validate(InputValidators.NAME,last_name))
        {
            new AlertDialog.Builder(this)
                    .setTitle("Invalid input")
                    .setMessage("Last name must contain only english characters")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        if(!mobile.isEmpty() && !InputValidators.validate(InputValidators.MOBILE,mobile))
        {
            new AlertDialog.Builder(this)
                    .setTitle("Invalid input")
                    .setMessage("Mobile phone number must start with 05 and contain 10 digits in total.")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        if(!telephone.isEmpty() && !InputValidators.validate(InputValidators.TELEPHONE,telephone))
        {
            new AlertDialog.Builder(this)
                    .setTitle("Invalid input")
                    .setMessage("Telephone number must start with 02,03,04,08 or 09 and contain 9 digits in total.")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        if(!email.isEmpty() && !InputValidators.validate(InputValidators.EMAIL,email))
        {
            new AlertDialog.Builder(this)
                    .setTitle("Invalid input")
                    .setMessage("Email field is invalid")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        if(!fax.isEmpty() && !InputValidators.validate(InputValidators.FAX,fax))
        {
            new AlertDialog.Builder(this)
                    .setTitle("Invalid input")
                    .setMessage("Fax number must start with 02,03,04,08 or 09 and contain 9 digits in total.")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
        if(!website.isEmpty() && !InputValidators.validate(InputValidators.WEBSITE,website))
        {
            new AlertDialog.Builder(this)
                    .setTitle("Invalid input")
                    .setMessage("Website field is invalid")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }


        try {
        if(VisitCardDAO.editVC(new VisitCardDTO.Builder()
                .setEmail(email)
                .setOwner(vc.getOwner())
                .setPrefix(prefix)
                .setFirst_name(first_name)
                .setMiddle_name(middle_name)
                .setLast_name(last_name)
                .setPosition_title(position_title)
                .setCompany(company)
                .setAddress(address)
                .setTelephone(telephone)
                .setFax(fax)
                .setMobile(mobile)
                .setWebsite(website)
                .build(), db)){
            Intent intent = new Intent(this,Home.class);
            //putExtra
            intent.putExtra(Constants.USER,user.toString());
            startActivity(intent);
        }else{
            //TODO: check if the visit card exists by comparing all the values not by ID
            new AlertDialog.Builder(this)
                    .setTitle("This visit card hasn't updated.")
                    .setMessage("Please try again!")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
        } catch (IllegalArgumentException e) {
            new AlertDialog.Builder(this)
                    .setTitle("Mandatory fields missing")
                    .setMessage("First name, Last name, Email, Position, Address, Telephone, Company are mandatory fields.")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
}
