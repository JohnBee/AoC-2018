package org.aoc.days.day7;

import java.util.ArrayList;

public class Worker {
    private int id;
    private Character currentTask = null;
    private int busyUntil = 0;

    public Worker(int id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public int getBusyUntil() {
        return busyUntil;
    }

    public Character getCurrentTask() {
        return currentTask;
    }
    public boolean isBusy(int globalTime) {
        return globalTime < busyUntil || currentTask != null;
    }
    public void tick(int globalTime, ArrayList<Character> jobsComplete){
        if(globalTime >= this.busyUntil){
            if(this.currentTask != null){
                jobsComplete.add(this.currentTask);
                this.currentTask = null;
            }

        }
    }

    private static int taskIdtoTime(char c){
        return c - 'A' + 1 + 60;
    }

    public void startJob(char c, int globalTime){
        this.busyUntil = taskIdtoTime(c) + globalTime;
        this.currentTask = c;
    }
}
