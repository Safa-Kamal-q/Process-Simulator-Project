/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.schedulingprocesssimulater;

import SchedulingAlgorithm.FirstComeFirstServed;
import com.mycompany.schedulingprocesssimulater.InputHanfler.InputFileReader;
import java.util.List;

/**
 *
 * @author safa
 */
public class SchedulingProcessSimulater {

    public static void main(String[] args) {
        
        List<ProcessControlBlock> processList = InputFileReader.readProcessesFromFile("C:\\Users\\safa\\Desktop\\input.txt");

        FirstComeFirstServed fcfsAlgorithm = new FirstComeFirstServed(1);
        ProcessScheduler scheduler = new ProcessScheduler(fcfsAlgorithm);

        scheduler.runScheduler(processList);
    }
}
