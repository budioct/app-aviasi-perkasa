package com.aviasi.perkasa.utils;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class ParseDate {

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public java.util.Date parseDate(String str) {
        try {

            Date date =Date.valueOf(DATE_FORMAT.format(sdf.parse(str)));//converting string into sql date
            return date;
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public java.util.Date parseTimestamp(String timestamp) {
        try {
            return DATE_TIME_FORMAT.parse(timestamp);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
