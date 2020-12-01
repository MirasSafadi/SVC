package com.example.svc;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import models.SVCDB;
import models.VisitCardDAO;
import models.VisitCardDTO;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class Home extends AppCompatActivity {
    private String email;
    private String full_name;
    private SVCDB db;
    ArrayList<VisitCardDTO> userVisitCards;

    TableLayout visitCardsTable;
    TableRow rowHeader;
    TextView vc_email, vc_full_name, vc_position_title, vc_company, vc_address, vc_telephone, vc_fax, vc_mobile, vc_website;

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
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            //set the views
            TextView full_name = new TextView(this);
            full_name.setText(vc.getFull_name());
            row.addView(full_name);

            TextView position_title = new TextView(this);
            position_title.setText(vc.getPosition_title());
            row.addView(position_title);

            TextView company = new TextView(this);
            company.setText(vc.getCompany());
            row.addView(company);


            Button viewVC = new Button(this);
            viewVC.setText("Edit");
            Context context = this;
            viewVC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,ViewVisitCard.class);
//                    intent.putExtra(EMAIL,user.getEmail());
//                    intent.putExtra(FULL_NAME,user.getFull_name());
                    startActivity(intent);
                }
            });
            row.addView(viewVC);

            //add the row to the table
            visitCardsTable.addView(row,i);
        }

    }
}