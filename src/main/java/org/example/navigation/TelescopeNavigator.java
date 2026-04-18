package org.example.navigation;

public class TelescopeNavigator {

    //Допустимая погрешность в градусах
    private static final double PRECISION = 0.5;
    private static final double FAR_AWAY = 5.0;

    public String getVoiceCommand(double currentAngle, double targetAngle) {
        double diff = targetAngle - currentAngle;

        // Обработка перехода через 360 градусов (для азимута)
        if (diff > 180) diff -= 360;
        if (diff < -180) diff += 360;

        if (Math.abs(diff) <= PRECISION) {
            return "Цель захвачена! Стоп.";
        }

        String direction = (diff > 0) ? "Правее" : "Левее";

        // команды в зависимости от расстояния
        if (Math.abs(diff) > FAR_AWAY) {
            return "Сильно " + direction;
        } else {
            return "Чуть-чуть " + direction;
        }
    }

    public static void main(String[] args) {
        TelescopeNavigator nav = new TelescopeNavigator();

        //  180 (юг), а Сатурн на 190
        System.out.println(nav.getVoiceCommand(180, 190.5));
        // почти навели
        System.out.println(nav.getVoiceCommand(180, 180.2));
    }
    public String getVoiceCommandAltitude(
            double currentAlt, double targetAlt)
    {
        double diff1 = targetAlt - currentAlt;


        if (Math.abs(diff1) <= PRECISION) {
            return "Цель захвачена! Стоп.";
        }

        String direction = (diff1 > 0) ? "Выше" : "Ниже";

        // команды в зависимости от расстояния
        if (Math.abs(diff1) > FAR_AWAY) {
            return "Сильно " + direction;
        } else {
            return "Чуть-чуть " + direction;
        }
    }
    }

