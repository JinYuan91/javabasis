package com.java.basis.datastructure.algorithm;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author ：无始
 * @date ：Created in 2021/1/12 2:20 下午
 * @description：冗余连接 684
 * @modified By：
 * @version:
 */
public class UnionFindRedundantConnection {

    /**
     * 数据数据
     */
    private int[] id = null;

    /**
     * 树权重记录 记录根节点下节点数
     */
    private int[] sz = null;

    private int[] result = new int[2];

    public static void main(String[] args) {
        UnionFindRedundantConnection unionFind = new UnionFindRedundantConnection();
        int[][] argsArrary = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        int[] result = unionFind.findRedundantConnection(argsArrary);
        System.out.println(result);
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
            result[0] = p;
            result[1] = q;
            return;
        }
        //加权 两个集合树比较节点数量大小 小树根节点作为大树子节点
        if (sz[i] >= sz[j]) {
            id[j] = i;
            sz[i] += sz[j];
            sz[j] = 1;
        } else {
            id[i] = j;
            sz[j] += sz[i];
            sz[i] = 1;
        }
    }

    public int[] getResult() {
        return result;
    }

    public int[] findRedundantConnection(int[][] edges) {
        UnionFindRedundantConnection unionFind = new UnionFindRedundantConnection();
        unionFind.initData(edges.length * edges[0].length);
        for (int[] item : edges) {
            unionFind.union(item[0], item[1]);
        }
        return unionFind.getResult();
    }


}
