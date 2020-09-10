package com.gczhu.alg.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 选择排序核心思想：
 *     将数据分为有序区和无序区，从无序区选出最小值插入有序区后
 *
 * 指针: i指向无序区第一个位置，min:指向无序区最小元素，默认为i, j:遍历元素，从i+1开始
 * 时间复杂度：O（n2）
 * 是否原地：是
 * 是否稳定：否
 */
public class ChoiceSort {
    public void sort(int[] a){
        if (a==null || a.length < 1) return;
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i+1; j < a.length; j++) {
                if(a[j] < a[min]) min = j;
            }
            if (min != i){
                int temp = a[min];
                a[min] = a[i];
                a[i] = temp;
            }
        }
    }
    public static void main(String[] args) {
        int max = 100000;
        int[] ints = new int[max];
        for (int i = 0; i < max; i++) {
            ints[i] = new Random().nextInt(100000);
        }
        ChoiceSort choiceSort = new ChoiceSort();


        long  startTime = System.currentTimeMillis();
        choiceSort.sort(ints);
        long  endTime = System.currentTimeMillis();
        System.out.println("选择排序耗时："+ (endTime-startTime));
        System.out.println(Arrays.toString(ints));


    }
}
