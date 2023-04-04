import demoConsoleApp.Utility.Func;

public class Utility {
    public static int tryParseInt(String value, Func<Integer> callback){
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return callback.Invoke();
        }
    }
}
