package baitap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface Function {
    public Account createAccount(Customer customer);
    public void login (Account account);
    public AccountTD createAccountTD(Customer customer);
    public List<Customer> loadCustomer (String file) throws ParseException, FileNotFoundException;
    public List<Account> loadAccount (String file) throws IOException;

    public void saveCustomer (Customer customer, String file) throws IOException, ParseException;
    public void saveAccount (Account account, String file) throws IOException;

}
