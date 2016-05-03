package com.param.calendarproject.MonthCalendar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.param.calendarproject.R;
import com.param.calendarproject.Utilities.LogUtils;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Aritra on 5/2/2016.
 */
public class CalendarAdapter extends PagerAdapter {

    private Context context;
    private Locale locale;
    private Calendar currentCalendar;
    private List<String> events;
    public CalendarAdapter (Context context, Locale locale, Calendar currentCalendar,List<String> events) {
        this.context = context;
        this.locale = locale;
        this.currentCalendar = currentCalendar;
        this.events = events;
    }
    public void refresh(Calendar currentCalendar){
        this.currentCalendar = currentCalendar;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return CustomMonthCalendar.MAX_PAGE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.monthcalendar_cell,null);

        /**************Days of Week**********************/
        RecyclerView rvDayOfWeek = (RecyclerView) convertView.findViewById(R.id.rvDayOfWeek);
        DayofWeekAdapter dayadapter = new DayofWeekAdapter(context,locale);
        rvDayOfWeek.setLayoutManager(new GridLayoutManager(context,7));
        rvDayOfWeek.setAdapter(dayadapter);

        RecyclerView rvCalendar = (RecyclerView) convertView.findViewById(R.id.rvCalendar);
        Calendar calAdapter = (Calendar) currentCalendar.clone();
        calAdapter.add(calAdapter.MONTH,position);
        String calendar_month = ""+calAdapter.get(Calendar.MONTH);
        LogUtils.debug("calendar_month",calendar_month);
        CalendarCellAdapter adapter = new CalendarCellAdapter(context,locale, calAdapter,events);
        rvCalendar.setLayoutManager(new GridLayoutManager(context,7));
        rvCalendar.setAdapter(adapter);


        container.addView(convertView);
        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
