package net.dirox.auluxa.data.sample;

import java.io.Serializable;

/**
 * Created by MyPC on 31/07/2017.
 */

public class BluetoothItems implements Serializable {
    private int image;
    private String Name = "";
    private String Description = "1234";
    private String Duration = "";
    private String Status = "";
    private int type = 0;
    private String TimeZone = "";
    private String StartDate = "";
    private String StartTime = "";
    private String EndDate = "";
    private String EndTime = "";
    private String Pin = "187465821";

    public String getPin() {
        return Pin;
    }

    public void setPin(String pin) {
        Pin = pin;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTimeZone() {
        return TimeZone;
    }

    public void setTimeZone(String timeZone) {
        TimeZone = timeZone;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public BluetoothItems(String name, String description, int image, String duration, String status, int type, String timeZone, String startDate, String startTime, String endDate, String endTime ) {
        Name = name;
        Description = description;
        this.image = image;
        Duration = duration;
        Status = status;
        this.type = type;
        TimeZone = timeZone;
        StartDate = startDate;
        StartTime = startTime;
        EndDate = endDate;
        EndTime = endTime;

    }

    public BluetoothItems(int image, String name, String description, int type) {
        this.image = image;
        Name = name;
        Description = description;
        this.type = type;
    }
}
