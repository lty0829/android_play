package com.example.administrator.wulianshan;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Administrator on 2016/8/5.
 */
public class ABCFragment extends Fragment {
    private int tag;
    private Button yuding;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.abclayout, container, false);
        yuding= (Button) view.findViewById(R.id.yu_button);
        Bundle fragment = getArguments();
        tag=fragment.getInt("commont_cout");
        yuding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tag==1){
                    Intent intent = new Intent(ABCFragment.this.getActivity(),TrickActivity.class);
                    ABCFragment.this.getActivity().startActivity(intent);
                }
                else if(tag==2){
                    Intent intent = new Intent(ABCFragment.this.getActivity(),WineActivity.class);
                    ABCFragment.this.getActivity().startActivity(intent);
                }
            }
        });

        return view;
    }
}
