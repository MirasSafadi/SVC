package models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import security.Auth;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class UserDTO {
    private String email;
    private String password;
    private String full_name;


    private UserDTO(Builder builder){
        this.email = builder.email;
        this.password = builder.password;
        this.full_name = builder.full_name;
    }


    public static class Builder{
        private String email;
        private String password;
        private String full_name;


        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder setPassword(String password, boolean hashPassword) {
            if(hashPassword)
                this.password = Auth.hashPassword(password);
            else
                this.password = password;
            return this;
        }
        public Builder setFull_name(String full_name) {
            this.full_name = full_name;
            return this;
        }
        public UserDTO build(){
            return new UserDTO(this);
        }

    }
    //===================================================================================

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



    @Override
    public String toString() {
        return String.format("%s;%s",this.full_name,this.email);
    }


    public static UserDTO stringToUser(String user){
        String[] info = user.split(";");
        return new UserDTO.Builder().
                            setFull_name(info[0]).
                            setEmail(info[1]).
                            build();
    }
}
