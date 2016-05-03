package com.param.calendarproject.MonthCalendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.param.calendarproject.R;

import java.text.DateFormatSymbols;
import java.util.Locale;

/**
 * Created by Aritra on 5/2/2016.
 */
public class DayofWeekAdapter extends RecyclerView.Adapter<DayofWeekAdapter.ViewHolder> {

    private Context context;
    private String[] weekDaysArray;
    private Locale locale;
    public DayofWeekAdapter(Context context,Locale locale){
        this.context = context;
        this.locale = locale;

        weekDaysArray = new DateFormatSymbols(locale).getShortWeekdays();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_cell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.ivDot.setVisibility(View.GONE);
        String dayOfTheWeekString = weekDaysArray[position+1];
        dayOfTheWeekString = checkSpecificLocales(dayOfTheWeekString, position+1);
        holder.tvDate.setText(dayOfTheWeekString);
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvDate;
        public final ImageView ivDot;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvDate      = (TextView) view.findViewById(R.id.tvDate);
            ivDot       = (ImageView) view.findViewById(R.id.ivDot);
        }

        @Override
        public String toString() {
            return "";
        }
    }

    private String checkSpecificLocales(String dayOfTheWeekString, int i) {
        // Set Wednesday as "X" in Spanish locale
        if (i == 4 && locale.getCountry().equals("ES")) {
            dayOfTheWeekString = "X";
        } else {
            dayOfTheWeekString = dayOfTheWeekString.substring(0, 1).toUpperCase();
        }
        return dayOfTheWeekString;
    }
}
