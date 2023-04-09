package demoConsoleApp.Core.Data;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
public class SavingAccount {
    private String id;
    private BigDecimal balance;
    public SavingAccount(String id, BigDecimal balance){
        this.id = id;
        this.balance = balance;
    }
    public String getID() {return id;}
    public BigDecimal getBalance() {return balance;}
    protected BigDecimal getInterestRate(){
        return new BigDecimal("0.02");
    }
    public BigDecimal[] calculateInterest(int year){
        var result = new BigDecimal[year + 1];
        var interestRate = getInterestRate();
        for (int i = 0 ; i <= year; i++){
            if(i == 0){
                result[i] = balance;
                continue;
            }
            result[i] = result[i-1].multiply(interestRate);
        }
        return result;
    }
}
