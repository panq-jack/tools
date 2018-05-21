package com.pq.tools;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.pq.toolslibrary.algorithm.FindUtil;
import com.pq.toolslibrary.algorithm.SortUtil;

import java.util.Random;


/**
 * Created by pan on 2018/4/29.
 */

public class AlgoDemoActivity extends AppCompatActivity implements View.OnClickListener {

    TextView contentView;
    StringBuilder stringBuilder=new StringBuilder();
    int[] arrays=null;
//    int[] originArrays=null;  //保留未排序前的数组
    int value;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algo);

        contentView = (TextView) findViewById(R.id.tv_text);
        findViewById(R.id.btn_random_array).setOnClickListener(this);
        findViewById(R.id.btn_random_value).setOnClickListener(this);

        findViewById(R.id.btn_sort_bubble1).setOnClickListener(this);
        findViewById(R.id.btn_sort_bubble2).setOnClickListener(this);
        findViewById(R.id.btn_sort_bubble3).setOnClickListener(this);


        findViewById(R.id.btn_sort_selection).setOnClickListener(this);
        findViewById(R.id.btn_sort_insertion).setOnClickListener(this);

        findViewById(R.id.btn_sort_shell).setOnClickListener(this);
        findViewById(R.id.btn_sort_quick).setOnClickListener(this);
        findViewById(R.id.btn_sort_quick2).setOnClickListener(this);




        findViewById(R.id.btn_find_sequense).setOnClickListener(this);
        findViewById(R.id.btn_find_binary_loop).setOnClickListener(this);
        findViewById(R.id.btn_find_binary_recursive).setOnClickListener(this);
        findViewById(R.id.btn_find_insertion_loop).setOnClickListener(this);
        findViewById(R.id.btn_find_insertion_recursive).setOnClickListener(this);
        findViewById(R.id.btn_find_fibonacci_loop).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_random_array:
                stringBuilder.append("产生随机数组:  ").append("\t");
                Random random = new Random();
                int size = random.nextInt(100);
                stringBuilder.append("数组大小: "+size).append("\n");
                stringBuilder.append("[  ");
                arrays = new int[size];
                for (int i=0; i<size; ++i){
                    arrays[i] = random.nextInt(100);
                    stringBuilder.append(arrays[i]).append("\t").append("\t");
                }
                stringBuilder.append("  ]").append("\n");
                contentView.setText(stringBuilder.toString());
                break;
            case R.id.btn_random_value:
                value = new Random().nextInt(100);
                if (stringBuilder.indexOf("生成的待查找数值是: ")==-1){
                    stringBuilder.append(String.format("生成的待查找数值是: %d",value)).append("\n");
                }else {
                    int startIndex = stringBuilder.indexOf("生成的待查找数值是: ")+"生成的待查找数值是: ".length();
                    int endIndex=stringBuilder.indexOf("\n",startIndex);
                    stringBuilder.replace(startIndex,endIndex,value+"");
                }
                contentView.setText(stringBuilder.toString());

                break;

            case R.id.btn_sort_bubble1:
            {
                long startTime = System.nanoTime();
                SortUtil.sort_bubble_1(arrays);
                long endTime = System.nanoTime();
                stringBuilder.append("冒泡排序--上浮  成功 ").append("     耗时： "+ns2ms(endTime-startTime)).append("\t")
                .append("排序后的数组为： ").append("\n");
                printArrays();
                contentView.setText(stringBuilder.toString());
            }
                break;

            case R.id.btn_sort_bubble2:
            {
                long startTime = System.nanoTime();
                SortUtil.sort_bubble_2(arrays);
                long endTime = System.nanoTime();
                stringBuilder.append("冒泡排序-下沉  成功 ").append("     耗时： "+ns2ms(endTime-startTime)).append("\t")
                        .append("排序后的数组为： ").append("\n");
                printArrays();
                contentView.setText(stringBuilder.toString());
            }
            break;

            case R.id.btn_sort_bubble3:
            {
                long startTime = System.nanoTime();
                SortUtil.sort_bubble_3(arrays);
                long endTime = System.nanoTime();
                stringBuilder.append("冒泡排序--优化  成功 ").append("     耗时： "+ns2ms(endTime-startTime)).append("\t")
                        .append("排序后的数组为： ").append("\n");
                printArrays();
                contentView.setText(stringBuilder.toString());
            }
            break;


            case R.id.btn_sort_selection:
            {
                long startTime = System.nanoTime();
                SortUtil.sort_selection(arrays);
                long endTime = System.nanoTime();
                stringBuilder.append("选择排序成功 ").append("     耗时： "+ns2ms(endTime-startTime)).append("\t")
                        .append("排序后的数组为： ").append("\n");
                printArrays();
                contentView.setText(stringBuilder.toString());
            }
            break;

            case R.id.btn_sort_insertion:
            {
                long startTime = System.nanoTime();
                SortUtil.sort_insertion(arrays);
                long endTime = System.nanoTime();
                stringBuilder.append("插入排序成功 ").append("     耗时： "+ns2ms(endTime-startTime)).append("\t")
                        .append("排序后的数组为： ").append("\n");
                printArrays();
                contentView.setText(stringBuilder.toString());
            }
            break;

            case R.id.btn_sort_shell:
            {
                long startTime = System.nanoTime();
                SortUtil.sort_shell(arrays);
                long endTime = System.nanoTime();
                stringBuilder.append("shell排序成功 ").append("     耗时： "+ns2ms(endTime-startTime)).append("\t")
                        .append("排序后的数组为： ").append("\n");
                printArrays();
                contentView.setText(stringBuilder.toString());
            }
            break;

            case R.id.btn_sort_quick:
            {
                long startTime = System.nanoTime();
                SortUtil.sort_quick1(arrays,0,arrays.length-1);
                long endTime = System.nanoTime();
                stringBuilder.append("简单快速排序成功 ").append("     耗时： "+ns2ms(endTime-startTime)).append("\t")
                        .append("排序后的数组为： ").append("\n");
                printArrays();
                contentView.setText(stringBuilder.toString());
            }
            break;

            case R.id.btn_sort_quick2:
            {
                long startTime = System.nanoTime();
                SortUtil.sort_quick2(arrays,0,arrays.length-1);
                long endTime = System.nanoTime();
                stringBuilder.append("优化快速排序成功 ").append("     耗时： "+ns2ms(endTime-startTime)).append("\t")
                        .append("排序后的数组为： ").append("\n");
                printArrays();
                contentView.setText(stringBuilder.toString());
            }
            break;



            case R.id.btn_find_sequense:
            {
                long startTime = System.nanoTime();
                int index = FindUtil.sequenceSearch(arrays,value);
                long endTime = System.nanoTime();
                if (index <0){
                    stringBuilder.append("顺序查找失败 ").append("耗时： "+ns2ms(endTime-startTime));
                }else {
                    if (value == arrays[index]){
                        stringBuilder.append("顺序查找成功 ").append("下标是： ").append(index).append("    值是：").append(arrays[index])
                                .append("     耗时： "+ns2ms(endTime-startTime));

                    }
                }
                stringBuilder.append("\n");
                contentView.setText(stringBuilder.toString());
            }
                break;
            case R.id.btn_find_binary_loop:{

                long startTime = System.nanoTime();
                int index = FindUtil.binarySearch_loop(arrays,value);
                long endTime = System.nanoTime();
                if (index <0){
                    stringBuilder.append("二分法循环查找失败 ").append("耗时： "+ns2ms(endTime-startTime));
                }else {
                    if (value == arrays[index]){
                        stringBuilder.append("二分法循环查找成功 ").append("下标是： ").append(index).append("    值是：").append(arrays[index])
                                .append("     耗时： "+ns2ms(endTime-startTime));

                    }
                }
                stringBuilder.append("\n");
                contentView.setText(stringBuilder.toString());
            }
                break;
            case R.id.btn_find_binary_recursive:
            {

                long startTime = System.nanoTime();
                int index = FindUtil.binarySearch_recursive(arrays,value,0,arrays.length-1);
                long endTime = System.nanoTime();
                if (index <0){
                    stringBuilder.append("二分法递归查找失败 ").append("耗时： "+ns2ms(endTime-startTime));
                }else {
                    if (value == arrays[index]){
                        stringBuilder.append("二分法递归查找成功 ").append("下标是： ").append(index).append("    值是：").append(arrays[index])
                                .append("     耗时： "+ns2ms(endTime-startTime));

                    }
                }
                stringBuilder.append("\n");
                contentView.setText(stringBuilder.toString());
            }

                break;
            case R.id.btn_find_insertion_loop:
            {

                long startTime = System.nanoTime();
                int index = FindUtil.insertionSearch_loop(arrays,value);
                long endTime = System.nanoTime();
                if (index <0){
                    stringBuilder.append("插值法循环查找失败 ").append("耗时： "+ns2ms(endTime-startTime));
                }else {
                    if (value == arrays[index]){
                        stringBuilder.append("插值法循环查找成功 ").append("下标是： ").append(index).append("    值是：").append(arrays[index])
                                .append("     耗时： "+ns2ms(endTime-startTime));

                    }
                }
                stringBuilder.append("\n");
                contentView.setText(stringBuilder.toString());
            }

                break;
            case R.id.btn_find_insertion_recursive:
            {

                long startTime = System.nanoTime();
                int index = FindUtil.insertionSearch_recursive(arrays,value,0,arrays.length-1);
                long endTime = System.nanoTime();
                if (index <0){
                    stringBuilder.append("插值法递归查找失败 ").append("耗时： "+ns2ms(endTime-startTime));
                }else {
                    if (value == arrays[index]){
                        stringBuilder.append("插值法递归查找成功 ").append("下标是： ").append(index).append("    值是：").append(arrays[index])
                                .append("     耗时： "+ns2ms(endTime-startTime));

                    }
                }
                stringBuilder.append("\n");
                contentView.setText(stringBuilder.toString());
            }

                break;
            case R.id.btn_find_fibonacci_loop:
            {

                long startTime = System.nanoTime();
                int index = FindUtil.fibonacciSearch(arrays,value);
                long endTime = System.nanoTime();
                if (index <0){
                    stringBuilder.append("fibonacci 循环查找失败 ").append("耗时： "+ns2ms(endTime-startTime));
                }else {
                    if (value == arrays[index]){
                        stringBuilder.append("fibonacci 循环查找成功 ").append("下标是： ").append(index).append("    值是：").append(arrays[index])
                                .append("     耗时： "+ns2ms(endTime-startTime));

                    }
                }
                stringBuilder.append("\n");
                contentView.setText(stringBuilder.toString());
            }

                break;
            default:
                break;
        }
    }

    public void printArrays(){
        stringBuilder.append("[ ");
        for (int i=0;i<arrays.length;++i){
            stringBuilder.append(arrays[i]).append("\t").append("\t");
        }
        stringBuilder.append("  ]").append("\n");
    }

    /**
     * 纳秒--》毫秒
     * @param ns
     * @return
     */
    public String ns2ms(long ns){
        return ns *1.0f /(1000*1000)+" ms";
    }
}
