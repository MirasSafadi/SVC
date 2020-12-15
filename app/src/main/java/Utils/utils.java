package Utils;

import android.content.Context;

public class utils {

    public static int dpFromPx(final Context context, final float px) {
        return (int) (px / context.getResources().getDisplayMetrics().density);
    }

    public static int pxFromDp(final Context context, final float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
    public static String[] fillArray(int size,String arr[], String s) {
        String newarr[] = new String[11];

        for (int i = 0; i < size; i++)
            newarr[i] = arr[i];

        newarr[size] = s;

        return newarr;
    }
}
