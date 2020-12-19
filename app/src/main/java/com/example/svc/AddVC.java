package com.example.svc;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import ReceiverPackage.Receiver;
import Utils.Constants;
import models.SVCDB;
import models.UserDTO;
import models.VisitCardDAO;
import models.VisitCardDTO;
import security.InputValidators;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class AddVC extends AppCompatActivity {
    private SVCDB db;
    private UserDTO user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vc);
        db = new SVCDB(this);
        //get the email of the user from intent
        Intent intent = getIntent();
        user = UserDTO.stringToUser(intent.getStringExtra(Constants.USER));
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

        if(!isValid){
            new AlertDialog.Builder(this)
                    .setTitle("Invalid input")
                    .setMessage("One or more of the fields is invalid")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }

        try {
            if(VisitCardDAO.addVC(new VisitCardDTO.Builder()
                   .setOwner(user.getEmail())
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
                //putExtra...
                intent.putExtra(Constants.USER,user.toString());
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
        } catch (IllegalArgumentException e) {
            new AlertDialog.Builder(this)
                    .setTitle("Mandatory fields missing")
                    .setMessage("Full name, Position, Company are mandatory fields.")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
    public void ReceiveVC(View v) {
        System.out.println("Receiving...");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 0);
        }
        else{
            Listen();
        }
    }

    /**********************************************************************************************
     * function: Listen
     * description: function will start when listen/Record start
     * args:
     * return: void
     **********************************************************************************************/
    private void Listen() {
        Receiver cReceiver = new Receiver();
        Integer[] SettingsArr = Utils.SoundSettings.getSettings();
        try {
            ArrayList<String> ReceivedMsg = cReceiver.receiveMsg(SettingsArr);
            String binaryRep = Utils.utils.concatArrayList(ReceivedMsg);
            String compressed = Utils.utils.binaryToText(binaryRep);
            String decompressed = Utils.LZString.decompress(compressed);
            //do whatever...
            new AlertDialog.Builder(this).setTitle("Received String").setMessage(decompressed).setNeutralButton("OK",null).show();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }



    /**********************************************************************************************
     * function: onRequestPermissionsResult
     * description: function to ask for a Record permissions
     * args: int requestCode, @NonNull String permissions[], @NonNull int[] grantResults
     * return: void
     **********************************************************************************************/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case 0: {
                //continue listening when user granted permission on mic
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Listen();
                }
                break;
            }
        }
    }
}
