package demoConsoleApp.Core.Data;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import demoConsoleApp.Core.Data.SavingAccount;
import demoConsoleApp.Core.InterestConfig;
import demoConsoleApp.Utility.DateTimeUtility;
import demoConsoleApp.Utility.StringUtility;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class TermSavingAccount extends SavingAccount{
    private Date createDate;
    private int accountType;

    public TermSavingAccount( Date createDate , Date paidDate , int accountType, String id, BigDecimal balance) {
        super(id, balance);
        this.createDate = createDate;
        this.accountType = accountType;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public int getAccountType() { return accountType; }
    @Override
    public BigDecimal calculateInterest(boolean isFinish){
        var rcInterest = InterestConfig.getInstance().getRecord(accountType);
        var calendar =Calendar.getInstance();
        calendar.setTime(createDate);
        calendar.add(rcInterest.getCalendarType(), rcInterest.getPeriod());
        int diffInDays = (int)((calendar.getTime().getTime()- createDate.getTime())
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

    @Override
    public String toString() {
        var dots =  "...";
        return String.format("\nUsername: %s\nBalance: %s\nPaid date: %s\nInterest: %s",
                StringUtility.HandleEmptyString(getID(), dots),
                StringUtility.HandleEmptyString(StringUtility.toVND(getBalance()), dots),
                StringUtility.HandleEmptyString(DateTimeUtility.toDefaultFormat(getPaidTime()), dots),
                StringUtility.HandleEmptyString(StringUtility.toVND(calculateInterest(getPaidTime().compareTo(new Date()) <= 0)), dots));
    }
}
