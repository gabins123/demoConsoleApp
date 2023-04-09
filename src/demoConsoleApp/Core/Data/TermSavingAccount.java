package demoConsoleApp.Core.Data;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import demoConsoleApp.Core.Data.SavingAccount;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

public class TermSavingAccount extends SavingAccount{
    private Date createDate;
    private byte accountType;

    public TermSavingAccount( Date createDate , byte accountType, String id, BigDecimal balance) {
        super(id, balance);
        this.createDate = createDate;
        this.accountType = accountType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public byte getAccountType() {
        return accountType;
    }

    public void setAccountType(byte accountType) {
        this.accountType = accountType;
    }
}
