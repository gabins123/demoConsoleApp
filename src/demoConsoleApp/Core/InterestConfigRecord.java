package demoConsoleApp.Core;

import java.math.BigDecimal;
import java.util.Calendar;

public class InterestConfigRecord {
    private int id;
    private int period;
    private float rate;
    private int calenderType;
    public InterestConfigRecord(int id,int period, int calenderType, float rate){
        this.id = id;
        this.rate = rate;
        this.period =period;
        this.calenderType = calenderType;
    }
    public float getRate() {
        return rate;
    }
    public int getCalendarType() {
        return calenderType;
    }
    public int getPeriod() {
        return period;
    }

    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%d. Ky han %d %s lai xuat %2.1f",id, period, InterestConfigRecord.getTermName(calenderType), rate * 100f) + "%";
    }
    private static String getTermName(int term){
        return switch (term){
            case Calendar.DATE -> "ngay";
            case Calendar.MONTH -> "thang";
            case Calendar.YEAR -> "nam";
            default ->  null;
        };
    }
}
