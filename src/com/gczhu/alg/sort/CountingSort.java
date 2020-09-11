package com.gczhu.alg.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 计数排序原理：
 *      1.待排序数组的最大值，新建一个长序为该值的计数数组，将待排序数组的值作计数数组的下标，记录该值出现的次数
 *      2.加工计数数组，使每个元素保存的小于等于下标值的数量。
 *      3.遍历待排序数组，根据值n从计数数组中获得小于等于该值的数量index，新建结果数组，在结果数组index-1处保存值n
 *  使用场景：使用在值范围不大的场景
 *  时间复杂度：O(n)
 *  是否原地排序：否
 *  是否稳定算法：排序时从后往前遍历待排序数组可以成为稳定算法
 */
public class CountingSort {
    public void sort(int[] a){
        if (a == null || a.length < 2) return;

        //寻找最大值
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) max = a[i];
        }
        //创建计数数组，保存每个值出现次数
        int[] temps = new int [max+1];
        for (int i = 0; i < a.length; i++) {
            temps[a[i]]++;
        }
        //重点：加工计数数组，使每个元素保存的是小于等于下标值的个数,
        for (int i = 1; i < temps.length; i++) {
            temps[i] = temps[i-1] + temps[i];
        }

        //遍历原数组，根据值从计数数组查询保存坐标
        int[] result = new int[a.length];
        for (int i = a.length-1; i >= 0 ; i--) {
            int saveIndex = temps[a[i]] - 1;
            result[saveIndex] = a[i];
            temps[a[i]]--;
        }
        for (int i = 0; i < a.length; i++) {
            a[i] = result[i];
        }

    }

    public static void main(String[] args) {
        CountingSort countingSort = new CountingSort();
        int max = 80000000;
        int[] ints = new int[max];
        int[] ints1 = new int[max];
        for (int i = 0; i < max; i++) {
            ints[i] = new Random().nextInt(100);
        }

        //System.out.println(Arrays.toString(ints));
        long  startTime = System.currentTimeMillis();
        countingSort.sort(ints);
        long  endTime = System.currentTimeMillis();
        System.out.println("计数排序耗时："+ (endTime-startTime));
        //System.out.println(Arrays.toString(ints));
    }
}
