package demoConsoleApp.Utility.Console;

import demoConsoleApp.Core.InputHandler;
import demoConsoleApp.Utility.Delegate.Action;
import demoConsoleApp.Utility.Delegate.Func1;

import java.text.NumberFormat;
import java.util.List;

public class ConsoleActionHandler<T> {
    private final Func1<T,String> handler;
    private final String des;
    private final String exitKey;
    private final boolean isExitWhenFail;

    public ConsoleActionHandler(Func1< T,String> handler,String des, String exitKey, boolean isExitWhenFail){
        this.handler = handler;
        this.des = des;
        this.exitKey = exitKey;
        this.isExitWhenFail = isExitWhenFail;
    }
    public T handle(Func1<ActionResult, T> validation){
        while(true){
            try{
                var input = InputHandler.getInput(des, null);
                assert input != null;
                if(!exitKey.equals("")   && input.equalsIgnoreCase(exitKey)){
                    return  null;
                }
                var value = handler.invoke(input);
                if(validation == null){
                    return value;
                }
                var valid = validation.invoke(value);
                if(valid.isSuccess()){
                    return value;
                }
                System.out.println(valid.getMessage());
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
                if(isExitWhenFail)
                   break;
            }
        }
        return null;
    }
    public T handle(Func1<ActionResult, T> validation, String overrideDes){
        while(true){
            try{
                var input = InputHandler.getInput(overrideDes, null);
                assert input != null;
                if(input.equalsIgnoreCase(exitKey)){
                    return  null;
                }
                var value = handler.invoke(input);
                if(validation == null){
                    return value;
                }
                var valid = validation.invoke(value);
                if(valid.isSuccess()){
                    return value;
                }
                System.out.println(valid.getMessage());
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
                if(isExitWhenFail)
                   break;
            }
        }
        return null;
    }

    public static void handleCommand(List<Command> commands, String des, String exitKey, Action onExit)
    {
        var indexes = commands.stream().map(Command::getIndex).toList();
        while(true){
            for (Command command : commands) {
                System.out.println(command.toString());
            }
            var input = InputHandler.getInput(des, null);
            assert input != null;
            if(!exitKey.equals("")   && input.equalsIgnoreCase(exitKey)){
                onExit.invoke();
                break;
            }
            try{
                var index = Integer.parseInt(input);
                if(!indexes.contains( index)){
                    System.out.println("Lua chon khong hop le.");
                    continue;
                }
                var commandIndex = commands.stream().filter(e->e.getIndex() == index).findAny().orElse(null);
                assert commandIndex != null;
                commandIndex.Handle();
                break;
            }
            catch (NumberFormatException e) {
                System.out.println("Lua chon khong hop le.");
            }
        }

    }

}
