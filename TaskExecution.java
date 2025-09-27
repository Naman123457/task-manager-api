package com.kaiburr.taskmanager.model;

import java.util.Date;

public class TaskExecution {
    private String id;
    private Date startTime;
    private Date endTime;
    private String output;

    public TaskExecution() {}

    public TaskExecution(Date startTime, Date endTime, String output) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.output = output;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public Date getStartTime() { return startTime; }
    public void setStartTime(Date startTime) { this.startTime = startTime; }
    
    public Date getEndTime() { return endTime; }
    public void setEndTime(Date endTime) { this.endTime = endTime; }
    
    public String getOutput() { return output; }
    public void setOutput(String output) { this.output = output; }
}