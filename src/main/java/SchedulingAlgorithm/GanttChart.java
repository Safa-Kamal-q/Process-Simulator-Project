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
      public static void drawGanttChart(List<GanttEntry> ganttChart, int contextSwitchTime) {
        System.out.println("Gantt Chart:");
        System.out.print("|");

        for (GanttEntry entry : ganttChart) {
            System.out.printf(" P%d |", entry.getProcessId());
        }
        System.out.println();
        
        System.out.print("0");
        int endTime = 0;
        for (GanttEntry entry : ganttChart) {
            for (int i = 0; i < entry.getStartTime() - endTime; i++) {
                System.out.print(" ");
            }

            for (int i = 0; i < entry.getExecutionTime(); i++) {
                System.out.print("-");
            }
            endTime = entry.getStartTime() + entry.getExecutionTime();
            System.out.print(endTime);
        }
        System.out.println();
      }

}
