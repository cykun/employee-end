package com.cyquen.employee.util;

import java.util.Calendar;
import java.util.Date;

public final class TimeUtils {

    /**
     * 获取特定日期结束时间
     *
     * @param date 特定日期
     * @return date 对应结束时间
     */
    public static Date getEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return calendar.getTime();
    }
}
