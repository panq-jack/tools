package com.pq.toolslibrary.algorithm;

/**
 * Created by pan on 2018/4/29.
 * 排序算法  https://www.cnblogs.com/onepixel/articles/7674659.html
 * 交换排序： 冒泡   快排
 * 插入排序： 插入   希尔
 * 选择排序： 选择   堆
 * 归并排序
 *
 */

public class SortUtil {

    /*----------------  冒泡 begin  --------------------*/

    /**
     * 冒泡排序    ---小值上浮
     * 时间复杂度 o(n2)
     * @param arrays
     */
    public static void sort_bubble_1(int[] arrays){
        for (int i=0; i<arrays.length; ++i){
            for (int j=arrays.length-1;j>i;--j){
                if (arrays[j]<arrays[j-1]){
                    swap(arrays,j,j-1);
                }
            }
        }
    }

    /**
     * 冒泡排序  --- 大值下沉
     *
     * @param arrays
     */
    public static void sort_bubble_2(int[] arrays){
        for (int i = 0; i<arrays.length; ++i){
            for (int j = 0; j<arrays.length-1-i; ++j){
                if (arrays[j]>arrays[j+1]){
                    swap(arrays,j,j+1);
                }
            }
        }
    }

    /**
     * 冒泡排序 --- 大值下沉  优化
     *
     * @param arrays
     */
    public static void sort_bubble_3(int[] arrays){
        boolean hasOrdered=false;  //temp variable

        for (int i = 0; i<arrays.length && !hasOrdered; ++i){
            hasOrdered = true;
            for (int j = 0; j<arrays.length-1-i; ++j){
                if (arrays[j]>arrays[j+1]){
                    swap(arrays,j,j+1);
                    hasOrdered=false;
                }
            }
        }
    }


    /*----------------  冒泡 end  --------------------*/


    /*----------------  选择 begin  --------------------*/

    /**
     * 选择排序
     *
     * @param arrays
     */
    public static void sort_selection(int[] arrays){
        int minIndex;
        for (int i = 0; i<arrays.length-1; ++i){
            minIndex = i;

            for (int j = i+1; j<arrays.length; ++j){
                if (arrays[minIndex] >arrays[j]){
                    minIndex = j;
                }
            }

            if (minIndex != i){
                swap(arrays , i, minIndex);
            }
        }
    }


    /*----------------  选择 end  --------------------*/


    /*----------------  插入 begin  --------------------*/

    /**
     * 插入排序
     *
     * @param arrays
     */
    public static void sort_insertion(int[] arrays){
        int index;  //插入的位置
        int value;  //插入的值
        for (int i = 1; i<arrays.length; ++i){
            index = i;
            value = arrays[i];
            for (int j = i-1; j>=0; --j){
                if (value < arrays[j]){
                    index=j;
                    arrays[j+1] = arrays[j];
                }
            }
            if (index != i){
                arrays[index] = value;
            }
        }
    }


    /*----------------  插入 end  --------------------*/



    /*----------------  shell begin  --------------------*/

    /**
     * shell sort--插入排序的优化
     *
     * @param arrays
     */
    public static void sort_shell(int[] arrays){
        int value;  //待插入的值
        int index;  //待插入的下标
        int gap=1;

        //确定gap的值
        while (gap < arrays.length / 3){
            gap = gap * 3 + 1;
        }

        for (; gap>0; gap = (int) Math.floor(gap / 3)){
            for (int i=gap;i<arrays.length;++i){
                value=arrays[i];
                index=i;
                for (int j=i-gap;j>=0 ; j-=gap){
                    if (arrays[j] > value){
                        index=j;
                        arrays[j+gap]=arrays[j];
                    }
                }
                if (index != i){
                    arrays[index] = value;
                }
            }
        }



    }


   /*----------------  shell end  --------------------*/


   /*----------------  quick begin  --------------------*/

    /**
     * 快速排序
     *
     * @param arrays
     */
   public static void sort_quick1(int[] arrays ,int low,int high){

       //优化： 三数取中
       adjustMiddleOfThree(arrays,low,high);

       if (low < high){
           int pivot = getPivot1(arrays,low,high);
           sort_quick1(arrays,low,pivot-1);
           sort_quick1(arrays,pivot+1,high);
       }
   }

   public final static int MAX_LENGTH_INSERT_SORT=7;  //

    /**
     * 快速排序  -- 优化 大数组，小数组的区别处理 && 尾递归优化
     * @param arrays
     * @param low
     * @param high
     */
   public static void sort_quick2(int[] arrays ,int low ,int high){
       //优化： 三数取中
       adjustMiddleOfThree(arrays,low,high);

       //优化：区别处理
       if (arrays.length >MAX_LENGTH_INSERT_SORT){
           //优化  尾递归
           while (low < high){
               int pivot = getPivot2(arrays,low,high);

               sort_quick2(arrays,low,pivot-1);
               low = pivot + 1;
           }


       }else {  //采用简单的排序算法
           //比如 插入排序
           sort_insertion(arrays);
       }
   }

    /**
     * 优化枢纽下标   --交换
     * @param arrays
     * @param low
     * @param high
     * @return
     */
   public static int getPivot1(int[] arrays,int low ,int high){
       int pivotValue = arrays[low];

       while (low<high){
           while (low<high && pivotValue<=arrays[high]){
               --high;
           }
           swap(arrays,low,high);

           while (low<high && pivotValue>=arrays[low]){
               ++low;
           }
           swap(arrays,low,high);
       }
       return low;

   }

    /**
     * 获取枢纽下标   --优化不必要的交换
     * @param arrays
     * @param low
     * @param high
     * @return
     */
   public static int getPivot2(int[] arrays,int low ,int high){
       int pivotValue = arrays[low];

       while (low<high){
           while (low<high && pivotValue<=arrays[high]){
               --high;
           }
           arrays[low] = arrays[high];
           while (low<high && pivotValue>=arrays[low]){
               ++low;
           }
           arrays[high] = arrays[low];
       }
       arrays[low]=pivotValue;
       return low;
   }

    /**
     * 三数取中  最后arrays[low]保存中间值,  arrays[high]保存最大值。。
     * @param arrays
     * @param low
     * @param high
     */
   public static void adjustMiddleOfThree(int[] arrays, int low,int high){
       if (low<high){
           int mid = (low + high) / 2;
           // ensure  arrays[low] < arrays[high]
           if (arrays[low]>arrays[high]){
               swap(arrays,low,high);
           }
           // ensure  arrays[mid] < arrays[high]
           if (arrays[mid] > arrays[high]){
               swap(arrays , mid ,high);
           }
           //ensure  arrays[low] > arrays[mid]  ----> arrays[low]保存中间值
           if (arrays[low]< arrays[mid]){
               swap(arrays,low,mid);
           }

       }
   }

   /*----------------  quick end  --------------------*/


    /*----------------  heap begin  --------------------*/

    /**
     * 堆排序   大顶堆，小顶堆
     * 一般升序采用大顶堆，降序采用小顶堆
     * @param arr
     */
    public static void sort_heap_big(int []arr){
        //1.构建大顶堆
        for(int i=arr.length/2-1;i>=0;i--){
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap_big(arr,i,arr.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=arr.length-1;j>0;j--){
            swap(arr,0,j);//将堆顶元素与末尾元素进行交换
            adjustHeap_big(arr,0,j);//重新对堆进行调整
        }

    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     * @param arr
     * @param i  :待调整的数值下标
     * @param length  ：下标 边界
     */
    public static void adjustHeap_big(int []arr,int i,int length){
        int temp = arr[i];//先取出当前元素i
        for(int k=i*2+1;k<length;k=k*2+1){//从i结点的左子结点开始，也就是2i+1处开始
            if(k+1<length && arr[k]<arr[k+1]){//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if(arr[k] >temp){//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }






    /**
     * 堆排序   大顶堆，小顶堆
     * 一般升序采用大顶堆，降序采用小顶堆
     * @param arr
     */
    public static void sort_heap_small(int []arr){
        //1.构建大顶堆
        for(int i=arr.length/2-1;i>=0;i--){
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap_small(arr,i,arr.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=arr.length-1;j>0;j--){
            swap(arr,0,j);//将堆顶元素与末尾元素进行交换
            adjustHeap_small(arr,0,j);//重新对堆进行调整
        }

    }

    /**
     * 调整大顶堆（仅是调整过程，建立在小顶堆已构建的基础上）
     * @param arr
     * @param i  :待调整的数值下标
     * @param length  ：下标 边界
     */
    public static void adjustHeap_small(int []arr,int i,int length){
        int temp = arr[i];//先取出当前元素i
        for(int k=i*2+1;k<length;k=k*2+1){//从i结点的左子结点开始，也就是2i+1处开始
            if(k+1<length && arr[k]>arr[k+1]){//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if(arr[k] > temp){//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }

    /*----------------  heap end  --------------------*/







    public static void swap(int[] arrays, int i,int j){
        int temp=arrays[i];
        arrays[i]=arrays[j];
        arrays[j]=temp;
    }







}
