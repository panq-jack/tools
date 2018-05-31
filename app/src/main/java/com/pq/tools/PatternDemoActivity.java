package com.pq.tools;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.pq.toolslibrary.algorithm.FindUtil;
import com.pq.toolslibrary.algorithm.SortUtil;
import com.pq.toolslibrary.pattern.produceconsume.Patter1;

import java.util.Random;


/**
 * Created by pan on 2018/4/29.
 * 设计模式
 */

public class PatternDemoActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern);

        findViewById(R.id.btn_produce_consume_1).setOnClickListener(this);
        findViewById(R.id.btn_produce_consume_2).setOnClickListener(this);
        findViewById(R.id.btn_produce_consume_3).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_produce_consume_1:
                Patter1.demo();
                break;

            default:
                break;
        }
    }

}
