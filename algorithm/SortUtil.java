package com.pq.toolslibrary.algorithm;

/**
 * Created by pan on 2018/4/29.
 * https://www.cnblogs.com/onepixel/articles/7674659.html
 */

public class SortUtil {


    /* --------------------------- 冒泡 begin --------------------------*/

    /**
     * 冒泡排序  -- 小值上浮
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

    /**
     *冒泡排序  -- 大值下沉
     * @param arrays
     */
    public static void sort_bubble_2(int[] arrays){
        for (int i=0;i<arrays.length;++i){
            for (int j=0;j<arrays.length-i-1;++j){
                if (arrays[j]>arrays[j+1]){
                    swap(arrays,j,j+1);
                }
            }
        }
    }

    /**
     * 冒泡排序   -- 大值下沉  -- 优化   如果某次循环没有比较，则已经排序好了
     * @param arrays
     */
    public static void sort_bubble_3(int[] arrays){
        boolean hasSorted = false;
        for (int i=0;i<arrays.length;++i){
            if (hasSorted)return;
            hasSorted=true;
            for (int j=0;j<arrays.length-i-1;++j){
                if (arrays[j]>arrays[j+1]){
                    swap(arrays,j,j+1);
                    hasSorted=false;
                }
            }
        }
    }

    /* --------------------------- 冒泡 end --------------------------*/



   /* --------------------------- 选择 begin --------------------------*/

    /**
     * 选择排序
     * 表现最稳定的排序算法之一，因为无论什么数据进去都是O(n2)的时间复杂度，所以用到它的时候，数据规模越小越好。唯一的好处可能就是不占用额外的内存空间了吧
     * @param arrays
     */
   public static void sort_choice(int[] arrays){
       for (int i=0;i<arrays.length-1;++i){
           int tmpMinIndex = i;  //记录最小值的下标
           for (int j=i+1;j<arrays.length;++j){
               if (arrays[tmpMinIndex] >arrays[j]){
                   tmpMinIndex = j;
               }
           }
           // 需要交换
           if (tmpMinIndex != i){
               swap(arrays , i, tmpMinIndex);
           }
       }
   }



   /* --------------------------- 选择 end --------------------------*/

    /* --------------------------- 插入 begin --------------------------*/

    /**
     * 插入排序
     * @param arrays
     */
    public static void sort_insertion(int[] arrays){
        int tempValue ;   // 每次比较的值
        int tempPreValueIndex;   //前一个值的下标
        for (int i=1;i<arrays.length;++i){
            tempValue = arrays[i];

            // 这种写法  对于[2 ,1]会出错
//            for (int j=i-1;j>=0;--j){
//                if (arrays[j]>tempValue){
//                    arrays[j+1] = arrays[j];
//                }else {
//                    arrays[j+1]=tempValue;
//                    break;
//                }
//            }


            tempPreValueIndex = i-1;
            while (tempPreValueIndex >=0 && arrays[tempPreValueIndex]>tempValue){
                arrays[tempPreValueIndex+1]=arrays[tempPreValueIndex];
                --tempPreValueIndex;
            }
            //最后插入值
            arrays[tempPreValueIndex+1]=tempValue;

        }
    }



    /* --------------------------- 插入 end --------------------------*/


    /* --------------------------- shell begin --------------------------*/

    /**
     * 插入排序---希尔排序
     * @param arrays
     */
    public static void sort_shell(int[] arrays){
        int tmpValue;  //待比较的值
        int tmpPreValueIndex; //前一个值的下标
        int gap=1;   //间隔
        //计算间隔
        while (gap < arrays.length /3){
            gap = gap *3 +1;
        }
        // gap 递减
        for (; gap > 0; gap = (int) Math.floor(gap / 3)){
            for (int i=gap ; i<arrays.length ; ++i){
                tmpValue = arrays[i];
                tmpPreValueIndex = i - gap;
                //跳跃式比较
                while (tmpPreValueIndex >= 0 && arrays [tmpPreValueIndex] > tmpValue){
                    arrays [ tmpPreValueIndex +gap]= arrays[tmpPreValueIndex];
                    tmpPreValueIndex-=gap;
                }
//                for (int j= i-gap ; j>0 && arrays[j] >tmpValue; j-=gap){
//                    arrays[j+gap] = arrays[j];
//                }
                arrays[tmpPreValueIndex+gap]=tmpValue;
            }
        }
    }


    /* --------------------------- shell end --------------------------*/


/* --------------------------- 快排 begin --------------------------*/

    /**
     * 快排   递归
     * @param arrays
     * @param low
     * @param high
     */
  public static void sort_quicksort(int[] arrays ,int low ,int high){
      if (low < high){
          int pivot = arrays[low];
          int i=low+1;
          int j=high;

          while (i<=j){
              while (i<=j && arrays[i]<=pivot){
                  ++i;
              }
              while (i<=j && arrays[j]>=pivot){
                  --j;
              }

              if (i<=j){
                  swap(arrays,i,j);
                  ++i;
                  --j;
              }
          }

          swap(arrays,low,j);
          sort_quicksort(arrays,low,j-1);
          sort_quicksort(arrays,j+1,high);
      }
  }




/* --------------------------- 快排 end --------------------------*/






















    public static void swap(int[] arrays, int i,int j){
        int temp=arrays[i];
        arrays[i]=arrays[j];
        arrays[j]=temp;
    }
}
