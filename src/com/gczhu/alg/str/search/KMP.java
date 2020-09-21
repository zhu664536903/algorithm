package com.gczhu.alg.str.search;

import java.util.Arrays;

/**
 *  kmp核心思想：
 *      通过模式串构建可匹配表，当模式串与子串存在相同且连续的字符且在连续字符后有不同字符时，
 *      根据可匹配表可以指示模式串向右移动的位数，从而降低无效匹配次数。
 *  kmp实现思路：
 *      1.根据模式串获得可匹配表
 *      2.定义主串m指针i，模式串p指针j
 *      3.如果m[i] == p[j],i++,j++  如果j>0 && m[i] != p[j]从next[j-1]（前一个子串）中查找可匹配值，移动j位置
 *
 *  应用场景：kmp字符串查找算法主要用于解决暴力匹配效率低问题
 *
 *  PMT：部份匹配表，保存的是模式串中所有前缀子串与该前缀的后缀子串重合的最大长度，这个长度称为适配值。
 *  当某个长度为n的字符串abcabc的适配值k不为-1时，意味着，在n-k位置可以遇到长度为k的前缀。
 *
 */
public class KMP {
    public int search(String main,String p){
        int[] next = getPmt(p.toCharArray());
        for (int i = 0,j=0; i < main.length() ; i++) {
            while (j>0 && main.charAt(i) != p.charAt(j)){
                j=next[j-1];
            }
            if (main.charAt(i) == p.charAt(j)) j++;

            if (j==p.length()) return i-j+1;

        }
        return -1;
    }
    public int[] getPmt(char[] p){

        int[] next = new int[p.length];
        next[0] = 0;

        for (int i=1,j=0; i < p.length; i++) {
            //当存在连续匹配字符且下一个字符不匹配字符时
            while (j>0 && p[i] != p[j]){
                j=next[j-1];// 查找前一个字符串的可匹配长度值，移动j位置
            }
            //如果模式串与主串相等，模式串指针往后移动;
            if( p[i] == p[j]) j++;

            next[i] = j;
        }
        return next;
    }

    public static void main(String[] args) {
        System.out.println(new KMP().search("ABDDASFJAF","ASFa"));
    }
}
