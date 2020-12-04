package security;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class Auth {

    //assumes both passwords are hashed.
    public static boolean checkPassword(String originalPassword, String passwordToCheck){
        return originalPassword.equals(passwordToCheck);
    }

    public static String hashPassword(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return toHexString(md.digest(password.getBytes(StandardCharsets.UTF_8)));
        }catch (NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

}
