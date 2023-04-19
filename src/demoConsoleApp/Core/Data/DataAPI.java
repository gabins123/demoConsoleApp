package demoConsoleApp.Core.Data;

import demoConsoleApp.Core.LoginSession;
import demoConsoleApp.Utility.Console.ActionResult;
import demoConsoleApp.Utility.DateTimeUtility;
import demoConsoleApp.Utility.StringUtility;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;;
public class DataAPI {
    private final static DatabaseLocal api = new DatabaseLocal() ;
    private static List<TermSavingAccount> savingAccounts;
    private static List<Account> accounts;
    private static List<Customer> customers;
    public static void initialize(){
        savingAccounts = load(DataPath.SAVING_ACCOUNT_PATH, TermSavingAccount.class);
        accounts  = load(DataPath.ACCOUNT_PATH, Account.class);
        customers = load(DataPath.CUSTOMER_PATH, Customer.class);
    }
    public static <T> List<T> load(String path, Class<T> tClass){
        return api.load(path, tClass);
    }
    public static <T> void set(String path, T data){
        api.set(path, data);
    }
    public static Account getAccount(String username){
        return accounts.stream().filter(e-> e.getUsername().equals(username)).findAny().orElse(null);
    }
    public static Customer getCustomerByID(String id){
        return customers.stream().filter(e-> e.getId().equals(id)).findAny().orElse(null);
    }
    public static List<Customer> getCustomers(){
        return customers;
    }
    public static TermSavingAccount getSavingAccountByID(String id){
        return savingAccounts.stream().filter(e-> e.getID().equals(id)).findAny().orElse(null);
    }
    public static boolean tryAddCustomer(Customer customer){
        if (customers.stream().anyMatch(e-> e.getCiC() == customer.getCiC())){
            System.out.println("CCCD is duplicated !!");
            return false;
        }
        customer.generateId(customers.size());
        customers.add(customer);
        return api.set(DataPath.CUSTOMER_PATH, customer);
    }
    public static ActionResult tryAddTermSavingAccount(TermSavingAccount savingAccount){
        var accountID = LoginSession.getInstance().getCurrentAccountID();
        if(accountID == null){
            return new ActionResult(false,null);
        }
        if (savingAccounts.stream().anyMatch(e-> e.getID().equals(savingAccount.getID()))){
            return new ActionResult(false,"id is duplicated !!");
        }
        var account= getAccount(accountID);
        account.addSavingAccount(savingAccount);

        var mainAccount = account.getDefaultAccount();
        mainAccount.withdraw(savingAccount.getBalance());
        account.setDefaultAccount(mainAccount);

        savingAccounts.add(savingAccount);
        api.set(DataPath.SAVING_ACCOUNT_PATH,savingAccount, savingAccount.getID());
        api.set(DataPath.ACCOUNT_PATH, account,account.getUsername());
        return new ActionResult(true,null);
    }
    public static ActionResult depositAccount(String accountID, BigDecimal amount){
        var account = getAccount(accountID);
        if(account == null){
            return new ActionResult(false,"Account ID khong ton tai");
        }
        account.getDefaultAccount().deposit(amount);
        api.set(DataPath.ACCOUNT_PATH, account,account.getUsername());
        return new ActionResult(true,"Nap tien thanh cong!! So du con lai: " + StringUtility.toVND(account.getDefaultAccount().getBalance()));
    }
    public static ActionResult withdrawAccount(String accountID, BigDecimal amount){
        var account = getAccount(accountID);
        if(account == null){
            return new ActionResult(false,"Account ID khong ton tai");
        }
        account.getDefaultAccount().withdraw(amount);
        api.set(DataPath.ACCOUNT_PATH, account,account.getUsername());
        return new ActionResult(true,"Rut tien thanh cong!! So du con lai: " + StringUtility.toVND(account.getDefaultAccount().getBalance()));
    }
    public static ActionResult withdrawSavingAccount(String accountSavingID, Account account){
        var accountSaving = getSavingAccountByID(accountSavingID);
        if(accountSaving == null){
            return new ActionResult(false,"Account Saving ID khong ton tai");
        }

        accountSaving.withdraw(accountSaving.getBalance());

        api.set(DataPath.SAVING_ACCOUNT_PATH, accountSaving,accountSaving.getID());

        return new ActionResult(true,"Rut tien tai khoan ky han thanh cong!!" + "\nBalance saving: " + StringUtility.toVND(accountSaving.getBalance()) + "\nBalance main: "+ StringUtility.toVND(account.getDefaultAccount().getBalance()) + "\nNgay dao han:" + DateTimeUtility.toDefaultFormat(accountSaving.getPaidTime()));
    }
    public static boolean tryAddAccount(Account account){
        if (accounts.stream().anyMatch(e-> e.getUsername().equals(account.getUsername()))){
            System.out.println("Khách hàng này đã có tài khoan");
            return false;
        }
        return api.set(DataPath.ACCOUNT_PATH, account);
    }
    public static Customer getCustomerByFullnameAndCustomerId(String fullname, String id){
        List<Customer> customerList = customers.stream().filter(e-> e.getFullName().equals(fullname)).collect(Collectors.toList());
        return customerList.stream().filter(customer -> customer.getId().equals(id)).findAny().orElse(null);
    }

    public static ActionResult tryChancePassword(String accountID, String newPass) {
        var account = getAccount(accountID);
        if(account == null){
            return new ActionResult(false,"Account ID khong ton tai");
        }
        account.setPassword(newPass);
        api.set(DataPath.ACCOUNT_PATH, account,account.getUsername());
        return new ActionResult(true,"Luu thanh cong");
    }
}
