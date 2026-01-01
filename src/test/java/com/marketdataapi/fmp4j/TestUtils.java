package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.csv.FmpCsvDeserializer.FMP_CSV_DESERIALIZER;
import static com.marketdataapi.fmp4j.json.FmpJsonDeserializer.FMP_JSON_DESERIALIZER;
import static java.lang.String.format;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public final class TestUtils {
    private TestUtils() {
        throw new AssertionError(TestUtils.class.getSimpleName() + " cannot be instantiated.");
    }

    public static <T> List<T> csvTestResource(Class<T> clazz, String filename, Object... args) {
        final var csv = testResource(filename, args);
        return FMP_CSV_DESERIALIZER.deserialize(csv, clazz);
    }

    public static <T> List<T> jsonTestResource(Class<T> clazz, String filename, Object... args) {
        final var json = testResource(filename, args);
        return FMP_JSON_DESERIALIZER.deserialize(json, clazz);
    }

    public static String testResource(String filename, Object... args) {
        var encoded = encodeFilename(filename, args);
        try (var inputStream = TestUtils.class.getClassLoader().getResourceAsStream(encoded)) {
            return new String(inputStream.readAllBytes());
        } catch (NullPointerException | IOException e) {
            System.out.printf("Failed reading: %s", encoded);
            throw new RuntimeException(e);
        }
    }

    public static String encodeFilename(String filename, Object[] args) {
        return format(filename, args)
                .replace("?", "%3F")
                .replace(":", "%3A")
                .replace("*", "%2A")
                .replace("<", "%3C")
                .replace(">", "%3E")
                .replace("|", "%7C");
    }

    public static byte[] serialize(Object o) throws IOException {
        var baos = new ByteArrayOutputStream();
        try (var oos = new ObjectOutputStream(baos)) {
            oos.writeObject(o);
        }
        return baos.toByteArray();
    }

    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        var after = (Object) null;
        var bais = new ByteArrayInputStream(bytes);
        try (var ois = new ObjectInputStream(bais)) {
            after = ois.readObject();
        }
        return after;
    }

    public static void assertAllFieldsNonNull(Object obj) {
        assertAllFieldsNonNull(obj, emptySet());
    }

    public static void assertAllFieldsNonNull(Object obj, Set<String> ignoreFields) {
        for (final var field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (!ignoreFields.contains(field.getName())) {
                    assertNotNull(field.get(obj), field.getName() + " should not be null");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("failed to access field: " + field.getName(), e);
            }
        }
    }

    public static <T> byte[] readSnapshot(Class<T> clazz) throws IOException {
        Path path = getSnapshotPath(clazz);
        if (Files.notExists(path)) {
            throw new FileNotFoundException("Snapshot not found at: " + path.toAbsolutePath());
        }
        return Files.readAllBytes(path);
    }

    public static <T> void verifySerialization(T obj) throws IOException {
        Class<?> clazz = obj.getClass();
        Path snapshotPath = getSnapshotPath(clazz);
        Path versionPath = getVersionPath(clazz);

        byte[] currentData = serialize(obj);
        long currentVersion = getSerialVersionUID(clazz);

        // Create snapshot if missing
        if (Files.notExists(snapshotPath)) {
            createSnapshotWithVersion(currentData, currentVersion, snapshotPath, versionPath);
            return;
        }

        // Read existing version metadata
        long existingVersion = readVersionMetadata(versionPath);

        // Update if version changed
        if (currentVersion != existingVersion) {
            createSnapshotWithVersion(currentData, currentVersion, snapshotPath, versionPath);
            return;
        }

        // Verify against existing snapshot
        byte[] expected = Files.readAllBytes(snapshotPath);
        if (!Arrays.equals(expected, currentData)) {
            throw new AssertionError("Serialization data mismatch for "
                    + clazz.getSimpleName()
                    + ". Update serialVersionUID if this is intentional.");
        }
    }

    private static <T> long getSerialVersionUID(Class<T> clazz) {
        try {
            Field field = clazz.getDeclaredField("serialVersionUID");
            field.setAccessible(true);
            return field.getLong(null);
        } catch (Exception e) {
            throw new RuntimeException("Missing serialVersionUID in " + clazz.getSimpleName(), e);
        }
    }

    private static long readVersionMetadata(Path versionPath) throws IOException {
        try (DataInputStream dis = new DataInputStream(Files.newInputStream(versionPath))) {
            return dis.readLong();
        }
    }

    private static void createSnapshotWithVersion(byte[] data, long version, Path snapshotPath, Path versionPath)
            throws IOException {
        Files.createDirectories(snapshotPath.getParent());
        Files.write(snapshotPath, data);

        try (DataOutputStream dos = new DataOutputStream(Files.newOutputStream(versionPath))) {
            dos.writeLong(version);
        }
    }

    private static Path getSnapshotPath(Class<?> clazz) {
        return Paths.get(
                "src", "testFixtures", "resources", "models", "serialization", clazz.getSimpleName() + ".snapshot");
    }

    private static Path getVersionPath(Class<?> clazz) {
        return Paths.get(
                "src", "testFixtures", "resources", "models", "serialization", clazz.getSimpleName() + ".version");
    }
}
