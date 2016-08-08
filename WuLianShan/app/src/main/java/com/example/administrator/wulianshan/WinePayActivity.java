package com.example.administrator.wulianshan;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;
import java.util.Map;

import static com.example.administrator.wulianshan.R.id.linearLayout1;

/**
 * Created by Administrator on 2016/8/7.
 */
public class WinePayActivity extends Activity {
    private Wine1Fragment wine1Fragment;
    private List<Map<String,Object>> data;
    private ToggleButton toggleButton;
    private LinearLayout linearLayout;
    private CheckBox checkBox1,checkBox2;
    private TextView winesize1,mon1,winesize2,mon2;
    private View v;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winepaylayout);
        toggleButton = (ToggleButton) findViewById(R.id.winezhifu);
        linearLayout= (LinearLayout) findViewById(linearLayout1);
        v= LayoutInflater.from(this).inflate(R.layout.winepay, null);
        checkBox1= (CheckBox) v.findViewById(R.id.jiudain1);
        checkBox2= (CheckBox) v.findViewById(R.id.jiudain1);
        winesize1= (TextView) v.findViewById(R.id.wine_size1);
        winesize2= (TextView) v.findViewById(R.id.wine_size2);
        mon1= (TextView) v.findViewById(R.id.wine_mon1);
        mon2=(TextView)v.findViewById(R.id.wine_mon2);
        Intent intent=getIntent();
        bundle=intent.getBundleExtra("myBundle");
        if(bundle.getInt("commont_cout")==0){
            wine1Fragment=new Wine1Fragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            beginTransaction.add(R.id.frame1, wine1Fragment);
            beginTransaction.addToBackStack(null);
            beginTransaction.commit();

        }
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(bundle.getInt("commont_cout")==0) {
                    data=wine1Fragment.getlist();
                    Log.e("info","time"+data.get(0).get("year")+"-"+
                            data.get(0).get("month")+"-"+data.get(0).get("day"));
                }
                if (!isChecked && v.getParent() == linearLayout) {
                    linearLayout.removeView(v);
                } else if (isChecked && v.getParent() != linearLayout) {
                    linearLayout.addView(v);
                    if(data.get(0).get("year")!=0){
                        checkBox1.setChecked(true);
                    }
                    winesize1.setText(data.get(0).get("month")+"月"+data.get(0).get("day")+
                            "日"+" - "+data.get(1).get("month")+"月"+data.get(1).get("day")+"日");
                    mon1.setText("XX元");
                }
            }
        });
    }
}
