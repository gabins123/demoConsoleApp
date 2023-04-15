package demoConsoleApp.Window;

import demoConsoleApp.Core.Data.DataAPI;
import demoConsoleApp.Core.Data.SavingAccount;
import demoConsoleApp.Core.InterestConfigRecord;
import demoConsoleApp.Core.LoginSession;
import demoConsoleApp.Main;
import demoConsoleApp.Utility.Console.ActionResult;
import demoConsoleApp.Utility.Console.Command;
import demoConsoleApp.Utility.Console.ConsoleActionHandler;
import demoConsoleApp.Utility.Console.ConsoleWindow;
import demoConsoleApp.Utility.StringUtility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;

public class AccountManagerWindow extends ConsoleWindow {
    private final ArrayList<Command> commands;
    public AccountManagerWindow() {
        commands = new ArrayList<>();
        commands.add(new Command(1,"Kiem tra so du", () ->
        {
            System.out.println("So du cua ban la "+ StringUtility.toVND(LoginSession.getInstance().getCurrentAccount().getDefaultAccount().getBalance()));
            onDraw();
        }));
        commands.add(new Command(2,"Tao tai khoan co ky han", () ->
        {
            var balance  =  LoginSession.getInstance().getCurrentAccount().getDefaultAccount().getBalance();
            if( balance.compareTo(new BigDecimal( "150000")) < 0){
                System.out.println("So du cua ban phai lon hon 150k de mo them tai khoan! vui long nap them!");
                onDraw();
                return;
            }
            Main.SwitchWindows(WindowType.CreateSavingAccount, null);
        }));
        commands.add(new Command(4,"Rut tien", () ->
        {
            var balance = LoginSession.getInstance().getCurrentAccount().getDefaultAccount().getBalance();
            var amount = handleWithdrawBalance(balance);
            if(amount == null){
                onDraw();
                return;
            }
            var rs = DataAPI.depositAccount(LoginSession.getInstance().getCurrentAccountID(), new BigDecimal(amount));
            System.out.println(rs.message);
            onDraw();
        }));
        commands.add(new Command(3,"Nap tien", () ->
        {
            var amount = handleDepositBalance();
            if(amount == null){
               onDraw();
               return;
            }
            var rs = DataAPI.depositAccount(LoginSession.getInstance().getCurrentAccountID(), new BigDecimal(amount));
            System.out.println(rs.message);
            onDraw();
        }));
        commands.add(new Command(5,"Tra cuu lai xuat", () ->
        {
            var handler = new ConsoleActionHandler<>(e->e, "Nhap tai khoan muon tra cuu", "exit", false);
            SavingAccount account =null;
            var id = handler.handle(e-> {
                var accounts = LoginSession.getInstance().getCurrentAccount().getSavingAccounts();
                var _account = accounts.stream().filter(a->a.getID().equals(e)).findAny().orElse(null);
                if(_account == null){
                    return new ActionResult(false, String.format("Khong the tim thay tai khoan tiet kiem co id %s trong tai khoan cua ban. vui long nhap id khac",e));
                }
                return new ActionResult(true, null);
            });
            if( id == null)
            {
                onDraw();
                return;
            }
            var accounts = LoginSession.getInstance().getCurrentAccount().getSavingAccounts();
            account = accounts.stream().filter(a->a.getID().equals(id)).findAny().orElse(null);
            System.out.println("Lai nhan duoc: " +StringUtility.toVND(account.calculateInterest()));
            onDraw();
        }));
        commands.sort(Comparator.comparingInt(Command::getIndex));
    }
    private String handleWithdrawBalance(BigDecimal mainBalance){
        var handlerBalance = new ConsoleActionHandler<>(String::toUpperCase, "Nhap so tien muon rut", "exit", false);
        return handlerBalance.handle((value)->{
            try{
                var balance = new BigDecimal(value);
                if(mainBalance.subtract(balance).compareTo(new BigDecimal(50000)) < 0){
                    return new ActionResult(false, "So du con lai phai la so >= 50k");
                }
                return new ActionResult(true, null);
            } catch (NumberFormatException e) {
                return new ActionResult(false, "So du phai la so");
            }
        });
    }
    private String handleDepositBalance(){
        var handlerBalance = new ConsoleActionHandler<>((e)->e, "Nhap so tien muon nap", "exit", false);
        return handlerBalance.handle((balance)->{
            try {
                return new ActionResult(true, null);
            }catch (NumberFormatException e) {
                return new ActionResult(false, "Phai la so");
            }

        });
    }
    @Override
    public void onDraw() {
        ConsoleActionHandler.handleCommand(commands, "Lua chon cua ban: ", "exit", ()->{
            LoginSession.getInstance().SignOut();
            Main.SwitchWindows(WindowType.Home,null );
        });
    }
}
