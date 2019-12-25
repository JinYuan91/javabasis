package com.java.basis.datastructure.sort;

/**
 * @Description: 最大堆排序
 * @Author: 无始
 * @Date: 2019/12/24+4:51 PM
 */
public class Stack {
    public static void main(String[] args) {
        int[] nums = {5, 2, 7, 3, 6, 1, 4};
//        int[] newNums = new int[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            buildStackTree(nums, newNums, i);
//        }


        Stack stackSort = new Stack();
        stackSort.initStack(nums);


        System.out.println(stackSort.pop());
        System.out.println(stackSort.pop());
        System.out.println(stackSort.pop());
        System.out.println(stackSort.pop());
        System.out.println(stackSort.pop());
        stackSort.addStack(20);
        System.out.println(stackSort.pop());
        stackSort.addStack(15);
        System.out.println(stackSort.pop());
        System.out.println(stackSort.pop());
        System.out.println(stackSort.pop());

    }


    private int[] newNums = null;

    /**
     * @Description: 初始化堆
     * @Param:nums 无序数组
     * @return:
     * @Author: 无始
     * @Date: 2019/12/24+4:51 PM
     */
    public void initStack(int[] nums) {
        this.newNums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            buildStackTree(nums, newNums, i);
        }
    }

    /**
     * @Description: 获取堆顶元素
     * @Param:
     * @return:
     * @Author: 无始
     * @Date: 2019/12/24+10:23 PM
     */
    public int pop() {
        int resultValue = newNums[0];

        int endValue = newNums[newNums.length - 1];
        newNums[0] = endValue;


        int[] copyNums = new int[newNums.length - 1];
        for (int i = 0; i < copyNums.length; i++) {
            copyNums[i] = newNums[i];
        }
        newNums = copyNums;


        int contion = 0;
        int currnetIndex = 0;
        while (contion == 0) {
            int childLeftIndex = getIndexChildLeftIndex(currnetIndex);
            int childRightIndex = getIndexChildRightIndex(currnetIndex);
            if (childLeftIndex >= newNums.length ||
                    childRightIndex >= newNums.length ||
                    (newNums[childLeftIndex] < newNums[currnetIndex] && newNums[childRightIndex] < newNums[currnetIndex])) {
                contion++;
                continue;
            }
            if (newNums[childLeftIndex] > newNums[childRightIndex]) {
                replace(newNums, childLeftIndex, childRightIndex);
            }

            if (newNums[childRightIndex] > newNums[currnetIndex]) {
                replace(newNums, childRightIndex, currnetIndex);
                currnetIndex = childRightIndex;
                continue;
            }
            if (newNums[childLeftIndex] > newNums[currnetIndex]) {
                replace(newNums, childLeftIndex, currnetIndex);
                currnetIndex = childLeftIndex;
                continue;
            }


        }

        return resultValue;

    }

    /**
     * @Description: 添加元素至
     * @Param:
     * @return:
     * @Author: 无始
     * @Date: 2019/12/25+10:32 AM
     */
    public void addStack(int num) {
        int[] nums = new int[newNums.length + 1];
        for (int i = 0; i < newNums.length; i++) {
            nums[i] = newNums[i];
        }
        int currentIndex = nums.length - 1;
        nums[currentIndex] = num;
        replaceRecursive(nums, currentIndex);
        newNums = nums;
    }


    /**
     * @Description: 无序数组构建最大堆
     * <p>
     * 父节点：(i-1)/2   i>0
     * 当前节点：i
     * 左子节点：2*i+1
     * 右子节点：2*i+2
     * @Param:
     * @return:
     * @Author: 无始
     * @Date: 2019/12/24+4:56 PM
     */
    private static void buildStackTree(int[] nums, int[] newNums, int currentIndex) {
        int numsValue = nums[currentIndex];
        newNums[currentIndex] = numsValue;
        if (currentIndex == 0) {
            return;
        }

        replaceRecursive(newNums, currentIndex);

    }

    /**
     * @Description: 构造堆树结构
     * @Param:
     * @return:
     * @Author: 无始
     * @Date: 2019/12/24+9:46 PM
     */
    private static void replaceRecursive(int[] newNums, int currentIndex) {
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

    /**
     * @Description: 对应下标值置换
     * @Param:
     * @return:
     * @Author: 无始
     * @Date: 2019/12/24+9:46 PM
     */
    private static void replace(int[] newNums, int one, int two) {
        int replace = newNums[one];
        newNums[one] = newNums[two];
        newNums[two] = replace;
    }

    /**
     * @Description: 获取当前下标节点对应父节点下标
     * @Param: 当前下标节点
     * @return:
     * @Author: 无始
     * @Date: 2019/12/24+6:02 PM
     */
    private static int getIndexParentIndex(int index) {
        if (index > 0) {
            return (index - 1) / 2;
        } else {
            return index;
        }
    }

    /**
     * @Description: 获取当前下标节点对应的左子节点下标
     * @Param:
     * @return:
     * @Author: 无始
     * @Date: 2019/12/24+6:08 PM
     */
    private static int getIndexChildLeftIndex(int index) {

        return index * 2 + 1;
    }

    /**
     * @Description: 获取当前下标节点对应的右子节点下标
     * @Param:
     * @return:
     * @Author: 无始
     * @Date: 2019/12/24+6:08 PM
     */
    private static int getIndexChildRightIndex(int index) {
        return getIndexChildLeftIndex(index) + 1;
    }


}
