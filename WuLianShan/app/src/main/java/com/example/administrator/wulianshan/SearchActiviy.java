package com.example.administrator.wulianshan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/8/9.
 */
public class SearchActiviy extends Activity {
    private SearchView search_edit;
    private ListView listView;
    private List<Map<String,Object>> mData;
    private String matchlist[]={"九仙山a","五莲山b","光明寺c","莲山大佛d"};
    private String searchtext;
    private Runnable eChanged;
    private SimpleAdapter adapter;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchlayout);
        search_edit= (SearchView) findViewById(R.id.search_search);
        mData=new ArrayList<Map<String, Object>>();
        search_edit.setIconifiedByDefault(false);
        search_edit.setSubmitButtonEnabled(true);
        handler=new Handler();
        search_edit.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length()!=0) {
                    searchtext = newText;

                }
                else{
                    searchtext="";
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String data = searchtext;
                        mData.clear();
                        getmDataSub(data);
                        adapter.notifyDataSetChanged();
                    }
                });

                return false;
            }
        });
        set_mListView_adapter();
    }
    private void set_mListView_adapter()
    {
        listView= (ListView) findViewById(R.id.search_list);

        Map<String,Object>map=new HashMap<String,Object>();
        map.put("text","");
        mData.add(map);

        adapter=new SimpleAdapter(this,mData,android.R.layout.simple_list_item_1,
                new String[]{"text"},new int[]{android.R.id.text1});

        listView.setAdapter(adapter);
    }
    private void getmDataSub(String data)
    {
        int length = matchlist.length;
        if(data.length()==0){
            length=0;
        }
        for(int i = 0; i < length; ++i){
            if(matchlist[i].contains(data)){
                Map<String,Object> item = new HashMap<String,Object>();
                item.put("text",matchlist[i]);
                mData.add(item);
            }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent intent=new Intent(SearchActiviy.this,MainPage.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
