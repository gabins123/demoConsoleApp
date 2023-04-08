package demoConsoleApp.Utility.Console;

import demoConsoleApp.Core.InputHandler;
import demoConsoleApp.Utility.Func1;

public class ConsoleActionHandler<T> {
    private final Func1<T,String> handler;
    private final String des;
    private final String errorMessage;
    private final String exitKey;
    private final boolean isExitWhenFail;

    public ConsoleActionHandler(Func1< T,String> handler,String des,
                                String errorMessage ,String exitKey, boolean isExitWhenFail){
        this.handler = handler;
        this.des = des;
        this.errorMessage = errorMessage;
        this.exitKey = exitKey;
        this.isExitWhenFail = isExitWhenFail;
    }
    public T handle(Func1<Boolean, T> validation){
        while(true){
            try{
                var input = InputHandler.getInput(des, null);
                assert input != null;
                if(input.equalsIgnoreCase(exitKey)){
                    return  null;
                }
                var value = handler.invoke(input);
                if(validation == null || validation.invoke(value)){
                    return value;
                }
                throw new Exception(errorMessage);
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
                if(isExitWhenFail)
                   break;
            }
        }
        return null;
    }
}
