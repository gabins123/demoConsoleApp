package baitap;

public class Account {
    private String username;
    private String password;
    private String customerCCCD;

    public Account(String username, String password, String customerCCCD) {
        this.username = username;
        this.password = password;
        this.customerCCCD = customerCCCD;
    }

    public String getCustomerCCCD() {
        return customerCCCD;
    }

    public void setCustomerCCCD(String customerCCCD) {
        this.customerCCCD = customerCCCD;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", customerCCCD='" + customerCCCD + '\'' +
                '}';
    }
}
