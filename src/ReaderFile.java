import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReaderFile {
    String readFileContents(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            return null;
        }
    }
}
