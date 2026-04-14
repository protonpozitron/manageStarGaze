package collection;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Stars {
    private static final String APP_FOLDER = "manageStarGaze";
    double Dec;
    double Ra;
    String name;

    public Stars() {
    }

    public Stars(String name, double Dec, double Ra) {
        this.name = name;
        this.Dec = Dec;
        this.Ra = Ra;

    }
    public double getDec() {
        return Dec;
    }
    public double getRa() {
        return Ra;
    }

    public static String getAppDirectory() {
        String userHome = System.getProperty("user.home");
        String appPath = userHome + File.separator + "Documents" + File.separator + APP_FOLDER;
        File appdirectory = new File(appPath);
        if (!appdirectory.exists()) {
       //     appdirectory.mkdirs();
            System.out.println("Создана папка приложения: " + appPath);
        }
        return appPath;
    }

    public static String getStarFilePath() {
        return getAppDirectory() + File.separator + "StarsName.json";
    }
    public static List<String> readStarsCatalog() {
        List catalog = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();// Наш "переводчик"
        try (BufferedReader reader = new BufferedReader(new FileReader(getStarFilePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {// Убираем лишние пробелы, если они есть
                line = line.trim();
                if (line.isEmpty()) continue;// Магия Jackson: превращаем строку JSON в объект Star
                Stars star = objectMapper.readValue(line, Stars.class);
                catalog.add(star);
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
        return catalog;
    }
}
