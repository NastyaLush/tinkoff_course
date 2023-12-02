package edu.project4.rend;

import edu.project4.AffineSpace;
import edu.project4.structures.Pixel;
import edu.project4.structures.Point;

public interface Renderer {

    default double random(double from, double till) {
        return Math.random() * (till - from) + from;
    }

    default void changeColor(Pixel pixel, AffineSpace affineSpace) {

        pixel.setR((pixel.getR() + affineSpace.getRed()) / 2);
        pixel.setG((pixel.getG() + affineSpace.getGreen()) / 2);
        pixel.setB((pixel.getB() + affineSpace.getBlue()) / 2);

        pixel.incrementHitCount();
    }

    default void dod(

        Pixel[][] pixels,
        ChosePoint chosePoint,
        int xRes, int yRes,
        double randomX, double randomY,
        int iterationPerSample,
        int symmetry,
        double xMIN,
        double xMAX,
        double yMIN,
        double yMAX
    ) {
        double newX;
        double newY;

        for (int step = -20; step < iterationPerSample; step++) {

//            Function transformation = randomCollection.next();
//
//            newX = transformation.calcFunction(new Point(randomX, randomY)).x();
//            newY = transformation.calcFunction(new Point(randomX, randomY)).y();
            RR rr = chosePoint.chosePoint();
            Point point = rr.getPoint();

            newX = point.x();
            newY = point.y();

            extracted(
                pixels,
                xRes,
                yRes,
                symmetry,
                xMIN,
                xMAX,
                yMIN,
                yMAX,
                step,
                newX,
                newY,
                rr.getAffineSpace()
            );
        }
    }

    private void extracted(
        Pixel[][] pixels,
        int xRes,
        int yRes,
        int symmetry,
        double xMIN,
        double xMAX,
        double yMIN,
        double yMAX,
        int step,
        double newX,
        double newY,
        AffineSpace affineSpace
    ) {
        int y;
        int x;
        double xRot;
        double theta;
        double yRot;
        if (step > 0) {
            theta = 0;
            for (int s = 0; s < symmetry; theta += 2 * Math.PI / symmetry, s++) {
                xRot = newX * Math.cos(theta) - newY * Math.sin(theta);
                yRot = newX * Math.sin(theta) + newY * Math.cos(theta);
                if (xRot >= xMIN && xRot <= xMAX && yRot >= yMIN && yRot <= yMAX) {
                    x = xRes - (int) (((xMAX - xRot) / (xMAX - xMIN)) * xRes);
                    y = yRes - (int) (((yMAX - yRot) / (yMAX - yMIN)) * yRes);

                    if (x >= 0 && x < xRes && y >= 0 && y < yRes) {
                        synchronized (pixels[x][y]) {
                            changeColor(pixels[x][y], affineSpace);
                        }
                    }
                }
            }
        }
    }

    class RR {

        Point point;
        AffineSpace affineSpace;

        public Point getPoint() {
            return point;
        }

        public AffineSpace getAffineSpace() {
            return affineSpace;
        }

        public RR(Point point, AffineSpace affineSpace) {
            this.point = point;
            this.affineSpace = affineSpace;
        }
    }
}
