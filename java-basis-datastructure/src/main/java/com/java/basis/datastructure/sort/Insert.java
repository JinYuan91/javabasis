package com.java.basis.datastructure.sort;

public class Insert {

    public static void main(String[] args) {

        int[] nums = {234,12, 3, 2, 4, 46, 54};
        int[] result = insertSort(nums);
        System.out.println(result[0] == 2);

    }

    /**
    * @Description:插入
    * @Param:
    * @return:
    * @Author: 无始
    * @Date: 2019/12/19+10:15 AM
    */
    public static int[] insertSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >=0; j--) {
                if (nums[j+1] < nums[j]) {
                    int numi = nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = numi;
                }
            }
        }
        return nums;
    }
}
