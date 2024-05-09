/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SchedulingAlgorithm;

import GanttChart.GanttEntry;
import GanttChart.GanttChart;
import com.mycompany.schedulingprocesssimulater.ProcessControlBlock;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author safa
 */
public class ShortestRemainingTime implements SchedulingAlgorithm {

    private List<GanttEntry> ganttEntries;
    private int currentTime;
    private int totalWaitingTime;
    private int totalTurnaroundTime;
    private int totalBurstTime;
    private int contextSwitchTime;
    private int endTime;

    public ShortestRemainingTime(int contextSwitchTime) {
        this.contextSwitchTime = contextSwitchTime;
        this.ganttEntries = new ArrayList<>();
        this.currentTime = 0;
        this.totalWaitingTime = 0;
        this.totalTurnaroundTime = 0;
        this.totalBurstTime = 0;
        this.endTime = 0;
    }

    public void schedule(List<ProcessControlBlock> processes) {
        System.out.println("********************************************************************");
        System.out.println("SRT Scheduling with Context Switch Time " + contextSwitchTime + ":\n");

        boolean[] completed = new boolean[processes.size()];
        int index = 0;
        int prevSelected = -1;
        int burstStartTime = currentTime;

        while (index < processes.size()) {
            int shortestRemainingTime = Integer.MAX_VALUE;
            int selectedProcess = -1;

            for (int i = 0; i < processes.size(); i++) {
                ProcessControlBlock process = processes.get(i);
                if (!completed[i] && process.getArrivalTime() <= currentTime && process.getRemainingBurstTime() < shortestRemainingTime) {
                    shortestRemainingTime = process.getRemainingBurstTime();
                    selectedProcess = i;
                }
            }

            if (selectedProcess == -1) {
                int nextArrivalTime = Integer.MAX_VALUE;
                for (ProcessControlBlock p : processes) {
                    if (!completed[processes.indexOf(p)] && p.getArrivalTime() > currentTime) {
                        nextArrivalTime = Math.min(nextArrivalTime, p.getArrivalTime());
                    }
                }
                if (nextArrivalTime > currentTime) {
                    ganttEntries.add(new GanttEntry(-1, currentTime, nextArrivalTime - currentTime, "Idle"));
                    currentTime = nextArrivalTime;
                    prevSelected = -1;
                }

            } else {

                ProcessControlBlock currentProcess = processes.get(selectedProcess);
                int remainingTime = currentProcess.getRemainingBurstTime() - 1;
                currentProcess.setRemainingBurstTime(remainingTime);

                if (prevSelected != selectedProcess && prevSelected != -1) {
                    if (processes.get(prevSelected).getRemainingBurstTime() != 0) {
                        ProcessControlBlock prevProcess = processes.get(prevSelected);

                        ganttEntries.add(new GanttEntry(prevProcess.getProcessId(), burstStartTime - 1, prevProcess.getBurstTime() - prevProcess.getRemainingBurstTime(), "Process"));
                    }
                    ganttEntries.add(new GanttEntry(-1, currentTime, contextSwitchTime, "ContextSwitch"));
                    currentTime += contextSwitchTime;
                    burstStartTime = currentTime;
                }

                currentTime++;

                if (currentProcess.getRemainingBurstTime() == 0) {
                    ganttEntries.add(new GanttEntry(currentProcess.getProcessId(), burstStartTime, currentTime - burstStartTime, "Process"));
                    currentProcess.setFinishTime(currentTime);
                    int turnaroundTime = currentTime - currentProcess.getArrivalTime();
                    currentProcess.setTurnaroundTime(turnaroundTime);
                    int waitingTime = turnaroundTime - currentProcess.getBurstTime();
                    currentProcess.setWaitingTime(waitingTime);
                    completed[selectedProcess] = true;
                    index++;
                }
                prevSelected = selectedProcess;
                burstStartTime = currentTime;
            }
        }

        for (ProcessControlBlock process : processes) {
            totalBurstTime += process.getBurstTime();
            totalWaitingTime += process.getWaitingTime();
            totalTurnaroundTime += process.getTurnaroundTime();
            endTime = Math.max(endTime, process.getFinishTime());
            System.out.println("Process" + process.getProcessId() + ": Finish = " + process.getFinishTime() + ", waiting = " + process.getWaitingTime() + ", TurnaroundTime = " + process.getTurnaroundTime());

            process.setWaitingTime(0);
            process.setFinishTime(0);
            process.setTurnaroundTime(0);
            process.setRemainingBurstTime(process.getBurstTime());
        }

        double averageWaitingTime = (double) totalWaitingTime / processes.size();
        double averageTurnaroundTime = (double) totalTurnaroundTime / processes.size();
        double cpuUtilization = (double) totalBurstTime / endTime * 100;

        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
        System.out.println("CPU Utilization: " + cpuUtilization + "%\n");
    }

    public void drawChart() {
        GanttChart.drawGanttChart(ganttEntries);
        System.out.println("********************************************************************");
    }
}
