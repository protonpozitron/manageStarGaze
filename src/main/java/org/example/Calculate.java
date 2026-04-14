package org.example;

import collection.Stars;

public class Calculate {
            public static double[] getAzimuthAndAltitude(Stars star, double lat, double lon) {
            // 1. Получаем местное звездное время (LST)
            double lstDeg = Coordinates.getLocalTime(lon);

            // 2. Считаем часовой угол (Hour Angle)
            // H = LST - RA (все в градусах)
            double hAngleDeg = lstDeg - star.getRa();

            // Переводим всё в радианы для Math функций
            double latRad = Math.toRadians(lat);
            double deltaRad = Math.toRadians(star.getDec());
            double hAngleRad = Math.toRadians(hAngleDeg);

            // 3. Считаем Высоту (Altitude)
            double sinAlt = Math.sin(latRad) * Math.sin(deltaRad) + Math.cos(latRad) * Math.cos(deltaRad) * Math.cos(hAngleRad);
            double altRad = Math.asin(sinAlt);
            double altDeg = Math.toDegrees(altRad);

            // 4. Считаем Азимут (Azimuth)
            // Используем atan2(y, x) для автоматического определения четверти угла
            double y = Math.sin(hAngleRad);
            double x = (Math.cos(hAngleRad) * Math.sin(latRad)) -
                    (Math.tan(deltaRad) * Math.cos(latRad));

            double azRad = Math.atan2(y, x);
            double azDeg = Math.toDegrees(azRad);

            // Приводим азимут к стандарту 0-360 (от Севера через Восток)
            azDeg = (azDeg + 180) % 360;

            return new double[]{azDeg, altDeg};
        }

}
