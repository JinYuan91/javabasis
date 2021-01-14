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
                int value = nums[i] + nums[l] + nums[r];
                if (value == 0) {
                    List<Integer> item = new ArrayList<>(3);
                    item.add(nums[i]);
                    item.add(nums[l]);
                    item.add(nums[r]);
                    result.add(item);
                    while (l < r && nums[i] == nums[l + 1]) {
//                        result.remove(item);
                        l++;
                    }


                    while (r > l && nums[r] == nums[r - 1]) {
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

    /**
     * @Description:双指针 字符串反转 leetcode
     * @Param:
     * @return:
     * @Author: 无始
     * @Date: 2019/12/25+6:27 PM
     */
    public void reverseString(char[] s) {
        int endIndex = s.length - 1;
        for (int i = 0; i < s.length; i++) {
            if (i < (endIndex - i)) {
                char value = s[i];
                s[i] = s[endIndex - i];
                s[endIndex - i] = value;
            }

        }
    }

    public static int findMaxConsecutiveOnes(int[] nums) {

        int k = 0;//1 计数
        int maxValue = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                k++;
            } else {
                if (k > 0) {
                    if (k > maxValue) {
                        maxValue = k;
                    }
                    k = 0;
                }
            }
        }
        return k > maxValue ? k : maxValue;
    }

    /**
     * @Description: 3. 滑动窗口 暴力解法
     * @Param:
     * @return:
     * @Author: 无始
     * @Date: 2020/1/3+2:46 PM
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> windowsArray = null;
        int maxWindows = 0;
        for (int i = 0; i < chars.length; i++) {
            if (maxWindows > chars.length - i) {
                continue;
            }
            windowsArray = new HashMap<>();

            windowsArray.put(chars[i], i);
            int windowLen = 1;
            for (int j = i + 1; j < chars.length; j++) {
                if (windowsArray.get(chars[j]) != null) {
                    windowsArray.remove(chars[i]);
                    break;
                }
                windowsArray.put(chars[j], j);
                windowLen++;
                if (windowLen > maxWindows) {
                    maxWindows = windowLen;
                }
            }
            if (windowLen > maxWindows) {
                maxWindows = windowLen;
            }
        }
        return maxWindows;
    }

    /**
     * @Description: 3.  滑动窗口解法
     * @Param:
     * @return:
     * @Author: 无始
     * @Date: 2020/1/3+2:46 PM
     */
    public static int lengthOfLongestSubstring2(String s) {
        int len = s.length();
        int begin = 0;
        int end = 0;
        int ans = 0;
        HashMap<Character, Integer> windowsArray = new HashMap<>();
        for (; end < len; end++) {
            if (windowsArray.containsKey(s.charAt(end))) {
                begin = Math.max(windowsArray.get(s.charAt(end)) + 1, begin);
            }
            ans = Math.max(ans, end - begin + 1);
            windowsArray.put(s.charAt(end), end);
        }
        return ans;
    }

    public static void main(String[] args) {

//        String s ="abcabcbb";
        String s = "tmmzuxt";
//        int result = lengthOfLongestSubstring(s);
        int result2 = lengthOfLongestSubstring2(s);
//        int[] ss = {1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1};
//        int[] ss = {1, 1,1};
//        int results = findMaxConsecutiveOnes(ss);
//        int[] argss = new int[6];
//        argss[0] = 0;
//        argss[1] = 0;
//        argss[2] = 0;

//        argss[0] = -2;
//        argss[1] = 0;
//        argss[2] = 0;
//        argss[3] = 2;
//        argss[4] = 2;


//        argss[0] = -1;
//        argss[1] = 0;
//        argss[2] = 1;
//        argss[3] = 2;
//        argss[4] = -1;
//        argss[5] = -4;
//        List<List<Integer>> result = threeSum(argss);


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

    /**
    * @Description: 1018. 可被 5 整除的二进制前缀 题解
    * @Param:  * @param null
    * @return:
    * @Author: 无始
    * @Date: 2021/1/14+10:38 上午
    */
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> result = new ArrayList();
        int temp = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                temp = temp * 2;
            } else {
                temp = temp * 2 + 1;
            }
            temp = temp % 5;
            if (temp == 0) {
                result.add(true);
            } else {
                result.add(false);
            }
        }
        return result;
    }

    /**
    * @Description: 1018. 可被 5 整除的二进制前缀 题解 优化
    * @Param:  * @param null
    * @return:
    * @Author: 无始
    * @Date: 2021/1/14+10:39 上午
    */
    public List<Boolean> prefixesDivBy5Ex(int[] A) {
        List<Boolean> result = new ArrayList();
        int temp = 0;
        for (int i : A) {
            if (i == 0) {
                temp = temp * 2 % 5;
            } else {
                temp = (temp * 2 + 1) % 5;
            }
            if (temp == 0) {
                result.add(true);
            } else {
                result.add(false);
            }
        }
        return result;
    }
}
