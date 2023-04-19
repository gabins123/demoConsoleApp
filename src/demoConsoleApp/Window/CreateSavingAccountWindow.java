package demoConsoleApp.Window;

import demoConsoleApp.Core.*;
import demoConsoleApp.Core.Data.DataAPI;
import demoConsoleApp.Core.Data.SavingAccount;
import demoConsoleApp.Core.Data.TermSavingAccount;
import demoConsoleApp.Main;
import demoConsoleApp.Utility.Console.ActionResult;
import demoConsoleApp.Utility.Console.Command;
import demoConsoleApp.Utility.Console.ConsoleActionHandler;
import demoConsoleApp.Utility.Console.ConsoleWindow;

import java.io.Console;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CreateSavingAccountWindow extends ConsoleWindow implements IConfigObserver<InterestConfigRecord> {

    ArrayList<Command> selectAccountType;
    public CreateSavingAccountWindow(){
        selectAccountType = new ArrayList<>();
    }
    @Override
    public void onDraw() {
        InterestConfig.getInstance().registered(this);
        var type = handleSavingTerm();
        if(type == 0){
            InterestConfig.getInstance().unregistered(this);
            Main.SwitchWindows(WindowType.AccountManager, null);
            return;
        }
       var balanceStr = handleInitalizeBalance();
       if(balanceStr == null){
           InterestConfig.getInstance().unregistered(this);
           Main.SwitchWindows(WindowType.AccountManager, null);
           return;
       }
       var balanace = new BigDecimal(balanceStr);
       var calendar = Calendar.getInstance();
        var rcInterest = InterestConfig.getInstance().getRecord(type);
       var current = calendar.getTime();
       calendar.add(rcInterest.getCalendarType(), rcInterest.getPeriod());
       var newAccount = new TermSavingAccount(current,calendar.getTime() ,type, LoginSession.getInstance().getNextSavingAccountID(),balanace);
       var result= DataAPI.tryAddTermSavingAccount(newAccount);
       if(!result.isSuccess()){
            System.out.println(result.getMessage());
            onDraw();
            return;
       }
       InterestConfig.getInstance().unregistered(this);
       Main.SwitchWindows(WindowType.AccountManager, null);
    }

    private int handleSavingTerm() {
        isShowingRate = true;
        var rcs = InterestConfig.getInstance().getRecords();
        var rcIndex = rcs.stream().map(InterestConfigRecord::getID).toList();
        for (var rc : rcs){
            if(rc.getID() == 0)
                continue;
            System.out.println(rc);
        }
        var handle=new ConsoleActionHandler<>(Integer::parseInt, "Chon ky han.","exit",false);
        var value =  handle.handle((e)->{
            if(!rcIndex.contains(e))
                return new ActionResult(false, "Khong tim thay lua chon!");
            return new ActionResult(true,"");
        });
        isShowingRate = false;
        if(value == null)
            return 0;
        return value;
    }

    private String handleInitalizeBalance(){
        var handlerBalance = new ConsoleActionHandler<>(String::toUpperCase, "Nhap so du ban dau", "exit", false);
        return handlerBalance.handle((value)->{
            try{
                var balance = new BigDecimal(value);
                if(balance.compareTo(new BigDecimal( 100000)) < 0){
                    return new ActionResult(false, "So du phai la so >= 100k");
                }
                var mainBalance = LoginSession.getInstance().getCurrentAccount().getDefaultAccount().getBalance();
                if(mainBalance.subtract(balance).compareTo(new BigDecimal(50000)) < 0){
                    return new ActionResult(false, "So du con lai phai la so >= 50k");
                }
                return ActionResult.valid();
            } catch (NumberFormatException e) {
                return new ActionResult(false, "So du phai la so");
            }
        });
    }
    boolean isShowingRate;
    @Override
    public void OnRefresh(List<InterestConfigRecord> rcs) {
        if(isShowingRate){
            for (var rc : rcs){
                if(rc.getID() == 0)
                    continue;
                System.out.println(rc);
            }
        }
    }
}
