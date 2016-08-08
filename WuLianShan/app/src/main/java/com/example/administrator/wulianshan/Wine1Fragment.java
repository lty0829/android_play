package com.example.administrator.wulianshan;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/7.
 */
public class Wine1Fragment extends Fragment {
    private TextView start,end;
    private ToggleButton yuding;
    private List<Map<String,Object>> data;
    private CalendarView calendarView;
    private int maptag;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //layout布局文件转换成View对象

        /**
         * resource:Fragment需要加载的布局文件
         * root：加载layout的父ViewGroup
         * attactToRoot：false，不返回父ViewGroup
         */
        View view = inflater.inflate(R.layout.wine1fragment, container, false);
        yuding= (ToggleButton) view.findViewById(R.id.data_yuding1);
        start= (TextView) view.findViewById(R.id.data_start);
        end= (TextView) view.findViewById(R.id.data_end);
        calendarView= (CalendarView) view.findViewById(R.id.calendar2);
        maptag=0;
        data=new ArrayList<Map<String,Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("year",0);
        map.put("month",0);
        map.put("day",0);
        data.add(map);
        data.add(map);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("year", year);
                map.put("month", month + 1);
                map.put("day", dayOfMonth);
                data.set(maptag, map);
            }
        });
        yuding.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    end.setText(data.get(maptag).get("year") + "-" + data.get(maptag).get("month")+"-"+data.get(maptag).get("day"));
                    maptag = 0;
                } else {
                    start.setText(data.get(maptag).get("year") + "-" + data.get(maptag).get("month")+"-"+data.get(maptag).get("day"));
                    maptag = 1;
                }
            }
        });
        return view;
    }
    public List<Map<String,Object>> getlist(){
        return data;
    }
}
