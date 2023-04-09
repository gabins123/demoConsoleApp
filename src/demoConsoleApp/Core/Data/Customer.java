package demoConsoleApp.Core.Data;

import demoConsoleApp.Utility.DateTimeUtility;
import demoConsoleApp.Utility.StringUtility;

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

    @Override
    public String toString() {
        var dots =  "...";
        return String.format("Họ và tên: %s\nGiới tính : %s\nNgày sinh: %s\nQuê quán: %s\nCCCD: %s",
                StringUtility.HandleEmptyString(fullName, dots),
                StringUtility.HandleEmptyString(sex, dots),
                StringUtility.HandleEmptyString(DateTimeUtility.toDefaultFormat(birthDate), dots),
                StringUtility.HandleEmptyString(homeTown,dots),
                (ciC== 0? dots : ciC));
    }

}
