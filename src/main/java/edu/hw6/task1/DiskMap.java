package edu.hw6.task1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Log4j2
public class DiskMap implements Map<String, String> {

    private final Path path;
    private Integer size = 0;


    public DiskMap(String fileName) throws IOException {
        this.path = Path.of(fileName);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        try (Stream<String> stream = Files.lines(path)) {
            size = Math.toIntExact(stream.count());
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        try (Stream<String> stream = Files.lines(path)) {
            return stream.anyMatch((line) -> line.split(":")[0].equals(key));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean containsValue(Object value) {
        try (Stream<String> stream = Files.lines(path)) {
            return stream.anyMatch((line) -> line.split(":")[1].equals(value));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String get(Object key) {
        if (!containsKey(key)) {
            return null;
        }
        try (Stream<String> stream = Files.lines(path)) {
            return stream.filter((line) -> line.split(":")[0].equals(key))
                         .map((line) -> line.split(":")[1])
                         .findFirst()
                         .orElse(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        String val = null;
        if (containsKey(key)) {
            val = get(key);
            remove(key);
            size--;
        }

        try (OutputStream stream = Files.newOutputStream(path, StandardOpenOption.APPEND)) {
            stream.write(key.getBytes());
            stream.write(":".getBytes());
            stream.write(value.getBytes());
            stream.write('\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        size++;
        return val;
    }

    @Override
    public String remove(Object key) {
        String prevValue = get(key);
        if (prevValue != null) {
            try {
                Path outputFilePath = Paths.get("output.txt");
                try (Stream<String> lines = Files.lines(path)) {
                    List<String> updatedLines = lines.filter(line -> !line.split(":")[0].equals(key))
                                                     .collect(Collectors.toList());

                    Files.write(outputFilePath, updatedLines);
                }
                Files.delete(path);
                Files.move(outputFilePath, path);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            size--;
        }

        return prevValue;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        for (Entry<? extends String, ? extends String> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        try (BufferedWriter ignored = Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING)) {
            size = 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        try (Stream<String> stream = Files.lines(path)) {
            return stream.map(e -> e.split(":")[0])
                         .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Collection<String> values() {
        try (Stream<String> stream = Files.lines(path)) {

            return stream.map(e -> e.split(":")[1])
                         .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        try (Stream<String> stream = Files.lines(path)) {
            return stream.map(e -> new AbstractMap.SimpleEntry<>(e.split(":")[0], e.split(":")[1]))
                         .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
