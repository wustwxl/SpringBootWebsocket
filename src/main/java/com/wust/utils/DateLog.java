package com.wust.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * @author : F7687967
 * Date: 2017/10/16
 * Time: 上午 08:54
 * Description: 日志输出时间格式
 */
public class DateLog {

    public static String getNow() {
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("[yyyy-MM-dd hh:mm:ss] ");
        return df.format(now);
    }
}
