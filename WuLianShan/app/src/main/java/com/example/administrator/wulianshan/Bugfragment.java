package com.example.administrator.wulianshan;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/27.
 */
public class Bugfragment extends Fragment{

    private SearchView searchView;
    private GridView gridView,gridViewpicture;
    private List<Map<String,Object>> grid,gridpicture;
    private SimpleAdapter simpleAdapter,simpleAdapterpicture;
    private String buyname[]={"abc","abc","abc","abc","abc","abc","abc","abc","abc","abc","abc","abc",
            "abc","abc","abc","abc","abc","abc","abc","abc"};
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //layout布局文件转换成View对象

        /**
         * resource:Fragment需要加载的布局文件
         * root：加载layout的父ViewGroup
         * attactToRoot：false，不返回父ViewGroup
         */
        View view = inflater.inflate(R.layout.buylayout, container, false);
        searchView= (SearchView) view.findViewById(R.id.search_view_buy);
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("");
        gridView= (GridView) view.findViewById(R.id.buy_name);
        grid =  new ArrayList<Map<String,Object>>();
        for(int i=0;i<buyname.length;i++){
            Map<String,Object>map=new HashMap<String,Object>();
            map.put("name1",buyname[i]);
            grid.add(map);
        }
        simpleAdapter=new SimpleAdapter(Bugfragment.this.getActivity(),grid, R.layout.item2, new String[]{"name1"}, new int[]{R.id.name1});
        gridView.setAdapter(simpleAdapter);

        return view;

    }
}
