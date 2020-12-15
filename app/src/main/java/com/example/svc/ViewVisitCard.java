package com.example.svc;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import Utils.Constants;
import models.UserDTO;
import models.VisitCardDTO;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class ViewVisitCard extends AppCompatActivity {
    private VisitCardDTO vc;
    private UserDTO user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_visit_card);

        Intent intent = getIntent();
        vc = VisitCardDTO.stringToVisitCard(intent.getStringExtra(Constants.VC_DATA));
        user = UserDTO.stringToUser(intent.getStringExtra(Constants.USER));

        TextView email = (TextView) findViewById(R.id.emailTextView);
        email.setText("Email: " + vc.getEmail());

        TextView full_name = (TextView) findViewById(R.id.full_nameTextView);
        full_name.setText("Full Name: " + vc.getFull_name());

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

    }

    public void Send(View v){

    }

    public void Edit(View v){
        Intent intent = new Intent(this,EditVC.class);
        intent.putExtra(Constants.VC_DATA,vc.toString());
        intent.putExtra(Constants.USER,user.toString());
        startActivity(intent);
    }

}