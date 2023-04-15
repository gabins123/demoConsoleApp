package demoConsoleApp.Window;

import demoConsoleApp.Core.Data.Account;
import demoConsoleApp.Core.Data.DataAPI;
import demoConsoleApp.Core.InputHandler;
import demoConsoleApp.Core.LoginSession;
import demoConsoleApp.Main;
import demoConsoleApp.Utility.Console.ActionResult;
import demoConsoleApp.Utility.Console.Command;
import demoConsoleApp.Utility.Console.ConsoleActionHandler;
import demoConsoleApp.Utility.Console.ConsoleWindow;
import demoConsoleApp.Utility.Delegate.Func1;

import java.io.Console;

public class SignInWindow extends ConsoleWindow {

    @Override
    public void onDraw() {
         Func1<Boolean, String> retry = (String des)->{
            var wrongUsernameHandler = new ConsoleActionHandler<>((e) -> e,
                    des, "", true);
            var value = wrongUsernameHandler.handle((e)->new ActionResult(e.equals("1") || e.equals("2") || e.equals("3"),"Lua chon khong phu hop"));
            if(value.equals("1"))
                return false;
            if(value.equals("2")){
                Main.SwitchWindows(WindowType.CreateAccount, null);
                return true;
            }
            Main.SwitchWindows(WindowType.Home, null);
            return true;
        };
        while(true){
            var stringHandler = new ConsoleActionHandler<>((e) -> e, "Nhap username: ", "exit", true);
            var username = stringHandler.handle(null);
            var password = stringHandler.handle(null, "Nhap password");

            var account = DataAPI.getAccount(username);
            if(account == null){
                try {
                    if(retry.invoke(String.format("Username %s khong ton tai\n Ban co muon:\n1. Nhap lai\n2. Dang ky\n3.Thoat", username)))
                        break;
                    continue;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    Main.SwitchWindows(WindowType.Home, null);
                    break;
                }
            }
            if(!account.getPassword().equals(password)){
                try {
                    if(retry.invoke("Password khong dung\n Ban co muon:\n1. Nhap lai\n2. Dang ky\n3.Thoat"))
                        break;
                    continue;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    Main.SwitchWindows(WindowType.Home, null);
                    break;
                }
            }
            LoginSession.getInstance().SignIn(account);
            System.out.format("Chao mung %s da quay tro lai.\n", DataAPI.getCustomerByID(username).getFullName());
            Main.SwitchWindows(WindowType.AccountManager, null);
            break;
        }

    }
}
