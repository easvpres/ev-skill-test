package org.eas.dto;

/**
 * @author eas
 */
public class DaysToBirthdayLeft {
    private String personnName;
    private int daysLeft;

    public DaysToBirthdayLeft() {
    }

    public DaysToBirthdayLeft(String personnName, int daysLeft) {
        this.personnName = personnName;
        this.daysLeft = daysLeft;
    }

    public String getPersonnName() {
        return personnName;
    }

    public void setPersonnName(String personnName) {
        this.personnName = personnName;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }
}
