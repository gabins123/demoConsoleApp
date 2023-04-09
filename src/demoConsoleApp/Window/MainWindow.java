package demoConsoleApp.Window;

import demoConsoleApp.Core.InputHandler;
import demoConsoleApp.Main;
import demoConsoleApp.Utility.Console.Command;
import demoConsoleApp.Utility.Console.ConsoleWindow;

import java.util.ArrayList;

public class MainWindow extends ConsoleWindow {
    private final ArrayList<Command> commands;
    public MainWindow() {
        commands = new ArrayList<>();
        commands.add(new Command(1,"Tạo tài khoản", () ->
        {
            Main.SwitchWindows(WindowType.CreateAccount, null);
        }));
        commands.add(new Command(2,"Đăng nhập", () ->
        {
            Main.SwitchWindows(WindowType.SignIn, null);
        }));
        commands.add(new Command(3,"Tra cứu", () ->
        {
            System.out.println("Handle tra cứu");
        }));
    }
    @Override
    public void onDraw() {
        for (Command command : commands) {
            System.out.println(command.toString());
        }
        InputHandler.handleInt("Your input?", this::ProcessInput, ()->-1);
    }
    public void ProcessInput(int value) {
        if(value == -1) {
            System.out.println("Wrong input");
            onDraw();
            return;
        }
        var commandIndex = commands.stream().filter(e->e.getIndex() == value).findAny().orElse(null);
        if(commandIndex == null){
            System.out.println("Wrong input");
            onDraw();
            return;
        }
        commandIndex.Handle();
    }
}
