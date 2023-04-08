package demoConsoleApp.Core.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer {
    private String id;
    private String fullName;
    private String sex;
    private Date birthDate;
    private String homeTown;
    private long ciC;

    public String getId() {return id;}
    public String getFullName() {return fullName;}
    public String getSex() {return sex;}
    public Date getBirthDate() {return birthDate;}
    public String getHomeTown() {return homeTown;}
    public long getCiC() {return ciC;}
    public void generateId(long order) {
        String pattern = "ddMMyyyy";
        var date = new SimpleDateFormat(pattern);

        //TODO: Them so thu tu
        id = date.format(new Date()) + order;
    }
    public void setFullName(String value) { fullName = value;}
    public void setSex(String value) { sex = value;}
    public void setBirthDate(Date value) { birthDate = value;}
    public void setHomeTown(String value) { homeTown = value;}
    public void setCiC(long value) { ciC = value;}

    //SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

}
