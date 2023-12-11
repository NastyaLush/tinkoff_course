package edu.hw10.task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class RandomObjectGenerator {

    protected final Map<Class<?>, Supplier<?>> declaredClasses;

    public RandomObjectGenerator(
        Map<Class<?>, Supplier<?>> declaredClasses
    ) {
        this.declaredClasses = declaredClasses;
    }

    public <T> T nextObject(Class<T> tClass, String methodName)
        throws InvocationTargetException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException {
        Method method = findMethod(tClass, methodName);
        Object[] params = getParams(method);
        method.setAccessible(true);
        return (T) method.invoke(null, params);
    }

    private <T> Method findMethod(Class<T> tClass, String methodName) {
        for (Method method : tClass.getDeclaredMethods()) {
            if (Modifier.isStatic(method.getModifiers()) && method.getName().equals(methodName)
                && method.getReturnType().equals(tClass)) {
                log.info("find method");
                return method;
            }
        }
        String methodNotFound = "method not found";
        log.error(methodNotFound);
        throw new IllegalArgumentException(methodNotFound);
    }

    protected Object[] getParams(Executable executable)
        throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        List<Object> parameters = new ArrayList<>();
        for (Class<?> paramertClass : executable.getParameterTypes()) {
            if (declaredClasses.containsKey(paramertClass)) {
                parameters.add(declaredClasses.get(paramertClass).get());
            } else {
                String classNotSupportedMessage = paramertClass.getName() + " is not supported";
                log.error(classNotSupportedMessage);
                throw new IllegalArgumentException(classNotSupportedMessage);
            }
        }
        return parameters.toArray();
    }

    public <T> T nextObject(Class<T> tClass)
        throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException,
        NoSuchFieldException, NoSuchMethodException {
        Constructor<?> constructor = Class.forName(tClass.getName()).getDeclaredConstructors()[0];
        Object[] params = getParams(constructor);
        constructor.setAccessible(true);
        return (T) constructor.newInstance(params);
    }
}
