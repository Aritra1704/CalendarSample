package com.param.calendarproject.MonthCalendar;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.param.calendarproject.R;
import com.param.calendarproject.Utilities.LogUtils;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Aritra on 5/2/2016.
 */
public class CustomMonthCalendar extends LinearLayout {

    private Context context;
    private View view;
    private TextView dateTitle;
    private ImageView leftButton;
    private ImageView rightButton;
    private ViewPager vpCalendar;
    private Calendar currentCalendar;
    private Locale locale;

    private CalendarAdapter adapter;
    public static int MAX_PAGE = 24;
    private int currentMonth = 0;
    private List<String> events;

    public CustomMonthCalendar(Context context){
        super(context);
        this.context = context;

        onCreateView();
    }
    public CustomMonthCalendar(Context context, AttributeSet attr){
        super(context,attr);
        this.context = context;

        onCreateView();
    }

    public View onCreateView() {
        LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflate.inflate(R.layout.calendar_project, this, true);

        initialaliseControls(view);

        locale = context.getResources().getConfiguration().locale;
        currentCalendar = Calendar.getInstance(locale);
        currentCalendar.add(Calendar.MONTH,-12);
        currentMonth = currentCalendar.get(Calendar.MONTH) + 1;

        events = new ArrayList<>();
        events.add("3-5-2016");
        events.add("5-5-2016");
        events.add("6-5-2016");
        events.add("10-5-2016");
        events.add("20-5-2016");
        events.add("19-5-2016");
        events.add("19-6-2016");
        initializeCalendar();

        currentCalendar = Calendar.getInstance(locale);
        currentMonth = currentCalendar.get(Calendar.MONTH) + 1;
        vpCalendar.setCurrentItem(12);
        return view;
    }

    public void initializeCalendar() {

        // Set date title
        initializeTitleLayout();

        adapter = new CalendarAdapter(context, locale, this.currentCalendar,events);
        vpCalendar.setOffscreenPageLimit(1);
        vpCalendar.setAdapter(adapter);

        vpCalendar.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private static final float thresholdOffset = 0.5f;
            private boolean scrollStarted, checkDirection;
            private int scroll = 1;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (checkDirection) {
                    if (thresholdOffset > positionOffset) {
                        scroll = 1;
                    } else {
                        scroll = -1;
                    }
                    checkDirection = false;
                }
            }

            @Override
            public void onPageSelected(int position) {
                onMonthChanged(currentMonth+scroll);
                //adapter.refresh(currentCalendar);
                initializeTitleLayout();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (!scrollStarted && state == ViewPager.SCROLL_STATE_DRAGGING) {
                    scrollStarted = true;
                    checkDirection = true;
                } else {
                    scrollStarted = false;
                }
            }
        });

        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if((vpCalendar.getCurrentItem() - 1) > 0){
                    onMonthChanged(currentMonth-1);
                    //adapter.refresh(currentCalendar);
                    initializeTitleLayout();

                    vpCalendar.setCurrentItem(vpCalendar.getCurrentItem() - 1);
                }
            }
        });

        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if((vpCalendar.getCurrentItem() + 1) < MAX_PAGE){
                    onMonthChanged(currentMonth+1);
                    //adapter.refresh(currentCalendar);
                    initializeTitleLayout();

                    vpCalendar.setCurrentItem(vpCalendar.getCurrentItem() + 1);
                }
            }
        });
    }

    private void initializeTitleLayout() {
        int requiredMonth = currentCalendar.get(Calendar.MONTH)+vpCalendar.getCurrentItem();
        if(requiredMonth > 11)
            requiredMonth = requiredMonth % 12;

        String dateText = new DateFormatSymbols(locale).getMonths()[requiredMonth];
        dateText = dateText.substring(0, 1).toUpperCase() + dateText.subSequence(1, dateText.length());
        Calendar calendar = Calendar.getInstance();
        if (currentCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
            dateTitle.setText(dateText);
        } else {
            dateTitle.setText(String.format("%s %s", dateText, currentCalendar.get(Calendar.YEAR)));
        }
    }

    private void onMonthChanged(int position){
        /*if(position > currentMonth)
            currentCalendar.add(Calendar.MONTH,1);
        else
            currentCalendar.add(Calendar.MONTH,-1);*/

        currentMonth = position;
    }

    private void initialaliseControls(View view) {
        leftButton      = (ImageView) view.findViewById(R.id.leftButton);
        rightButton     = (ImageView) view.findViewById(R.id.rightButton);
        dateTitle       = (TextView) view.findViewById(R.id.dateTitle);
        vpCalendar      = (ViewPager) view.findViewById(R.id.vpCalendar);

    }
}
