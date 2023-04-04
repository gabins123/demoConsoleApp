import java.util.Date;

public class Customer {
    private long id;
    private String fullName;
    private String sex;
    private Date date;
    private String homeTown;
    private long ciC;

    public long getId() {return id;}
    public String getFullName() {return fullName;}
    public String getIsMale() {return sex;}
    public Date getDate() {return date;}
    public String getHomeTown() {return homeTown;}
    public long getCiC() {return ciC;}
    public void setId(long value) { id = value;}
    public void setFullName(String value) { fullName = value;}
    public void setIsMale(String value) { sex = value;}
    public void setDate(Date value) { date = value;}
    public void setHomeTown(String value) { homeTown = value;}
    public void setCiC(long value) { ciC = value;}

    //SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
}
