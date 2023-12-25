package edu.project4.structures;

import lombok.Data;

@Data public class Pixel {

    private int r;
    private int g;
    private int b;
    private volatile int hitCount = 0;
    private double normal;

    public Pixel(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Pixel(int r, int g, int b, int hitCount, double normal) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.hitCount = hitCount;
        this.normal = normal;
    }

    public Pixel() {
    }

    public void incrementHitCount() {
        hitCount++;
    }
}

