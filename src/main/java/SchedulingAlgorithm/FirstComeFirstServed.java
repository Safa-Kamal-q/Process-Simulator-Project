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

    private int contextSwitchTime;

    public FirstComeFirstServed(int contextSwitchTime) {
        this.contextSwitchTime = contextSwitchTime;
    }

    @Override
    public void schedule(List<ProcessControlBlock> processList) {

        if (processList.isEmpty()) {
            System.out.println("No processes to schedule.");
            return;
        }

        List<GanttEntry> ganttChart = new ArrayList<>();
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int totalBurstTime = 0;
        int totalCpuIdel = 0;

        int index = 0;
        while (index < processList.size()) {
            ProcessControlBlock currentProcess = processList.get(index);

            if (currentTime < currentProcess.getArrivalTime()) {
                totalCpuIdel += currentProcess.getArrivalTime() - currentTime;
                currentTime = currentProcess.getArrivalTime();
            }

            ganttChart.add(new GanttEntry(currentProcess.getProcessId(), currentTime, currentProcess.getBurstTime()));

            currentProcess.setWaitingTime(currentTime - currentProcess.getArrivalTime());
            totalWaitingTime += currentProcess.getWaitingTime();

            int executionTime = currentProcess.getBurstTime();
            totalBurstTime += executionTime;
            currentTime += executionTime;

            int turnaroundTime = currentTime - currentProcess.getArrivalTime();
            currentProcess.setTurnaroundTime(turnaroundTime);
            totalTurnaroundTime += turnaroundTime;

            currentTime += contextSwitchTime;

            currentProcess.setFinishTime(currentTime - contextSwitchTime);
            index++;
        }

        System.out.println("********************************************************************");
        System.out.println("FCFS Scheduling with Context Switch Time " + contextSwitchTime + ":\n");

        for (ProcessControlBlock process : processList) {

            System.out.println("Process" + process.getProcessId() + ": Finish = " + process.getFinishTime() + ", waiting = " + process.getWaitingTime() + ", TurnaroundTime = " + process.getTurnaroundTime());
        }

        System.out.println("\nAverage Waiting Time: " + ((double) totalWaitingTime / ganttChart.size()));
        System.out.println("Average Turnaround Time: " + ((double) totalTurnaroundTime / ganttChart.size()));

        double cpuUtilization = (double) totalBurstTime / (currentTime - 1) * 100;
        System.out.println("CPU Utilization: " + cpuUtilization + "%\n");

        GanttChart.drawGanttChart(ganttChart, contextSwitchTime);
        
        System.out.println("********************************************************************"); 
    }
}
