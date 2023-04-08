package demoConsoleApp.Core.Data;

import baitap.Account;

import java.util.List;

public class DataAPI {
    private final static DatabaseLocal api = new DatabaseLocal() ;
    public static <T> List<T> load(String path, Class<T> tClass){
        return api.load(path, tClass);
    }
    public static boolean tryAddCustomer(Customer customer){
        var customers= load(DataPath.CUSTOMER_PATH, demoConsoleApp.Core.Data.Customer.class);
        List<Long> cccds = customers.stream().map(demoConsoleApp.Core.Data.Customer::getCiC).toList();
        if (cccds.contains(customer.getCiC())){
            System.out.println("CCCD is duplicated !!");
            return false;
        }
        customer.generateId(cccds.size());
        return api.set(DataPath.CUSTOMER_PATH, customer);
    }
//    public static boolean tryAddAccount(Account account){
//        var accounts= api.load(DataPath.ACCOUNT_PATH, Account.class);
//        List<String> usernames = accounts.stream().map(Account::getUsername).toList();
//        List<String> cccds = accounts.stream().map(Account::getCustomerCCCD).toList();
//        if (usernames.contains(account.getUsername()) && !cccds.contains(account.getCustomerCCCD())){
//            System.out.println("Username or cccd is duplicated !!");
//            return false;
//        }
//        return api.addAccount(account);
//    }
}
