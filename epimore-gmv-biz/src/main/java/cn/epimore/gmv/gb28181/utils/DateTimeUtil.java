package cn.epimore.gmv.gb28181.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeUtil {
    public static long toTimestampMillis(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static long toTimestampSeconds(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

}
