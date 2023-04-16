package demoConsoleApp.Core.Data;
import com.google.gson.annotations.JsonAdapter;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@JsonAdapter(AccountSerializer.class)
public class Account implements Cloneable{
    private String username;
    private String password;
    private SavingAccount defaultAccount;
    private List<TermSavingAccount> savingAccounts;
    public Account(String username, String password, SavingAccount defaultAccount, List<TermSavingAccount> savingAccounts){

        this.username = username;
        this.password = password;
        this.defaultAccount = defaultAccount;
        this.savingAccounts = savingAccounts;
    }
    public Account(String username, String balance){
        this.username = username;
        var rand  = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< 6;i++){
            sb.append((int)(rand.nextDouble() * 9));
        }
        password = sb.toString();
        defaultAccount = new SavingAccount(username, new BigDecimal(balance));
        savingAccounts = new ArrayList<>();
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

    public SavingAccount getDefaultAccount() {
        return defaultAccount;
    }

    public void setDefaultAccount(SavingAccount defaultAccount) {
        this.defaultAccount = defaultAccount;
    }

    public List<TermSavingAccount> getSavingAccounts() {
        return savingAccounts;
    }

    public void setSavingAccounts(List<TermSavingAccount> savingAccounts) {
        this.savingAccounts = savingAccounts;
    }
    public Object clone() throws CloneNotSupportedException
    {
        var rs = (Account)super.clone();
        rs.savingAccounts = rs.savingAccounts.stream().map(e-> new TermSavingAccount((Date) e.getCreateDate().clone(),
                (Date)e.getPaidDate().clone(),e.getAccountType(),
                new String(e.getID()), e.getBalance())).collect(Collectors.toList());
        rs.defaultAccount = new SavingAccount(rs.username, rs.defaultAccount.getBalance());
        return rs;
    }

    public void addSavingAccount(TermSavingAccount savingAccount) {
        savingAccounts.add(savingAccount);
    }
}

