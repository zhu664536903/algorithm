package com.gczhu.alg.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 插入排序核心思想：
 *      将待排序的数据分为有序区（初始为第一个元素）和无序区（第二个元素开始），将无序区第一个元素在有序区找到合适位置插入
 *
 * 指针：i:指向无序区第一个元素  j：遍历有序区元素
 * 时间复杂度：最好为O(n),最坏为O(n2)
 * 是否原地排序：是
 * 是否稳定：如果元素相同，会将无序区的元素插入有序区该元素的后面，所以是稳定算法。
 */
public class InsertSort {
    public void sort(int a[],int len){
        if(a == null && a.length < 1) return;

        //分区循环 i
        for (int i = 1; i < a.length; i++) {
            int value = a[i];
            int j = i-1;
            //比较循环 j
            for (; j >= 0 ; j--) {
                if (value < a[j] ) a[j+1] = a[j];//如果当前元素大于待插入元素，将当前元素往后移动
                else break;
            }
            a[j+1] = value;
        }
    }

    public static void main(String[] args) {
        int max = 100000;
        int[] ints = new int[max];
        int[] ints1 = new int[max];
        for (int i = 0; i < max; i++) {
            ints[i] = new Random().nextInt(100000);
        }
        ints1 = Arrays.copyOf(ints, max);
        InsertSort insertSort = new InsertSort();
        BubbleSort bubbleSort = new BubbleSort();

        long  startTime = System.currentTimeMillis();
        insertSort.sort(ints,ints.length);
        long  endTime = System.currentTimeMillis();
        System.out.println("插入排序耗时："+ (endTime-startTime));
        //System.out.println(Arrays.toString(ints));

        //System.out.println(Arrays.toString(ints1));
        startTime = System.currentTimeMillis();
        bubbleSort.sort(ints1);
        endTime = System.currentTimeMillis();
        System.out.println("冒泡排序耗时："+ (endTime-startTime));
        //System.out.println(Arrays.toString(ints1));
    }
}
