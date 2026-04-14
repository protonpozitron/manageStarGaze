package org.example.astronomy;

public class Coordinates {
private static final long epochTime=946728000000L;
    private static final double MS_IN_DAY=86400000.0;

    public static double getDays(){//сколько прошло дней
        long currentMs=System.currentTimeMillis();
        return (currentMs-epochTime)/MS_IN_DAY;
    }
    public static double getLocalTime(double longtitude){
        double d = getDays();
        //время по Гринвичу
        double gmst=280.46061837+(360.98564736629*d);
        double lst=gmst+longtitude;
        lst=lst%360;
        if(lst<0)lst+=360;
        return lst;

    }

}
