package com.gczhu.datastruct;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 堆：堆是一种以完全二叉树为基础，且满足任意一个父节点大于或小于其所有子节点，称为大堆顶或小堆顶
 * 使用场景:堆排序、优先级队列、top排名
 * 大堆顶原理：
 *      计算节点的父节点索引：i = （i-1）/2
 *      计算节点的子节点索引：i左 = 2*i+1  i右 = 2*i+2
 *      计算最后一个非叶子节点索引：i = n/2-1
 *
 *      新增元素（从下至上堆化）：将元素插入数组后再与父节点对比，如果大于，则交换值，再将父节点与父节点的父节点比较，一直到根节点。
 *      删除元素（从上至下堆化）：
 *              1.将最后一个叶子节点替换根节点值
 *              2.从根节点开始向下堆化
 * 时间复杂度：
 *      插入：O(log n)
 *      删除: O(log n)
 *      排序: O(nlog n) 原地，非稳定算法
 *
 */
public class Heap {
    private int index = -1;//指向最后一个元素
    private int data[];

    public Heap(int capacity) {
        this.data = new int[capacity];
    }
    //插入一个元素
    public void insert(int value){
        index++;
        if (index >= data.length) return;//校验节点个数是否为最大数
        this.data[index] = value;

        //循环向上堆化
        int i = index;//指向新插入元素
        while ((i-1)/2 >= 0 && data[i] > data[(i-1)/2]){
            int parentIndex = (i-1)/2;
            sweap(this.data,i,parentIndex);
            i = parentIndex;
        }
    }

    //移除堆顶元素
    public int  removeMax(){
        if(index < 0) return -1;
        int result = data[0];
        data[0] = data[index--];//最后一个节点替换根节点
        headify(data,index+1,0);

        return result;
    }

    /**
     *
     * @param a  数组
     * @param len 数组长度
     * @param i 堆化起始索引
     */
    public  static void headify(int a[],int len,int i){
        //针对指定节点从上到下堆化
        while (true){
            int maxPost = i;
            int leftIndex = 2*maxPost+1;
            int rightIndex = 2*maxPost+2;

            if ( leftIndex < len && a[maxPost] < a[leftIndex]) maxPost = leftIndex; //左子节点
            if ( rightIndex < len && a[maxPost] < a[rightIndex]) maxPost = rightIndex;//右子节点
            if(maxPost == i) break;  //如果左右子节点值都不大于于当前节点，终止堆化

            sweap(a,i,maxPost);
            i = maxPost;


        }
    }

    //对指定数组构建成堆
    public static void buildHead(int a[],int len){
        for(int i=len/2-1;i>=0;i--){
            headify(a,len,i);
        }
    }

    //交换元素
    private static void sweap(int[] a,int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void sort(int a[],int len){
        //建堆
        buildHead(a,len);
        int k = len;
        while (k > 1){
            sweap(a,0,k-1);//将堆顶元素移至末尾
            k--; //数组容量减少
            headify(a,k,0);
        }

    }

    public void print(){
        System.out.println(Arrays.toString(data));
    }

    public static void main(String[] args) {
        Heap heap = new Heap(10);
        int max = 40000000;
        int[] ints = new int[max];
        for (int i = 0; i <max; i++) {
           ints[i] = new Random().nextInt(100000);
        }
        long  startTime = System.currentTimeMillis();
        //System.out.println(Arrays.toString(ints));

        heap.sort(ints,max);
        long  endTime = System.currentTimeMillis();
        System.out.println("排序耗时："+ (endTime-startTime));
        //System.out.println(Arrays.toString(ints));


    }
}
