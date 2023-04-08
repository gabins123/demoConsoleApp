package demoConsoleApp.Core.Data;

import baitap.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseLocal {

    public <T> List<T> load(String path, Class<T> classes)
    {
        FileLoader<T> loader = new FileLoader<>();
        try{
            return loader.load(path, classes);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean addCustomer(Customer customer) {
        var saver = new DataSaver<Customer>();
        try {
            saver.save(DataPath.CUSTOMER_PATH,customer);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean addAccount(Account account)  {
        var saver = new DataSaver<Account>();
        try {
            saver.save(DataPath.ACCOUNT_PATH,account);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
