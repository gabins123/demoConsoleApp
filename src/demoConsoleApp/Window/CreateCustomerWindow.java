package demoConsoleApp.Window;

import demoConsoleApp.Core.Data.Account;
import demoConsoleApp.Core.Data.Customer;
import demoConsoleApp.Core.Data.DataAPI;
import demoConsoleApp.Core.LoginSession;
import demoConsoleApp.Main;
import demoConsoleApp.Utility.Console.ConsoleActionHandler;
import demoConsoleApp.Utility.Console.ConsoleWindow;
import demoConsoleApp.Utility.DateTimeUtility;
import demoConsoleApp.Utility.StringUtility;

import java.text.ParseException;
import java.util.Date;

public class CreateCustomerWindow extends ConsoleWindow {

    public CreateCustomerWindow() {
    }
    @Override
    public void onDraw() {
        Customer customer = new Customer();
        ShowSignInForm(customer);
        var stringValue = handleName();
        if(stringValue == null){
            Main.SwitchWindows(WindowType.Home, null);
            return;
        }
        customer.setFullName(stringValue);
        //ConsoleUtility.clearConsole();
        ShowSignInForm(customer);

        stringValue = handleSex();
        if(stringValue == null){
            Main.SwitchWindows(WindowType.Home, null);
            return;
        }
        customer.setSex(stringValue);
        //ConsoleUtility.clearConsole();
        ShowSignInForm(customer);
        var cic = handleCic();
        if(cic == 0){
            Main.SwitchWindows(WindowType.Home, null);
            return;
        }

        customer.setCiC(cic);
        //ConsoleUtility.clearConsole();
        ShowSignInForm(customer);
        stringValue = handlHomeTown();
        if(stringValue == null){
            Main.SwitchWindows(WindowType.Home, null);
            return;
        }
        customer.setHomeTown(stringValue);
        //ConsoleUtility.clearConsole();
        ShowSignInForm(customer);

        var bd =handleBirthDate();
        if(bd == null){
            Main.SwitchWindows(WindowType.Home, null);
            return;
        }
        customer.setBirthDate(bd);

        //ConsoleUtility.clearConsole();
        ShowSignInForm(customer);

        var handler = new ConsoleActionHandler<>(String::toUpperCase, "Confirm lại thông tin 1 lần nữa! (Y/N)?", "Chỉ được nhập Y/N", "exit", false);
        var confirming = handler.handle((e)-> e.equalsIgnoreCase("y") || e.equalsIgnoreCase("n"));
        if(confirming.equals("Y")){
            if(!DataAPI.tryAddCustomer(customer))            {
                //TODO: Do something
            }
            else {
                System.out.println("Đăng ký thành công!");
                var account =new Account(customer.getId());
                DataAPI.tryAddAccount(account);
                LoginSession.getInstance().SignIn(account);
                Main.SwitchWindows(WindowType.AccountManager, null);
            }
            Main.SwitchWindows(WindowType.Home, null);
        }
        else{
            System.out.println("Đăng ký không1 thành công!");
            Main.SwitchWindows(WindowType.Home, null);
        }
    }
    private String handleName(){
        var handler = new ConsoleActionHandler<>((e) -> e, "Họ và tên của bạn là gì?", "Tên chỉ được chữ cái và khoảng trống, không được có số", "exit", false);
        return handler.handle(StringUtility::isValidName);
    }
    private String handleSex(){
        var handler = new ConsoleActionHandler<>(String::toUpperCase, "Nhập giới tính của bạn? (Nam hoặc nữ)", "Giới tính phải là nam hoặc nữ", "exit", false);
        return handler.handle((e)-> e.equalsIgnoreCase("nam") || e.equalsIgnoreCase("nữ"));
    }
    private String handlHomeTown(){
        var handler = new ConsoleActionHandler<>((e) -> e, "Quê quán của bạn là gì?", "Có lỗi xảy ra, hãy nhập lại!", "exit", false);
        return handler.handle(null);
    }
    private long handleCic(){
        var handler = new ConsoleActionHandler<>((e) -> {
            try{
                return Long.parseLong(e);
            }
            catch (NumberFormatException exception){
                throw new Exception("CCCD phải là 12 chữ số!");
            }
        }, "CCCD của bạn là gì?", "CCCD phải là 12 chữ số!", "exit", false);

        return handler.handle((e)->e.toString().length() == 12);
    }
    private Date handleBirthDate() {

        var handler = new ConsoleActionHandler<>((e) -> {
            try{
                return DateTimeUtility.DatePattern.parse(e);
            }
            catch (ParseException exception){
                throw new Exception("Nhập bị sai. Hãy nhập lại ngày tháng theo chuẩn dd/MM/yyyy");
            }
        }, "Ngày sinh của bạn là gì?", "Nhập bị sai. Hãy nhập lại ngày tháng theo chuẩn dd/MM/yyyy", "exit", false);
        return handler.handle(null);
    }
    private void ShowSignInForm(Customer currentFormData) {
        System.out.println(currentFormData.toString());
    }

}