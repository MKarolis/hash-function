package com.karolismed.hashfunction.utils;

import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public String readStringFromClasspath(String fileName) {
        String data = "";

        try (InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName)) {
            data = new String(in.readAllBytes());
        } catch (IOException ex) {
            System.out.println("ERROR: Error while reading data from file " + fileName);
        }

        return data;
    }

}
