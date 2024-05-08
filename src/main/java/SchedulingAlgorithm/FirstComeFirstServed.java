/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SchedulingAlgorithm;

import com.mycompany.schedulingprocesssimulater.ProcessControlBlock;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author safa
 */
public class FirstComeFirstServed implements SchedulingAlgorithm {
    private List<GanttEntry> ganttEntries;
    private int lastEndTime;  
    private int totalWaitingTime;
    private int totalTurnaroundTime;
    private int totalBurstTime;
    private int contextSwitchTime;

    public FirstComeFirstServed(int contextSwitchTime) {
        ganttEntries = new ArrayList<>();
        lastEndTime = 0;  
        totalWaitingTime = 0;
        totalTurnaroundTime = 0;
        totalBurstTime = 0;
        this.contextSwitchTime=contextSwitchTime;
    }

    public void schedule(List<ProcessControlBlock> processes) {
        processes.sort((p1, p2) -> Integer.compare(p1.getArrivalTime(), p2.getArrivalTime())); 

        System.out.println("********************************************************************");
        System.out.println("FCFS Scheduling with Context Switch Time " + contextSwitchTime + ":\n");

        for (ProcessControlBlock process : processes) {
            int waitingTime = Math.max(0, lastEndTime - process.getArrivalTime());
            totalWaitingTime += waitingTime;

            if (process.getArrivalTime() > lastEndTime) {
                ganttEntries.add(new GanttEntry(-1, lastEndTime, process.getArrivalTime() - lastEndTime, "Idle"));
                lastEndTime = process.getArrivalTime();
            }

            ganttEntries.add(new GanttEntry(process.getProcessId(), lastEndTime, process.getBurstTime(), "Process"));
            lastEndTime += process.getBurstTime();
   
            int finishTime = lastEndTime;
            int turnaroundTime = finishTime - process.getArrivalTime();
            totalTurnaroundTime += turnaroundTime;
            totalBurstTime += process.getBurstTime();

            System.out.println("Process" + process.getProcessId() + ": Finish = " + finishTime + ", waiting = " + waitingTime + ", TurnaroundTime = " + turnaroundTime);

            if (contextSwitchTime > 0) {
                ganttEntries.add(new GanttEntry(-1, lastEndTime, contextSwitchTime, "ContextSwitch"));
                lastEndTime += contextSwitchTime;  
            }
        }

        if (!ganttEntries.isEmpty() && "ContextSwitch".equals(ganttEntries.get(ganttEntries.size() - 1).getEntryType())) {
            ganttEntries.remove(ganttEntries.size() - 1);
            lastEndTime -= contextSwitchTime;
        }

        double averageWaitingTime = (double) totalWaitingTime / processes.size();
        double averageTurnaroundTime = (double) totalTurnaroundTime / processes.size();
        double cpuUtilization = (double) totalBurstTime / (lastEndTime ) * 100;

        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
        System.out.println("CPU Utilization: " + cpuUtilization + "%\n");
    }

    public void displayResults() {
        GanttChart.drawGanttChart(ganttEntries);
    }
}
