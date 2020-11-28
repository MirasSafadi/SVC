package security;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InputValidators {
    public static final String EMAIL = "EMAIL";
    public static final String NAME = "NAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String MOBILE = "MOBILE";
    public static final String TELEPHONE = "TELEPHONE";
    public static final String FAX = "FAX";
    public static final String WEBSITE = "WEBSITE";


    //for user model
    private static final Pattern nameRegex = Pattern.compile("^[a-z ]+[^0-9]$",Pattern.CASE_INSENSITIVE);
    private static final Pattern passwordRegex = Pattern.compile("^((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{6,}))$");
    private static final Pattern emailRegex = Pattern.compile("^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$",Pattern.CASE_INSENSITIVE);
    //for visit card model
    private static final Pattern mobileRegex = Pattern.compile("^[05].[0-9].[0-9]{7}$"); //matches Israeli mobile numbers
    private static final Pattern telephone_faxRegex = Pattern.compile("^[02,03,04,08,09][0-9]{7}$");
    private static final Pattern websiteRegex = Pattern.compile("^(https?:\\/\\/)?(www\\.)[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,4}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)|(https?:\\/\\/)?(www\\.)?(?!ww)[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,4}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)$",Pattern.CASE_INSENSITIVE);

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
