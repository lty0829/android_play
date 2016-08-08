package com.example.administrator.wulianshan;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/27.
 */
public class Pagefragment extends Fragment implements MyScrollView.OnScrollListener {
    private MyHorizontalScrollView mHorizontalScrollView;
    private HorizontalScrollViewAdapter mAdapter;
    private ImageView[] tips;
    private ViewGroup group;
    private List<Integer> mDatas = new ArrayList<Integer>(Arrays.asList(
            R.drawable.map1, R.drawable.map2, R.drawable.map3, R.drawable.map4,
            R.drawable.map1, R.drawable.map2));
    private List<String> mText = new ArrayList<String>(Arrays.asList("莲山大佛","光明寺","五莲山","九仙山","莲山大佛","光明寺"));

    private MyHorizontalScrollView mHorizontalScrollView1;
    private HorizontalScrollViewAdapter mAdapter1;
    private ImageView[] tips1;
    private ViewGroup group1;
    private List<Integer> mDatas1= new ArrayList<Integer>(Arrays.asList(
            R.drawable.map1, R.drawable.map2, R.drawable.map3, R.drawable.map4,
            R.drawable.map1, R.drawable.map2));
    private List<String> mText1 = new ArrayList<String>(Arrays.asList("莲山大佛","光明寺","五莲山","九仙山","莲山大佛","光明寺"));

    private GridView gridView;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> dataList;

    private SearchView search_edit;
    private MyScrollView myScrollView;
    private int searchLayoutTop;
    private int gridTop;

    private boolean isMeasured;
    private boolean isMeasured1;
    private boolean high;
    private boolean low;
    LinearLayout search01,search02;
    RelativeLayout rlayout,rlayout1;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //layout布局文件转换成View对象

        /**
         * resource:Fragment需要加载的布局文件
         * root：加载layout的父ViewGroup
         * attactToRoot：false，不返回父ViewGroup
         */
        View view = inflater.inflate(R.layout.pagelayout, container, false);
        gridTop=2000;
        isMeasured=false;
        isMeasured1=false;
        high=true;
        low=false;
        srollview(view);
        initgrid(view);
        setGridViewHeightBasedOnChildren(gridView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        initEdit(view);
        return view;

    }
    private void initEdit(View view){
        search_edit = (SearchView) view.findViewById(R.id.find);
        search_edit.setIconifiedByDefault(false);
        search_edit.setSubmitButtonEnabled(true);
        search_edit.setQueryHint("搜景点、酒店和游记");
        myScrollView = (MyScrollView) view.findViewById(R.id.scroll);
        search01 = (LinearLayout)view.findViewById(R.id.search01);
        search02 = (LinearLayout)view.findViewById(R.id.search02);
        rlayout = (RelativeLayout) view.findViewById(R.id.rlayout);
        rlayout1 = (RelativeLayout) view.findViewById(R.id.rlayout1);
        ViewTreeObserver observer = rlayout.getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                if (!isMeasured) {
                    searchLayoutTop = rlayout.getBottom();
                    isMeasured = true;
                }
                return true;
            }
        });

        myScrollView.setOnScrollListener(this);
    }
    private void srollview(View view){
        group = (ViewGroup)view.findViewById(R.id.viewGroup);
        tips = new ImageView[mDatas.size()];
        for(int i=0; i<tips.length; i++){
            ImageView imageView = new ImageView(Pagefragment.this.getActivity());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(5,5));
            tips[i] = imageView;
            if(i == 0){
                tips[i].setBackgroundResource(R.drawable.press);
            }else{
                tips[i].setBackgroundResource(R.drawable.unpress);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            group.addView(imageView, layoutParams);
        }
        mHorizontalScrollView = (MyHorizontalScrollView) view.findViewById(R.id.id_horizontalScrollView);
        mAdapter = new HorizontalScrollViewAdapter(Pagefragment.this.getActivity(), mDatas,mText);
        mHorizontalScrollView
                .setCurrentImageChangeListener(new MyHorizontalScrollView.CurrentImageChangeListener()
                {
                    @Override
                    public void onCurrentImgChanged(int position,
                                                    View viewIndicator)
                    {
                        for(int i=0; i<tips.length; i++){
                            if(i == position){
                                tips[i].setBackgroundResource(R.drawable.press);
                            }else{
                                tips[i].setBackgroundResource(R.drawable.unpress);
                            }
                        }
                     //   viewIndicator.setBackgroundColor(Color
                      //          .parseColor("#AA024DA4"));
                    }
                });
        //添加点击回调
        mHorizontalScrollView.setOnItemClickListener(new MyHorizontalScrollView.OnItemClickListener()
        {

            @Override
            public void onClick(View view, int position)
            {
                for(int i=0; i<tips.length; i++){
                    if(i == position){
                        tips[i].setBackgroundResource(R.drawable.press);
                    }else{
                        tips[i].setBackgroundResource(R.drawable.unpress);
                    }
                }
              //  view.setBackgroundColor(Color.parseColor("#AA024DA4"));
            }
        });
        //设置适配器
        mHorizontalScrollView.initDatas(mAdapter);


        group1 = (ViewGroup)view.findViewById(R.id.viewGroup1);
        tips1 = new ImageView[mDatas1.size()];
        for(int i=0; i<tips1.length; i++){
            ImageView imageView = new ImageView(Pagefragment.this.getActivity());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(5,5));
            tips1[i] = imageView;
            if(i == 0){
                tips1[i].setBackgroundResource(R.drawable.press);
            }else{
                tips1[i].setBackgroundResource(R.drawable.unpress);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            group1.addView(imageView, layoutParams);
        }
        mHorizontalScrollView1 = (MyHorizontalScrollView) view.findViewById(R.id.id_horizontalScrollView1);
        mAdapter1 = new HorizontalScrollViewAdapter(Pagefragment.this.getActivity(), mDatas1,mText);
        mHorizontalScrollView1
                .setCurrentImageChangeListener(new MyHorizontalScrollView.CurrentImageChangeListener()
                {
                    @Override
                    public void onCurrentImgChanged(int position,
                                                    View viewIndicator)
                    {
                        for(int i=0; i<tips1.length; i++){
                            if(i == position){
                                tips1[i].setBackgroundResource(R.drawable.press);
                            }else{
                                tips1[i].setBackgroundResource(R.drawable.unpress);
                            }
                        }
                    //    viewIndicator.setBackgroundColor(Color
                     //           .parseColor("#AA024DA4"));
                    }
                });
        //添加点击回调
        mHorizontalScrollView1.setOnItemClickListener(new MyHorizontalScrollView.OnItemClickListener()
        {

            @Override
            public void onClick(View view, int position)
            {
                for(int i=0; i<tips1.length; i++){
                    if(i == position){
                        tips1[i].setBackgroundResource(R.drawable.press);
                    }else{
                        tips1[i].setBackgroundResource(R.drawable.unpress);
                    }
                }
              //  view.setBackgroundColor(Color.parseColor("#AA024DA4"));
            }
        });
        //设置适配器
        mHorizontalScrollView1.initDatas(mAdapter);
    }
    private void initgrid(View view){
        gridView=(GridView) view.findViewById(R.id.gridView);
        dataList=new ArrayList<Map<String,Object>>();
        adapter=new SimpleAdapter(Pagefragment.this.getActivity(), getData(), R.layout.item, new String[]{"pic","name"}, new int[]{R.id.pic,R.id.name});
        gridView.setAdapter(adapter);

    }
    private List<Map<String, Object>> getData() {

        int[] drawable = { R.drawable.grid1, R.drawable.grid2,
                R.drawable.grid3,R.drawable.grid1,R.drawable.grid2,
                R.drawable.grid3,R.drawable.grid2,R.drawable.grid2,
                R.drawable.grid3};
        String[] iconName = {"酒店住宿", "线路规划", "交通指南", "特色活动", "舌尖美食", "两山特产", "图片欣赏", "酷游须知",
                "大美日照"};
        for (int i = 0; i < drawable.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pic", drawable[i]);
            map.put("name", iconName[i]);
            dataList.add(map);
        }
        Log.i("Main", "size=" + dataList.size() + "sdsd" + drawable.length);
        return dataList;
    }
    public static void setGridViewHeightBasedOnChildren(GridView gridView) {
        // 获取GridView对应的Adapter
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
            }
        int rows;
        int columns = 0;
        int horizontalBorderHeight = 0;
        Class<?> clazz = gridView.getClass();
        try {
            // 利用反射，取得每行显示的个数
            Field column = clazz.getDeclaredField("mRequestedNumColumns");
            column.setAccessible(true);
            columns = (Integer) column.get(gridView);
            // 利用反射，取得横向分割线高度
            Field horizontalSpacing = clazz
            .getDeclaredField("mRequestedHorizontalSpacing");
            horizontalSpacing.setAccessible(true);
            horizontalBorderHeight = (Integer) horizontalSpacing.get(gridView);
            } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            }
        // 判断数据总数除以每行个数是否整除。不能整除代表有多余，需要加一行
        if (listAdapter.getCount() % columns > 0) {
            rows = listAdapter.getCount() / columns + 1;
            } else {
            rows = listAdapter.getCount() / columns;
            }
        int totalHeight = 0;
        for (int i = 0; i < rows; i++) { // 只计算每项高度*行数
            View listItem = listAdapter.getView(i, null, gridView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
            }
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight + horizontalBorderHeight * (rows - 1);// 最后加上分割线总高度
        gridView.setLayoutParams(params);
        }


    @Override
    public void onScroll(int scrollY) {
        if(scrollY >= searchLayoutTop){
            if (search_edit.getParent()!=search01) {
                search02.removeView(search_edit);
                search01.addView(search_edit);
                if (!isMeasured1) {
                    ViewTreeObserver observer1 = rlayout1.getViewTreeObserver();
                    observer1.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        public boolean onPreDraw() {
                            if (!isMeasured1) {
                                gridTop = rlayout1.getTop();
                                isMeasured1 = true;
                            }
                            return true;
                        }
                    });
                }
            }
        }else{
            if (search_edit.getParent()!=search02) {
                search01.removeView(search_edit);
                search02.addView(search_edit);
            }
        }
        if(scrollY>=gridTop&&high){
            search_edit.setBackgroundColor(Color.GREEN);
            high=false;
            low=true;
        }
        else if(scrollY<gridTop&&low){
            search_edit.setBackgroundColor(Color.WHITE);
            high=true;
            low=false;
        }

    }
}
