package com.example.svc;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import SenderPackage.Sender;
import Utils.Constants;
import Utils.Smaz;
import models.SVCDB;
import models.UserDTO;
import models.VisitCardDAO;
import models.VisitCardDTO;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class ViewVisitCard extends AppCompatActivity {
    private SVCDB db;
    private VisitCardDTO vc;
    private UserDTO user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_visit_card);
        db = new SVCDB(this);

        Intent intent = getIntent();
        vc = VisitCardDTO.stringToVisitCard(intent.getStringExtra(Constants.VC_DATA));
        user = UserDTO.stringToUser(intent.getStringExtra(Constants.USER));

        TextView email = (TextView) findViewById(R.id.emailTextView);
        email.setText("Email: " + vc.getEmail());

        TextView full_name = (TextView) findViewById(R.id.full_nameTextView);
        full_name.setText("Full Name: " + vc.getFirst_name());

        TextView position_title = (TextView) findViewById(R.id.positionTextView);
        position_title.setText("Position: " + vc.getPosition_title());

        TextView company = (TextView) findViewById(R.id.companyTextView);
        company.setText("Company: " + vc.getCompany());

        TextView address = (TextView) findViewById(R.id.addressTextView);
        address.setText("Address: " + vc.getAddress());

        TextView telephone = (TextView) findViewById(R.id.telephoneTextView);
        telephone.setText("Telephone: " + vc.getTelephone());

        TextView fax = (TextView) findViewById(R.id.faxTextView);
        fax.setText("Fax: " + vc.getFax());

        TextView mobile = (TextView) findViewById(R.id.mobileTextView);
        mobile.setText("Mobile: " + vc.getMobile());

        TextView website = (TextView) findViewById(R.id.websiteTextView);
        website.setText("Website: " + vc.getWebsite());

    }



    public void Delete(View v){
        if(VisitCardDAO.deleteVC( vc.getEmail(),vc.getFirst_name(),vc.getLast_name(),db)){
            Intent intent = new Intent(this,Home.class);
            //putExtra
            intent.putExtra(Constants.USER,user.toString());
            startActivity(intent);
        }else{
            //TODO: check if the visit card exists by comparing all the values not by ID
            new AlertDialog.Builder(this)
                    .setTitle("This visit card hasn't deleted.")
                    .setMessage("Please try again!")
                    .setNeutralButton("Close", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
    }

    public void Send(View v){
        //if algorithm is Smaz
        Smaz smaz = new Smaz();
        byte[] compressedVC = smaz.compress(vc.prepareForCompression());
        String binaryRep = Utils.utils.byteArrayToBinary(compressedVC);
        //if algorithm is LZString
//        String compressedVC = Utils.LZString.compress(vc.toString());
//        String binaryRep = Utils.utils.strToBinary(compressedVC);

        if (!binaryRep.isEmpty() && !binaryRep.equals(" ")) {
            Sender cSender = new Sender();
            cSender.setMsg2Send(binaryRep);
            Integer[] SettingsArr = Utils.SoundSettings.getSettings();
            //TODO Rani: add Alert with progress bar
            cSender.sendMsg(SettingsArr);
        }


    }

    public void Edit(View v){
        Intent intent = new Intent(this,EditVC.class);
        intent.putExtra(Constants.VC_DATA,vc.toString());
        intent.putExtra(Constants.USER,user.toString());
        startActivity(intent);
    }
}