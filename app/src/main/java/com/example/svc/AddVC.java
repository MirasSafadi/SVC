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
import models.VisitCardDAO;
import models.VisitCardDTO;
import security.InputValidators;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class AddVC extends AppCompatActivity {
    private SVCDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vc);
        db = new SVCDB(this);
        //get the email of the user from intent
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
        //need to test regex independently first
        /*boolean isValid = true;
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
            isValid = false;*/

//        if(VisitCardDAO.addVC(new VisitCardDTO(Integer.parseInt(id), null, null, null,null,null,null,null), db)){
//            Intent intent = new Intent(this,Home.class);
//            startActivity(intent);
//        }else{
//            new AlertDialog.Builder(this)
//                    .setTitle("You already have this visit card!")
//                    .setMessage("Please add another visit card with another ID!")
//                    .setNeutralButton("Close", null)
//                    .setIcon(android.R.drawable.ic_dialog_alert)
//                    .show();
//
//        }
    }
}
