package pta;

import java.math.BigInteger;
import java.util.*;

public class 简单计算器 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str= in.next();
        if((str.indexOf('/') != -1)==true) {
            String[] ob = str.split("/", 0);
            BigInteger n1 = new BigInteger(ob[0]);
            BigInteger n2 = new BigInteger(ob[1]);
            System.out.println(n1.divide(n2));
        }else if((str.indexOf('-') != -1)==true){
            String[] ob = str.split("-", 0);
            BigInteger n1 = new BigInteger(ob[0]);
            BigInteger n2 = new BigInteger(ob[1]);
            System.out.println(n1.subtract(n2));
        }else if((str.indexOf('+') != -1)==true){
            String[] ob = str.split("+", 0);
            BigInteger n1 = new BigInteger(ob[0]);
            BigInteger n2 = new BigInteger(ob[1]);
            System.out.println(n1.add(n2));
        }else if((str.indexOf('*') != -1)==true){
            String[] ob = str.split("*", 0);
            BigInteger n1 = new BigInteger(ob[0]);
            BigInteger n2 = new BigInteger(ob[1]);
            System.out.println(n1.multiply(n2));
        }
    }
}
