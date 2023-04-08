package baitap;

import java.util.Date;
import java.util.List;

public class Customer {
    private String ten;
    private String gioiTinh;
    private Date ngaySinh;
    private String queQuan;
    private String canCuoc;
    private double tienGui;
    private List<AccountTD> accountTDS;

    public Customer(){}

    public Customer(String ten, String gioiTinh, Date ngaySinh, String queQuan, String canCuoc, double tienGui, List<AccountTD> accountTDS) {
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.queQuan = queQuan;
        this.canCuoc = canCuoc;
        this.tienGui = tienGui;
        this.accountTDS = accountTDS;
    }

    public List<AccountTD> getAccountTDS() {
        return accountTDS;
    }

    public void setAccountTDS(List<AccountTD> accountTDS) {
        this.accountTDS = accountTDS;
    }
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getCanCuoc() {
        return canCuoc;
    }

    public void setCanCuoc(String canCuoc) {
        this.canCuoc = canCuoc;
    }

    public double getTienGui() {
        return tienGui;
    }

    public void setTienGui(double tienGui) {
        if(tienGui > 50)
            this.tienGui = tienGui;
        else System.out.println("Tien gui khong hop le !!");
    }


    @Override
    public String toString() {
        return "demoConsoleApp.Core.Data.Customer{" +
                "ten='" + ten + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", queQuan='" + queQuan + '\'' +
                ", canCuoc='" + canCuoc + '\'' +
                ", tienGui=" + tienGui +
                ", accountTDS=" + accountTDS +
                '}';
    }
}
