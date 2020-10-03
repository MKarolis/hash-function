package com.karolismed.hashfunction;

import com.karolismed.hashfunction.benchmark.BenchMarkService;
import com.karolismed.hashfunction.hashing.HashingService;
import com.karolismed.hashfunction.utils.FileUtils;

public class Main {

    public static void main(String[] argv) {
        FileUtils fileUtils = new FileUtils();
        HashingService hashingService = new HashingService();
        BenchMarkService benchMarkService = new BenchMarkService();

//        String data = fileUtils.readStringFromClasspath("HGBIG.txt");
//        System.out.println(hashingService.hash("1234567890".repeat(3) + "abcdefgh"));
        System.out.println(hashingService.hash("ab0") + "\n");
        System.out.println(hashingService.hash("ab8") + "\n");

        System.out.println("Match " + benchMarkService.stringMatchPercentage(
            hashingService.hash("ab0"), hashingService.hash("ab8")) + " %"
        );
    }
}
