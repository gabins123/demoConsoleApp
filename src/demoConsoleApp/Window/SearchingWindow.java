package demoConsoleApp.Window;

import demoConsoleApp.Core.Data.*;
import demoConsoleApp.Core.LoginSession;
import demoConsoleApp.Main;
import demoConsoleApp.Utility.Console.ActionResult;
import demoConsoleApp.Utility.Console.ConsoleActionHandler;
import demoConsoleApp.Utility.Console.ConsoleWindow;
import demoConsoleApp.Utility.Console.Command;
import demoConsoleApp.Utility.StringUtility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class SearchingWindow  extends ConsoleWindow{
    private final ArrayList<Command> commandSearch;

    public SearchingWindow() {
        commandSearch = new ArrayList<>();
        commandSearch.add(new Command(1,"Tra cứu khách hàng theo họ tên và mã số khách hàng", () ->
        {
            var handlerFullname = new ConsoleActionHandler<>((e) -> e, "Họ và tên của bạn là gì?", "exit", false);
            var fullnameValue = handlerFullname.handle((e)->new ActionResult(StringUtility.isValidName(e),"Tên chỉ được chữ cái và khoảng trống, không được có số"));

            if(fullnameValue == null){
                Main.SwitchWindows(WindowType.Home, null);
                return;
            }
            var handlerID = new ConsoleActionHandler<>((e) -> e, "Mã số khách hàng của bạn là gì?", "exit", false);
            var idValue = handlerID.handle(null);

            Customer customer = DataAPI.getCustomerByFullnameAndCustomerId(fullnameValue, idValue);
            if(customer == null){
                Main.SwitchWindows(WindowType.Home, null);
                return;
            }

            System.out.println(customer.toString());
            onDraw();
        }));
        commandSearch.add(new Command(2,"Tra cứu danh sách tài khoản của một khách hàng theo mã số khách hàng", () ->
        {
            var handlerID = new ConsoleActionHandler<>((e) -> e, "Mã số khách hàng của bạn là gì?", "exit", false);
            var idValue = handlerID.handle(null);
            Customer customer = DataAPI.getCustomerByID(idValue);
            if(customer == null){
                Main.SwitchWindows(WindowType.Home, null);
                return;
            }

            Account account = DataAPI.getAccount(customer.getId());
            System.out.println(account.infoAccountToString());
            System.out.println("=====================================");
            List<TermSavingAccount> savingAccounts = account.getSavingAccounts();
            System.out.println("\n\tTài khoản có kỳ hạn:");
            savingAccounts.stream().forEach(termSavingAccount -> System.out.println(termSavingAccount.savingAccountToString()+"\n"));

            onDraw();
        }));
        commandSearch.add(new Command(3,"Sắp xếp danh sách khách hàng có tổng số tiền gửi giảm dần", () ->
        {
            List<Customer> customers = DataAPI.getCustomers();
            customers.stream().sorted((o1, o2) -> {
                Account account1 = DataAPI.getAccount(o1.getId());
                BigDecimal ac1Balance = account1.getDefaultAccount().getBalance();
                account1.getSavingAccounts().forEach(termSavingAccount -> {
                    ac1Balance.add(termSavingAccount.getBalance());
                });
                Account account2 = DataAPI.getAccount(o2.getId());
                BigDecimal ac2Balance = account2.getDefaultAccount().getBalance();
                account2.getSavingAccounts().forEach(termSavingAccount -> {
                    ac2Balance.add(termSavingAccount.getBalance());
                });
                return ac1Balance.compareTo(ac2Balance);
            });

            customers.stream().forEach(customer -> {
                System.out.println("\t======== Khách hàng "+(customers.indexOf(customer)+1)+" ======\n");
                System.out.println(customer.toString());
                Account account = DataAPI.getAccount(customer.getId());
                BigDecimal acBalance = account.getDefaultAccount().getBalance();
                for (int i = 0; i < account.getSavingAccounts().size(); i++){
                    acBalance = acBalance.add(account.getSavingAccounts().get(i).getBalance());
                }

                System.out.println(String.format("Tổng tiền gửi: %s\n",
                        StringUtility.HandleEmptyString(StringUtility.toVND(acBalance), "...")));
            });
            onDraw();
        }));

        commandSearch.sort(Comparator.comparingInt(Command::getIndex));
    }

    @Override
    public void onDraw() {
        ConsoleActionHandler.handleCommand(commandSearch, "Lua chon cua ban: ", "exit", ()->{
            LoginSession.getInstance().SignOut();
            Main.SwitchWindows(WindowType.Home,null );
        });
    }
}
