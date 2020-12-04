package models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import security.Auth;


@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class UserDAO {

    public static UserDTO login(UserDTO user,SVCDB db){
        //do input validation!!
        //get the user from the database
        UserDTO dbUser = db.getUser(user.getEmail());
        //if the user doesn't exist in db return false
        if(dbUser == null)
            return null;
        //check credentials and send response
        if(Auth.checkPassword(dbUser.getPassword(),user.getPassword()))
            return dbUser;
        return null;
    }
    public static boolean signUp(UserDTO user,SVCDB db){
        //do input validation!!!

        return db.addUser(user);
    }
}

