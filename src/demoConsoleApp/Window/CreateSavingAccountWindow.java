package demoConsoleApp.Window;

import demoConsoleApp.Core.Data.TermSavingAccount;
import demoConsoleApp.Core.LoginSession;
import demoConsoleApp.Utility.Console.ConsoleActionHandler;
import demoConsoleApp.Utility.Console.ConsoleWindow;

import java.math.BigDecimal;

public class CreateSavingAccountWindow extends ConsoleWindow {

    @Override
    public void onDraw() {
        var balance  =  LoginSession.getInstance().getCurrentAccount().getDefaultAccount().getBalance();
        if( balance.compareTo(new BigDecimal( "150000")) < 0){
            System.out.println("So du phan lon hon hoac bang 150k ");
            onDraw();
            return;
        }
        System.out.println("So du cua ban la "+  balance);
        var handle = new ConsoleActionHandler<>((e)->{
            try{
                return new BigDecimal(e);
            }
            catch (NumberFormatException exception){
                throw new Exception("CCCD phải là 12 chữ số!");
            }
        }, "Nhap so tien ban muon gui co ky han", "", "exit", false);
        var insertBalance = handle.handle(null);
    }
}
