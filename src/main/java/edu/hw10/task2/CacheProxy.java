package edu.hw10.task2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class CacheProxy implements InvocationHandler {

    private final Object target;
    private final Map<String, Object> cache = new HashMap<>();
    private final Path temporalDirectory;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String fileName = "data";
    private final String keySeparator = ":";
    private final String stringSeparator = ";";
    private final Short countOfPartsOfString = 3;

    private CacheProxy(Object target, Path temporalDirectory) throws IOException {
        this.target = target;
        this.temporalDirectory = temporalDirectory;
        if (!Files.exists(temporalDirectory)) {
            Files.createDirectory(temporalDirectory);
        }
        if (!Files.exists(Path.of(temporalDirectory.toAbsolutePath() + "/" + fileName))) {
            Files.createFile(Path.of(temporalDirectory.toAbsolutePath() + "/" + fileName));
        } else {
            readCachedData();
        }
    }

    private void readCachedData() throws IOException {

        Files.readAllLines(Path.of(temporalDirectory.toAbsolutePath() + "/" + fileName)).forEach((line) -> {
            String[] splitedLines = line.split(stringSeparator, countOfPartsOfString);
            try {
                Class clazz = objectMapper.readValue(splitedLines[1], Class.class);
                cache.put(
                    splitedLines[0],
                    objectMapper.readValue(splitedLines[2], clazz)
                );
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static <T> T create(T target, Class<? extends T> interfaceType, Path temporalDirectory) throws IOException {
        return (T) Proxy.newProxyInstance(
            interfaceType.getClassLoader(),
            new Class<?>[] {interfaceType},
            new CacheProxy(target, temporalDirectory)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cache cacheAnnotation = method.getAnnotation(Cache.class);

        if (cacheAnnotation != null) {
            String key = generateCacheKey(method, args);
            if (cache.containsKey(key)) {
                return cache.get(key);
            } else {
                Object result = method.invoke(target, args);
                cache.put(key, result);
                if (cacheAnnotation.persist()) {
                    persistToDisk(key, result, method.getReturnType());
                }

                return result;
            }
        } else {
            return method.invoke(target, args);
        }
    }

    private String generateCacheKey(Method method, Object[] args) {
        return method.getName() + keySeparator + Arrays.hashCode(args);
    }

    private void persistToDisk(String key, Object value, Class clazz) throws IOException {
        Files.writeString(
            Path.of(temporalDirectory.toAbsolutePath() + "/" + fileName),
            key + stringSeparator + objectMapper.writeValueAsString(clazz) + stringSeparator
                + objectMapper.writeValueAsString(value),
            StandardOpenOption.APPEND
        );
    }

}

