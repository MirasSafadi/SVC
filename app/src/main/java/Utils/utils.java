package Utils;

import android.content.Context;

import java.util.ArrayList;

public class utils {

    public static int dpFromPx(final Context context, final float px) {
        return (int) (px / context.getResources().getDisplayMetrics().density);
    }

    public static int pxFromDp(final Context context, final float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
    public static String[] fillArray(String arr[]) {
        String newarr[] = new String[11];
        int i = 0;
        for (; i < arr.length; i++)
            newarr[i] = arr[i];
        for(;i<11;i++)
            newarr[i] = "";

        return newarr;
    }
    public static String strToBinary(String s) {
        int n = s.length();

        StringBuilder binRes = new StringBuilder("");
        for (int i = 0; i < n; i++)
        {
            // convert each char to
            // ASCII value
            int val = Integer.valueOf(s.charAt(i));

            // Convert ASCII value to binary
            String bin = "";
            while (val > 0) {
                if (val % 2 == 1) {
                    bin += '1';
                }
                else
                    bin += '0';
                val /= 2;
            }
            bin = reverse(bin);
            binRes.append(bin);
        }
        return binRes.toString();
    }

    private static String reverse(String input) {
        char[] a = input.toCharArray();
        int l, r = 0;
        r = a.length - 1;

        for (l = 0; l < r; l++, r--)
        {
            // Swap values of l and r
            char temp = a[l];
            a[l] = a[r];
            a[r] = temp;
        }
        return String.valueOf(a);
    }
    public static String concatArrayList(ArrayList<String> arrayList){
        StringBuilder sb = new StringBuilder();
        for(String st: arrayList){
            sb.append(st);
        }
        if(sb.length()%8 != 0) sb.append("0000");
        return sb.toString();
    }
    public static String binaryToText(String binaryRep){
        StringBuilder sb = new StringBuilder();
        int n = binaryRep.length();
        for(int i = 0; (i+8) <= n ; i += 8){
            String asciiCode = binaryRep.substring(i,i+8);
            int charCode = Integer.parseInt(asciiCode, 2);
            String str = new Character((char)charCode).toString();
            sb.append(str);
        }
        return sb.toString();
    }



}
