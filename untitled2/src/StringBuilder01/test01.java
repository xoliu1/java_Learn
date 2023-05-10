package StringBuilder01;

import java.time.LocalDate;
import java.util.*;

public class test01 {
    public static void main(String[] args) {
//        stu a1 = new stu("xiaoliu");
//        stu a2 = new stu("xiaoliu");
//        HashSet hashSet = new HashSet();
//        hashSet.add(a1);
//        hashSet.add(a2);
//        System.out.println(hashSet.size());
        StringBuilder s1 = new StringBuilder("abc");
    }
}

class stu{
    public String name;

    public stu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}