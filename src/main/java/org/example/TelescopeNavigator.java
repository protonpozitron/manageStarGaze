package org.example;

public class TelescopeNavigator {

    // Допустимая погрешность в градусах (поле зрения окуляра обычно около 1-2 градусов)
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

        // Добавляем эмоциональный окрас в зависимости от расстояния
        if (Math.abs(diff) > FAR_AWAY) {
            return "Сильно " + direction;
        } else {
            return "Чуть-чуть " + direction;
        }
    }

    public static void main(String[] args) {
        TelescopeNavigator nav = new TelescopeNavigator();

        // Пример: мы на 180 (юг), а Сатурн на 190
        System.out.println(nav.getVoiceCommand(180, 190.5));
        // Пример: мы почти навели
        System.out.println(nav.getVoiceCommand(180, 180.2));
    }
}
