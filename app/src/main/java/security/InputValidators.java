package security;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidators {
    public static final String EMAIL = "EMAIL";
    public static final String NAME = "NAME";
    public static final String PASSWORD = "PASSWORD";

    //for user model
    public static final Pattern nameRegex = Pattern.compile("^[a-z ]+[^0-9]$",Pattern.CASE_INSENSITIVE);
    public static final Pattern passwordRegex = Pattern.compile("^((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{6,}))$");
    public static final Pattern emailRegex = Pattern.compile("^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$",Pattern.CASE_INSENSITIVE);
    //for visit card model

    public static boolean validate(String type, String value){
        Matcher matcher;
        if(type == EMAIL){
            matcher = emailRegex.matcher(value);
            return matcher.find();
        } else if(type == NAME){
            matcher = nameRegex.matcher(value);
            return matcher.find();
        } else if(type == PASSWORD){
            matcher = passwordRegex.matcher(value);
            return matcher.find();
        }
        return false;

    }


}
