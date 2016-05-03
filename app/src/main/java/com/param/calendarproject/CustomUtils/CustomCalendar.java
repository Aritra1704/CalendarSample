package com.param.calendarproject.CustomUtils;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.param.calendarproject.Adapter.CalendarGridAdapter;
import com.param.calendarproject.DataObject.EventDO;
import com.param.calendarproject.R;

import java.util.LinkedHashMap;

/**
 * Created by Aritra on 4/25/2016.
 */
public class CustomCalendar extends Fragment {

    RecyclerView recyclerView;
    private CalendarGridAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.calendar_view, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.calendar_grid);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 7));

        adapter = new CalendarGridAdapter(getActivity(),new LinkedHashMap<String, EventDO>());
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}
