package com.wulin.suanfa;

/**
 * @author Liu WuLin
 * @date 2020 - 05 - 10 15:57
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr={49,38,65,97,3};
        int low = 0, high = arr.length-1;
        quickSort(arr,low,high);

        for(int i=0;i<arr.length;i++)
            System.out.println(arr[i]);
    }

    public static void quickSort(int[] arr, int low, int high) {
        if(low >= high) {
            return;
        }
        int index = partition(arr,low,high);
        quickSort(arr,low,index-1);
        quickSort(arr,index+1,high);
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