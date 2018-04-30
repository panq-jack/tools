package com.pq.toolslibrary.algorithm;

/**
 * Created by pan on 2018/4/29.
 */

public class SortUtil {

    /**
     * 冒泡排序
     * 时间复杂度 o(n2)
     * @param arrays
     */
    public static void sort_bubble(int[] arrays){

        for (int i=0; i<arrays.length; ++i){

            for (int j=arrays.length-1;j>i;--j){
                if (arrays[j]<arrays[j-1]){
                    swap(arrays,j,j-1);
                }
            }

        }
    }

    public static void swap(int[] arrays, int i,int j){
        int temp=arrays[i];
        arrays[i]=arrays[j];
        arrays[j]=temp;
    }
}
