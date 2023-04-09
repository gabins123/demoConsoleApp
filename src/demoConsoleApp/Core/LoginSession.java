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
        return currentAccount.getUsername();
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
