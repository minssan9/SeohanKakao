package com.message.util;


import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class CommonUtil {
    public static final DateTimeFormatter dateString = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter timeString = DateTimeFormatter.ofPattern("HHmm");
    public static final DateTimeFormatter dateFormatString = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter timeFormatString = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter dateTimeFormatString = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
