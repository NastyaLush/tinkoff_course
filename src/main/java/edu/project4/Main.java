package edu.project4;

import edu.project4.postProcess.Correction;
import edu.project4.postProcess.Reduce;
import edu.project4.rend.wikiImpl.WikiRendererSingleThreaded;
import edu.project4.rend.wikiImpl.WikiRendererMultiThreaded;
import edu.project4.rend.habrImpl.HabrRendererMultiThreaded;
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

public class Main {

    public static void main(String[] args) throws IOException {
        randomLikeInHabr(args);
    }

    public static void randomFun(String[] args) throws IOException {
        long start = System.nanoTime();
        FractalImage fractalImage = FractalImage.create(1080, 1920);

        List<TransformationFunction> transformations1 = new ArrayList<>();

        transformations1.add(new SphereTransformationFunction());
        transformations1.add(new HeartTransformationFunction());
        transformations1.add(new SinTransformationFunction());
        transformations1.add(new DiskTransformationFunction());
        transformations1.add(new SwirlTransformationFunction());

//        transformations1.add(new HorseshoeTransformationFunction());
//        transformations1.add(new HorseshoeTransformationFunction());
//        transformations1.add(new HorseshoeTransformationFunction());
//        transformations1.add(new HorseshoeTransformationFunction());

        WikiRendererSingleThreaded wikiRendererSingleThreaded = new WikiRendererMultiThreaded(6);
        wikiRendererSingleThreaded.rend(
            fractalImage,
            Function.generateFunctions(transformations1, 40),
            40000,
            2000,
            1,
            -1.777,
            1.777f,
            -1,
            1
        );
        new Correction().process(fractalImage);
        ImageUtils.save(fractalImage, Path.of("img_f.png"), ImageFormat.PNG);
        FractalImage red = new Reduce().process(fractalImage);
        ImageUtils.save(red, Path.of("img.png"), ImageFormat.PNG);
        System.out.println(System.nanoTime() - start);
    }

    public static void randomLikeInHabr(String[] args) throws IOException {
        long start = System.nanoTime();
        FractalImage fractalImage = FractalImage.create(1080, 1920);

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

//        transformations1.add(new HorseshoeTransformationFunction());
//        transformations1.add(new HorseshoeTransformationFunction());
//        transformations1.add(new HorseshoeTransformationFunction());
//        transformations1.add(new HorseshoeTransformationFunction());

        HabrRendererMultiThreaded render = new HabrRendererMultiThreaded(6);
        render.rend(
            fractalImage,
            affineSpaces,
            transformations1,
            20000,
            1000,
            1,
            -1.777,
            1.777f,
            -1,
            1
        );
        new Correction().process(fractalImage);
        ImageUtils.save(fractalImage, Path.of("img_f.png"), ImageFormat.PNG);
        FractalImage red = new Reduce().process(fractalImage);
        ImageUtils.save(red, Path.of("img.png"), ImageFormat.PNG);
        System.out.println(System.nanoTime() - start);
    }
}
