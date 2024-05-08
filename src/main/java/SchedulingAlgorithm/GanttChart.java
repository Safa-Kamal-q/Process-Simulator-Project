/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SchedulingAlgorithm;

import java.util.List;

/**
 *
 * @author safa
 */
public class GanttChart {
         public static void drawGanttChart(List<GanttEntry> ganttChart) {
        System.out.println("Gantt Chart:");

        int currentTime = 0;
        System.out.printf("0 |==");
        for (GanttEntry entry : ganttChart) {
            if ("ContextSwitch".equals(entry.getEntryType())) {
                System.out.printf("S==| %d |==", entry.getStartTime() + entry.getExecutionTime());
            } else if ("Idle".equals(entry.getEntryType())) {
                System.out.printf("I==| %d |==", entry.getStartTime() + entry.getExecutionTime());
            } else {
                System.out.printf("P%d==| %d |==", entry.getProcessId(), entry.getStartTime() + entry.getExecutionTime());
            }
            currentTime = entry.getStartTime() + entry.getExecutionTime();
        }
        System.out.println();
    }
}
