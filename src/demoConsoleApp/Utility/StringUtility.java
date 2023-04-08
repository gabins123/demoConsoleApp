package demoConsoleApp.Utility;

import java.text.SimpleDateFormat;
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
}
