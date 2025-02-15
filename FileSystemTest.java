import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FileSystemTest {
    private FileSystem fs;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUp() {
        fs = new FileSystem();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testCreateAndList() {
        fs.create("fruits");
        fs.create("vegetables");
        fs.create("grains");
        fs.list();
        String output = outContent.toString();
        assertTrue(output.contains("  fruits"), "Output should contain '  fruits'");
        assertTrue(output.contains("  grains"), "Output should contain '  grains'");
        assertTrue(output.contains("  vegetables"), "Output should contain '  vegetables'");
    }

    @Test
    public void testNestedList() {
        fs.create("fruits/apples/fuji");
        fs.list();
        String output = outContent.toString();
        assertTrue(output.contains("  fruits"), "Output should contain '  fruits'");
        assertTrue(output.contains("    apples"), "Output should contain '    apples'");
        assertTrue(output.contains("      fuji"), "Output should contain '      fuji'");
    }

    @Test
    public void testMove() {
        fs.create("grains/squash");
        fs.create("vegetables");
        fs.move("grains/squash", "vegetables");
        outContent.reset();
        fs.list();
        String output = outContent.toString();
        assertTrue(output.contains("  grains"), "Output should contain '  grains'");
        assertTrue(output.contains("  vegetables"), "Output should contain '  vegetables'");
        assertTrue(output.contains("    squash"), "Output should contain '    squash'");
    }

    @Test
    public void testDelete() {
        fs.create("foods/fruits/apples");
        fs.delete("foods/fruits/apples");
        outContent.reset();
        fs.list();
        String output = outContent.toString();
        assertFalse(output.contains("apples"), "Output should not contain 'apples'");
        assertTrue(output.contains("  foods"), "Output should contain '  foods'");
        assertTrue(output.contains("    fruits"), "Output should contain '    fruits'");
    }

    @Test
    public void testInvalidDelete() {
        fs.create("fruits");
        fs.delete("fruits/apples");
        String output = outContent.toString();
        assertTrue(output.contains("Cannot delete fruits/apples"), "Output should contain error for invalid delete");
    }

    @Test
    public void testInvalidMove() {
        fs.create("grains");
        fs.move("grains/rice", "foods");
        String output = outContent.toString();
        assertTrue(output.contains("Cannot move grains/rice"), "Output should contain error for invalid move");
    }
}