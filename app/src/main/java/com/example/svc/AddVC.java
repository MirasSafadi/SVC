package com.example.svc;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import ReceiverPackage.Receiver;
import Utils.Constants;
import Utils.Smaz;
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
        String prefix = ((EditText) findViewById(R.id.prenameTF)).getText().toString();
        String first_name = ((EditText) findViewById(R.id.fnameTF)).getText().toString();
        String middle_name = ((EditText) findViewById(R.id.mnameTF)).getText().toString();
        String last_name = ((EditText) findViewById(R.id.lnameTF)).getText().toString();
        String mobile = ((EditText) findViewById(R.id.mobileTF)).getText().toString();
        String company = ((EditText) findViewById(R.id.companyTF)).getText().toString();
        String telephone = ((EditText) findViewById(R.id.telephoneTF)).getText().toString();
        String email = ((EditText) findViewById(R.id.emailET)).getText().toString();
        String fax = ((EditText) findViewById(R.id.faxTF)).getText().toString();
        String position_title = ((EditText) findViewById(R.id.positionTF)).getText().toString();
        String website = ((EditText) findViewById(R.id.websiteTF)).getText().toString();
        String address = ((EditText) findViewById(R.id.addressTF)).getText().toString();


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
            if(VisitCardDAO.addVC(new VisitCardDTO.Builder()
                   .setOwner(user.getEmail())
                   .setEmail(email)
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
                    .setMessage("First name, Last name, Email, Position, Address, Telephone, Company are mandatory fields.")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        //save contact in phone book..
        saveInPhoneBook(new VisitCardDTO.Builder()
                .setEmail(email)
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
                .build());
    }
    public void ReceiveVC(View v) {
        System.out.println("Receiving...");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 0);
        }
        else{
            Listen();

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS}, 0);
            }
            else{
                //get the values from the text fields, create a VC and save it phone book
                String prefix = "";
                String first_name = "";
                String middle_name = "";
                String last_name = "";
                String mobile = ((EditText) findViewById(R.id.mobileTF)).getText().toString();
                String company = ((EditText) findViewById(R.id.companyTF)).getText().toString();
                String telephone = ((EditText) findViewById(R.id.telephoneTF)).getText().toString();
                String email = ((EditText) findViewById(R.id.emailET)).getText().toString();
                String fax = ((EditText) findViewById(R.id.faxTF)).getText().toString();
                String position_title = ((EditText) findViewById(R.id.positionTF)).getText().toString();
                String website = ((EditText) findViewById(R.id.websiteTF)).getText().toString();
                String address = ((EditText) findViewById(R.id.addressTF)).getText().toString();

                //save contact in phone book..
                saveInPhoneBook(new VisitCardDTO.Builder()
                        .setEmail(email)
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
                        .build());

            }
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
            //If Algorithm is Smaz
            byte[] compressed = Utils.utils.binaryToByteArray(binaryRep);
            Smaz smaz = new Smaz();
            String decompressed = smaz.decompress(compressed);
            //If Algorithm is LZString
//            String compressed = Utils.utils.binaryToText(binaryRep);
//            String decompressed = Utils.LZString.decompress(compressed);
            //do whatever...
            //fill in the text fields...
            new AlertDialog.Builder(this).setTitle("Received String").setMessage(decompressed).setNeutralButton("OK",null).show();
            VisitCardDTO receivedVC = VisitCardDTO.receiveVisitCard(decompressed);
            //TODO Rani: do the same for the 4 name TF's after you add them..
            ((EditText) findViewById(R.id.mobileTF)).setText(receivedVC.getMobile());
            ((EditText) findViewById(R.id.companyTF)).setText(receivedVC.getCompany());
            ((EditText) findViewById(R.id.telephoneTF)).setText(receivedVC.getTelephone());
            ((EditText) findViewById(R.id.emailET)).setText(receivedVC.getEmail());
            ((EditText) findViewById(R.id.faxTF)).setText(receivedVC.getFax());
            ((EditText) findViewById(R.id.positionTF)).setText(receivedVC.getPosition_title());
            ((EditText) findViewById(R.id.websiteTF)).setText(receivedVC.getWebsite());
            ((EditText) findViewById(R.id.addressTF)).setText(receivedVC.getAddress());

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

    private void saveInPhoneBook(VisitCardDTO vc){
        ArrayList<ContentProviderOperation> contact = new ArrayList<ContentProviderOperation>();
        contact.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());

        // prefix, first, middle and last name
        contact.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.PREFIX, vc.getPrefix())
                .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, vc.getFirst_name())
                .withValue(ContactsContract.CommonDataKinds.StructuredName.MIDDLE_NAME, vc.getMiddle_name())
                .withValue(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME, vc.getLast_name())
                .build());

        // company, position, and address
        contact.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Organization.TITLE,vc.getPosition_title())
                .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, vc.getCompany())
                .withValue(ContactsContract.CommonDataKinds.Organization.OFFICE_LOCATION, vc.getAddress())
                .build());

        // Mobile number
        contact.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, vc.getMobile())
                .build());

        // Telephone
        contact.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, vc.getTelephone())
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
                .build());
        // Fax
        contact.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, vc.getFax())
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK)
                .build());

        // Email
        contact.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Email.DATA, vc.getEmail())
                .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                .build());
        // Website
        contact.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Website.URL, vc.getWebsite())
                .build());

        try {
            ContentProviderResult[] results = getContentResolver().applyBatch(ContactsContract.AUTHORITY, contact);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
