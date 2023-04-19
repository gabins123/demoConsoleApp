package demoConsoleApp.Utility.Console;

public class ActionResult  {
    private boolean isSuccess;
    private String message;

    public ActionResult(boolean isValid, String s) {
        isSuccess = isValid;
        message = s;
    }
    public static ActionResult valid() {
        return new ActionResult(true, null);
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }
}
