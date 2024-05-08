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
    private SchedulingAlgorithm schedulingAlgorithm;

    public ProcessScheduler(SchedulingAlgorithm schedulingAlgorithm) {
        this.schedulingAlgorithm = schedulingAlgorithm;
    }

    public void runScheduler(List<ProcessControlBlock> processList) {
        schedulingAlgorithm.schedule(processList);
    }
}
