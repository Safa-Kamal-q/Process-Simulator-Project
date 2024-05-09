/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schedulingprocesssimulater.InputHanfler;

import com.mycompany.schedulingprocesssimulater.ProcessControlBlock;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author safa
 */
public class InputFileReader {

    public static int contextSwitchTime;
    public static int quantum;

    public static List<ProcessControlBlock> readProcessesFromFile(String filename) {
        List<ProcessControlBlock> processList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            int numberOfProcesses = Integer.parseInt(reader.readLine());
            int index = 1;

            String line;
            while (index <= numberOfProcesses) {
                line = reader.readLine();
                String[] tokens = line.split(",");
                int processId = Integer.parseInt(tokens[0].trim());
                int arrivalTime = Integer.parseInt(tokens[1].trim());
                int burstTime = Integer.parseInt(tokens[2].trim());
                processList.add(new ProcessControlBlock(processId, arrivalTime, burstTime));
                index++;
            }

            contextSwitchTime = Integer.parseInt(reader.readLine());
            quantum = Integer.parseInt(reader.readLine());

        } catch (IOException e) {
            System.out.println(e);
        }
        return processList;
    }
}
