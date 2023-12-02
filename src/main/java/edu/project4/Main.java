package edu.project4;

import edu.project4.postProcess.Correction;
import edu.project4.postProcess.Reduce;
import edu.project4.rend.ConfigRender;
import edu.project4.rend.habrImpl.HabrRendererMultiThreaded;
import edu.project4.rend.wikiImpl.WikiRendererMultiThreaded;
import edu.project4.rend.wikiImpl.WikiRendererSingleThreaded;
import edu.project4.save.ImageFormat;
import edu.project4.save.ImageUtils;
import edu.project4.structures.FractalImage;
import edu.project4.transformation.transformationFunctions.DiskTransformationFunction;
import edu.project4.transformation.transformationFunctions.HeartTransformationFunction;
import edu.project4.transformation.transformationFunctions.SinTransformationFunction;
import edu.project4.transformation.transformationFunctions.SphereTransformationFunction;
import edu.project4.transformation.transformationFunctions.SwirlTransformationFunction;
import edu.project4.transformation.transformationFunctions.TransformationFunction;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {

    private static final int WIDTH = 1080;
    private static final int HEIGHT = 1920;
    private static final int NUMBER_SAMPLES = 20000;
    private static final int ITERATION_PER_SAMPLE = 1000;
    private static final int SYMMETRY = 1;
    private static final double X_MIN = -1.777;
    private static final float X_MAX = 1.777f;
    private static final int Y_MIN = -1;
    private static final int Y_MAX = 1;
    private static final int COUNT_OF_THREADS = 6;
    private static final int COUNT_OF_FUNCTIONS = 40;
    private static final double GAMMA = 2.2;

    private Main() {
    }

    public static void main(String[] args) throws IOException {
        randomLikeInHabr(args);
    }

    public static void randomLikeInHabr(String[] args) throws IOException {
        runProgramm(() -> {

            FractalImage fractalImage = FractalImage.create(WIDTH, HEIGHT);

            List<TransformationFunction> transformations1 = new ArrayList<>();

            transformations1.add(new SphereTransformationFunction());
            transformations1.add(new HeartTransformationFunction());
            transformations1.add(new SinTransformationFunction());
            transformations1.add(new DiskTransformationFunction());
            transformations1.add(new SwirlTransformationFunction());
            List<AffineSpace> affineSpaces = new ArrayList<>();
            affineSpaces.add(new AffineSpace());
            affineSpaces.add(new AffineSpace());
            affineSpaces.add(new AffineSpace());
            affineSpaces.add(new AffineSpace());
            affineSpaces.add(new AffineSpace());
            affineSpaces.add(new AffineSpace());
            affineSpaces.add(new AffineSpace());

            HabrRendererMultiThreaded render = new HabrRendererMultiThreaded(COUNT_OF_THREADS);

            render.rend(
                fractalImage,
                affineSpaces,
                transformations1,
                NUMBER_SAMPLES,
                ITERATION_PER_SAMPLE,
                SYMMETRY,
                new ConfigRender(
                    X_MIN,
                    X_MAX,
                    Y_MIN,
                    Y_MAX
                )
            );
            return fractalImage;
        });

    }

    public static void runProgramm(Supplier<FractalImage> supplier) throws IOException {
        long start = System.nanoTime();

        FractalImage fractalImage = supplier.get();
        new Correction().process(fractalImage, GAMMA);
        ImageUtils.save(fractalImage, Path.of("img_f.png"), ImageFormat.PNG);
        FractalImage red = new Reduce().process(fractalImage);
        ImageUtils.save(red, Path.of("img.png"), ImageFormat.PNG);
        log.info(System.nanoTime() - start);
    }

    public static void randomFun(String[] args) throws IOException {
        FractalImage fractalImage = FractalImage.create(WIDTH, HEIGHT);

        List<TransformationFunction> transformations1 = new ArrayList<>();
        runProgramm(() -> {

            transformations1.add(new SphereTransformationFunction());
            transformations1.add(new HeartTransformationFunction());
            transformations1.add(new SinTransformationFunction());
            transformations1.add(new DiskTransformationFunction());
            transformations1.add(new SwirlTransformationFunction());

            WikiRendererSingleThreaded wikiRendererSingleThreaded = new WikiRendererMultiThreaded(COUNT_OF_THREADS);

            wikiRendererSingleThreaded.rend(
                fractalImage,
                Function.generateFunctions(transformations1, COUNT_OF_FUNCTIONS),
                NUMBER_SAMPLES,
                ITERATION_PER_SAMPLE,
                SYMMETRY,
                new ConfigRender(
                    X_MIN,
                    X_MAX,
                    Y_MIN,
                    Y_MAX
                )
            );
            return fractalImage;
        });

    }
}
