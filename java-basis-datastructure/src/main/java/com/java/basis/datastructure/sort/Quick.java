package com.java.basis.datastructure.sort;

public class Quick {
    public static void main(String[] args) {
        int[] nums = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
//        int[] nums = {72, 6};
        final int l = 0;
        final int r = nums.length - 1;
        quickSort(nums, l, r);
        System.out.println('c');

        /**
         * 递归
         * 「找基准点 排序 挖坑填数
         * 然后分左右两快区域 」
         *
         */
    }

    /**
     * @Description:快速排序 原地排序
     * @Param:
     * @return:
     * @Author: 无始
     * @Date: 2019/12/19+10:22 AM
     */
    private static void quickSort(int[] nums, final int l, final int r) {
        int p = l;//定义数组偏中间值为基准数位置

        int pValue = nums[p];//获取基准数

        int left = l;
        int right = r;

        /*--挖坑填数--*/
        while (left < right) {
            if (nums[right] >= pValue) {//如果大于等于基准数 则right向左偏移
                right--;
                continue;
            } else {
                //如果小于基准数 则把当前right对应数据挖走 填入到之前挖的坑中
                nums[left] = nums[right];

                while (left < right) {
                    if (nums[left] < pValue) {//如果小于基准数 则left向右偏移
                        left++;
                        continue;
                    } else {//如果大于基准数 则把left对应数据挖走 填入到之前right所留坑中
                        nums[right] = nums[left];
                        break;
                    }
                }

            }

        }
        if (left == right) {//将基准数填入到最后的坑位中
            nums[left] = pValue;
        }
        /*至此--第一次排序完成--*/
        //分治  左边部分 小于基准数的排序
        if (left - l >= 2) {
            quickSort(nums, l, left - 1);
        }
        //分治  右边部分 大于基准数的排序
        if (r - right >= 2) {
            quickSort(nums, right + 1, r);
        }
    }
}
