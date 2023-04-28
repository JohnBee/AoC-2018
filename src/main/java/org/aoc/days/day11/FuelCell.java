package org.aoc.days.day11;


import java.util.HashMap;

public class FuelCell {
    private HashMap<Integer, HashMap<Integer, Long>> cache = new HashMap<>();
    private int gridSerialNumber;
    private int gridWidth;
    private int gridHeight;

    public FuelCell(int gridSerialNumber, int gridWidth, int gridHeight) {
        this.gridSerialNumber = gridSerialNumber;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        for(int y = 1; y <= gridHeight; y++){
            for(int x = 1; x <= gridWidth; x++){
                calculatePowerLevel(x, y); // pre-fill look up table.
            }
        }
    }
    public long getPowerLevel(int x, int y){
        return cache.get(x).get(y);
    }

    public long calculatePowerLevel(int x, int y){
        if(cache.containsKey(x) && cache.get(x).containsKey(y)) return cache.get(x).get(y);
        int rackId = x + 10;
        long powerLevel = rackId*y;
        powerLevel += gridSerialNumber;
        powerLevel *= rackId;

        // get hundreds
        powerLevel = (powerLevel / 100) % 10;

        powerLevel -=5;
        if(!cache.containsKey(x)){
            cache.put(x, new HashMap<>());
            cache.get(x).put(y, powerLevel);
        }
        else{
            cache.get(x).put(y, powerLevel);
        }
        return powerLevel;
    }
    public long get3x3TotalPower(int x, int y, int squareWidth, int squareHeight){
        long totalPower = 0;
        for(int iy = y; iy < y+squareHeight; iy++){
            for(int ix = x; ix < x+squareWidth; ix++){
                totalPower += getPowerLevel(ix, iy);
            }
        }
        return totalPower;
    }
    public Result getHighestTotalPower(int squareWidth, int squareHeight){
        long maxPower = Long.MIN_VALUE;
        int topx, topy;
        topx = topy = 0;
        for(int y = 1; y <= (gridHeight-squareHeight)+1; y++){
            for(int x = 1; x <= (gridWidth-squareWidth)+1; x++){
                long totalPower = get3x3TotalPower(x,y, squareWidth, squareHeight);
                if(totalPower > maxPower){
                    maxPower = totalPower;
                    topx = x;
                    topy = y;
                }
            }
        }
        Result out = new Result(topx, topy, maxPower);
        return out;
    }
    public record Result(int topx, int topy, long maxPower){}
}
