package edu.project4;

import edu.project4.structures.Point;
import lombok.Getter;
import static edu.project4.Random.randomDouble;

@Getter
public class AffineSpace {

    private static final Integer COUNT_OF_COLORS = 256;
    private static final Integer MIN_VALUE = -1;
    private static final Integer MAX_VALUE = 1;

    private final int red;
    private final int green;
    private final int blue;
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
        this.red = (int) (Math.random() * COUNT_OF_COLORS);
        this.blue = (int) (Math.random() * COUNT_OF_COLORS);
        this.green = (int) (Math.random() * COUNT_OF_COLORS);
    }

    public AffineSpace() {
        do {
            do {
                this.a = randomDouble(MIN_VALUE, MAX_VALUE);
                this.d = randomDouble(MIN_VALUE, MAX_VALUE);
            } while ((a * a + d * d) > 1);
            do {
                b = randomDouble(MIN_VALUE, MAX_VALUE);
                e = randomDouble(MIN_VALUE, MAX_VALUE);
            } while ((b * b + e * e) > 1);
        } while ((a * a + b * b + d * d + e * e) > (1 + (a * e - d * b) * (a * e - d * b)));
        this.c = randomDouble(MIN_VALUE, MAX_VALUE);
        this.f = randomDouble(MIN_VALUE, MAX_VALUE);

        this.red = (int) (randomDouble(0, MAX_VALUE) * COUNT_OF_COLORS);
        this.blue = (int) (randomDouble(0, MAX_VALUE) * COUNT_OF_COLORS);
        this.green = (int) (randomDouble(0, MAX_VALUE) * COUNT_OF_COLORS);
    }

    @SuppressWarnings("ParameterNumber")
    public AffineSpace(
        double red, double green, double blue, double a, double b, double c, double d, double e, double f
    ) {
        this.red = (int) (red * COUNT_OF_COLORS);
        this.green = (int) (green * COUNT_OF_COLORS);
        this.blue = (int) (blue * COUNT_OF_COLORS);
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

}
