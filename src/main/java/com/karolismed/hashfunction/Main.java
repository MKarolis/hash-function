package com.karolismed.hashfunction;

import com.karolismed.hashfunction.benchmark.BenchMarkService;
import com.karolismed.hashfunction.hashing.HashingService;
import com.karolismed.hashfunction.utils.FileUtils;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] argv) {
        FileUtils fileUtils = new FileUtils();
        HashingService hashingService = new HashingService();
        BenchMarkService benchMarkService = new BenchMarkService();

//        String data = fileUtils.readStringFromClasspath("HGBIG.txt");
//        System.out.println(hashingService.hash("1234567890".repeat(3) + "abcdefgh"));
//        System.out.println(hashingService.hash("") + "\n");
//        System.out.println(hashingService.hash("3") + "\n");
//        System.out.println(hashingService.hash("4") + "\n");
//
//
//        System.out.println("Match " + benchMarkService.stringMatchPercentage(
//            hashingService.hash("4"),
//            hashingService.hash("5")) + " %"
//        );
        Set<String> set = new HashSet<>();
        int collisions = 0;
        for (long i = 123L; i < 10000000L; i++) {
            if (!set.add(hashingService.hash(Long.toString(i)))) {
                System.out.println(i);
                collisions++;
            }
        }
        System.out.println("Collisions " + collisions);
    }
}
