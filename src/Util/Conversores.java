package Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Conversores {

    public static Date epochToDate(String date) {//en segundos
        Date d = new Date(Long.parseLong(date) * 1000);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String formatted = format.format(d);
        //System.out.println(formatted);
        Date dateParaRetornar = null;
        try {
            dateParaRetornar = format.parse(formatted);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateParaRetornar;
    }

    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static Double stringADouble (String unString){
        Double d = 0.0;

        if(unString != "" && unString != null){
            d = Double.parseDouble(unString);
        }
        return d;
    }

    public static Long stringALong (String unString){
        Long l = Long.valueOf(0);

        if(unString != "" && unString != null){
            l = Long.parseLong(unString);
        }
        return l;
    }

    public static Double msAs (long milis){
        double seg = milis/1000.0;
        return seg;
    }
}
