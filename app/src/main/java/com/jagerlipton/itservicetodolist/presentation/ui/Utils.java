package com.jagerlipton.itservicetodolist.presentation.ui;

import android.annotation.SuppressLint;
import android.text.format.DateFormat;
import android.util.Log;

import java.util.Date;
import java.util.TimeZone;

public class Utils {

    @SuppressLint("SimpleDateFormat")
    public static String longToTime(long timestamp) {
        try {
            return DateFormat.format("HH:mm:ss", new Date(timestamp)).toString();
        } catch (Exception e) {
            e.toString();
            return null;
        }
    }

    public static String longToDateTime(long timestamp) {
        try {
            return DateFormat.format("HH:mm:ss dd/MM/yyyy", new Date(timestamp)).toString();
        } catch (Exception e) {
            e.toString();
            return null;
        }
    }


    public static long getTimeNow() {
        return System.currentTimeMillis();
    }
}
