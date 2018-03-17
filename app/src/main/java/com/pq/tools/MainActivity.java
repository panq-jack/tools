package com.pq.tools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pq.toolslibrary.DPIUtil;
import com.pq.toolslibrary.MetadataUtil;
import com.pq.toolslibrary.PackageInfoUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkVersion(View view){
        Bundle bundle=new Bundle();
        bundle.putString("msg",PackageInfoUtil.demo(App.getInstance()));
        bundle.putString("title","查询版本号");
        Intent intent=new Intent(this,CommonActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void transform(View view){
        Bundle bundle=new Bundle();
        bundle.putString("msg", DPIUtil.demo(App.getInstance()));
        bundle.putString("title","单位换算示例");
        Intent intent=new Intent(this,CommonActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void searchmetadata(View view){
        Bundle bundle=new Bundle();
        bundle.putString("title","MetaData查询示例");
        bundle.putString("msg", MetadataUtil.demo(App.getInstance().getApplicationContext()));
        Intent intent=new Intent(this,CommonActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
