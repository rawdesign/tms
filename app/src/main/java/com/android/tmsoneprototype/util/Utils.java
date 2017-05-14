package com.android.tmsoneprototype.util;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tmsoneprototype.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

public class Utils {

    public static String nowDateComplete() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdformat.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        return sdformat.format(date);
    }

    public static String getImageUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getImageURL(String image) {
        String path = "";
        if(image.startsWith("http") || image.startsWith("https")){
            path = image;
        }else if(image.startsWith("uploads/")){
            path = Const.BASE_URL + image;
        }else{
            path = "file://" + image;
        }

        return path;
    }

    public static String formatRupiah(double price) {
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        return kursIndonesia.format(price);
    }

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    public static int getTabsHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.tabsHeight);
    }

    public static boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();

        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public static String textInput(TextView textView) {
        return textView.getText().toString().trim();
    }

    public static void intent(Context context, Class<?> classs) {
        Intent intent = new Intent(context, classs);
        context.startActivity(intent);
    }

    public static void displayToast(Context context, String message, int toast) {
        Toast.makeText(context, message, toast).show();
    }

}