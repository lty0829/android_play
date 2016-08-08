package com.example.administrator.wulianshan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/7.
 */
public class WineActivity extends Activity {
    private MyHorizontalScrollView mHorizontalScrollView;
    private HorizontalScrollViewAdapter mAdapter;
    private ImageView[] tips;
    private List<Integer> mDatas = new ArrayList<Integer>(Arrays.asList(
            R.drawable.map1, R.drawable.map2));
    private List<String> mText = new ArrayList<String>(Arrays.asList("abc","bcd"));
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winelayuot);
        srollview();
    }
    private void srollview(){
        mHorizontalScrollView = (MyHorizontalScrollView)findViewById(R.id.id_horizontalScrollView2);
        mAdapter = new HorizontalScrollViewAdapter(this, mDatas,mText);
        back= (Button) findViewById(R.id.backact2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(WineActivity.this,MainPage.class);
                Bundle bundle = new Bundle();
                bundle.putInt("commont_cout", 42);
                intent1.putExtra("myBundle", bundle);
                startActivity(intent1);
                finish();
            }
        });
        mHorizontalScrollView.setOnItemClickListener(new MyHorizontalScrollView.OnItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                if(pos==0){
                    Intent intent=new Intent(WineActivity.this,WinePayActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("commont_cout", pos);
                    intent.putExtra("myBundle", bundle);
                    startActivity(intent);
                }
            }
        });
        mHorizontalScrollView.initDatas(mAdapter);
    }

}
