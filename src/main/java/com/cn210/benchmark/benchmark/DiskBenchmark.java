package com.cn210.benchmark.benchmark;

import com.cn210.benchmark.App;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class DiskBenchmark {
    public long start() {
        // start the Timer
        long start = System.currentTimeMillis();

        try {
            // create temp file, reader, and random obj
            //File fileControl = new File("test_control.txt");
            InputStream inputStream = App.class.getResourceAsStream("/test_control.txt");
            Scanner fileReader = new Scanner(inputStream);
            Random rand = new Random();

            // we do this process for 5M time
            for (int j = 0; j < 5000000; j++) { //5000000
                // create the writer here everytime and close at the end
                FileWriter fileWriter = new FileWriter("test_control.txt");

                // we write 1000 alphabet in the file
                for (int i = 0; i < 1000; i++) {
                    fileWriter.write((char) ('a' + rand.nextInt(26)));
                }
                // then read it
                while (fileReader.hasNext()) {
                    String data = fileReader.next();
                }
                // close the file and repeat
                fileWriter.close();
            }

            File fileControl = new File("test_control.txt");
            fileControl.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // you know the drill. we get the stop time and return it
        return System.currentTimeMillis() - start;
    }
}
