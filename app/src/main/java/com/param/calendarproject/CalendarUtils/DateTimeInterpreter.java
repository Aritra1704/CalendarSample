package com.param.calendarproject.CalendarUtils;

import java.util.Calendar;

/**
 * Created by Aritra on 4/27/2016.
 */
public interface DateTimeInterpreter {
    String interpretDay(Calendar date);
    String interpretDate(Calendar date);
    String interpretTime(int hour);
}
