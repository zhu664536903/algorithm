package com.gczhu.alg.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 桶排序原理：
 *      创建一定数量的数据桶，将数据序列尽可能均匀的映射到桶中，按顺序对每个桶使用排序算法排序，再按顺序从每个桶中取出数据，这时得到的数据是有序的。
 *
 * 应用场景：适合用于排序外部储存数据，比如保存在磁盘的大文件数据无法一下读入内存。需要遍历出最大与最小值，将数据拆分到不同的小文件对每一个小文件排序后
 * 再按顺序合并成有序文件。
 *
 * 指针 bucketSize：桶大小，也代表数据序列的分割粒度  buckertNum:数据对应桶号，(a[i]-minValue)/bucketSize
 * 如何使数据均匀分布：针对原有分割粒度情况下再次分割
 *
 * 时间复杂度：最好情况：O（n） 最坏情况：O(nlogn)
 * 是否原地排序：否
 * 是否稳定：否
 */
public class BucketSort {
    /**
     *
     * @param a
     * @param bucketSize 数据分割长度。如1到10的数据范围，分割长度为3，1-3范围为一个桶，4-6为第二桶，7-9为第三个桶，10为第四个桶
     */
    public void sort(int[] a,int bucketSize){
        if (a == null || a.length < 2) return;

        //寻找最大值与最小值，确定数据范围
        int minValue = a[0];
        int maxValue = a[1];
        for (int i = 0; i < a.length ; i++) {
            if (minValue > a[i]) minValue = a[i];
            if (maxValue < a[i]) maxValue = a[i];
        }

        //计算桶数量
        int bucketCount = (maxValue-minValue)/bucketSize +1;
        int[][] bucket = new int[bucketCount][bucketSize];
        int[] bucketIndex = new int[bucketCount];//记录每个小桶数据量


        //将数量映射到对应的桶
        for (int i = 0; i < a.length; i++) {
            int bucketNum = (a[i] - minValue) / bucketSize; //桶号
            if (bucketIndex[bucketNum] == bucket[bucketNum].length){//小桶扩容
                ensureCapacity(bucket,bucketNum);
            }
            bucket[bucketNum][ bucketIndex[bucketNum]++ ] = a[i];

        }
        QuickSort quickSort = new QuickSort();
        //使用快速快序对每个小桶排序
        int k=0;
        for (int i = 0; i < bucket.length; i++) {
            if(bucketIndex[i] == 0)continue;
            quickSort.sort(bucket[i],0,bucketIndex[i]-1);

            //将数据写回原数组
            for (int j=0; j < bucketIndex[i]; j++) {
                a[k++] = bucket[i][j];
            }
        }


    }

    //小桶扩容
    private void ensureCapacity(int[][] bucket, int bucketNum) {
        int[] ints = Arrays.copyOf(bucket[bucketNum], bucket[bucketNum].length * 2);
        bucket[bucketNum] = ints;
    }

    public static void main(String[] args) {
        BucketSort bucketSort = new BucketSort();

        int max = 80000000;
        int[] ints = new int[max];
        int[] ints1 = new int[max];
        for (int i = 0; i < max; i++) {
            ints[i] = new Random().nextInt(1000000);
        }


        long  startTime = System.currentTimeMillis();
        bucketSort.sort(ints,100);
        long  endTime = System.currentTimeMillis();
        System.out.println("桶排序耗时："+ (endTime-startTime));
        //System.out.println(Arrays.toString(ints));
    }
}
