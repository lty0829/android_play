package com.example.administrator.wulianshan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/8/6.
 */
public class TrickActivity extends Activity implements View.OnClickListener{
    private Button back,add1,reduce1;
    private CalendarView calendarView1;
    private TextView textView1,tricksize1,data1,tricksize2,data2;
    private ToggleButton zhifu;
    private View v;
    private LinearLayout layout;
    private CheckBox checkBox1,checkBox2;
    int tyear,tmonth,tday;
    int text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tricklayout);
        back= (Button) findViewById(R.id.backact1);
        add1= (Button) findViewById(R.id.add_button1);
        reduce1= (Button) findViewById(R.id.reduce_button1);
        zhifu= (ToggleButton) findViewById(R.id.zhifu);
        textView1= (TextView) findViewById(R.id.textsize1);
        calendarView1= (CalendarView) findViewById(R.id.calendar1);
        layout= (LinearLayout) findViewById(R.id.linearLayout);
        v= LayoutInflater.from(this).inflate(R.layout.trickpay, null);
        checkBox1= (CheckBox) v.findViewById(R.id.radio_wu);
        checkBox2= (CheckBox) v.findViewById(R.id.radio_hua);
        tricksize1= (TextView) v.findViewById(R.id.trick_size1);
        tricksize2= (TextView) v.findViewById(R.id.trick_size2);
        data1= (TextView) v.findViewById(R.id.trick_data1);
        data2=(TextView)v.findViewById(R.id.trick_data2);
        text1=0;
        Calendar c = Calendar.getInstance();
        tyear = c.get(Calendar.YEAR);
        tmonth = c.get(Calendar.MONTH)+1;
        tday = c.get(Calendar.DAY_OF_MONTH);
        back.setOnClickListener(this);
        add1.setOnClickListener(this);
        reduce1.setOnClickListener(this);
        calendarView1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                tyear = year;
                tmonth = month + 1;
                tday = dayOfMonth;
            }
        });
        zhifu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked && v.getParent() == layout) {
                    layout.removeView(v);
                } else if (isChecked && v.getParent() != layout) {
                    layout.addView(v);
                    if(text1>0){
                        checkBox1.setChecked(true);
                    }
                    tricksize1.setText("X"+text1);
                    data1.setText(tyear+"-"+tmonth+"-"+tday);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent1=new Intent(TrickActivity.this,MainPage.class);
        switch (v.getId()){
            case R.id.backact1:
                Bundle bundle = new Bundle();
                bundle.putInt("commont_cout", 41);
                intent1.putExtra("myBundle", bundle);
                startActivity(intent1);
                finish();
                break;
            case R.id.add_button1:
                text1++;
                textView1.setText("预定 "+text1);
                break;
            case R.id.reduce_button1:
                if(text1>0) {
                    text1--;
                    textView1.setText("预定 " + text1);
                }
                break;

        }
    }
}
