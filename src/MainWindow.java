import java.util.ArrayList;
import java.util.Scanner;

public class MainWindow extends ConsoleWindow {
    ArrayList<Command> commands;
    public MainWindow() {
        commands = new ArrayList<>();
        commands.add(new Command(1,"Test command 1", () ->
        {
            System.out.println("Handle success");
            return null;
        }));
        commands.add(new Command(2,"Test command 2", () ->
        {
            System.out.println("Handle success");
            return null;
        }));
        commands.add(new Command(3,"Test command 3", () ->
        {
            System.out.println("Handle success");
            return null;
        }));
        commands.add(new Command(4,"Test command 4", () ->
        {
            System.out.println("Handle success");
            return null;
        }));
        commands.add(new Command(5,"Test command 5", () ->
        {
            System.out.println("Handle success");
            return null;
        }));
    }
    @Override
    public void OnDraw() {
        for (Command command : commands) {
            System.out.println(command.toString());
        }
        InputHandler.handleInt("Your input?", this::ProcessInput, ()->-1);
    }
    @Override
    public void ProcessInput(String input) {
        int value = Utility.tryParseInt(input, ()-> -1);
        if(value == -1){
            System.out.println("Wrong input");
            OnDraw();
            return;
        }
        var commandIndex = commands.stream().filter(e->e.getIndex() == value).findAny().orElse(null);
        if(commandIndex == null){
            System.out.println("Wrong input");
            OnDraw();
            return;
        }
        commandIndex.Handle();
    }
    public void ProcessInput(int value) {
        if(value == -1) {
            System.out.println("Wrong input");
            OnDraw();
            return;
        }
        var commandIndex = commands.stream().filter(e->e.getIndex() == value).findAny().orElse(null);
        if(commandIndex == null){
            System.out.println("Wrong input");
            OnDraw();
            return;
        }
        commandIndex.Handle();
    }
}
