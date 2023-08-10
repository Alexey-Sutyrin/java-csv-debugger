import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileReader {   // механизм считывания данных из CSV файла
    ArrayList<String> readFileContents(String fileName) {
        String path = "./resources/" + fileName;
        try {
            return new ArrayList<>(Files.readAllLines(Path.of(path)));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл отсутствует в нужной директории.");
            System.out.println(e);
            return null;
            //return new ArrayList<>(); - можно вернуть null, чтобы не было сомнений что не загружено
            // по причине ошибки vs загружен файл в котором просто нет данных по причине что не было
            // транзакций в данном периоде.
        }
    }

} 