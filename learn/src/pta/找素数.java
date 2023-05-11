package pta;

import java.math.BigInteger;
import java.util.*;

public class 找素数 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String sc = in.next();

        BigInteger m = new BigInteger(sc);
        int n = in.nextInt();
        int i = 0;
        while (i < n) {
            if (isPrime(m)) {
                System.out.println(m);
                i++;
            }
            m = m.add(BigInteger.ONE);
        }
    }

    public static boolean isPrime(BigInteger num) {
        return num.isProbablePrime(50);
    }
}
