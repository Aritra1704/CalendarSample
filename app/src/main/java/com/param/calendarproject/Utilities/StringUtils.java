package com.param.calendarproject.Utilities;

import android.text.TextUtils;

/**
 * Created by Aritra on 4/25/2016.
 */
public class StringUtils {
    public static int getInt(String integer) {
        int reqInteger = 0;

        if(integer == null || TextUtils.isEmpty(integer))
            return reqInteger;

        reqInteger = Integer.parseInt(integer);

        return reqInteger;
    }

    public static long getLong(String integer) {
        long reqInteger = 0;

        if(integer == null || TextUtils.isEmpty(integer))
            return reqInteger;

        reqInteger = Long.parseLong(integer);

        return reqInteger;
    }
}
