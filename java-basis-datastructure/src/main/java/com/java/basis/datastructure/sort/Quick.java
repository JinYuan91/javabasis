package com.java.basis.datastructure.sort;

public class Quick {
    public static void main(String[] args) {
        int[] nums={72,6,57,88,60,42,83,73,48,85};
//        int[] nums = {72, 6};
        final int l = 0;
        final int r = nums.length - 1;
        recursive(nums, l, r);
        System.out.println('c');
    }

    public static int[] quickSort(int[] nums) {
        return null;
    }

    private static void recursive(int[] nums, final int l, final int r) {
        int p = l;//定义数组偏中间值为基准数位置

        int pValue = nums[p];//获取基准数

        int left = l;
        int right = r;
        while (left < right) {
            if (nums[right] >= pValue) {
                right--;
                continue;
            } else {
                nums[left] = nums[right];

                while (left < right) {
                    if (nums[left] < pValue) {
                        left++;
                        continue;
                    } else {
                        nums[right] = nums[left];
                        break;
                    }
                }

            }

        }
        if (left == right) {
            nums[left] = pValue;
        }
        /*--第一次排序完成--*/
        //分治  左边部分 小于基准数的排序
        if (left -l>= 2) {
            recursive(nums, l, left - 1);
        }
        //分治
        if (r - right >= 2) {
            recursive(nums, right + 1, r);
        }
    }
}
