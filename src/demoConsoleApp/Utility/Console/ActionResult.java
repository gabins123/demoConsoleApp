package demoConsoleApp.Utility.Console;

public class ActionResult  {
    public boolean isSuccess;
    public String message;

    public ActionResult(boolean isValid, String s) {
        isSuccess = isValid;
        message = s;
    }
}
