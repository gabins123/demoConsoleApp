package baitap;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
//        demoConsoleApp.Window.MainWindow test = new demoConsoleApp.Window.MainWindow();
//        test.OnDraw();
//
        LoaiKyHan loaiKyHan1 = new LoaiKyHan(7,2);
        LoaiKyHan loaiKyHan2 = new LoaiKyHan(30,5.5);
        LoaiKyHan loaiKyHan3 = new LoaiKyHan(180,7.5);
        LoaiKyHan loaiKyHan4 = new LoaiKyHan(365,7.9);
        Customer customer = new Customer();
        customer.setTen("dahsdjkakdak");
        customer.setGioiTinh("dahsdjkakdak");
        customer.setNgaySinh(new Date());
        customer.setQueQuan("dahsdjkakdak");
        customer.setTienGui(56);
        customer.setCanCuoc("dahsdjkakdak");

        Account account1 = new Account("ac1","ac1",customer.getCanCuoc());
        Account account2 = new Account("ac2","ac2",customer.getCanCuoc());
        Account account3 = new Account("ac3","ac3",customer.getCanCuoc());
        Account account4 = new Account("ac4","ac4",customer.getCanCuoc());

        String fileAccount = "data/accountsData.txt";
        String fileCustomer = "data/customersData.txt";
        FunctionImpl function = new FunctionImpl();
        function.saveAccount(account1,fileAccount);
        function.saveAccount(account1,fileAccount);
        function.saveAccount(account1,fileAccount);
        function.saveAccount(account1,fileAccount);
        function.saveAccount(account2,fileAccount);
        function.saveAccount(account3,fileAccount);
        function.saveAccount(account4,fileAccount);
        function.loadAccount(fileAccount).forEach(account -> System.out.println(account));
        function.saveCustomer(customer,fileCustomer);
        function.loadCustomer(fileCustomer).forEach(account -> System.out.println(account));

    }
}