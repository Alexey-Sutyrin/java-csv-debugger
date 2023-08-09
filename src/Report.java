import java.util.ArrayList;

public class Report {
    protected String header = "";
    protected ArrayList<String> rawData = new ArrayList<String>();
    
    public void load(String filename) {
        FileReader fileReader = new FileReader();
        System.out.println("Загружается файл " + filename);
        ArrayList<String> lines = fileReader.readFileContents(filename);
        if (lines.size() == 0 && isLoaded()) {
            System.out.println("Загрузка файла не удалась, используется старая версия");
            return;
        }

        header = "";
        rawData.clear();
        for(String line: lines) {
            if (header.length() == 0) {
                header = line;
            }
            else {
                rawData.add(line);
            }
        }
        System.out.println("Загрузка успешна");
    }
    public boolean isLoaded() {

        return header.length() > 0;
    }
}
