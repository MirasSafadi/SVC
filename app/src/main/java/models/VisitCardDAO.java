package models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

/**
 * This class connects the <i>Visit Card</i> module to the DB. i.e., contains all the CRUD operations for the visit card.
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class VisitCardDAO {

    /**
     * gets the list of visit cards owned by the user.
     * @param email The user email
     * @param db The DB instance
     * @return A list of visit cards (empty if user owns none).
     */
    public static ArrayList<VisitCardDTO> getUserVisitCards(String email,SVCDB db){
        return db.getUserVisitCards(email);
    }

    /**
     * Adds a visit card to the DB.
     * @param vc The visit card to add.
     * @param db The DB instance.
     * @return success/failure of the operation
     */
    public static boolean addVC (VisitCardDTO vc, SVCDB db){
        //do input validation!!
        //get the vc from the database
        System.out.println(vc.getPosition_title());
        return db.addVC(vc);
    }

    /**
     * Modifies an existing visit card in the DB
     * @param vc The changes to apply to the visit card.
     * @param db The DB instance.
     * @return success/failure of the operation
     */
    public static boolean editVC (VisitCardDTO vc, SVCDB db){
        //do input validation!!
        //get the vc from the database

        return db.editVC(vc);
    }

    /**
     * Deletes a visit card from the DB.
     * @param email The email field of the visit card to delete
     * @param first_name The first_name field of the visit card to delete.
     * @param last_name The last_name field of the visit card to delete.
     * @param db The DB instance.
     * @return success/failure of the operation
     */
    public static boolean deleteVC (String email, String first_name, String last_name, SVCDB db){
        return db.deleteVC(email,first_name,last_name);
    }
}
