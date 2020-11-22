package models;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import security.auth;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class UserDTO {
    private String email;
    private String password;
    private String full_name;

    public UserDTO(String email,String password, String full_name,boolean hashPassword){
        this.setEmail(email);
        this.setPassword(password,hashPassword);
        this.setFull_name(full_name);
    }
    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String getFull_name(){
        return this.full_name;
    }

    //===================================================================================

    public void setEmail(String email){
        this.email = email;
    }


    public void setPassword(String password,boolean hashPassword) {
        if(hashPassword)
            this.password = auth.hashPassword(password);
        else
            this.password = password;
    }

    public void setFull_name(String full_name){
        this.full_name = full_name;
    }


    @Override
    public String toString() {
        return String.format("%s %s",this.full_name,this.email);
    }
}
