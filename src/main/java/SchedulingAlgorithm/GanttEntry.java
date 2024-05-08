/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SchedulingAlgorithm;

/**
 *
 * @author safa
 */
public class GanttEntry {

    private int processId;
    private int startTime;
    private int executionTime;
    private String entryType;  

    public GanttEntry(int processId, int startTime, int executionTime, String entryType) {
        this.processId = processId;
        this.startTime = startTime;
        this.executionTime = executionTime;
        this.entryType = entryType;
    }

    public int getProcessId() {
        return processId;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public String getEntryType() {
        return entryType;
    }
}
