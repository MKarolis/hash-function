package com.karolismed.hashfunction.io;

import com.karolismed.hashfunction.constants.ResourceFilename;
import com.karolismed.hashfunction.hashing.HashingService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IOService {
    private HashingService hashingService;

    public IOService() {
        this.hashingService = new HashingService();
    }

    public void hashInputFromConsole() {
        System.out.println("Enter text and press [Enter] to hash it. Press [Ctr + C] to exit");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println(hashingService.hash(reader.readLine()));
            }
        } catch (IOException e) {
            System.out.println("ERROR: Failed to get input from console");
        }
    }

    public void hashInputFromFile(String fileName) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(fileName));
            System.out.println(hashingService.hash(new String(bytes)));
        }  catch (IOException e) {
            System.out.println("ERROR: Reading file " + fileName + ", make sure it exists");
            System.exit(0);
        }
    }
}
