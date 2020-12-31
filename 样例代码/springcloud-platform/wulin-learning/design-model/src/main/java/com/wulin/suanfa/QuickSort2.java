package com.wulin.suanfa;

import java.util.Stack;

/**
 * @author Liu WuLin
 * @date 2020 - 05 - 10 15:59
 */
public class QuickSort2 {
    public static void main(String[] args) {
        int[] arr={49,38,65,97};
        int low = 0, high = arr.length-1;
        quickSort(arr,low,high);

        for(int i=0;i<arr.length;i++)
            System.out.println(arr[i]);
    }

    public static void quickSort(int[] arr, int low, int high) {
        if(low >= high) {
            return;
        }

        Stack<Integer> stack = new Stack<Integer>();
        if(low < high) {
            stack.push(low);
            stack.push(high);
            while(!stack.isEmpty()) {
                int right = stack.pop();
                int left = stack.pop();
                int pivot = partition(arr,left,right);
                if(pivot-1 > left) {
                    stack.push(low);
                    stack.push(pivot-1);
                }
                if(pivot+1 < right) {
                    stack.push(pivot+1);
                    stack.push(right);
                }
            }
        }
    }
    public static int partition(int[] arr, int low, int high) {
        int key = arr[low];
        while(low < high) {
            while(low < high && arr[high] >= key) {
                high --;
            }
            arr[low] = arr[high];
            while(low < high && arr[low] <= key) {
                low ++;
            }
            arr[high] = arr[low];
        }
        arr[high] = key;
        return high;
    }
}
