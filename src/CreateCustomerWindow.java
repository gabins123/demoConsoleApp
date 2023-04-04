import demoConsoleApp.Core.InputHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class CreateCustomerWindow extends ConsoleWindow{
    public CreateCustomerWindow() {
    }
    @Override
    public void OnDraw() {
        Customer customer = new Customer();
        boolean isBreaked = false;
        while (!isBreaked){
            var value= InputHandler.getInput("Họ và tên của bạn là gì?",()->OnFail("Họ và tên bị sai. Hãy nhập lại họ tên"));
            value= InputHandler.getInput("Nhập ngày sinh",null);
            try{
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YY");
                Date text = formatter.parse(value);
                System.out.println(text.toString());
            } catch (ParseException e) {
                OnFail("Họ và tên bị sai. Hãy nhập lại ngày tháng theo chuẩn dd/MM/YY");
            }
        }
    }
    private void OnFail(String message){
        System.out.println(message);
        Main.SwitchWindows(WindowType.Home, null);
    }
}
