package com.hopchurch.utilfunctions;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import com.hopchurch.Application.MyApplicaionClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilClass extends MyApplicaionClass {
    public static String getWeekValue(int week, String parent) {
        Context context = MyApplicaionClass.getContext();
        String[] weeks = null;
        if (!isEmpty(parent)) {
            if (parent.trim().equalsIgnoreCase("dad")) {
                Log.d("parentdad", parent + "");
                weeks = context.getResources().getStringArray(com.hopchurch.godsgift.R.array.dadweek);
            } else {
                Log.d("parentmom", parent + "");
                weeks = context.getResources().getStringArray(com.hopchurch.godsgift.R.array.weeks);
            }
        }
        for (int i = 0; i < 40; i++) {
            if (i == week - 1) {
                return weeks[i];
            }
        }
        return null;
    }

    public static String getWeekValuePrayer(int week) {
        String[] weeks = MyApplicaionClass.getContext().getResources().getStringArray(com.hopchurch.godsgift.R.array.weekPrayer);
        for (int i = 0; i < 40; i++) {
            if (i == week - 1) {
                return weeks[i];
            }
        }
        return null;
    }

    public static String getWeekValuePrayerNew(int week) {
        String[] weeks = MyApplicaionClass.getContext().getResources().getStringArray(com.hopchurch.godsgift.R.array.weekPrayercontent);
        for (int i = 0; i < 40; i++) {
            if (i == week - 1) {
                return weeks[i];
            }
        }
        return null;
    }

    public static boolean isEmpty(String value) {
        return value == null || value == "";
    }

    public static Date getLnitialDate(Date start) {
        Date initialDate = start;
        Calendar endCal = Calendar.getInstance();
        Calendar stCal = Calendar.getInstance();
        stCal.setTime(start);
        stCal.set(11, 0);
        stCal.set(12, 0);
        stCal.set(13, 0);
        stCal.set(14, 0);
        endCal.setTime(new Date());
        endCal.set(11, 0);
        endCal.set(12, 0);
        endCal.set(13, 0);
        endCal.set(14, 0);
        while (stCal.get(7) != endCal.get(7)) {
            Log.d("ndt dat", endCal.getTime() + "");
            endCal.add(5, 1);
            if (stCal.getTimeInMillis() > endCal.getTimeInMillis()) {
                return initialDate;
            }
        }
        initialDate = endCal.getTime();
        Log.d("stat dat", initialDate + "");
        return initialDate;
    }

    public static int noOfSundays(Date start) {
        int number = 0;
        Date currentdate = new Date();
        Calendar stCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        stCal.setTime(start);
        endCal.setTime(new Date());
        endCal.set(11, 0);
        endCal.set(12, 0);
        endCal.set(13, 0);
        endCal.set(14, 0);
        stCal.add(5, 1);
        stCal.set(11, 0);
        stCal.set(12, 0);
        stCal.set(13, 0);
        stCal.set(14, 0);
        if (endCal.getTimeInMillis() > stCal.getTimeInMillis()) {
            do {
                stCal.add(5, 1);
                number++;
                stCal.set(11, 0);
                stCal.set(12, 0);
                stCal.set(13, 0);
            } while (stCal.getTimeInMillis() <= endCal.getTimeInMillis());
        }
        Log.d("ni of sunday", number + "");
        return number / 7;
    }

    public static String getStrinGDate(Date T) {
        String dateString = "";
        return new SimpleDateFormat("yyyy-MM-dd").format(T);
    }

    public static Date getDatefromString(String T) {
        Date date = null;
        if (!isEmpty(T)) {
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(T);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public static Spanned fromHtml(String html) {
        if (VERSION.SDK_INT >= 24) {
            return Html.fromHtml(html.toString(), 0);
        }
        return Html.fromHtml(html.toString());
    }
}
