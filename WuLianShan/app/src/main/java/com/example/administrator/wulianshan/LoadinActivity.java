package com.example.administrator.wulianshan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/8/10.
 */
public class LoadinActivity extends Activity implements View.OnClickListener{
    Button back,loadin;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadinlayout);
        back= (Button) findViewById(R.id.backact4);
        loadin=(Button)findViewById(R.id.loadin_btton);
        username= (EditText) findViewById(R.id.username_ex);
        password=(EditText)findViewById(R.id.password_ex);
        back.setOnClickListener(this);
        loadin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backact4:
                finish();
                break;
            case R.id.loadin_btton:
                Intent intent=new Intent(LoadinActivity.this,MainPage.class);
                startActivity(intent);
                finish();
        }
    }
}
