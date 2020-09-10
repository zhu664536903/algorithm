package com.gczhu.alg.sort;

import java.util.Arrays;

/**
 * 归并排序：
 *      采用分治思想，对数据进行拆分到最细的粒度（n=1），再对拆分的数据进行比较合并。
 *  指针：i:指向需要拆分数组的中间位置，i = left +(right-left)/2
 * 时间复杂度：O(nlog n)
 * 是否原地算法：否
 * 是否稳定算法：是
 */
public class MergeSort {
    public void merSort(int[] a,int left,int right){
        if(left >= right) return ;
        int m = left +(right-left)/2;
        merSort(a,left,m);
        merSort(a,m+1,right);
        merge(a,left,m,right);
    }
    private void merge(int[] a,int left,int m,int right){
        //新建中间数组
        int[] temp = new int[right - left + 1];
        int k = 0;
        //定义遍历指针
        int left1 = left;
        int left2 = m+1;

        //拷贝数组
        while (left1 <= m && left2 <= right){
            if (a[left1] > a[left2]){
                temp[k++] = a[left2++];
            }else{
                temp[k++] = a[left1++];
            }
        }

        //将剩余数据拷贝到中间数组
        while (left1 <= m){
            temp[k++] = a[left1++];
        }
        while (left2 <= right){
            temp[k++] = a[left2++];
        }

        //将中间数组考回原数组
        for (int i = 0; i < temp.length; i++) {
            a[left++] = temp[i];
        }

    }

    public static void main(String[] args) {
        int[] ints = {2, 3, 1, 2, 4, 5};
        MergeSort mergeSort = new MergeSort();
        mergeSort.merSort(ints,0,ints.length-1);
        System.out.println(Arrays.toString(ints));
    }
}
