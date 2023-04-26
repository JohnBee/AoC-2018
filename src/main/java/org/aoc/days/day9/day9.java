package org.aoc.days.day9;

import org.aoc.days.day3.Pair;
import org.aoc.filehandling.FileLoader;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day9 {
    public static long part1(Pair<Integer, Integer> input){
        MarbleGame mg = new MarbleGame(input.getFirst(), input.getSecond());
        for(int i = 0; i < input.getSecond(); i++){
            mg.takeTurn();
//            System.out.println(mg.toString());
        }
        return mg.getGamestate().getPlayerScores().values().stream().max(Long::compare).get();
    }
    public static long part2(Pair<Integer, Integer> input){
        int players = input.getFirst();
        int lastMarble = input.getSecond()*100;
        MarbleGame mg = new MarbleGame(players, lastMarble);
        for(int i = 0; i < lastMarble; i++){
            mg.takeTurn();
        }
        return mg.getGamestate().getPlayerScores().values().stream().max(Long::compare).get();
    }
    public static Pair<Integer, Integer> parseInput(List<String> input){
        int noOfPlayers = 0;
        int lastMarble = 0;
        Pattern p = Pattern.compile("(\\d+)");
        Matcher m = p.matcher(input.get(0));
        m.find();
        noOfPlayers = Integer.parseInt(m.group(0));
        m.find();
        lastMarble = Integer.parseInt(m.group(0));
        return new Pair(noOfPlayers, lastMarble);
    }
    public static void run(String inputFileName){
        List<String> input = FileLoader.loader(inputFileName);
        Pair<Integer, Integer> inPair = parseInput(input);
        System.out.println("Part 1: " + part1(inPair));
        System.out.println("Part 2: " + part2(inPair));
    }
}
