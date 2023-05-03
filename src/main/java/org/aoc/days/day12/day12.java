package org.aoc.days.day12;

import org.aoc.filehandling.FileLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class day12 {

    public static int part1(ProblemInput pi){
        PlantsState ps = new PlantsState(pi.initialState);
        List<PlantPattern> patterns = pi.patterns;
        System.out.println("0 " + ps.toString());
        for(int generation = 0; generation < 20; generation++){
            PlantsState next = ps.applyRulesGenerateNewState(patterns);
            System.out.println((generation+1) + " " + next.toString());
            ps = new PlantsState(next.getState());
        }
        // sum plants
        int sum = ps.getState().entrySet().stream().filter(s -> s.getValue() == '#').map(Map.Entry::getKey).reduce(0, Integer::sum);
        return sum;
    }
    public static int part2(List<String> input){
        return 0;
    }
    public static ProblemInput buildInput(List<String> input){
        Pattern logicPattern = Pattern.compile("(\\.|#)+");
        Matcher initStateMatcher = logicPattern.matcher(input.get(0));
        initStateMatcher.find();
        String initState = initStateMatcher.group(0);
        List<PlantPattern> pPatterns = new ArrayList<>();
        for(int index = 2; index < input.size(); index++){
            String inputLine = input.get(index);
            if(inputLine.length() > 0){
                Matcher logicMatcher = logicPattern.matcher(inputLine);
                logicMatcher.find();
                String pat = logicMatcher.group(0);
                logicMatcher.find();
                char result = logicMatcher.group(0).charAt(0);
                pPatterns.add(new PlantPattern(pat, result));
            }
        }

        return new ProblemInput(initState, pPatterns);
    }
    public static void run(String inputFileName){
        List<String> input = FileLoader.loader(inputFileName);
        ProblemInput pi = buildInput(input);
        System.out.println("Part 1: " + part1(pi));
        System.out.println("Part 2: " + part2(input));
    }
    public record ProblemInput(String initialState, List<PlantPattern> patterns){};
}
