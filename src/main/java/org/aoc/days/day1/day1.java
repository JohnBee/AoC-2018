package org.aoc.days.day1;

import org.aoc.filehandling.FileLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class day1 {
    public static Integer part1(ArrayList<String> input){
        int currentFreq = 0;
        for(String s : input){
            int fChange = Integer.parseInt(s);
            currentFreq += fChange;
        }
        return currentFreq;
    }
    public static Integer part2(ArrayList<String> input){
        int currentFreq = 0;
        ArrayList<Integer> checkList = new ArrayList<>();
        checkList.add(currentFreq);
        while(true){
            for(String s : input){
                int fChange = Integer.parseInt(s);
                currentFreq += fChange;
                if(checkList.contains(currentFreq)) {
                    return currentFreq;
                }
                checkList.add(currentFreq);
            }
        }
    }
    public static void run(String inputFileName) {
        ArrayList<String> input = FileLoader.loader(inputFileName);
        System.out.println("Part 1 " + part1(input));
        System.out.println("Part 2 " + part2(input));
    }
}
