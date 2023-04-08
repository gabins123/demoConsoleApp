package baitap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FunctionImpl implements Function{
    @Override
    public Account createAccount(Customer customer) {
//        if(loadAccount("accountsData.txt") == null){
//
//        }
        return null;
    }

    @Override
    public void login(Account account) {

    }

    @Override
    public AccountTD createAccountTD(Customer customer) {
        return null;
    }

    @Override
    public List<Customer> loadCustomer(String file) throws ParseException, FileNotFoundException {
        List<Customer> customers = new ArrayList<>();
        File fileInput = new File(file);
        Scanner scanner = new Scanner(fileInput);
        while (scanner.hasNext()){
            String[] s = scanner.nextLine().split(",");
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

            if(s != null && s.length > 0){
                Date date = sdf.parse(s[2]);
                Customer customerTmp = new Customer(s[0], s[1], date, s[3], s[4], Double.parseDouble(s[5]), new Gson().fromJson(s[6], new TypeToken<ArrayList<AccountTD>>(){}.getType()));
                customers.add(customerTmp);
            }

        }
        return customers;
    }

    @Override
    public List<Account> loadAccount(String file) throws FileNotFoundException {
        List<Account> accounts = new ArrayList<>();
        File fileInput = new File(file);
        Scanner scanner = new Scanner(fileInput);
        while (scanner.hasNext()){
            String[] s = scanner.nextLine().split(",");
            if(s != null && s.length > 0){
                Account accountTmp = new Account(s[0], s[1], s[2]);
                accounts.add(accountTmp);
            }

        }
        return accounts;
    }

    @Override
    public void saveCustomer(Customer customer, String file) throws IOException, ParseException {

        List<Customer> customers = loadCustomer(file);
        String data = customer.getTen() + "," + customer.getGioiTinh() + ","
                + customer.getNgaySinh() + "," + customer.getQueQuan() + ","
                + customer.getCanCuoc() + "," + customer.getTienGui() + "," + customer.getAccountTDS();
        File fileOutput = new File(file);
        FileWriter fileWriter = new FileWriter(fileOutput, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        List<String> cccds = customers.stream().map(Customer::getCanCuoc).collect(Collectors.toList());
        if(!cccds.contains(customer.getCanCuoc()))
            bufferedWriter.write(data + "\n");
        else System.out.println("CCCD is duplicated !!");

        bufferedWriter.close();
    }

    @Override
    public void saveAccount(Account account, String file) throws IOException {
        List<Account> accounts = loadAccount(file);
        String data = account.getUsername() + "," + account.getPassword() + "," + account.getCustomerCCCD();
        File fileOutput = new File(file);
        FileWriter fileWriter = new FileWriter(fileOutput, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        List<String> usernames = accounts.stream().map(Account::getUsername).collect(Collectors.toList());
        List<String> cccds = accounts.stream().map(Account::getCustomerCCCD).collect(Collectors.toList());
        if(!usernames.contains(account.getUsername()) && !cccds.contains(account.getCustomerCCCD()))
            bufferedWriter.write(data + "\n");
        else System.out.println("Username or cccd is duplicated !!");

        bufferedWriter.close();
    }
}
