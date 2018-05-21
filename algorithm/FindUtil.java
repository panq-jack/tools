package com.pq.toolslibrary.algorithm;

import java.util.Arrays;

/**
 * Created by pan on 2018/4/29.
 * 查找算法:
 *  有序or无序
 *  顺序存储 or 链表存储
 *  asl average search length 平均查找长度
 */

public class FindUtil {


    /**
     * 顺序查找：   可有序可无序，可顺序存储可链表存储
     * 时间复杂度 o(n)
     * @param arrays
     * @param value
     * @return
     */
    public static int sequenceSearch(int[] arrays, int value){
        for (int i=0;i<arrays.length;++i){
            if (arrays[i]==value){
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分查找：   有序顺序存储  循环法
     * 时间复杂度 o(log2n)
     * @param arrays
     * @param value
     * @return
     */
    public static int binarySearch_loop(int[] arrays, int value){
        int low=0,high=arrays.length-1;

        while (low <= high){
            int mid=(low+high)/2;
            if (arrays[mid] <value){
                low = mid + 1;
            }else if (arrays[mid] >value){
                high = mid - 1;
            }else {
                return mid;
            }
        }
        return ~low;
    }


    /**
     * 二分查找：： 有序顺序存储   递归法
     * 时间复杂度  o(log2n)
     * @param arrays
     * @param value
     * @param low
     * @param high
     * @return
     */
    public static int binarySearch_recursive(int[] arrays , int value ,int low ,int high){
        if (low >high) return ~low;

        int mid = (low+high)/2;
        if (arrays[mid] < value){
            return binarySearch_recursive(arrays,value,mid+1,high);
        }else if (arrays[mid] >value){
            return binarySearch_recursive(arrays,value,low ,high-1);
        }else {
            return mid;
        }
    }


    /**
     * 插值查找循环法： 有序顺序存储   二分查找的优化
     * 改进点：mid=low+(key-a[low])/(a[high]-a[low])*(high-low)，
     * 基本思想：基于二分查找算法，将查找点的选择改进为自适应选择，可以提高查找效率。当然，差值查找也属于有序查找。
     　　注：对于表长较大，而关键字分布又比较均匀的查找表来说，插值查找算法的平均性能比折半查找要好的多。反之，数组中如果分布非常不均匀，那么插值查找未必是很合适的选择。
     　　复杂度分析：查找成功或者失败的时间复杂度均为O(log2(log2n))。
     * @param arrays
     * @param value
     * @return
     */
    public static int insertionSearch_loop(int [] arrays,int value){
        int low = 0;
        int high = arrays.length-1;
        while (low <= high){
            int mid = low + (int)(1.0f*(value - arrays[low])/(arrays[high] - arrays[low]) * (high-low));
            if (arrays[mid] <value){
                low = mid + 1;
            }else if (arrays[mid] >value){
                high = mid - 1;
            }else {
                return mid;
            }
        }

        return ~low;
    }

    /**插值查找递归法： 有序顺序存储   二分查找的优化
     * 改进点：mid=low+(key-a[low])/(a[high]-a[low])*(high-low)，
     * 基本思想：基于二分查找算法，将查找点的选择改进为自适应选择，可以提高查找效率。当然，差值查找也属于有序查找。
     　　注：对于表长较大，而关键字分布又比较均匀的查找表来说，插值查找算法的平均性能比折半查找要好的多。反之，数组中如果分布非常不均匀，那么插值查找未必是很合适的选择。
     　　复杂度分析：查找成功或者失败的时间复杂度均为O(log2(log2n))。
     * @param arrays
     * @param value
     * @param low
     * @param high
     * @return
     */
    public static int insertionSearch_recursive(int[] arrays, int value ,int low ,int high){
        if (low >high) return ~low;

        int mid = low + (int)(1.0f*(value - arrays[low])/(arrays[high] - arrays[low]) * (high-low));
        if (arrays[mid] < value){
            return binarySearch_recursive(arrays,value,mid+1,high);
        }else if (arrays[mid] >value){
            return binarySearch_recursive(arrays,value,low ,high-1);
        }else {
            return mid;
        }
    }



    /*------------------------------fibonacci find begin-----------------------*/

    /**
     * 构建一个 fibonacci 数组 eg: 0 1 1 2 3  5 8 13 21 34 55 89 144 ...
     * @param size
     * @return
     */
    public static int[] generateFibonacciArrays(int size){
        int[] arrays=new int[size];
        arrays[0]=0;
        arrays[1]=1;

        for (int i=2;i<size;++i){
            arrays[i] = arrays[i-1]+arrays[i-2];
        }

        return arrays;
    }

    /**
     * fibonacci 查找 要求有序数组   循环法
     * 时间复杂度  o(log2n)
     * @param arrays
     * @param value
     * @return
     */
    public static int fibonacciSearch(int[] arrays,int value){
        //1. 生成fibonacci数组  空间复杂度
        //2。扩展arrays使得大小等于 fibonacci 数组中某个数值-1  fibonacci(k)-1  ；
        //3。获取 mid值，然后比较查找

        int length=arrays.length;

        int[] fibonacci = generateFibonacciArrays(20);

        int k=0; //k为fibonacii数组下标，使得 arrays.length == fibonacci(k)-1;
        while (length > fibonacci[k]-1){
            ++k;
        }

        //扩展 arrays  使得 arrays.length == fibonacci[k]-1;  ,用arrays[length-1]填充待扩展的值
        if (fibonacci[k]-1 >length){
            int[] newArrays=new int[fibonacci[k]-1];
            System.arraycopy(arrays,0,newArrays,0,length);
            Arrays.fill(newArrays , length,fibonacci[k]-1,arrays[length-1]);

            arrays = newArrays;
        }

        int low = 0;
        int high = arrays.length-1;
        //开始比较

        while (low < high){
            int mid = low + fibonacci[k-1]-1;
            if (arrays[mid] < value){
                low = mid+1;
                k-=2;
            }else if (arrays[mid] >value){
                high = mid-1;
                k-=1;
            }else {
                if (mid < length){
                    return mid;
                }else {
                    mid = length-1;
                }
            }
        }

        return ~low;

    }
    /*------------------------------fibonacci find end-----------------------*/






}
