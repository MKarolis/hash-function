package com.karolismed.hashfunction;

import com.karolismed.hashfunction.benchmark.BenchMarkService;
import com.karolismed.hashfunction.hashing.HashingService;
import com.karolismed.hashfunction.utils.FileUtils;

import java.io.IOException;

public class Main {

    public static void main(String[] argv) {
        FileUtils fileUtils = new FileUtils();
        HashingService hashingService = new HashingService();
        BenchMarkService benchMarkService = new BenchMarkService();

//        try {
//            fileUtils.generateTestDataPairsDiffInOneSymbol("100k_pairs_diffOneSymbol.txt");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        benchMarkService.benchmarkHashFunction();

    }
}
