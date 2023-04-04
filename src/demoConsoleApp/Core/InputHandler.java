package demoConsoleApp.Core;

import demoConsoleApp.Utility.Action;
import demoConsoleApp.Utility.Action1;
import demoConsoleApp.Utility.Func;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    static Scanner scanner;
    public static void handleInt(String title, Action1<Integer> handler, Func<Integer> OnHandleFail){
        System.out.println(title);
        scanner = new Scanner(System.in);
        try{
            var input =  scanner.nextInt();
            handler.Invoke(input);
        }
        catch(InputMismatchException exception)
        {
            handler.Invoke(OnHandleFail.Invoke());
        }
    }
    public static void handle(String title, Action1<String> handler, Func<String> OnHandleFail){
        System.out.println(title);
        scanner = new Scanner(System.in);
        try{
            var input =  scanner.nextLine();
            handler.Invoke(input);
        }
        catch(InputMismatchException exception)
        {
            handler.Invoke(OnHandleFail.Invoke());
        }
    }
    public static String getInput(String title, Action onHandleFail){
        System.out.println(title);
        scanner = new Scanner(System.in);
        try{
            return scanner.nextLine();
        }
        catch(InputMismatchException exception)
        {
            if(onHandleFail != null)
                onHandleFail.Invoke();
            return null;
        }
    }
}

