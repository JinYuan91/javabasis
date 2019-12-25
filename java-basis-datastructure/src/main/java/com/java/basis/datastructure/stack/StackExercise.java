package com.java.basis.datastructure.stack;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class StackExercise {
    public static boolean isValid(String s) {

        if (Objects.equals(s, null) || Objects.equals(s, "")) {
            return true;
        }
        Stack<Character> stacks = new Stack<Character>();


        char[] chars = s.toCharArray();
        if (chars.length % 2 != 0) {
            return false;
        }
        for (char item : chars) {
            if (Objects.equals('{', item) || Objects.equals('[', item) || Objects.equals('(', item)) {
                stacks.push(item);
            } else if (Objects.equals('}', item) || Objects.equals(']', item) || Objects.equals(')', item)) {
                if (stacks.empty()) {
                    return false;
                }
                Character topValue = stacks.pop();
                if (Objects.equals('}', item) && Objects.equals('{', topValue) == false) {
                    return false;
                } else if (Objects.equals(']', item) && Objects.equals('[', topValue) == false) {
                    return false;
                } else if (Objects.equals(')', item) && Objects.equals('(', topValue) == false) {
                    return false;
                }
            }
        }
        return stacks.empty();


    }

    /**
    * @Description: 滑动窗口最大值练习
    * @Param:
    * @return:
    * @Author: 无始
    * @Date: 2019/12/25+4:13 PM
    */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length<=0)
        {
            return new int[]{};
        }
        if(k<=0)
        {
            return new int[]{};
        }
        int[] result = new int[nums.length - k + 1];

        for (int i = 0; i < (nums.length - k + 1); i++) {
            int[] items = new int[k];
            int[] copyItems = new int[k];
            for (int j = 0; j < k; j++) {
                items[j] = nums[i + j];
            }
            for (k = 0; k < items.length; k++) {
                buildStackTree(items, copyItems, k);
            }
            result[i] = copyItems[0];
        }
        return result;
    }


    private void buildStackTree(int[] nums, int[] newNums, int currentIndex) {
        int numsValue = nums[currentIndex];
        newNums[currentIndex] = numsValue;
        if (currentIndex == 0) {
            return;
        }

        replaceRecursive(newNums, currentIndex);

    }


    private void replaceRecursive(int[] newNums, int currentIndex) {
        int parentIndex = getIndexParentIndex(currentIndex);
        if (newNums[parentIndex] >= newNums[currentIndex]) {
            return;
        } else {
            replace(newNums, parentIndex, currentIndex);
            if (parentIndex > 0) {
                replaceRecursive(newNums, parentIndex);
            }
        }
    }


    private void replace(int[] newNums, int one, int two) {
        int replace = newNums[one];
        newNums[one] = newNums[two];
        newNums[two] = replace;
    }


    private  int getIndexParentIndex(int index) {
        if (index > 0) {
            return (index - 1) / 2;
        } else {
            return index;
        }
    }


    private int getIndexChildLeftIndex(int index) {

        return index * 2 + 1;
    }


    private  int getIndexChildRightIndex(int index) {
        return getIndexChildLeftIndex(index) + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        StackExercise s=new StackExercise();
        int[] result = s.maxSlidingWindow(nums, 3);
        System.out.println('c');
    }
}
