package hashmap01;

import java.util.*;
import java.util.stream.*;

public class haxi01 {
    public static void main(String[] args) {
        TreeSet treeSet = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });
        treeSet.add(1);
        treeSet.add(5);
        treeSet.add(52);
        treeSet.add(-1);
        treeSet.add(4);
        treeSet.add(4);
        System.out.println(treeSet);
    }
}


