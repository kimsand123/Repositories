package com.company;

public class Recursive {
    
    public int factorial (int n) {
        if (n <= 1) {
            return (1);
        }
        return (factorial(n - 1) * n);
    }

    public String simplify (int nominator, int denominator){
        int divisor = gcd(nominator, denominator);
        return nominator/divisor + "/" + denominator/divisor;
    }

    public int gcd(int a, int b){
        if (a > b){
            return gcd(a-b, b);
        }
        if (b > a){
            return gcd(a, b-a);
        }
        return a;
    }

    public int fibnoacci (int n) {
        if (n<3&&n>0){
            return 1;
        }
        if (n <= 0){
            return 0;
        }
        return fibnoacci(n-1)+fibnoacci(n-2);
    }

    public int power(int b, int e){
        if (e-1 < 1){
            return b;
        }
        return power(b,e-1)*b;
    }
}
