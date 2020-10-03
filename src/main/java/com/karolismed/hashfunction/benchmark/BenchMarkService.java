package com.karolismed.hashfunction.benchmark;

public class BenchMarkService {
    public double stringMatchPercentage(String base, String test) {
        if (base.length() != test.length()) {
            throw new RuntimeException(String.format(
                "Strings %s and %s are of different lengths", base, test)
            );
        }
        int sameChars = 0;
        for (int i = 0; i < base.length(); i++) {
            if (base.charAt(i) == test.charAt(i)) {
                sameChars++;
            }
        }

        return (sameChars / (double)base.length()) * 100.0;
    }
}
