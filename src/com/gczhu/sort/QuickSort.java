package com.gczhu.sort;

import java.util.Random;

/**
 * 快速排序核心思想：
 *  1.采用分区方式，选取一个基准值，将小于基准值的元素移动到基准值左边，将大于基准值的元素移动到基准值右边
 *  2.分别递归处理基准值两边的元素
 *
 *  时间复杂度：O(n*log n)
 *  当元素已经有序时，退化为最坏时间复杂度：O(n2) (平方阶)
 *  是否是原地排序：是
 *  是否为稳定算法：否
 *
 */
public class QuickSort {
    //排序
    public void sort(int a[],int left,int right){
        if (left >= right) return;
        int i = fenqu(a, left, right);
        sort(a,left,i-1);
        sort(a,i+1,right);
    }
    //分区方法
    public int fenqu(int a[],int left,int right){
        int p = right;
        int i=left,j=left;
        for (; j < right; j++) {
            if (a[j] < a[p]){
                if(i==j){
                    i++;
                }else{
                    int temp  = a[j];
                    a[j] = a[i];
                    a[i++] = temp;
                }
            }
        }
        //移动基准值
        int temp  = a[p];
        a[p] = a[i];
        a[i] = temp;
        //System.out.println(i);
        return i;
    }

    public static void main(String[] args) {
        int[] a = new int[10000];
        for (int i = 0; i < 10000; i++) {
            a[i] = new Random().nextInt(10000);
        }

        for (int i = 0; i < 10000; i++) {
            System.out.print(a[i]+",");
        }
        long start = System.currentTimeMillis();
        QuickSort quickSort = new QuickSort();
        quickSort.sort(a,0,a.length-1);
        long end = System.currentTimeMillis();
        System.out.println("完成时间："+(end-start));

        System.out.println("");
        for (int i = 0; i < 10000; i++) {
            System.out.print(a[i]+",");
        }
    }
}
