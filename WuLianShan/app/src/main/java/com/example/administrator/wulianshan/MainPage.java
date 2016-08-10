package com.example.administrator.wulianshan;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by Administrator on 2016/7/26.
 */
public class MainPage extends Activity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup group;
    private RadioButton first;
    private RadioButton second;
    private RadioButton third;
    private RadioButton fourth;
    private RadioButton fifth;
 //   FragmentManager fragmentManager;
 //   FragmentTransaction beginTransaction;
    Pagefragment pagefragment;
    Bugfragment bugfragment;
    Myfragment myfragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        group = (RadioGroup) findViewById(R.id.radiogroup);
        first= (RadioButton) findViewById(R.id.first);
        second= (RadioButton) findViewById(R.id.second);
        third= (RadioButton) findViewById(R.id.third);
        fourth= (RadioButton) findViewById(R.id.fourth);
        fifth= (RadioButton) findViewById(R.id.fifth);
        first.setChecked(true);
        pagefragment=new Pagefragment();
        bugfragment =new Bugfragment();
        myfragment=new Myfragment();

        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("myBundle");
        if(bundle==null) {

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            beginTransaction.add(R.id.frame, pagefragment);
            beginTransaction.addToBackStack(null);
            beginTransaction.commit();

        }
        else{
            if((int)bundle.getInt("commont_cout")/10==4){
                fourth.setChecked(true);
                ABCFragment abcFragment=new ABCFragment();
                Bundle fragment=new Bundle();
                fragment.putInt("commont_cout",(int)bundle.getInt("commont_cout")%10);
                abcFragment.setArguments(fragment);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.add(R.id.frame, abcFragment);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
            }
            if((int)bundle.getInt("commont_cout")/10==5){
                fifth.setChecked(true);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.replace(R.id.frame, myfragment);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
            }
        }
        group.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.first: {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.replace(R.id.frame, pagefragment);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
            }
            case R.id.second: {
                Intent intent =new Intent();
                intent.setClass(MainPage.this, ReserveActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.third: {
                Intent intent =new Intent();
                intent.setClass(MainPage.this,TripActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.fourth: {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.replace(R.id.frame, bugfragment);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
            }
            case R.id.fifth: {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.replace(R.id.frame, myfragment);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
            }

        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            ExitDialog(MainPage.this).show();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    private Dialog ExitDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
       // builder.setIcon(R.drawable.icon);
        builder.setTitle("系统信息");
        builder.setMessage("确定要退出程序吗?");
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();
                    }
                });
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
        return builder.create();
    }
}
