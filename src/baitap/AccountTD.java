package baitap;

import java.util.Date;

public class AccountTD{
    private LoaiKyHan loaiKyHan;
    private double laiSuatNhan;
    private Date ngayTao;
    private boolean daoHan;
    private double tienGui;
    public AccountTD() {
    }

    public AccountTD(LoaiKyHan loaiKyHan, double laiSuatNhan, Date ngayTao, boolean daoHan, double tienGui) {
        this.loaiKyHan = loaiKyHan;
        this.laiSuatNhan = laiSuatNhan;
        this.ngayTao = ngayTao;
        this.daoHan = daoHan;
        this.tienGui = tienGui;
    }

    public double getTienGui() {
        return tienGui;
    }

    public void setTienGui(double tienGui) {
        this.tienGui = tienGui;
    }

    public LoaiKyHan getLoaiKyHan() {
        return loaiKyHan;
    }

    public void setLoaiKyHan(LoaiKyHan loaiKyHan) {
        this.loaiKyHan = loaiKyHan;
    }

    public double getLaiSuatNhan() {
        return laiSuatNhan;
    }

    public void setLaiSuatNhan(double laiSuatNhan) {
        this.laiSuatNhan = laiSuatNhan;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public boolean isDaoHan() {
        return daoHan;
    }

    public void setDaoHan(boolean daoHan) {
        this.daoHan = daoHan;
    }

    @Override
    public String toString() {
        return "AccountTD{" +
                "loaiKyHan='" + loaiKyHan + '\'' +
                ", laiSuatNhan=" + laiSuatNhan +
                ", ngayTao=" + ngayTao +
                ", daoHan=" + daoHan +
                '}';
    }
}
