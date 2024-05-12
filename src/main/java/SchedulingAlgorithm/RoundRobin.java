/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SchedulingAlgorithm;

import GanttChart.GanttChart;
import GanttChart.GanttEntry;
import com.mycompany.schedulingprocesssimulater.ProcessControlBlock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 *
 * @author safa
 */
public class RoundRobin implements SchedulingAlgorithm {

    private Queue<ProcessControlBlock> processQueue;
    private List<GanttEntry> ganttEntries;
    private int quantum;
    private int currentTime;
    private int contextSwitchTime;

    public RoundRobin(int quantum, int contextSwitchTime) {
        this.quantum = quantum;
        this.contextSwitchTime = contextSwitchTime;
        this.processQueue = new LinkedList<>();
        this.ganttEntries = new ArrayList<>();
        this.currentTime = 0;
    }

    @Override
    public void schedule(List<ProcessControlBlock> processes) {
        processes.sort((p1, p2) -> Integer.compare(p1.getArrivalTime(), p2.getArrivalTime()));

        System.out.println("********************************************************************");
        System.out.println("RR Scheduling with Context Switch Time " + contextSwitchTime + ":\n");

        processQueue.addAll(processes);
        ProcessControlBlock lastProcess = null;

        while (!processQueue.isEmpty() || processes.stream().anyMatch(p -> p.getFinishTime() == 0)) {
            ProcessControlBlock currentProcess = processQueue.poll();

            boolean isTheLastIdle = false;
            if (currentProcess.getArrivalTime() > currentTime) {
                int nextArrivalTime = currentProcess.getArrivalTime();
                ganttEntries.add(new GanttEntry(-1, currentTime, nextArrivalTime - currentTime, "Idle"));
                currentTime = nextArrivalTime;
                isTheLastIdle = true;

            }

            if (lastProcess != null && lastProcess != currentProcess && !isTheLastIdle) {
                ganttEntries.add(new GanttEntry(-1, currentTime, contextSwitchTime, "ContextSwitch"));
                currentTime += contextSwitchTime;
            }

            int startTime = currentTime;
            int timeSlice = Math.min(currentProcess.getRemainingBurstTime(), quantum);
            currentProcess.setRemainingBurstTime(currentProcess.getRemainingBurstTime() - timeSlice);
            currentTime += timeSlice;

            ganttEntries.add(new GanttEntry(currentProcess.getProcessId(), startTime, timeSlice, "Process"));

            if (currentProcess.getRemainingBurstTime() > 0) {
                processQueue.add(currentProcess);
            } else {
                currentProcess.setFinishTime(currentTime);
            }

            lastProcess = currentProcess;
        }

        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int totalBurstTime = 0;

        for (ProcessControlBlock process : processes) {
            int turnaroundTime = process.getFinishTime() - process.getArrivalTime();
            int waitingTime = turnaroundTime - process.getBurstTime();
            process.setTurnaroundTime(turnaroundTime);
            process.setWaitingTime(waitingTime);

            totalWaitingTime += waitingTime;
            totalTurnaroundTime += turnaroundTime;
            totalBurstTime += process.getBurstTime();

            System.out.println("Process" + process.getProcessId() + ": Finish = " + process.getFinishTime()
                    + ", Waiting = " + waitingTime + ", Turnaround = " + turnaroundTime);
        }

        System.out.println("\nAverage Waiting Time: " + ((double) totalWaitingTime / processes.size()));
        System.out.println("Average Turnaround Time: " + ((double) totalTurnaroundTime / processes.size()));
        System.out.println("CPU Utilization: " + (double) totalBurstTime / currentTime * 100 + "%\n");
    }

    public void drawChart() {
        GanttChart.drawGanttChart(ganttEntries);
        System.out.println("********************************************************************");

    }
}
