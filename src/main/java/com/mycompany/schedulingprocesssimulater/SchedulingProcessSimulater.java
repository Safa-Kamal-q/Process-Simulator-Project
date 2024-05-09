/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.schedulingprocesssimulater;

import SchedulingAlgorithm.FirstComeFirstServed;
import SchedulingAlgorithm.SchedulingAlgorithm;
import SchedulingAlgorithm.ShortestRemainingTime;
import com.mycompany.schedulingprocesssimulater.InputHanfler.InputFileReader;
import java.util.List;

/**
 *
 * @author safa
 */
public class SchedulingProcessSimulater {

    public static void main(String[] args) {

        List<ProcessControlBlock> processList = InputFileReader.readProcessesFromFile("C:\\Users\\safa\\Desktop\\input.txt");

        SchedulingAlgorithm[] algorithms = new SchedulingAlgorithm[2];

        FirstComeFirstServed fcfsAlgorithm = new FirstComeFirstServed(1);
        algorithms[0] = fcfsAlgorithm;

        ShortestRemainingTime srtAlgorithm = new ShortestRemainingTime(1);
        algorithms[1] = srtAlgorithm;

        ProcessScheduler scheduler = new ProcessScheduler(algorithms);

        scheduler.runScheduler(processList);
    }
}
