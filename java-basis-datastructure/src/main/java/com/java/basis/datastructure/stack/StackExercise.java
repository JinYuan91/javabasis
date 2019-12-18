package com.java.basis.datastructure.stack;

import java.util.ArrayList;
import java.util.List;
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
     * n=3
     * [
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     *
     * @Description:
     * @Param:
     * @return:
     * @Author: 无始
     * @Date: 2019/12/17+10:32 AM
     */
//    public static List<String> generateParenthesis(int n) {
//        List<String> result = new ArrayList<>();
//        if (n <= 0) {
//            return result;
//        }
//        for (int b = 1; b <= n; b++) {
//            StringBuilder stringBuilder = new StringBuilder();
//
//            for (int i = 0; i < b; i++) {
//                stringBuilder.append('(');
//            }
//            for (int r = n-b; r >=0; r--) {
//                stringBuilder.append(')');
//
//            }
//        }
//        return result;
//    }

    public static void main(String[] args) {
//       List<String> result= generateParenthesis(3);
       System.out.println('c');
    }
}
