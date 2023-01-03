package com.cyquen.employee.util;

import java.util.Date;

public final class TimeUtils {

    public static Date getEnd(Date date) {
        return new Date(date.getTime() + 24 * 60 * 60 * 1000);
    }
}
