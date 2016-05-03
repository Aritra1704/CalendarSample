package com.param.calendarproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.param.calendarproject.DataObject.EventDO;
import com.param.calendarproject.R;
import com.param.calendarproject.Utilities.LogUtils;

import java.util.Calendar;
import java.util.LinkedHashMap;

/**
 * Created by Aritra on 4/25/2016.
 */
public class CalendarGridAdapter extends RecyclerView.Adapter<CalendarGridAdapter.ViewHolder> {

    private Context context;
    private LinkedHashMap<String, EventDO> hashEvents;
    private Calendar calendar;

    public CalendarGridAdapter(Context context, LinkedHashMap<String, EventDO> hashEvents) {
        this.context=context;
        this.hashEvents = hashEvents;
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,1);
    }

    public void refresh(LinkedHashMap<String,EventDO> hashEvents) {
        this.hashEvents = hashEvents;
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,1);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_cell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        switch(position){
            case 0:
                holder.tvDate.setText("S");
                holder.ivDot.setVisibility(View.GONE);
                break;
            case 1:
                holder.tvDate.setText("M");
                holder.ivDot.setVisibility(View.GONE);
                break;
            case 2:
                holder.tvDate.setText("T");
                holder.ivDot.setVisibility(View.GONE);
                break;
            case 3:
                holder.tvDate.setText("W");
                holder.ivDot.setVisibility(View.GONE);
                break;
            case 4:
                holder.tvDate.setText("T");
                holder.ivDot.setVisibility(View.GONE);
                break;
            case 5:
                holder.tvDate.setText("F");
                holder.ivDot.setVisibility(View.GONE);
                break;
            case 6:
                holder.tvDate.setText("S");
                holder.ivDot.setVisibility(View.GONE);
                break;
            default:
                getCalendarDate(holder.tvDate, holder.ivDot, position%7);
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void getCalendarDate(TextView tvDate,ImageView ivDot, int position){
        LogUtils.debug("test_check",""+calendar.get(Calendar.DAY_OF_MONTH));
        switch(position){
            case Calendar.MONDAY:
                setdate(tvDate,ivDot);
                break;
            case Calendar.TUESDAY:
                setdate(tvDate,ivDot);
                break;
            case Calendar.WEDNESDAY:
                setdate(tvDate,ivDot);
                break;
            case Calendar.THURSDAY:
                setdate(tvDate,ivDot);
                break;
            case Calendar.FRIDAY:
                setdate(tvDate,ivDot);
                break;
            case Calendar.SATURDAY:
                setdate(tvDate,ivDot);
                break;
            case Calendar.SUNDAY:
                setdate(tvDate,ivDot);
                break;
            default:
                tvDate.setText("");
                ivDot.setVisibility(View.GONE);
        }
    }

    private void setdate(TextView tvDate,ImageView ivDot){
        tvDate.setText(""+calendar.get(Calendar.DAY_OF_MONTH));
        ivDot.setVisibility(View.GONE);
        calendar.add(Calendar.DAY_OF_MONTH,1);
    }

    @Override
    public int getItemCount() {
        if(calendar != null) {
            return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvDate;
        public final ImageView ivDot;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvDate = (TextView) view.findViewById(R.id.tvDate);
            ivDot = (ImageView) view.findViewById(R.id.ivDot);
        }

        @Override
        public String toString() {
            return "";
        }
    }
}
