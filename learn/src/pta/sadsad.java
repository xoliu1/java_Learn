package pta;

import java.math.BigInteger;
import java.util.*;


    public class sadsad {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int num = input.nextInt();
            if(num == 0){
                System.out.println(0);
                System.exit(1);
            }
            BigInteger n = BigInteger.ONE;
            BigInteger m = BigInteger.ONE;
            for (int i = 1; i <= num; i++) {
                n = n.multiply(m);
                m = m.add(BigInteger.ONE);}
            System.out.println(n);
        } }


