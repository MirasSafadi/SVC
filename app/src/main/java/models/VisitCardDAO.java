package models;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class VisitCardDAO {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean addVC (VisitCardDTO vc, SVCDB db){
        //do input validation!!
        //get the vc from the database

        return db.addVC(vc);
    }
}
