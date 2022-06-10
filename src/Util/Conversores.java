package Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversores {

    public static Date epochToDate(String date) {//en segundos
        Date d = new Date(Long.parseLong(date) * 1000);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String formatted = format.format(d);
        System.out.println(formatted);
        Date dateParaRetornar = null;
        try {
            dateParaRetornar = format.parse(formatted);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateParaRetornar;
    }
}
