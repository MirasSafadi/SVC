package models;

import android.content.Context;

public class User {
    private static final String email = "safadimiras@gmail.com";
    private static final String password = "123456";

    public static boolean login(String email, String password, Context context){
        //do input validation and open next screen

        //check credentials and send response
        if(email.equals(User.email) && password.equals(User.password))
            return true;
        return false;
    }
    public static boolean signUp(String email, String password, Context context){
        //do input validation and open next screen

        //check credentials and send response
        if(email.equals(User.email))
        return false;
        if (password.equals(User.password))
            return true;
        return false;
    }
}
