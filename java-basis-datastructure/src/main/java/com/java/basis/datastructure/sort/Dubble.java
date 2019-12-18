package com.java.basis.datastructure.sort;

public class Dubble {

    public static void main(String[] args) {

        int[] nums={12,3,2,4,46,54,234};
        int[] result= dubbleSort(nums);
        System.out.println(result[0]==2);

    }


    public static int[] dubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[i]>nums[j])
                {
                    int numsj=nums[j];
                    nums[j]=nums[i];
                    nums[i]=numsj;
                }
            }
        }
        return nums;
    }


}
