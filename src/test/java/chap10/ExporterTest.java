package chap10;

import jdk.jfr.Enabled;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.io.File;
import java.lang.reflect.Member;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExporterTest {

    @Test
    void export(){
        String folder = System.getProperty("java.io.tmpdir");
        Exporter exporter = new Exporter(folder);
        exporter.export("file.txt");

        Path path = Paths.get(folder, "file.text");
        assertTrue(Files.exists(path));
    }

    @Test
    @EnabledOnOs({OS.LINUX, OS.MAC})
    void callBash(){
    }

    @Test
    @DisabledOnOs({OS.WINDOWS})
    void changeMode(){
    }
}
