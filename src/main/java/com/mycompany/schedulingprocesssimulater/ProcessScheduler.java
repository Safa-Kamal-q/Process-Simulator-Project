/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schedulingprocesssimulater;

import SchedulingAlgorithm.SchedulingAlgorithm;
import java.util.List;

/**
 *
 * @author safa
 */
public class ProcessScheduler {

    private SchedulingAlgorithm[] schedulingAlgorithms;

    public ProcessScheduler(SchedulingAlgorithm[] schedulingAlgorithms) {
        this.schedulingAlgorithms = schedulingAlgorithms;
    }

    public void runScheduler(List<ProcessControlBlock> processList) {
        
        for (SchedulingAlgorithm schedulingAlgorithm : schedulingAlgorithms) {
            schedulingAlgorithm.schedule(processList);
            schedulingAlgorithm.drawChart();
        }
    }
}
