package org.aoc.days.day10;

import org.aoc.days.day3.Pair;
import org.aoc.filehandling.FileLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.abs;

public class day10 {

    public static int part1(List<String> input){
        PointCloud pc = new PointCloud();
        for(String s : input){
            pc.addPoint(s);
        }
        long entropy = pc.entropy();
        long smallest = Long.MAX_VALUE;
        long smallestStep = 0;
        while(pc.entropy() <= entropy){
            entropy = pc.entropy();
            if(entropy < smallest){
                smallest = entropy;
                smallestStep = pc.getSteps();
            }
            pc.step();
        }
        // 1354233
        pc.reverseStep(); // we've gained entropy in the while loop, step back one to get the answer
        System.out.println(smallest);
        System.out.println(smallestStep);
        System.out.println(pc.pointCloudToString());

        return 0;
    }
    public static int part2(List<String> input){
        PointCloud pc = new PointCloud();
        for(String s : input){
            pc.addPoint(s);
        }
        long entropy = pc.entropy();
        long smallest = Long.MAX_VALUE;
        long smallestStep = 0;
        while(pc.entropy() <= entropy){
            entropy = pc.entropy();
            if(entropy < smallest){
                smallest = entropy;
                smallestStep = pc.getSteps();
            }
            pc.step();
        }
        pc.reverseStep();
        return pc.getSteps();
    }
    public static void run(String inputFileName){
        List<String> input = FileLoader.loader(inputFileName);
        System.out.println("Part 1: " + part1(input));
        System.out.println("Part 2: " + part2(input));
    }
}



