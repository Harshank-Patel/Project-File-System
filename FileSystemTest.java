import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FileSystemTest {
    private FileSystem fs;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        fs = new FileSystem();
        System.setOut(new PrintStream(outputStream)); // Redirect system output for testing
    }

    @Test
    void testCreate() {
        fs.create("fruits");
        fs.create("vegetables");
        fs.create("grains");

        String output = outputStream.toString().trim();
        assertTrue(output.contains("CREATE fruits"));
        assertTrue(output.contains("CREATE vegetables"));
        assertTrue(output.contains("CREATE grains"));
    }

    @Test
    void testList() {
        fs.create("fruits");
        fs.create("fruits/apples");
        fs.create("fruits/apples/fuji");

        fs.list();
        String output = outputStream.toString().trim();
        assertTrue(output.contains("fruits"));
        assertTrue(output.contains("  apples"));
        assertTrue(output.contains("    fuji"));
    }

    @Test
    void testMove() {
        fs.create("grains");
        fs.create("grains/squash");
        fs.create("vegetables");

        fs.move("grains/squash", "vegetables");

        String output = outputStream.toString().trim();
        assertTrue(output.contains("MOVE grains/squash vegetables"));

        fs.list();
        output = outputStream.toString().trim();
        assertTrue(output.contains("vegetables\n  squash"));
    }

    @Test
    void testDelete() {
        fs.create("foods");
        fs.create("foods/fruits");
        fs.create("foods/fruits/apples");

        fs.delete("foods/fruits/apples");

        String output = outputStream.toString().trim();
        assertTrue(output.contains("DELETE foods/fruits/apples"));

        fs.list();
        output = outputStream.toString().trim();
        assertFalse(output.contains("apples"));
    }

    @Test
    void testInvalidDelete() {
        fs.create("fruits");
        fs.delete("fruits/apples");

        String output = outputStream.toString().trim();
        assertTrue(output.contains("Cannot delete fruits/apples"));
    }

    @Test
    void testInvalidMove() {
        fs.create("grains");
        fs.move("grains/rice", "foods");

        String output = outputStream.toString().trim();
        assertTrue(output.contains("Cannot move grains/rice"));
    }
}