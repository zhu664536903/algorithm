package com.gczhu.alg.sort;

import java.util.Arrays;

/**
 * 冒泡排序核心思想：
 *      将数据分为有序和无序区，通过将无序区元素两两交换，将最小值推向有序区末尾。
 * 指针：i指向无序区第一个位置,从0开始，j:遍历无序区元素，从leng-1开始，止于j>i
 * 时间复杂度：最好情况O（n），最坏情况O（n2）
 * 是否原地排序：是
 * 是否稳定算法：是
 */
public class BubbleSort {
    public void sort(int[] a){
        if (a==null || a.length < 1) return;
        //定义分区指针
        for (int i = 0; i < a.length ; i++) {
            boolean isSweap = false;
            for (int j = a.length-1; j > i; j--) {
                if (a[j] < a[j-1]){
                    isSweap = true;
                    int temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }

            }
            if (!isSweap) break;//如果上一次冒泡不存在交换元素，则认为全部数据有序
        }
    }

    public static void main(String[] args) {
        int max = 100000;
        int[] ints = new int[max];
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
