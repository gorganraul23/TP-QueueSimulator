package Controller;

import Model.Scheduler;
import Model.Server;
import Model.Strategy.SelectionPolicy;
import Model.Task;
import View.View;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimulationManager implements Runnable {

    public int timeLimit;

    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;

    private Scheduler scheduler;
    private View view;
    private List<Task> generatedTasks;

    public SimulationManager(int n, int q, int simTime, int minArrival, int maxArrival, int minProc, int maxProc, View view) {
        this.view = view;

        scheduler = new Scheduler(q);
        scheduler.changeStrategy(selectionPolicy);

        generatedTasks = new ArrayList<>();
        generateRandomTaks(n, minProc, maxProc, minArrival, maxArrival);

        timeLimit = simTime;
    }

    public void generateRandomTaks(int n, int minProc, int maxProc, int minArrival, int maxArrival) {
        int processingTime = 0, arrivalTime = 0;
        Task task;
        for (int i = 0; i < n; i++) {
            processingTime = (int) (Math.random() * (maxProc - minProc + 1) + minProc);
            arrivalTime = (int) (Math.random() * (maxArrival - minArrival + 1) + minArrival);
            task = new Task(i + 1, arrivalTime, processingTime);
            generatedTasks.add(task);
        }
        Collections.sort(generatedTasks);
    }

    public int getPeakTime(){
        int size = 0;
        for(Server s : scheduler.getServers()){
            size += s.getTasks().size();
        }
        return size;
    }

    public float waitingTimePerSecond() {
        int time = 0, clients = 0;
        for (Server s : scheduler.getServers()) {
            clients += s.getTasks().size();
            time += s.getWaitingPeriod().intValue();
        }
        if(clients == 0)
            return 0;
        return (float)time/clients;
    }

    public int getRemainingClients(){
        int numberOfClients = 0;
        for (Server server : scheduler.getServers()){
            numberOfClients += server.getTasks().size();
        }
        return numberOfClients;
    }
    public int getRemainingTime(){
        int time = 0;
        for (Server server : scheduler.getServers()){
            time += server.getWaitingPeriod().intValue();
        }
        return time;
    }

    public void realTimeSim(int currentTime) {
        String realTime = "";
        realTime += "Time " + currentTime + "\n";       //set real time simulation
        for (Server s : scheduler.getServers()) {
            realTime += s.toString();
        }
        view.setTextArea(realTime);
    }

    public boolean printToFile(int currentTime, BufferedWriter bw){
        boolean hasMore = false, closed = true;
        try {
            bw.write("\nTime " + currentTime + "\n");
            bw.write("Waiting clients: ");
            for (Task task : generatedTasks){
                if(task.getArrivalTime() > currentTime) {
                    hasMore = true;
                    bw.write(task.toString() + " ");
                }
            }
            for (Server server : scheduler.getServers()){
                if(server.getTasks().size() != 0)
                    closed = false;
            }
            bw.write("\n");
            for (Server server : scheduler.getServers())
                bw.write(server.toString());
        } catch (IOException e) {
            e.printStackTrace(); }
        if(!hasMore && closed)
            return false;
        return true;
    }

    @Override
    public void run() {
        int currentTime = 0, peakTime = 0, actualSize = 0, totalServingTime = 0, totalClients = 0;
        float waitingTime = 0;
        view.setStartBtnRed();
        File file = new File("log.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file.getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace(); }
        BufferedWriter bw = new BufferedWriter(fw);

        while (currentTime <= timeLimit) {
            for (Task task : generatedTasks) {
                if (task.getArrivalTime() == currentTime) {     //task dispatch
                    this.scheduler.dispatchTask(task);
                    totalClients++; totalServingTime += task.getProcessingTime();     //for servingTime
                }
            }
            if(getPeakTime() > actualSize){ peakTime = currentTime; actualSize = getPeakTime();}
            waitingTime += waitingTimePerSecond();      //waitingTime
            if(!printToFile(currentTime, bw))
                currentTime = timeLimit;                //daca nu mai sunt in waitingQueue, ies
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace(); }
            realTimeSim(currentTime);
            currentTime++;
        }
        view.setStartBtnGreen();
        try {
            bw.write("\nPeak time: " + peakTime + "\n");
            bw.write("Average waiting time: " + (waitingTime /timeLimit) + "\n");
            bw.write("Average serving time: " + ((float)(totalServingTime-getRemainingTime())/(totalClients-getRemainingClients())));
        } catch (IOException e) {
            e.printStackTrace(); }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace(); }
    }

}