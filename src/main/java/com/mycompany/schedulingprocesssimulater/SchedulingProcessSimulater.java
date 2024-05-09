/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.schedulingprocesssimulater;

import SchedulingAlgorithm.FirstComeFirstServed;
import SchedulingAlgorithm.RoundRobin;
import SchedulingAlgorithm.SchedulingAlgorithm;
import SchedulingAlgorithm.ShortestRemainingTime;
import com.mycompany.schedulingprocesssimulater.InputHanfler.InputFileReader;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author safa
 */
public class SchedulingProcessSimulater {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the absolute input file path");
        String filePath = scanner.nextLine();

        List<ProcessControlBlock> processList = InputFileReader.readProcessesFromFile(filePath);

        SchedulingAlgorithm[] algorithms = new SchedulingAlgorithm[3];

        FirstComeFirstServed fcfsAlgorithm = new FirstComeFirstServed(1);
        algorithms[0] = fcfsAlgorithm;

        ShortestRemainingTime srtAlgorithm = new ShortestRemainingTime(1);
        algorithms[1] = srtAlgorithm;

        RoundRobin rrAlgorithm = new RoundRobin(2, 1);
        algorithms[2] = rrAlgorithm;

        ProcessScheduler scheduler = new ProcessScheduler(algorithms);

        scheduler.runScheduler(processList);
    }
}
