package com.pq.tools;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pq.toolslibrary.pattern.deadlock.DeadLock;
import com.pq.toolslibrary.pattern.deadlock.DeadLock2;
import com.pq.toolslibrary.pattern.produceconsume.first.Patter1;
import com.pq.toolslibrary.pattern.produceconsume.first.Pattern2;
import com.pq.toolslibrary.pattern.produceconsume.first.Pattern3;


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
        findViewById(R.id.btn_deallock_1).setOnClickListener(this);
        findViewById(R.id.btn_deallock_2).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_produce_consume_1:
                Patter1.demo();
                break;

            case R.id.btn_produce_consume_2:
                Pattern2.demo();
                break;

            case R.id.btn_produce_consume_3:
                Pattern3.demo();
                break;

            case R.id.btn_deallock_1:
                simulateDeadLock1();
                break;

            case R.id.btn_deallock_2:
                simulateDeadLock2();
                break;

            default:
                break;
        }
    }

    /**
     * 死锁：
     */
    private void simulateDeadLock1(){
        new Thread(new DeadLock(DeadLock.fork1)).start();
        new Thread(new DeadLock(DeadLock.fork2)).start();
    }

    /**
     * 死锁： lock解决死锁问题
     */
    private void simulateDeadLock2(){
        new Thread(new DeadLock2(DeadLock2.fork1)).start();
        new Thread(new DeadLock2(DeadLock2.fork2)).start();
    }
}
