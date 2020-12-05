package com.example.svc;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import Utils.utils;
import models.SVCDB;
import models.VisitCardDAO;
import models.VisitCardDTO;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class Home extends AppCompatActivity {
    public static final String VC_DATA = "com.example.svc.VC_DATA";

    private String email;
    private String full_name;
    private SVCDB db;
    ArrayList<VisitCardDTO> userVisitCards;

    TableLayout visitCardsTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db = new SVCDB(this);


        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        email = intent.getStringExtra(MainActivity.EMAIL);
        full_name = intent.getStringExtra(MainActivity.FULL_NAME);

        userVisitCards = VisitCardDAO.getUserVisitCards(email,db);
        System.out.println(userVisitCards);
        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.welcomeTV);
        textView.setText("Welcome, " + full_name);

        visitCardsTable = (TableLayout) findViewById(R.id.visitCardsTable);

        for(int i=1; i <= userVisitCards.size(); i++){
            VisitCardDTO vc = userVisitCards.get(i-1);

            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
            lp.setMargins(8, 2, 16, 2);
            row.setLayoutParams(lp);

            //set the views
            TextView full_name = new TextView(this);
            full_name.setText(vc.getFull_name());
            lp.setMargins(utils.pxFromDp(this,8),0,utils.pxFromDp(this,16),0);
            row.addView(full_name,lp);

            TextView position_title = new TextView(this);
            position_title.setText(vc.getPosition_title());
            lp.setMargins(utils.pxFromDp(this,8),0,utils.pxFromDp(this,16),0);
            row.addView(position_title,lp);

            TextView company = new TextView(this);
            company.setText(vc.getCompany());
            lp.setMargins(utils.pxFromDp(this,8),0,utils.pxFromDp(this,16),0);
            row.addView(company,lp);


            Button viewVC = new Button(this);
            viewVC.setText("Edit");
            viewVC.setMinHeight(0);
            viewVC.setMinimumHeight(0);
            viewVC.setHeight(90);
            lp.setMargins(utils.pxFromDp(this,8),0,utils.pxFromDp(this,16),0);
            Context context = this;
            viewVC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,ViewVisitCard.class);
                    intent.putExtra(VC_DATA,vc.toString());
                    startActivity(intent);
                }
            });
            row.addView(viewVC,lp);

            //add the row to the table
            visitCardsTable.addView(row,i);
        }

    }
    public void addVC(View v){
        Intent intent = new Intent(this,AddVC.class);
        startActivity(intent);
    }
}