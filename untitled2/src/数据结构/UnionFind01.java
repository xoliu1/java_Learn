package 数据结构;

import java.util.*;

public class UnionFind01 {
}

/**
class UnionFind {
    int cntOfUnion; //非连通集合个数
    int parent[];   //父节点索引数组
    int size[];     //各个点的集合大小数组(仅父节点的数值有效）


    public int getCntOfUnion() {
        return cntOfUnion;
    }

    public UnionFind(int num) {  //输入数据个数
        this.parent = new int[num];
        this.size = new int[num];
        Arrays.fill(size, 1);    //刚开始每个数字都是一个集合，所以size为1
        this.cntOfUnion = num;
    }

    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public boolean unite(int x, int y) {     //返回值为是否合并成功
        x = find(x);
        y = find(y);
        if (x == y) {
            return false;   // 已在同一集合中
        }
        //接下来让集合小的并向集合大的
        if (size[x] < size[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }  // 保证 size[x] >= size[y]
        parent[y] = x;  //让小团体老大归降于大团体老大
        --cntOfUnion;   //非连通集合数 - 1
        return true;
    }

    public boolean isConnected(int x, int y){    //判断是否在一个集合中
        return find(x) == find(y);
    }
}
*/

/**
 * 547.省份数量
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，
 * 而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 * */
/*class SolutionOf547 {
    int cntOfUnion; //非连通集合个数
    int parent[];   //父节点索引数组


    public int getCntOfUnion() {
        return cntOfUnion;
    }

    public void unionfind(int num) {
        this.parent = new int[num];
        this.cntOfUnion = num;
        for (int i = 1; i < num ; ++i) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public boolean unite(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return false;
        }
        parent[y] = x;
        --cntOfUnion;
        return true;
    }

    public int findCircleNum(int[][] isConnected) {
        unionfind(isConnected.length);
        for (int i = 0; i < isConnected.length; ++i) {
            for (int j = 0; j < isConnected.length; ++j) {
                if(isConnected[i][j] == 1){
                    unite(i, j);
                }
            }
        }
        return cntOfUnion;

    }
}*/

/**684.冗余连接
 * 树可以看成是一个连通且无环的无向图。
 * 给定往一棵n 个节点 (节点值1～n) 的树中添加一条边后的图。添加的边的两个顶点包含在 1 到 n中间
 * 且这条附加的边不属于树中已存在的边。图的信息记录于长度为 n 的二维数组 edges，edges[i] = [ai, bi]表示图中在 ai 和 bi 之间存在一条边。
 * 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组edges中最后出现的边。
 */

/*class SolutionOf684 {
    int[] parent;
    public void union(int x, int y){
        parent[find(x)] = parent[find(y)];
    }

    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    public boolean isConnected(int x, int y){
        return find(x) == find(y);
    }
    public int[] findRedundantConnection(int[][] edges) {
        parent = new int[edges.length + 1];
        for (int i = 1; i < parent.length; ++i) {
            parent[i] = i;
        }
        for (int i = 0; i < edges.length; ++i) {
            int x = edges[i][0], y = edges[i][1];
            if(!isConnected(x, y)){
                union(x, y);
            }else{
                return edges[i];
            }
        }
        return new int[0];
    }
}*/


