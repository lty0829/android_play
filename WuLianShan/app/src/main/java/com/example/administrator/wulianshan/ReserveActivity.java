package com.example.administrator.wulianshan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2016/8/5.
 */
public class ReserveActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservelayout);
    }
    public void doclick(View v){
        Intent intent1=new Intent(ReserveActivity.this,MainPage.class);
        switch (v.getId()){
            case R.id.yu_trick:
                Bundle bundle = new Bundle();
                bundle.putInt("commont_cout", 41);
                intent1.putExtra("myBundle", bundle);
                startActivity(intent1);
                break;
            case R.id.yu_wine:
                Bundle bundle1 = new Bundle();
                bundle1.putInt("commont_cout",42);
                intent1.putExtra("myBundle",bundle1);
                startActivity(intent1);
                break;
        }
    }
}
