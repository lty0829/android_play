package com.example.administrator.wulianshan;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2016/8/8.
 */
public class TripActivity extends Activity {
    private ListView listView;
    private SearchView searchView;
    private List<Map<String,Object>> listdata,griddata;
    private MySimpleAdapter mySimpleAdapter;
    private String username []={"小黄","皇儿"};
    private String usercom []={"美不胜收，不虚此行","美不胜收，不虚此行"};
    private SimpleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.triplayout);
        searchView= (SearchView) findViewById(R.id.search_view_trip);
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("搜游记");
        listView= (ListView) findViewById(R.id.trip_list);

        griddata=new ArrayList<Map<String,Object>>();
       adapter=new SimpleAdapter(this, getData(), R.layout.item1, new String[]{"pic1"}, new int[]{R.id.pic1});
        listdata=new ArrayList<Map<String,Object>>();
        for(int i=0;i<username.length;i++){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("user_head_view",R.drawable.map1);
            map.put("user_name",username[i]);
            map.put("user_phone","华为"+i);
            map.put("user_commont",usercom[i]);
            listdata.add(map);
        }
        mySimpleAdapter=new MySimpleAdapter(this,listdata, R.layout.commont,new String[] { "user_head_view", "user_name", "user_phone", "user_commont"},
                new int[] {R.id.user_head_view,R.id.user_name,R.id.user_phone,R.id.user_commont});
        listView.setAdapter(mySimpleAdapter);


    }
    private List<Map<String, Object>> getData() {

        int[] drawable = { R.drawable.grid1, R.drawable.grid2};
        for (int i = 0; i < drawable.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pic1",drawable[i]);
            griddata.add(map);
        }
        return griddata;
    }
    public class MySimpleAdapter extends SimpleAdapter{

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
        public MySimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = super.getView(position, convertView, parent);
            GridView gridView= (GridView) v.findViewById(R.id.user_grid);

            gridView.setAdapter(adapter);


            return v;
        }
    }
}
