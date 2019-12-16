package com.java.basis.datastructure.stack;

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
                if(stacks.empty())
                {
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

    public static void main(String[] args) {
        System.out.println(isValid("]["));
    }
}
