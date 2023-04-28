package org.aoc.days.day11;

import org.aoc.filehandling.FileLoader;

import java.util.List;

public class day11 {

    public static String part1(List<String> input){
        int gridSerialNumber = Integer.parseInt(input.get(0));
        FuelCell fc = new FuelCell(gridSerialNumber, 300, 300);
        FuelCell.Result out = fc.getHighestTotalPower( 3, 3);
        return out.topx() + "," + out.topy();
    }
    public static String part2(List<String> input){
        int gridSerialNumber = Integer.parseInt(input.get(0));
        FuelCell fc = new FuelCell(gridSerialNumber, 300, 300);
        int bestGridSize = 1;
        FuelCell.Result best = new FuelCell.Result(0,0,0);
        for(int gridSize = 1; gridSize <= 300; gridSize++){
            FuelCell.Result out = fc.getHighestTotalPower(gridSize, gridSize);
            if(out.maxPower() > best.maxPower()){
                best = out;
                bestGridSize = gridSize;
                System.out.println("Current Best: " + best.topx() + "," + best.topy() + "," + bestGridSize + " Power: " + best.maxPower());
            }
        }
        assert best != null;
        return best.topx() + "," + best.topy() + "," + bestGridSize;
    }
    public static void run(String inputFileName){
        List<String> input = FileLoader.loader(inputFileName);
        System.out.println("Part 1: " + part1(input));
        System.out.println("Part 2: " + part2(input));
    }
}
