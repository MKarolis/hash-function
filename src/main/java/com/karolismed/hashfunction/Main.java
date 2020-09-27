package com.karolismed.hashfunction;

import com.karolismed.hashfunction.hashing.HashingService;
import com.karolismed.hashfunction.utils.FileUtils;

public class Main {

    public static void main(String[] argv) {
        FileUtils fileUtils = new FileUtils();
        HashingService hashingService = new HashingService();

        String data = fileUtils.readStringFromClasspath("input.txt");
        System.out.println(hashingService.hash(data));
    }
}
