package demoConsoleApp.Window;

import demoConsoleApp.Core.Data.DataAPI;
import demoConsoleApp.Core.LoginSession;
import demoConsoleApp.Main;
import demoConsoleApp.Utility.Console.Command;
import demoConsoleApp.Utility.Console.ConsoleActionHandler;
import demoConsoleApp.Utility.Console.ConsoleWindow;

import java.io.Console;
import java.math.BigDecimal;
import java.util.ArrayList;

public class AccountManagerWindow extends ConsoleWindow {
    private final ArrayList<Command> commands;
    public AccountManagerWindow() {
        commands = new ArrayList<>();
        commands.add(new Command(1,"Kiem tra so du", () ->
        {
            System.out.println("So du cua ban la "+ LoginSession.getInstance().getCurrentAccount().getDefaultAccount().getBalance());
            onDraw();
        }));
        commands.add(new Command(2,"Tao tai khoan co ky han", () ->
        {

        }));
        commands.add(new Command(3,"Rut tien", () ->
        {
            Main.SwitchWindows(WindowType.SignIn, null);
        }));
        commands.add(new Command(4,"Tra cứu", () ->
        {
            System.out.println("Handle tra cứu");
        }));
    }
    @Override
    public void onDraw() {
        for (Command command : commands) {
            System.out.println(command.toString());
        }
        System.out.print("Lua chon cua ban: ");
        var handle = new ConsoleActionHandler<>(Integer::parseInt,"Lua chon cua ban: ","Lua chon khong hoop le", "exit", false);
        var index = handle.handle(value->commands.stream().anyMatch(e->e.getIndex() == value));
        var commandIndex = commands.stream().filter(e->e.getIndex() ==  index).findAny().orElse(null);
        assert commandIndex != null;
        commandIndex.Handle();
    }
}
