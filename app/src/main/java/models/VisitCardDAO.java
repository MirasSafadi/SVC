package models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class VisitCardDAO {

    public static ArrayList<VisitCardDTO> getUserVisitCards(String email,SVCDB db){
        return db.getUserVisitCards(email);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean addVC (VisitCardDTO vc, SVCDB db){
        //do input validation!!
        //get the vc from the database
        System.out.println(vc.getPosition_title());
        return db.addVC(vc);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean editVC (VisitCardDTO vc, SVCDB db){
        //do input validation!!
        //get the vc from the database

        return db.editVC(vc);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean deleteVC (String email, String first_name, String last_name, SVCDB db){
        return db.deleteVC(email,first_name,last_name);
    }
}
