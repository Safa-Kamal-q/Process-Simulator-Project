/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package SchedulingAlgorithm;

import com.mycompany.schedulingprocesssimulater.ProcessControlBlock;
import java.util.List;

/**
 *
 * @author safa
 */
public interface SchedulingAlgorithm {
      void schedule(List<ProcessControlBlock> processList);
      void displayResults();
}
