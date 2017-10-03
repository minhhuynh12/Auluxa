package net.dirox.auluxa.data.sample;

import java.io.Serializable;

/**
 * Created by DoanThiPhuongHuyen on 26/07/2017.
 */

public class AssignedLockPinItem implements Serializable{
    private String Name ="";
    private String Description="1234";
    private int Image;
    private String Duration ="";
    private  String Status ="";
    private int type = 0;
    private String TimeZone ="";
    private String StartDate ="";
    private String StartTime ="";
    private String EndDate ="";
    private String EndTime ="";

    public AssignedLockPinItem()
    {

    }
    public AssignedLockPinItem(String name, String description, int image, String duration, String status) {
        Name = name;
        Description = description;
        Image = image;
        Duration = duration;
        Status = status;
    }

    public AssignedLockPinItem(String name, String description, int image) {
        Name = name;
        Description = description;
        Image = image;
    }

    public AssignedLockPinItem(String name, String description, int image, String duration, String status, int type, String startDate, String startTime, String endDate, String endTime) {
        Name = name;
        Description = description;
        Image = image;
        Duration = duration;
        Status = status;
        this.type = type;
        StartDate = startDate;
        StartTime = startTime;
        EndDate = endDate;
        EndTime = endTime;
    }
    public AssignedLockPinItem(String name, String description, int image, String duration, String status, int type) {
        Name = name;
        Description = description;
        Image = image;
        Duration = duration;
        Status = status;
        this.type = type;
    }

    public AssignedLockPinItem(String name, String description, int image, String duration, String status, int type, String timeZone, String startDate, String startTime, String endDate, String endTime) {
        Name = name;
        Description = description;
        Image = image;
        Duration = duration;
        Status = status;
        this.type = type;
        TimeZone = timeZone;
        StartDate = startDate;
        StartTime = startTime;
        EndDate = endDate;
        EndTime = endTime;
    }

    public String getTimeZone() {
        return TimeZone;
    }

    public void setTimeZone(String timeZone) {
        TimeZone = timeZone;
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

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }
}
