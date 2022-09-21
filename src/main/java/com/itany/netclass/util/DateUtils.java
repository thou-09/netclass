package com.itany.netclass.util;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author Thou
 * @date 2022/9/8
 */
public class DateUtils {

    /**
     * 获得 /年/月/日 格式的字符串，用于做文件夹目录
     *
     * @return java.lang.String
     * @author Thou
     * @date 2022/9/8
     */
    public static String getYmd() {
        Calendar c = Calendar.getInstance();
        Date d = new Date();
        c.setTime(d);
        StringBuffer bf = new StringBuffer();
        bf.append(File.separator)
                .append(c.get(Calendar.YEAR)).append(File.separator)
                .append((c.get(Calendar.MONTH) + 1 < 10) ? "0" + (c.get(Calendar.MONTH) + 1) : c.get(Calendar.MONTH) + 1)
                .append(File.separator)
                .append((c.get(Calendar.DAY_OF_MONTH) < 10) ? "0" + c.get(Calendar.DAY_OF_MONTH) : c.get(Calendar.DAY_OF_MONTH));
        return bf.toString();
    }
}
