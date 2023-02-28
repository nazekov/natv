package kg.mega.natv.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private static DateUtil INSTANCE = null;

    public static DateUtil getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new DateUtil();
        }
        return INSTANCE;
    }

    public Date getEndDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.YEAR, 2999);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
