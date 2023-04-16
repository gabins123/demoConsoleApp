package demoConsoleApp.Core.Data;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import demoConsoleApp.Core.Data.SavingAccount;
import demoConsoleApp.Core.InterestConfig;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class TermSavingAccount extends SavingAccount{
    private Date createDate;
    private Date paidDate;
    private int accountType;

    public TermSavingAccount( Date createDate , Date paidDate , int accountType, String id, BigDecimal balance) {
        super(id, balance);
        this.createDate = createDate;
        this.accountType = accountType;
        this.paidDate = paidDate;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public Date getPaidDate() { return paidDate; }
    public int getAccountType() { return accountType; }
    @Override
    public BigDecimal calculateInterest(){
        var rcInterest = InterestConfig.getInstance().getRecord(accountType);
        int diffInDays = (int)((paidDate.getTime() - createDate.getTime())
                / (1000 * 60 * 60 * 24));
        return balance.multiply(new BigDecimal(rcInterest.getRate() * diffInDays /365));
    }
}
