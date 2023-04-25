package org.aoc.days.day5;
import org.aoc.filehandling.FileLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day5 {

    public static int part1(String input){

        String reactedInput = input;
        ArrayList<String> patList = generatePolymerPatterns();
        // fully react
        reactedInput = reactString(reactedInput, patList);
        return reactedInput.length();
    }

    private static String reactString(String reactedInput, ArrayList<String> patList) {
        boolean didReact = true;
        while(didReact == true) {
            int iLength = reactedInput.length();
            for(String pat : patList){
                reactedInput = reactedInput.replaceAll(pat,"");
            }
            didReact = iLength != reactedInput.length();
        }
        return reactedInput;
    }

    private static ArrayList<String> generatePolymerPatterns() {
        ArrayList<String> patList = new ArrayList<>();
        for(char c = 'a'; c <= 'z'; c++){
            String part1 =  Character.toString(c);
            String part2 = Character.toString(Character.toUpperCase(c));
            patList.add(part1+part2);
            patList.add(part2+part1);
        }
        return patList;
    }

    public static int part2(String input){
        ArrayList<String> patList = new ArrayList<>();
        ArrayList<String> polymerPattern = generatePolymerPatterns();
        String processed = reactString(input, polymerPattern);
        for(char c = 'a'; c <= 'z'; c++){
            patList.add(Character.toString(c));
        }
        int smallest = Integer.MAX_VALUE;
        for(String s : patList){
            // remove all A & a, B & b, etc.
            String tempInput = processed.replaceAll(s,"");
            tempInput = tempInput.replaceAll(s.toUpperCase(),"");
            int tempSmall = part1(tempInput);
            if(tempSmall < smallest){
                smallest = tempSmall;
            }
        }
        return smallest;
    }

    public static void run(String inputFileName){
        ArrayList<String> input = FileLoader.loader(inputFileName);
        System.out.println("Part 1: " + part1(input.get(0)));
        System.out.println("Part 2: " + part2(input.get(0)));
    }
}
