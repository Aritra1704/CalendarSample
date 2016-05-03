package com.param.calendarproject.MonthCalendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.param.calendarproject.R;
import com.param.calendarproject.Utilities.LogUtils;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Aritra on 5/2/2016.
 */
public class CalendarCellAdapter extends RecyclerView.Adapter<CalendarCellAdapter.ViewHolder>{

    private Context context;
    private Locale locale;
    private Calendar currentCalendar, auxCalendar;
    private int firstDayOfMonth,dayOfMonthIndex;
    private List<String> events;

    private static final String DAY_OF_MONTH_TEXT = "dayOfMonthText";
    private static final String DAY_OF_MONTH_BACKGROUND = "dayOfMonthBackground";
    private static final String DAY_OF_MONTH_CONTAINER = "dayOfMonthContainer";
    private static final String FIRST_UNDERLINE = "firstUnderlineView";
    private static final String SECOND_UNDERLINE = "secondUnderlineView";

    public CalendarCellAdapter(Context context,Locale locale,Calendar currentCalendar,List<String> events){
        this.context = context;
        this.locale = locale;
        this.currentCalendar = currentCalendar;

        auxCalendar = Calendar.getInstance(locale);
        auxCalendar.setTime(currentCalendar.getTime());
        String calendar_month = ""+currentCalendar.get(Calendar.MONTH);
        LogUtils.debug("calendar_month",calendar_month);
        auxCalendar.set(Calendar.MONTH, currentCalendar.get(Calendar.MONTH));
        auxCalendar.set(Calendar.DAY_OF_MONTH, 1);
        firstDayOfMonth = auxCalendar.get(Calendar.DAY_OF_WEEK);

        dayOfMonthIndex = getWeekIndex(firstDayOfMonth, auxCalendar);

        this.events = events;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_cell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvDate.setVisibility(View.INVISIBLE);
        holder.ivDot.setVisibility(View.INVISIBLE);

        if(dayOfMonthIndex <= (position+1)) {

            // Apply styles
            holder.tvDate.setBackgroundResource(android.R.color.transparent);
            //holder.tvDate.setBackgroundResource(R.color.color_Black);


            //dayOfMonthContainer.setOnClickListener(onDayOfMonthClickListener);
            holder.tvDate.setVisibility(View.VISIBLE);
            int test = auxCalendar.get(Calendar.DAY_OF_MONTH);
            LogUtils.debug("onBindViewHolder",""+test);
            holder.tvDate.setText(""+auxCalendar.get(Calendar.DAY_OF_MONTH));

            String date = ""+auxCalendar.get(Calendar.DAY_OF_MONTH)+"-"+(auxCalendar.get(Calendar.MONTH)+1)+"-"+auxCalendar.get(Calendar.YEAR);
            if(events.contains(date)){
                holder.ivDot.setVisibility(View.VISIBLE);
                holder.ivDot.setBackgroundResource(R.color.color_Black);
                holder.ivDot.setBackgroundResource(android.R.color.transparent);
                holder.ivDot.setImageResource(R.drawable.ic_pinkdot);
            }
            if(auxCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)>auxCalendar.get(Calendar.DAY_OF_MONTH))
                auxCalendar.add(Calendar.DAY_OF_MONTH,1);
            else
                dayOfMonthIndex = 100;
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        int totalItemCount = auxCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)+dayOfMonthIndex;//firstDayOfMonth;
        LogUtils.debug("getItemCount",""+totalItemCount);
        //return auxCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return 35;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvDate;
        public final ImageView ivDot;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvDate      = (TextView) view.findViewById(R.id.tvDate);
            ivDot = (ImageView) view.findViewById(R.id.ivDot);
        }

        @Override
        public String toString() {
            return "";
        }
    }

    private int getWeekIndex(int weekIndex, Calendar currentCalendar) {
        int firstDayWeekPosition = currentCalendar.getFirstDayOfWeek();

        if (firstDayWeekPosition == 1) {
            return weekIndex;
        } else {

            if (weekIndex == 1) {
                return 7;
            } else {
                return weekIndex - 1;
            }
        }
    }
}
