package demoConsoleApp.Window;

import demoConsoleApp.Core.Data.DataAPI;
import demoConsoleApp.Core.Data.TermSavingAccount;
import demoConsoleApp.Core.LoginSession;
import demoConsoleApp.Main;
import demoConsoleApp.Utility.Console.ActionResult;
import demoConsoleApp.Utility.Console.Command;
import demoConsoleApp.Utility.Console.ConsoleActionHandler;
import demoConsoleApp.Utility.Console.ConsoleWindow;
import demoConsoleApp.Utility.StringUtility;

import java.io.Console;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

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
            var rs = DataAPI.withdrawAccount(LoginSession.getInstance().getCurrentAccountID(), new BigDecimal(amount));
            System.out.println(rs.getMessage());
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
            System.out.println(rs.getMessage());
            onDraw();
        }));
        commands.add(new Command(5,"Tra cuu lai xuat", () ->
        {
            var handler = new ConsoleActionHandler<>(e->e, "Nhap tai khoan muon tra cuu", "exit", false);
            TermSavingAccount account =null;
            var id = handler.handle(e-> {
                var accounts = LoginSession.getInstance().getCurrentAccount().getSavingAccounts();
                var _account = accounts.stream().filter(a->a.getID().equals(e)).findAny().orElse(null);
                if(_account == null){
                    return new ActionResult(false, String.format("Khong the tim thay tai khoan tiet kiem co id %s trong tai khoan cua ban. vui long nhap id khac",e));
                }
                return ActionResult.valid();
            });
            if( id == null)
            {
                onDraw();
                return;
            }
            var accounts = LoginSession.getInstance().getCurrentAccount().getSavingAccounts();
            account = accounts.stream().filter(a->a.getID().equals(id)).findAny().orElse(null);
            System.out.println("Lai nhan duoc: " +StringUtility.toVND(account.calculateInterest(account.getPaidTime().compareTo(new Date()) <=0)));
            onDraw();
        }));
        commands.add(new Command(6,"Rut tien tai khoan co ky han", () ->
        {
            var handler = new ConsoleActionHandler<>(e->e, "Nhap tai khoan muon rut", "exit", false);
            TermSavingAccount savingAccount =null;
            var id = handler.handle(e-> {
                var accounts = LoginSession.getInstance().getCurrentAccount().getSavingAccounts();
                var _account = accounts.stream().filter(a->a.getID().equals(e)).findAny().orElse(null);
                if(_account == null){
                    return new ActionResult(false, String.format("Khong the tim thay tai khoan tiet kiem co id %s trong tai khoan cua ban. vui long nhap id khac",e));
                }
                return ActionResult.valid();
            });
            if( id == null)
            {
                onDraw();
                return;
            }
            var mainAccount = DataAPI.getAccount(LoginSession.getInstance().getCurrentAccount().getUsername());
            var accounts = mainAccount.getSavingAccounts();

            savingAccount = accounts.stream().filter(a->a.getID().equals(id)).findAny().orElse(null);

            if(savingAccount != null){
                var balance = savingAccount.getBalance();
                var rsWithdraw = DataAPI.withdrawSavingAccount(savingAccount.getID(), mainAccount);
                var rsDepositSavingBalance = DataAPI.depositAccount(mainAccount, balance.add(savingAccount.calculateInterest(savingAccount.getPaidTime().compareTo(new Date()) <= 0)));
                System.out.println(rsDepositSavingBalance.getMessage());
                onDraw();
            }//xu li xoa tai khoan khi rut
        }));
        commands.add(new Command(7,"Doi mat khau", () ->
        {
            var handler = new ConsoleActionHandler<>(e->e, "Nhap lai mat khau cu", "exit", false);
            var id = handler.handle(e-> {
                var password = LoginSession.getInstance().getCurrentAccount().getPassword();
                if(!password.equals(e)){
                    return new ActionResult(false, "Mat khau sai");
                }
                return ActionResult.valid();
            });
            if(id == null)
            {
                onDraw();
                return;
            }
            id = handler.handle(e-> {
                if(e.length() < 6){
                    return new ActionResult(false, "Mat khau phai lon hon 6 ky tu!");
                }
                return ActionResult.valid();
            }, "Mat khau moi");
            var trySave = DataAPI.tryChancePassword(LoginSession.getInstance().getCurrentAccountID(), id);
            System.out.println(trySave.getMessage());
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
                return ActionResult.valid();
            } catch (NumberFormatException e) {
                return new ActionResult(false, "So du phai la so");
            }
        });
    }
    private String handleDepositBalance(){
        var handlerBalance = new ConsoleActionHandler<>((e)->e, "Nhap so tien muon nap", "exit", false);
        return handlerBalance.handle((balance)->{
            try {
                return ActionResult.valid();
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
