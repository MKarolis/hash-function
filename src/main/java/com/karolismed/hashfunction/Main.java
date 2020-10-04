package com.karolismed.hashfunction;

import com.karolismed.hashfunction.benchmark.BenchMarkService;
import com.karolismed.hashfunction.hashing.HashingService;

public class Main {

    public static void main(String[] argv) {
        HashingService hashingService = new HashingService();
        BenchMarkService benchMarkService = new BenchMarkService();

//        try {
//            fileUtils.generateTestDataPairsDiffInOneSymbol("100k_pairs_diffOneSymbol.txt");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        benchMarkService.benchmarkHashFunction();
        benchMarkService.benchmarkAgainstHash("SHA-512");
    }
}
