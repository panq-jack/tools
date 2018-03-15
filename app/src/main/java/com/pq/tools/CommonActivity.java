package com.pq.tools;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * Created by panqian on 2018/3/15.
 */

public class CommonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar=getSupportActionBar();


        TextView textView=new TextView(this);
        StringBuilder stringBuilder=new StringBuilder();
        if (null!=getIntent().getExtras()){
            Bundle bundle=getIntent().getExtras();
            if (bundle.containsKey("title")){
                actionBar.setTitle(bundle.getString("title"));
            }

            if (bundle.containsKey("msg")){
                stringBuilder.append(getIntent().getStringExtra("msg"));
            }

        }


        String text=stringBuilder.toString();
        if (TextUtils.isEmpty(text)){
            text="暂无数据";
        }
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
        setContentView(textView);
    }
}
