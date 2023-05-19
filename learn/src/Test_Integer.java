/*
class Input {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public int nextInt() throws IOException {
        in.nextToken();
        return (int)in.nval;
    }
}*/

import java.math.BigInteger;

public class Test_Integer {
    public static void main(String[] args) {
        MyInteger myInt = new MyInteger(31);
        System.out.println("Value: " + myInt.getValue());
        System.out.println("value isEven: " + myInt.isEven());
        System.out.println("value isOdd: " + myInt.isOdd());
        System.out.println("value isPrime: " + myInt.isPrime());
        System.out.println("=============================================");
        System.out.println("isEven(46): " + MyInteger.isEven(46));
        System.out.println("isOdd(46): " + MyInteger.isOdd(46));
        System.out.println("isPrime(46): " + MyInteger.isPrime(46));
        char[] charArray = {'4', '7', '8', '4', '1'};
        System.out.println("parseInt(4,7,8,4,1): " + MyInteger.parseInt(charArray));

        String str = "666";
        System.out.println("parseInt(666): " + MyInteger.parseInt(str));
    }
}

class MyInteger {
    private int value;

    public MyInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isEven() {
        return value % 2 == 0;
    }

    public boolean isOdd() {
        return value % 2 == 1;
    }

    public boolean isPrime() {
        return new BigInteger(Integer.toString(value)).isProbablePrime(10);
    }

    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    public static boolean isOdd(int n) {
        return n % 2 != 0;
    }

    public static boolean isPrime(int n) {
        if (n == 1 || n == 2) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEven(MyInteger myInt) {
        return myInt.isEven();
    }

    public static boolean isOdd(MyInteger myInt) {
        return myInt.isOdd();
    }

    public static boolean isPrime(MyInteger myInt) {
        return myInt.isPrime();
    }

    public boolean equals(int n) {
        return value == n;
    }

    public boolean equals(MyInteger myInt) {
        return value == myInt.getValue();
    }

    public static int parseInt(char[] charArray) {
        int result = 0;
        for (char c : charArray) {
            if (Character.isDigit(c)) {
                result = result * 10 + (c - '0');
            }
        }
        return result;
    }

    public static int parseInt(String str) {
        return parseInt(str.toCharArray());
    }
}
