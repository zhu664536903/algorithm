package com.gczhu.alg.datastruct;

import java.io.*;
import java.util.ArrayList;

/**
 * 稀疏数组
 * 使用场景:压缩二维数组，只保存有效数据，达到节省磁盘空间目的
 * 原理：
 *      1.获得原二维数组的有效元素个数sum
 *      2.创建一个新的二维数组[sum+1][3],第一行保存原二维数组的行数、列数、有效元素个数
 *      3.第n行保存的是有效元素所在的行数、列数、值
 */
public class SparseArray {

    public int[][] converToSparseArray(int[][] oldArray){
        if (oldArray == null) return null;
        //遍历原二维数据，获得有效元素个数
        ArrayList<int[]> temp = new ArrayList<>();

        for (int i = 0; i < oldArray.length; i++) {
            for (int j = 0; j < oldArray[0].length; j++) {
                if (oldArray[i][j] != 0){
                    int[] el = new int[3];
                    el[0] = i;
                    el[1] = j;
                    el[2] = oldArray[i][j];
                    temp.add(el);
                }
            }
        }
        if (temp.size() == 0) return null;

        //遍历原二维数据保存有效数据到稀疏数组
        int[][] sparseArray = new int[temp.size() + 1][3];
        sparseArray[0][0] = oldArray.length;
        sparseArray[0][1] = oldArray[0].length;
        sparseArray[0][2] = temp.size();

        int count = 0;
        for (int[] ints:temp) {
            count++;
            sparseArray[count][0] = ints[0];
            sparseArray[count][1] = ints[1];
            sparseArray[count][2] = ints[2];
        }

        return sparseArray;
    }
    public boolean saveSparseArray(int[][] sparseArray,String path) throws IOException {
        if(sparseArray == null || path ==null) return false;

        File file = new File(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(sparseArray);


        return true;
    }
    public int[][] getArray(String path) throws IOException, ClassNotFoundException {
        if(path == null) return null;
        File file = new File(path);
        if (!file.exists() || !file.isFile()) return null;
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        int[][] o = (int[][]) objectInputStream.readObject();
        int[][] old = new int[o[0][0]][o[0][1]];
        for (int i = 1; i < o.length; i++) {
            old[o[i][0]][o[i][1]] = o[i][2];
        }
        return old;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SparseArray obj = new SparseArray();
        int[][] arry1 = new int[11][11];
        arry1[0][1] = 10;
//        arry1[2][5] = 7;
//        arry1[6][1] = 11;
        //输出二维数组
        for (int[] row:arry1) {
            for (int col:row) {
                System.out.printf("%d\t",col);
            }
            System.out.println();
        }
        //转换成稀疏数组
        int[][] sparseArray = obj.converToSparseArray(arry1);
        if(sparseArray == null){
            System.out.println("原数组为空");
            return;
        }
        System.out.println("输出稀疏数组！");
        for (int[] row:sparseArray) {
            for (int col:row) {
                System.out.printf("%d\t",col);
            }
            System.out.println();
        }
        //保存对象
        System.out.printf("保存对象%s",obj.saveSparseArray(sparseArray,"haha"));

        //恢复对象
        int[][] hahas = obj.getArray("haha");
        System.out.println("恢复的对象"+hahas);
        for (int[] row:hahas) {
            for (int col:row) {
                System.out.printf("%d\t",col);
            }
            System.out.println();
        }
    }
}
