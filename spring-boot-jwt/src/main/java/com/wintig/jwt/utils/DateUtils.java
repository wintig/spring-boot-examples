package com.wintig.jwt.utils;

import java.time.*;
import java.util.Date;

public class DateUtils {


    public static Date LocalDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }


}
