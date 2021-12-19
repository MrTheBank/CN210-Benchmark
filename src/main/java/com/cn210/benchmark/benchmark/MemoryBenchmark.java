package com.cn210.benchmark.benchmark;

import java.util.Vector;

public class MemoryBenchmark {
    public long start() {
        // create vector to hold byte to consume ram
        Vector v = new Vector();
        // start the timer
        long start = System.currentTimeMillis();
        // each loop will assign 1MB to the ram and remove it from the ram
        // we repeate this process 5M time
        for (int i = 0; i < 5000000; i++) { //5000000
            byte b[] = new byte[1048576];
            v.add(b);
            v.remove(b);
        }
        // calculate the stop time and return in string format
        return System.currentTimeMillis() - start;
    }
}
