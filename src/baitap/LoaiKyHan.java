package baitap;

public class LoaiKyHan {
    private int kyHan;
    private double laiSuat;

    public LoaiKyHan() {
    }

    public LoaiKyHan(int kyHan, double laiSuat) {
        this.kyHan = kyHan;
        this.laiSuat = laiSuat;
    }

    public int getKyHan() {
        return kyHan;
    }

    public void setKyHan(int kyHan) {
        this.kyHan = kyHan;
    }

    public double getLaiSuat() {
        return laiSuat;
    }

    public void setLaiSuat(double laiSuat) {
        this.laiSuat = laiSuat;
    }

    @Override
    public String toString() {
        return "LoaiKyHan{" +
                "kyHan=" + kyHan +
                ", laiSuat=" + laiSuat +
                '}';
    }
}
