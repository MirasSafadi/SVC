package Utils;

import android.content.Context;

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
}
