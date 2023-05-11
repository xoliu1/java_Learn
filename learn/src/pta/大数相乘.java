package pta;

import java.math.BigInteger;
import java.util.*;

public class 大数相乘 {
    public static void main(String[] args) {
        String s1,s2;
        Scanner in = new Scanner(System.in);
        s1=in.next();s2=in.next();
        BigInteger n1 = new BigInteger(s1);
        BigInteger n2 = new BigInteger(s2);
        System.out.println(n1.divide(n2));
    }
}
