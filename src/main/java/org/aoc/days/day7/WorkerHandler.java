package org.aoc.days.day7;

import org.aoc.days.day3.Pair;

import java.util.*;

public class WorkerHandler {
    private List<Worker> workers;
    private int globalTime = -1;
    public WorkerHandler(int numberOfWorkers) {
        workers = new ArrayList<>();
        for(int i = 0; i < numberOfWorkers; i++){
            workers.add(new Worker(i));
        }
    }
    private Boolean anyWorkerBusy(int globalTime){
        for(Worker w : workers){
            if(w.isBusy(globalTime)){
                return true;
            }
        }
        return false;
    }

    private Character getNextJob(ArrayList<Character> jobList, ArrayList<Character> jobsComplete,
                           HashMap<Character, ArrayList<Character>> requirementsMap){
        for(Character c : jobList){
            if(!requirementsMap.containsKey(c) || jobsComplete.containsAll(requirementsMap.get(c))){ // all requirements met;
                Character found = c;
                return c;
            }
        }
        return 0;
    }
    public int run(String jobOrderString, HashMap<Character, ArrayList<Character>> requirementsMap){
        ArrayList<Character> jobList = new ArrayList<>();
        ArrayList<Character> jobsComplete = new ArrayList<>();
        for(char c : jobOrderString.toCharArray()){
            jobList.add(c);
        }
        while(!jobList.isEmpty() || anyWorkerBusy(globalTime)){
            globalTime++;

            for(Worker w : workers){
                w.tick(globalTime, jobsComplete);
                if(!w.isBusy(globalTime)){
                    Character job = getNextJob(jobList, jobsComplete, requirementsMap);
                    if(job != 0){ //Compatible job found to assign
                        w.startJob(job, globalTime);
                        jobList.remove(job);
                    }
                }
                w.tick(globalTime, jobsComplete);
            }
            // print state
//            System.out.print(globalTime + "\t\t");
//            for(Worker w : workers){
//                System.out.print(w.getCurrentTask() + "\t\t");
//            }
//            System.out.println(jobsComplete.stream().map(a -> a.toString()).reduce("", (a, b) -> (a + b)));


        }

        return globalTime;
    }
}
