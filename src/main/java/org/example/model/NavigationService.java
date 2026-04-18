package org.example.model;

import org.example.navigation.TelescopeNavigator;

public class NavigationService {
    private final Star star = new Star();
    private final TelescopeNavigator navigator = new TelescopeNavigator();

    public String[] getCommands(
            String starName,
            double lat,           //широта
            double lon,           //долгота
            double currentAz,     //текущий азимут телескопа
            double currentAlt     //текущая высота телескопа
    ) {
        Star found = star.findTheStar(starName);
        if (found == null) {
            return new String[]{"Звезда не найдена", ""};
        }
       double azAndAlt[]= Star.getAzimuthAndAltitude(found, found.getDec(),found.getRa());
        String voice = navigator.getVoiceCommand(currentAz,azAndAlt[0]);
        String altVoice = navigator.getVoiceCommandAltitude(currentAlt,azAndAlt[1]);
        return new String[]{voice,altVoice};
    }
}