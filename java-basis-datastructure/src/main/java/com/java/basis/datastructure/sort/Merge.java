package com.java.basis.datastructure.sort;

public class Merge {
    public static void main(String[] args) {
        int[] nums = {72, 6, 57, 88, 60, 42, 83, 73};
//        int[] nums = {72, 6};
        mergeSort(nums, 0, nums.length - 1);
        System.out.println('c');

    }

    /**
    * @Description:  归并
    * @Param:
    * @return:
    * @Author: 无始
    * @Date: 2019/12/19+5:43 PM
    */
    private static void mergeSort(int[] nums, int start, int end) {
        if (start < end) {
            int p = (start + end) / 2;
            mergeSort(nums, start, p);//分左
            mergeSort(nums, p + 1, end);//分右
            merge(nums, start, p, end);//合并
        }

    }

    private static void merge(int[] nums, int start, int p, int end) {
        int[] temp = new int[end - start + 1];//辅助数组




        int l = start;
        int r = p + 1;
        int index = 0;

        /**
         /
         /【6】【57】【72】【88】  【42】【60】【73】【83】
         /  l               p      r
         /
         */

        while (l <= p && r <= end) {
            if (nums[l] < nums[r]) {
                temp[index] = nums[l];
                l++;
                index++;
            } else {
                temp[index] = nums[r];
                r++;
                index++;
            }
        }

        while (l <= p) {
            temp[index] = nums[l];
            l++;
            index++;
        }
        while (r <= end) {
            temp[index] = nums[r];
            r++;
            index++;
        }

        for (int i = 0; i < temp.length; i++) {
            nums[i+start] = temp[i];
        }

    }

}
