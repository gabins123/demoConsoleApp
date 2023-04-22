package demoConsoleApp.Core.Data;

import java.math.BigDecimal;
public class SavingAccount {
    private String id;

    protected BigDecimal balance;
    public SavingAccount(String id, BigDecimal balance){
        this.id = id;
        this.balance = balance;
    }

    public void withdraw(BigDecimal value){
        balance = balance.subtract(value);
    }
    public void deposit(BigDecimal value){
        balance = balance.add(value);
    }
    public String getID() {return id;}

    public BigDecimal getBalance() {return balance;}
    public BigDecimal calculateInterest(boolean isFinish){
        return balance;
    }

    @Override
    public String toString() {
        return "SavingAccount{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                '}';
    }
}
