package com.java.basis.datastructure.algorithm;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author ：无始
 * @date ：Created in 2021/1/12 2:20 下午
 * @description：并查集算法
 * @modified By：
 * @version:
 */
public class UnionFind {

    /**
     * 数据数据
     */
    private int[] id = null;

    /**
     * 树权重记录 记录根节点下节点数
     */
    private int[] sz = null;

    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind();
//        unionFind.initData(10);
//        unionFind.union(1, 2);
//        unionFind.union(2, 7);
//        unionFind.union(3, 4);
//        unionFind.union(3, 8);
//        unionFind.union(4, 9);
//        unionFind.union(0, 5);
//        unionFind.union(5, 6);
////        unionFind.union(6, 8);
//        System.out.println(unionFind.connection(0, 8));
//        System.out.println(unionFind.connection(0, 7));

//        char[][] grid = new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        char[][] grid = new char[][]{{'1', '0', '1', '1', '1'}, {'1', '0', '1', '0', '1'}, {'1', '1', '1', '0', '1'}};
        System.out.println(unionFind.numIslands(grid));
    }

    public void initData(int size) {
        id = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    /**
     * @Description: 根据值查询根节点
     * @Param: * @param null
     * @return:
     * @Author: 无始
     * @Date: 2021/1/12+2:37 下午
     */
    public int findRoot(int value) {
        int index = value;
        while (id[index] != index) {
            index = id[index];
            id[index] = id[id[index]];//树路径压缩 缩短树高度 将当前节点 设置到祖父节点下 跟父节点平级
        }
        return id[index];
    }

    /**
     * @Description: 连接点 (归并到同一集合)
     * @Param: * @param null
     * @return:
     * @Author: 无始
     * @Date: 2021/1/12+2:47 下午
     */
    public void union(int p, int q) {
        int i = findRoot(p);
        int j = findRoot(q);
        if (i == j) {
            return;
        }
        //加权 两个集合树比较节点数量大小 小树根节点作为大树子节点
        if (sz[i] >= sz[j]) {
            id[j] = i;
            sz[i] += sz[j];
            sz[j]=1;
        } else {
            id[i] = j;
            sz[j] += sz[i];
            sz[i]=1;
        }
    }

    /**
     * @Description: 检查点是否想通 (是否在同一集合内)
     * @Param: * @param null
     * @return:
     * @Author: 无始
     * @Date: 2021/1/12+2:47 下午
     */
    public boolean connection(int p, int q) {
        int i = findRoot(p);
        int j = findRoot(q);
        if (i == j) {
            return true;
        } else {
            return false;
        }
    }

    /** 
    * @Description: 200: 岛屿数量
    * @Param:  * @param null 
    * @return:  
    * @Author: 无始
    * @Date: 2021/1/13+11:38 上午 
    */ 
    public int numIslands(char[][] grid) {
        int rowLength = grid.length;
        int colLength = grid[0].length;
        int count = 0;
        UnionFind unionFind = new UnionFind();
        unionFind.initData(rowLength * colLength);
        for (int rowIndex = 0; rowIndex < rowLength; rowIndex++) {
            for (int colIndex = 0; colIndex < colLength; colIndex++) {
                boolean union = false;
                //向右
                if (colIndex < colLength - 1) {
                    if (Objects.equals(grid[rowIndex][colIndex], '1')) {
                        if (Objects.equals(grid[rowIndex][colIndex], grid[rowIndex][colIndex + 1])) {
                            unionFind.union(unionFind.getArrayIndex(rowIndex, colIndex, colLength), unionFind.getArrayIndex(rowIndex, colIndex + 1, colLength));
                            union = true;
                        }
                    }
                }
                //向下
                if (rowIndex < rowLength - 1) {
                    if (Objects.equals(grid[rowIndex][colIndex], '1')) {
                        if (Objects.equals(grid[rowIndex][colIndex], grid[rowIndex + 1][colIndex])) {
                            unionFind.union(unionFind.getArrayIndex(rowIndex, colIndex, colLength), unionFind.getArrayIndex(rowIndex + 1, colIndex, colLength));
                            union = true;
                        }
                    }
                }

                if (!union && Objects.equals(grid[rowIndex][colIndex], '1')) {
                    if (unionFind.validateSingleNode(unionFind.getArrayIndex(rowIndex, colIndex, colLength))) {
                        count++;
                    }
                }

            }
        }
        return unionFind.getConnectedComponent() + count;
    }

    public int getArrayIndex(int rowIndex, int colIndex, int colLength) {
        //二维矩阵m*n,在一维数组的位置是：（第几行×矩阵宽度）+ 在第几列
        return rowIndex * colLength + colIndex;
    }

    public int getConnectedComponent() {
        return (int) Arrays.stream(sz).filter(t -> t > 1).count();
    }

    public boolean validateSingleNode(int arrayIndex) {
        if (arrayIndex == findRoot(arrayIndex)) {
            return true;
        } else {
            return false;
        }

    }


}
