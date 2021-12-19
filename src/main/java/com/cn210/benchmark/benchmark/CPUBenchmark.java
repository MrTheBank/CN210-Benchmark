package com.cn210.benchmark.benchmark;

import java.util.Random;

public class CPUBenchmark {
    public String insertString(String org_string, String add_string, int index){
        StringBuilder temp_string = new StringBuilder();
        // find we simply add chr 1 by 1 till i equal index then we add the "XXXXX" String
        for (int i = 0; i < org_string.length(); i++) {
            if (i == index) {
                temp_string.append(add_string);
            } else {
                temp_string.append(org_string.charAt(i));
            }
        }

        return temp_string.toString();
    }

    public long start() {
        Random rand = new Random();
        // Start the timer
        long start = System.currentTimeMillis();
        // start out with the test control string
        String test_control = "Hello it me your student in your classroom";
        // we do this 500 million time randomly insert "XXXXX" in to the test_control string and replace it with "CN210"
        for (int i = 0; i < 500000000; i++) { // 500000000
            int rand_index = rand.nextInt(test_control.length());
            String test = insertString(test_control, "XXXXX", rand_index).replaceAll("XXXXX", "CN210");
        }
        // calculate the stop time and return it in minute
        return System.currentTimeMillis() - start;
    }
}
