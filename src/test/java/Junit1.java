import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Junit1 {
    @Test
    public void givenFilePath_whenUsingFilesLines_thenFileData() {
        String expectedData = "Hello World from fileTest.txt!!!";

        Path path = null;
        try {
            path = Paths.get(getClass().getClassLoader()
                    .getResource("fileTest.txt").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Stream<String> lines = null;
        try {
            lines = Files.lines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String data = lines.collect(Collectors.joining("\n"));
        lines.close();

        Assert.assertEquals(expectedData, data.trim());
    }
}
