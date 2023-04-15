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
    private int accountType;

    public TermSavingAccount( Date createDate , int accountType, String id, BigDecimal balance) {
        super(id, balance);
        this.createDate = createDate;
        this.accountType = accountType;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public int getAccountType() {
        return accountType;
    }
    @Override
    public BigDecimal calculateInterest(){
        var rcInterest = InterestConfig.getInstance().getRecord(accountType);
        var calendar =Calendar.getInstance();
        calendar.setTime(createDate);
        calendar.add(rcInterest.getCalendarType(), rcInterest.getPeriod());
        int diffInDays = (int)((calendar.getTime().getTime() - createDate.getTime())
                / (1000 * 60 * 60 * 24));

        return balance.multiply(new BigDecimal(rcInterest.getRate() * diffInDays /365));
    }
    public Date getPaidTime(){
        var rcInterest = InterestConfig.getInstance().getRecord(accountType);
        var calendar =Calendar.getInstance();
        calendar.setTime(createDate);
        calendar.add(rcInterest.getCalendarType(), rcInterest.getPeriod());
        return calendar.getTime();
    }
}
