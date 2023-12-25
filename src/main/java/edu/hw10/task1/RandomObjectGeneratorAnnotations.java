package edu.hw10.task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import lombok.extern.log4j.Log4j2;

@Log4j2 public class RandomObjectGeneratorAnnotations extends RandomObjectGenerator {

    private final List<Class<?>> comparable;

    public RandomObjectGeneratorAnnotations(Map<Class<?>, Supplier<?>> declaredClasses, List<Class<?>> comparable) {
        super(declaredClasses);
        this.comparable = comparable;
    }

    @Override protected Object[] getParams(Executable executable)
        throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        List<Object> parameters = new ArrayList<>();
        for (Parameter parameter : executable.getParameters()) {
            if (declaredClasses.containsKey(parameter.getType())) {
                Object generated = null;
                for (Annotation annotation : parameter.getAnnotations()) {
                    if (annotation.annotationType().getName().equals(NotNull.class.getName())) {
                        notNullAnnotationHandler(parameter);
                    } else {
                        String annotationValue = "value";
                        if (annotation.annotationType().getName().equals(Min.class.getName())) {
                            generated = minAnnotationHandler(parameter, annotation, annotationValue, generated);
                        } else if (annotation.annotationType().getName().equals(Max.class.getName())) {
                            generated = maxAnnotationHandler(parameter, annotation, annotationValue, generated);
                        }
                    }

                }
                parameters.add(generated == null ? declaredClasses.get(parameter.getType()).get() : generated);
            } else {
                String classSupportedError = parameter.getType().getName() + " is not supported";
                log.error(classSupportedError);
                throw new IllegalArgumentException(classSupportedError);
            }
        }
        return parameters.toArray();
    }

    private void notNullAnnotationHandler(Parameter parameter) {
        if (declaredClasses.get(parameter.getType()).get() == null) {
            String nullErrorMessage = parameter.getType().getName() + "can't be null, please change generator";
            log.error(nullErrorMessage);
            throw new IllegalArgumentException(nullErrorMessage);
        }
    }

    private Object minAnnotationHandler(
        Parameter parameter, Annotation annotation, String annotationValue, Object generated
    ) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return minMaxAnnotationHandler(
            () -> (int) generated < (int) annotation.annotationType().getDeclaredMethod(annotationValue, null)
                                                    .invoke(annotation),
            parameter,
            annotation,
            annotationValue,
            generated
        );
    }

    private Object maxAnnotationHandler(
        Parameter parameter, Annotation annotation, String annotationValue, Object generated
    ) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return minMaxAnnotationHandler(
            () -> (int) generated > (int) annotation.annotationType().getDeclaredMethod(annotationValue, null)
                                                    .invoke(annotation),
            parameter,
            annotation,
            annotationValue,
            generated
        );
    }

    private Object minMaxAnnotationHandler(
        CustomPredicate customPredicate,
        Parameter parameter,
        Annotation annotation,
        String annotationValue,
        Object generated
    ) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (!comparable.contains(parameter.getType())) {
            String minErrorMessage = parameter.getType().getName() + "can't be annotated min or max";
            log.error(minErrorMessage);
            throw new IllegalArgumentException(minErrorMessage);
        }
        if (generated != null) {
            if (customPredicate.test()) {
                String minMaxError = parameter.getType().getName() + "max more than min or min more than max";
                log.error(minMaxError);
                throw new IllegalArgumentException(minMaxError);
            }
        } else {
            return annotation.annotationType().getDeclaredMethod(annotationValue, null).invoke(annotation);
        }
        return generated;

    }

    @FunctionalInterface private interface CustomPredicate {

        boolean test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    }
}
