package org.example.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.astronomy.Coordinates;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Star {
    private static final String APP_FOLDER = "manageStarGaze\\src\\main\\java\\org\\example\\model";
    double dec;
    double ra;
    String name;

    public Star() {
    }

    public Star(String name, double dec, double ra) {
        this.name = name;
        this.dec = dec;
        this.ra = ra;

    }
    public double getDec() {
        return dec;
    }
    public double getRa() {
        return ra;
    }

    public String getname() {
        return name;
    }
    public static String getAppDirectory() {
        String userHome = System.getProperty("user.home");
        String appPath = userHome + File.separator + "IdeaProjects" + File.separator + APP_FOLDER;
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
    public static List<Star> readStarsCatalog() {
        List<Star> catalog = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try (BufferedReader reader = new BufferedReader(new FileReader(getStarFilePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;// превращаем строку JSON в объект Star
                Star star = objectMapper.readValue(line, Star.class);
                catalog.add(star);
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
        return catalog;
    }

    public static double[] getAzimuthAndAltitude(Star star, double lat, double lon) {
        //  местное звездное время (LST)
        double lstDeg = Coordinates.getLocalTime(lon);

        //  часовой угол (Hour Angle)
        // H = LST - RA (все в градусах)
        double hAngleDeg = lstDeg - star.getRa();

        // в радианы для Math функций
        double latRad = Math.toRadians(lat);
        double deltaRad = Math.toRadians(star.getDec());
        double hAngleRad = Math.toRadians(hAngleDeg);

        // 3. Высоту (Altitude)
        double sinAlt = Math.sin(latRad) * Math.sin(deltaRad) + Math.cos(latRad) * Math.cos(deltaRad) * Math.cos(hAngleRad);
        double altRad = Math.asin(sinAlt);
        double altDeg = Math.toDegrees(altRad);

        // 4.  Азимут (Azimuth)
        // atan2(y, x) для автоматического определения четверти угла
        double y = Math.sin(hAngleRad);
        double x = (Math.cos(hAngleRad) * Math.sin(latRad)) -
                (Math.tan(deltaRad) * Math.cos(latRad));

        double azRad = Math.atan2(y, x);
        double azDeg = Math.toDegrees(azRad);

        // Приводим азимут к стандарту 0-360 (от Севера через Восток)
        azDeg = (azDeg + 180) % 360;

        return new double[]{azDeg, altDeg};
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Star other = (Star) obj;
        return dec == other.dec &&  ra == other.ra && Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dec, ra);
    }

    public Star findTheStar(String name){
        List <Star> stars=readStarsCatalog();
        Star newStar = null;
        for (Star star:stars) {
            if(star.getname().equals(name)){
                newStar= star;
                break;
        }
        }

        return newStar;

    }

}
