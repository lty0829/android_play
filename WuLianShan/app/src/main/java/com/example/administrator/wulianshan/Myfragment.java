package com.example.administrator.wulianshan;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2016/7/27.
 */
public class Myfragment extends Fragment {

    private List<String>  group_list;
    private List<List<Map<String,Object>>> child_map;
    private ExpandableListView expandableListView;
    private MyExpandableListViewAdapter adapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //layout布局文件转换成View对象

        /**
         * resource:Fragment需要加载的布局文件
         * root：加载layout的父ViewGroup
         * attactToRoot：false，不返回父ViewGroup
         */
        View view = inflater.inflate(R.layout.mylayout, container, false);
        group_list = new ArrayList<String>();
        group_list.add("我的足迹");
        group_list.add("我的预定");
        group_list.add("1");
        group_list.add("我的好友");
        group_list.add("好友动态");
        group_list.add("1");
        group_list.add("设置");
        child_map=new ArrayList<List<Map<String,Object>>>();


        for(int i=0;i<7;i++){
            if(i==3){
                List<Map<String,Object>> childchild_map= new ArrayList<Map<String,Object>>();
                Map<String,Object> map= new HashMap<String,Object>();
                map.put("head",R.drawable.trick);
                map.put("name", "小黄");
                childchild_map.add(map);
                child_map.add(childchild_map);
                Log.e("info", "child:" + childchild_map.size() + " sd " + child_map.size()+","+group_list.size());
            }
            else{
                List<Map<String,Object>> childchild_map= new ArrayList<Map<String,Object>>();
                child_map.add(childchild_map);
                Log.e("info", "child:" + childchild_map.size() + " sd " + child_map.size());
            }
        }
        expandableListView= (ExpandableListView) view.findViewById(R.id.expandable_list_view);
        expandableListView.setDivider(null);
        adapter = new MyExpandableListViewAdapter(Myfragment.this.getActivity());
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if(group_list.get(groupPosition).equals("设置")){
                    Intent intent = new Intent(Myfragment.this.getActivity(),SetActivity.class);
                    startActivity(intent);

                }
                return false;
            }
        });
        return view;

    }

    public class MyExpandableListViewAdapter extends BaseExpandableListAdapter {
        private Context context;

        public MyExpandableListViewAdapter(Context context)
        {
            this.context = context;
        }
        @Override
        public int getGroupCount() {
            return group_list.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return child_map.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return group_list.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return child_map.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

            if(group_list.get(groupPosition).equals("1")){
                convertView = LayoutInflater.from(context).inflate(R.layout.addexam_list_item_tag, null);
                return convertView;
            }
            else{
                GroupHolder groupHolder = null;
                convertView = LayoutInflater.from(context).inflate(R.layout.addexam_list_item, null);
                groupHolder = new GroupHolder();
                groupHolder.txt = (TextView)convertView.findViewById(R.id.addexam_list_item_text);
                groupHolder.img = (ImageView)convertView.findViewById(R.id.addexam_list_icon);
                groupHolder.tri=(ImageView)convertView.findViewById(R.id.la_shen);
                if (!isExpanded)
                {
                    groupHolder.tri.setBackgroundResource(R.drawable.trick);
                }
                else
                {
                    groupHolder.tri.setBackgroundResource(R.drawable.untrick);
                }
                groupHolder.txt.setText(group_list.get(groupPosition));

            }
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if(groupPosition==3) {
                NameHolder nameHolder=null;
                if (convertView == null) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.addexam_child_name, null);
                    nameHolder = new NameHolder();
                    nameHolder.txt = (TextView)convertView.findViewById(R.id.addexam_name_item_text);
                    nameHolder.img = (ImageView)convertView.findViewById(R.id.addexam_name_icon);
                    nameHolder.button= (Button) convertView.findViewById(R.id.addexam_name_button);
                    convertView.setTag(nameHolder);
                } else {
                    nameHolder = (NameHolder)convertView.getTag();
                }
               nameHolder.img.setBackgroundResource((Integer)child_map.get(groupPosition).get(childPosition).get("head"));
               nameHolder.txt.setText((String) child_map.get(groupPosition).get(childPosition).get("name"));
            }
            else{
                convertView=null;
            }
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
        class GroupHolder
        {
            public TextView txt;

            public ImageView img;
            public ImageView tri;
        }
        class NameHolder{
            public TextView txt;
            public ImageView img;
            public Button button;
        }

    }
}
