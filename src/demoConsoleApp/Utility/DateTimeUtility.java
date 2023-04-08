package demoConsoleApp.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtility {
    public final static SimpleDateFormat DatePattern = new SimpleDateFormat ("dd/MM/yyyy");

    public static String toDefaultFormat(Date date) {
        if(date == null)
            return null;
        return DatePattern.format(date);
    }
}
