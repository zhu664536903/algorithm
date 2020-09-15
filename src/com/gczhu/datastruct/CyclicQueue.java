package com.gczhu.datastruct;

import java.util.Scanner;

/**
 * 基于数组实现的循环队列
 * 指针移动算法 (point+1)%len  point:指针，len:数组容量
 * 1.定义队头和队尾指针，head指向队列第一个元素，tail指向队尾第一个空位置。
 * 2.队空条件:head == tail, 队满条件:tail下一个位置等于head
 * 3.添加元素tail需要往后移动，弹出元素head需要往后移动
 *
 * @param <T>
 */
public class CyclicQueue<T> {
    private static int DEFAULT_LEN = 10;
    private  Object[] data;
    private int head;
    private int tail;

    public CyclicQueue() {
        this(DEFAULT_LEN);
    }

    public CyclicQueue(int len) {
        this.data = new Object[len+1];
    }

    public void push(T obj){
        if(isFull()){
            System.out.printf("队列已满！");
            return ;
        }
        data[tail] = obj;
        this.tail = (tail+1)%data.length;
    }
    public T pop(){
        if(isEmpty()){
            System.out.printf("队列为空！");
            return null;
        }
        T tem = (T)data[head];
        this.head = (head+1)%data.length;
        return tem;
    }
    public boolean isFull(){
        return (tail+1)%data.length == head;
    }
    public boolean isEmpty(){
        return head == tail;
    }
    public void printData(){
        for (int i = head; i != tail ; i=(i+1) % data.length) {
            System.out.printf("%s\t",data[i].toString());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CyclicQueue<String> queue = new CyclicQueue<>(3);
        Scanner scanner = new Scanner(System.in);
        String mode = "1";
        boolean isRun = true;
        while (isRun){
            System.out.println("1:push");
            System.out.println("2:pop");
            System.out.println("3:print");
            System.out.println("4:exit");
            mode = scanner.nextLine();
            switch (mode){
                case "1":
                    System.out.println("请输入一个字符串：");
                    queue.push(scanner.nextLine());
                    break;
                case "2":
                    queue.pop();
                    break;
                case "3":
                    queue.printData();
                    break;
                case "4":
                    isRun = false;
                    System.out.println("程序退出！");
                    break;
                default:
                    System.out.println("不存在的选项！");
                    break;

            }
        }


    }
}
