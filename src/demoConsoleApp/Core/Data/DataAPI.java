package demoConsoleApp.Core.Data;

import java.util.List;

public class DataAPI {
    private final static DatabaseLocal api = new DatabaseLocal() ;
    private static List<TermSavingAccount> savingAccounts;
    private static List<Account> accounts;
    private static List<Customer> customers;
    public static void initialize(){
        savingAccounts = load(DataPath.SAVING_ACCOUNT_PATH, TermSavingAccount.class);
        accounts  = load(DataPath.ACCOUNT_PATH, Account.class);
        customers= load(DataPath.CUSTOMER_PATH, Customer.class);
    }
    public static <T> List<T> load(String path, Class<T> tClass){
        return api.load(path, tClass);
    }
    public static Account getAccount(String username){
        return accounts.stream().filter(e-> e.getUsername().equals(username)).findAny().orElse(null);
    }
    public static Customer getCustomerByID(String id){
        return customers.stream().filter(e-> e.getId().equals(id)).findAny().orElse(null);
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
    public static boolean tryAddTermSavingAccount(TermSavingAccount savingAccount){
        if (savingAccounts.stream().anyMatch(e-> e.getID().equals(savingAccount.getID()))){
            System.out.println("id is duplicated !!");
            return false;
        }
        savingAccounts.add(savingAccount);
        return api.set(DataPath.SAVING_ACCOUNT_PATH, savingAccount);
    }
    public static boolean tryAddAccount(Account account){
        if (accounts.stream().anyMatch(e-> e.getUsername().equals(account.getUsername()))){
            System.out.println("Khách hàng này đã có tài khoan");
            return false;
        }
        return api.set(DataPath.ACCOUNT_PATH, account);
    }
}
