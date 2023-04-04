import demoConsoleApp.Utility.Action;

import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<WindowType, ConsoleWindow> views = null;
    public static void main(String[] args) {
        Initialize();
        SwitchWindows(WindowType.Home, null);
    }
    public static void SwitchWindows(WindowType type, Action callback){
        if(views.containsKey(type)){
            views.get(type).OnDraw();
            if(callback != null)
                callback.Invoke();
            return;
        }
        System.out.println("Cannot find window type " + type);
    }
    private static void Initialize(){
        views = new HashMap<>();
        views.put(WindowType.Home, new MainWindow());
        views.put(WindowType.CreateAccount, new CreateCustomerWindow());
    }
}

