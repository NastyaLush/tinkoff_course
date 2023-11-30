package edu.project4.structures;

import lombok.Data;

@Data public class Pixel {

    private int r = 1;
    private int g = 1;
    private int b = 1;
    private volatile int hitCount = 0;
    private double normal;

    public void incrementHitCount() {
        hitCount++;
    }
}

