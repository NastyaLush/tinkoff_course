package edu.project4;

import edu.project4.structures.Point;
import lombok.Getter;

public class AffineSpace {

    @Getter private final int red;
    @Getter private final int green;
    @Getter private final int blue;
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;

    public AffineSpace(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.red = (int) (Math.random() * 256);
        this.blue = (int) (Math.random() * 256);
        this.green = (int) (Math.random() * 256);
    }

    public AffineSpace() {
        do {
            do {
                this.a = random();
                this.d = random();
            }
            while ((a * a + d * d) > 1);
            do {
                b = random();
                e = random();
            }
            while ((b * b + e * e) > 1);
        }
        while ((a * a + b * b + d * d + e * e) >
            (1 + (a * e - d * b) * (a * e - d * b)));
        this.c = random();
        this.f = random();

        this.red = (int) (Math.random() * 256);
        this.blue = (int) (Math.random() * 256);
        this.green = (int) (Math.random() * 256);
    }

    public AffineSpace(
        double red,
        double green,
        double blue,
        double a,
        double b,
        double c,
        double d,
        double e,
        double f
    ) {
        this.red = (int) (red * 255);
        this.green = (int) (green * 255);
        this.blue = (int) (blue * 255);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    public Point apply(Point point) {
        return new Point(a * point.x() + b * point.y() + c, d * point.x() + e * point.y() + f);
    }

    private double random() {
        return Math.random() * ((double) 1 - (double) -1) + (double) -1;
    }
}
