package com.hopchurch.staticstring;

import android.content.Context;
import com.hopchurch.Application.MyApplicaionClass;

public class StaticValues {
    static Context context = MyApplicaionClass.getContext();
    public static final String defaultlmpdate = context.getResources().getString(com.hopchurch.godsgift.R.string.defaultlmpdate);
    public static final String defaultweek = context.getResources().getString(com.hopchurch.godsgift.R.string.defaultweek);
    public static final int finalWeek = 40;
    public static final int initialWeek = 1;
    public static final int initialweek = 1;
    public static final String prayerrequest = context.getResources().getString(com.hopchurch.godsgift.R.string.prayerrequest);
    public static final String week = "week";
}
