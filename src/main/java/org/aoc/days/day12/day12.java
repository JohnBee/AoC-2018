package org.aoc.days.day12;

import org.aoc.filehandling.FileLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day12 {

    public static Long part1(ProblemInput pi) {
        PlantsState ps = new PlantsState(pi.initialState);
        List<PlantPattern> patterns = pi.patterns;
        for (int generation = 0; generation < 20; generation++) {
            PlantsState next = ps.applyRulesGenerateNewState(patterns);
            ps = new PlantsState(next.getState());
        }
        // sum plants
        return getSum(ps);
    }


    public static long part2(ProblemInput pi) {
        PlantsState ps = new PlantsState(pi.initialState);
        List<PlantPattern> patterns = pi.patterns;
        List<Long> genSums = new ArrayList<>();
        genSums.add(getSum(ps));
        // run until it stabilise
        long generations = 0L;
        while (!hasStabilised(genSums)) {
            PlantsState next = ps.applyRulesGenerateNewState(patterns);
            ps = new PlantsState(next.getState());
            generations++;
            genSums.add(getSum(ps));
        }
        long difference = genSums.get(genSums.size() - 1) - genSums.get(genSums.size() - 2);

        return genSums.get(genSums.size() - 1) + difference * (50000000000L - generations);
    }

    private static boolean hasStabilised(List<Long> genSums) {
        if (genSums.size() <= 11) {
            return false;
        }
        int endIndex = genSums.size() - 1;
        int convSum = 0;
        for (int i = endIndex - 11; i < endIndex - 1; i++) {
            convSum += genSums.get(i + 1) - genSums.get(i);
        }
        float avgDif = convSum / 10f;
        return (int) avgDif == (genSums.get(endIndex) - genSums.get(endIndex - 1));
    }

    private static Long getSum(PlantsState ps) {
        return ps.getState().entrySet().stream().filter(s -> s.getValue() == '#').map(e -> e.getKey().longValue()).reduce(0L, Long::sum);
    }

    public static ProblemInput buildInput(List<String> input) {
        Pattern logicPattern = Pattern.compile("([\\.#])+");
        Matcher initStateMatcher = logicPattern.matcher(input.get(0));
        boolean found = initStateMatcher.find();
        assert found;
        String initState = initStateMatcher.group(0);
        List<PlantPattern> pPatterns = new ArrayList<>();
        for (int index = 2; index < input.size(); index++) {
            String inputLine = input.get(index);
            if (inputLine.length() > 0) {
                Matcher logicMatcher = logicPattern.matcher(inputLine);
                found = logicMatcher.find();
                assert found;
                String pat = logicMatcher.group(0);
                found = logicMatcher.find();
                assert found;
                char result = logicMatcher.group(0).charAt(0);
                pPatterns.add(new PlantPattern(pat, result));
            }
        }

        return new ProblemInput(initState, pPatterns);
    }

    public static void run(String inputFileName) {
        List<String> input = FileLoader.loader(inputFileName);
        ProblemInput pi = buildInput(input);
        System.out.println("Part 1: " + part1(pi));
        System.out.println("Part 2: " + part2(pi));
    }

    public record ProblemInput(String initialState, List<PlantPattern> patterns) {
    }

}
