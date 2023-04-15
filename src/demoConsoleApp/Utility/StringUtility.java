package demoConsoleApp.Utility;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtility {
    static String regx = "^[\\p{L} .'-]+$";
    public static String HandleEmptyString(String source, String defaultValue){
        return source == null ? defaultValue : source;
    }
    public static boolean isValidName(String txt) {

        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        return matcher.find();
    }
    public static String toVND(BigDecimal value){
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return format.format(value);
    }
}
