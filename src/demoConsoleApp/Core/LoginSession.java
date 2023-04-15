package demoConsoleApp.Core;

import demoConsoleApp.Core.Data.Account;

public class LoginSession {
    private static LoginSession instance;
    public static LoginSession getInstance(){
        if(instance == null){
            instance = new LoginSession();
        }
        return  instance;
    }
    private Account currentAccount;
    public void SignIn(Account account){
        currentAccount = account;
    }
    public void SignOut(){
        currentAccount = null;
    }
    public String getCurrentAccountID(){
        if(currentAccount == null)
            return null;
        return currentAccount.getUsername();
    }
    public String getNextSavingAccountID(){
        return currentAccount.getUsername() + currentAccount.getSavingAccounts().size();
    }
    public Account getCurrentAccount(){
        try {
            return (Account) currentAccount.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
