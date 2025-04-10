package readAndwrite;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class FileService {
    public StringBuilder readFile(Path path) {
        StringBuilder builder = new StringBuilder();
        try (FileReader reader = new FileReader(path.toFile())) {
            int c;
            while ((c = reader.read()) != -1) {
                builder.append((char) c);
            }
        } catch (IOException e) {
            throw new RuntimeException("Помилка читання файлу: " + path, e);
        }
        return builder;
    }

    public void writeFile(Path originalPath, StringBuilder text, String mode) {
        try {
            String fileName = originalPath.getFileName().toString();
            String parent = originalPath.getParent() != null ? originalPath.getParent() + "/" : "";

            String newFileName = getModifiedName(fileName, mode);
            Path newPath = Path.of(parent + newFileName);

            try (FileWriter writer = new FileWriter(newPath.toFile())) {
                writer.write(text.toString());
            }

        } catch (IOException e) {
            throw new RuntimeException("Помилка запису у файл", e);
        }
    }

    private String getModifiedName(String fileName, String mode) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1) {
            String name = fileName.substring(0, dotIndex);
            String extension = fileName.substring(dotIndex);
            return name + "[" + mode + "]" + extension;
        } else {
            return fileName + "[" + mode + "]" + ".txt";
        }
    }
}
