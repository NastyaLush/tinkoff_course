package edu.hw10.task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2 public class CacheProxyTest {

    private static final Path dirPath = Path.of("testDir");

    @BeforeAll() public static void createTestDir() throws IOException {
        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
        }
    }

    @AfterAll() public static void deleteTestDir() throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath)) {
            for (Path path : stream) {
                Files.delete(path);
            }
        }
        Files.delete(dirPath);
    }

    @Test public void cache_shouldCacheData() throws IOException {
        int delay = 2000000;
        HardWorkLogic hardWorkLogic = CacheProxy.create(new HardWorkLogicImpl(), HardWorkLogic.class, dirPath);
        hardWorkLogic.workHard(delay);
        long start = System.nanoTime();
        hardWorkLogic.workHard(delay);
        long finish = System.nanoTime();
        assertTrue(finish - start < delay);
    }

    @Test public void cache_shouldWriteDataToDiskIfPersist() throws IOException {
        int delay = 2000000;
        HardWorkLogicImpl hardWorkLogicImpl = new HardWorkLogicImpl();
        HardWorkLogic hardWorkLogic = CacheProxy.create(hardWorkLogicImpl, HardWorkLogic.class, dirPath);
        hardWorkLogic.workHard(delay);
        String expectedString = "workHard:" + Arrays.hashCode(new Object[] {delay}) + ":" + (
            hardWorkLogicImpl.hashCode()
                + delay);
        String actualString = Files.readString(Path.of(dirPath + "/" + hardWorkLogic.hashCode()));
        assertEquals(expectedString, actualString);
    }

    public interface HardWorkLogic {

        @Cache(persist = true)
        long workHard(int delay);
    }

    public class HardWorkLogicImpl implements HardWorkLogic {

        @Override public long workHard(int delay) {
            log.info("begin hard work");

            try {
                TimeUnit.NANOSECONDS.sleep(delay);
            } catch (InterruptedException e) {
                log.error(e);
                throw new RuntimeException(e);
            }
            log.info("finished work");
            return this.hashCode() + delay;
        }
    }
}
