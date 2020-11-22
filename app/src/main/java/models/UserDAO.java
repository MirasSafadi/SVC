package models;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import security.auth;


@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class UserDAO {

    public static boolean login(UserDTO user,SVCDB db){
        //do input validation!!
        //get the user from the database
        System.out.println(user.getPassword());
        UserDTO dbUser = db.getUser(user.getEmail());
        System.out.println(dbUser);
        System.out.println(dbUser.getPassword());
        //if the user doesn't exist in db return false
        if(dbUser == null)
            return false;
        //check credentials and send response
        if(auth.checkPassword(dbUser.getPassword(),user.getPassword()))
            return true;
        return false;
    }
    public static boolean signUp(UserDTO user,SVCDB db){
        //do input validation!!!
        return db.addUser(user);
    }
}
