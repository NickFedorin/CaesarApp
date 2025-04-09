package readAndwrite;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

//Логіка читання і запису файлів
public class FileService {
    // Зчитування файлу як символів у StringBuilder
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

    // Запис символів із StringBuilder у файл з модифікованою назвою
    public void writeFile(Path originalPath, StringBuilder text, String mode) {
        try {
            String fileName = originalPath.getFileName().toString();
            String parent = originalPath.getParent() != null ? originalPath.getParent().toString() + "/" : "";

            String newFileName = getModifiedName(fileName, mode);
            Path newPath = Path.of(parent + newFileName);

            try (FileWriter writer = new FileWriter(newPath.toFile())) {
                writer.write(text.toString());
            }

        } catch (IOException e) {
            throw new RuntimeException("Помилка запису у файл", e);
        }
    }

    // Метод для формування нової назви файлу
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
