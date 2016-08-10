package com.example.administrator.wulianshan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/9.
 */
public class SetActivity extends Activity {
    private Button back;
    private ListView setlist;
    private List<Map<String,Object>> setdata;
    private int head[]={R.drawable.map1,R.drawable.map1,R.drawable.map1,R.drawable.map1};
    private String name[]={"消息推送","清除缓存","检查更新","意见反馈"};
    private MySimpleAdapter mySimpleAdapter;
    private LinearLayout linearLayout;
    private View postv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setlayout);
        back = (Button) findViewById(R.id.backact3);
        linearLayout= (LinearLayout) findViewById(R.id.linearLayout2);
        postv= LayoutInflater.from(this).inflate(R.layout.advice, null);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(SetActivity.this,MainPage.class);
                Bundle bundle = new Bundle();
                bundle.putInt("commont_cout", 50);
                intent1.putExtra("myBundle", bundle);
                startActivity(intent1);
                finish();
            }
        });
        setlist= (ListView) findViewById(R.id.set_list);
        setdata=new ArrayList<Map<String,Object>>();
        for(int i=0;i<name.length;i++){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("set_img",head[i]);
            map.put("set_name",name[i]);
            setdata.add(map);
        }
        mySimpleAdapter = new MySimpleAdapter(this,setdata,R.layout.item3,new String[]{"set_img","set_name"},
                new int[]{R.id.set_img,R.id.set_name});
        setlist.setAdapter(mySimpleAdapter);
    }
    public class MySimpleAdapter extends SimpleAdapter {

        /**
         * Constructor
         *
         * @param context  The context where the View associated with this SimpleAdapter is running
         * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
         *                 Maps contain the data for each row, and should include all the entries specified in
         *                 "from"
         * @param resource Resource identifier of a view layout that defines the views for this list
         *                 item. The layout file should include at least those named views defined in "to"
         * @param from     A list of column names that will be added to the Map associated with each
         *                 item.
         * @param to       The views that should display column in the "from" parameter. These should all be
         *                 TextViews. The first N views in this list are given the values of the first N columns
         */
        private Context context;
        public MySimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
            this.context=context;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View v = super.getView(position, convertView, parent);
            final ToggleButton toggleButton= (ToggleButton) v.findViewById(R.id.set_toggle);
            toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(position==0&&isChecked){
                        AlertDialog.Builder builder=new AlertDialog.Builder(context);
                        builder.setTitle("提示");
                        builder.setMessage("你确定打开消息推送吗");
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                toggleButton.setChecked(false);
                            }
                        });
                        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        AlertDialog dialog=builder.create();
                        dialog.show();
                    }
                    else if(position==3&&isChecked&&postv.getParent()==null){
                        linearLayout.addView(postv);
                    }
                    else if(position==3&&!isChecked&&postv.getParent()!=null){
                        linearLayout.removeView(postv);
                    }
                }
            });


            return v;
        }
    }
}
