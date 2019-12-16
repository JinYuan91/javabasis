package com.java.basis.datastructure.array;

import java.util.*;

public class ArrayExercise {
    public static int[] twoSum(int[] nums, int target) {

        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 0; i < nums.length; i++) {

            if (hashMap.containsKey(nums[i])) {
                hashMap.get(nums[i]).add(i);
            } else {
                ArrayList<Integer> intqueue = new ArrayList<Integer>(2);
                intqueue.add(i);
                hashMap.put(nums[i], intqueue);

            }


        }
        int[] result = new int[2];
        int value = 0;

        for (Map.Entry<Integer, ArrayList<Integer>> item : hashMap.entrySet()
                ) {
            if (null == item.getValue()) {
                continue;
            }
            value = target - item.getKey();
            result[0] = item.getValue().get(0);

            ArrayList<Integer> value1 = hashMap.get(value);
            if (value1 != null) {
                if (value == item.getKey()) {
                    result[1] = item.getValue().get(1);
                    return result;
                }
                result[1] = value1.get(0);
                return result;
            }
            value = 0;
            item.setValue(null);
        }


        return result;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) break;
            int l = i + 1;
            int r = len - 1;
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            while (l < r) {
                int value=nums[i] + nums[l] + nums[r];
                if (value == 0) {
                    List<Integer> item = new ArrayList<>(3);
                    item.add(nums[i]);
                    item.add(nums[l]);
                    item.add(nums[r]);
                    result.add(item);
                    while (l < r &&nums[i] == nums[l + 1]  ) {
//                        result.remove(item);
                        l++;
                    }


                    while (r > l&&nums[r] == nums[r - 1] ) {
//                        result.remove(item);
                        r--;
                    }
                    l++;
                    r--;
                } else if (value > 0) {
                    r--;
                } else if (value < 0) {
                    l++;
                }

            }
        }

        return result;

    }

    public static void main(String[] args) {
        int[] argss = new int[6];
//        argss[0] = 0;
//        argss[1] = 0;
//        argss[2] = 0;

//        argss[0] = -2;
//        argss[1] = 0;
//        argss[2] = 0;
//        argss[3] = 2;
//        argss[4] = 2;


        argss[0] = -1;
        argss[1] = 0;
        argss[2] = 1;
        argss[3] = 2;
        argss[4] = -1;
        argss[5] = -4;
        List<List<Integer>> result = threeSum(argss);


//        int[] argss = new int[4];
//        argss[0] = -3;
//        argss[1] = 4;
//        argss[2] = 3;
//        argss[3] = 90;
//
//
//        int[] result = twoSum(argss, 0);
        System.out.print("1");

    }
}
