import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    public static void handleInt(String title, Action<Integer> handler, Func<Integer> OnHandleFail){
        System.out.println(title);
        Scanner myObj = new Scanner(System.in);
        try{
            var input =  myObj.nextInt();
            handler.Invoke(input);
        }
        catch(InputMismatchException exception)
        {
            handler.Invoke(OnHandleFail.Invoke());
        }
    }
}

