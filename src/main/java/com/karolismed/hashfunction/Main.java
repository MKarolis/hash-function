package com.karolismed.hashfunction;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.karolismed.hashfunction.benchmark.BenchMarkService;
import com.karolismed.hashfunction.hashing.HashingService;
import com.karolismed.hashfunction.io.IOService;
import com.karolismed.hashfunction.jcommander.CLParams;

public class Main {

    public static void main(String[] args) {
        HashingService hashingService = new HashingService();
        BenchMarkService benchMarkService = new BenchMarkService();
        IOService ioService = new IOService();

        CLParams clParams = new CLParams();
        JCommander jCommander = JCommander.newBuilder()
            .addObject(clParams)
            .build();

        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            System.exit(1);
        }

        if (clParams.isHelp()) {
            jCommander.usage();
            System.exit(0);
        }

        switch (clParams.getOperationMode()) {
            case CL_ARG: {
                System.out.println(hashingService.hash(clParams.getValue()));
            }
            case BENCHMARK: {
                benchMarkService.benchmarkHashFunction();
            }
            case VERSUS: {
                benchMarkService.benchmarkAgainstHash(clParams.getValue());
            }
            case INTERACTIVE: {
                ioService.hashInputFromConsole();
            }
            case FILE_ARG: {
                ioService.hashInputFromFile(clParams.getValue());
            }
        }
    }
}
