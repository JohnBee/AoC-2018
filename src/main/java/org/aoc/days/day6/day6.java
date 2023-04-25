package org.aoc.days.day6;

import org.aoc.filehandling.FileLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.abs;


public class day6 {

    public static Coord getTopLeft(ArrayList<Coord> coords){
        Integer topx = Integer.MAX_VALUE;
        Integer topy = Integer.MAX_VALUE;
        for(Coord c : coords){
            if(c.getX() < topx) topx = c.getX();
            if(c.getY() < topy) topy = c.getY();
        }
        return new Coord(-1, topx, topy);
    }
    public static Coord getBottomRight(ArrayList<Coord> coords){
        Integer bottomx = 0;
        Integer bottomy = 0;
        for(Coord c : coords){
            if(c.getX() > bottomx) bottomx = c.getX();
            if(c.getY() > bottomy) bottomy = c.getY();
        }
        return new Coord(-1, bottomx, bottomy);
    }

    private static int getManhattan(int x, int y, Coord c) {
        return abs(x-c.getX()) + abs(y-c.getY());
    }

    private static Integer getClosestCoord(int x, int y, ArrayList<Coord> coords) {
        int closestId = -1;
        int closestDist = Integer.MAX_VALUE;
        for(Coord c : coords){
            int dist = getManhattan(x, y, c);
            if(dist < closestDist){
                closestId = c.getId();
                closestDist = dist;
            }
        }
        // repeat for closest to 2;
        int found = 0;
        for(Coord c : coords){
            int dist = getManhattan(x, y, c);
            if(dist == closestDist){
                found++;
            }
        }
        // close to multiple
        if(found > 1) return -1;

        return closestId;
    }

    public static int part1(ArrayList<Coord> coords){
        Coord topLeft = getTopLeft(coords);
        Coord bottomRight = getBottomRight(coords);
        HashMap<Integer, ArrayList<Coord>> coordsWithClosestPoints = new HashMap<>();
        for(int y = topLeft.getY(); y < bottomRight.getY(); y++){
            for(int x = topLeft.getX(); x < bottomRight.getX(); x++){
                Integer closestCoord = getClosestCoord(x, y, coords);
                if(closestCoord != -1){
                    if(coordsWithClosestPoints.containsKey(closestCoord)){
                        ArrayList<Coord> cList = coordsWithClosestPoints.get(closestCoord);
                        cList.add(new Coord(-1, x , y));
                        coordsWithClosestPoints.replace(closestCoord, cList);
                    }
                    else{
                        ArrayList<Coord> cList = new ArrayList<>();
                        cList.add(new Coord(-1, x , y));
                        coordsWithClosestPoints.put(closestCoord, cList);
                    }
                }
            }
        }
        // go through map find the largest area that's not infinite.
        // Disregards areas that touch the infinite bounding box.
        Integer largestArea = 0;
        for(Integer id : coordsWithClosestPoints.keySet()){
            int areaCount = 0;
            for(Coord c : coordsWithClosestPoints.get(id)){
                if(c.getX() != topLeft.getX() && c.getX() != bottomRight.getX() &&
                   c.getY() != topLeft.getY() && c.getY() != bottomRight.getY()){
                    areaCount++;
                }
                else{
                    areaCount = 0;
                    break;
                }
            }
            if(areaCount > largestArea){
                largestArea = areaCount;
            }
        }
        return largestArea;
    }


    public static int part2(ArrayList<Coord> coords){
        Coord _topLeft = getTopLeft(coords);
        Coord _bottomRight = getBottomRight(coords);
        Coord topLeft = new Coord(-1, _bottomRight.getX()-5000, _bottomRight.getY()-5000);
        Coord bottomRight = new Coord(-1, _topLeft.getX()+5000, _topLeft.getX()+5000);

        int largestArea = 0;
        for(int y = topLeft.getY(); y < bottomRight.getY(); y++){
            for(int x = topLeft.getX(); x < bottomRight.getX(); x++){
                // sum distance to all coords
                int finalX = x;
                int finalY = y;
                int sum = coords.stream().map(coord -> {
                    return getManhattan(finalX, finalY, coord);
                }).reduce(0, Integer::sum);
                if(sum < 10000) largestArea++;
            }
        }
        return largestArea;
    }

    public static ArrayList<Coord> buildCoordinateList(ArrayList<String> input){
        ArrayList<Coord> out = new ArrayList<>();
        Pattern p = Pattern.compile("(\\d+), (\\d+)");
        int id = 0;
        for(String s : input){
             Matcher m = p.matcher(s);
             Integer x = null;
             Integer y = null;
             if(m.find()){
                x = Integer.parseInt(m.group(1));
                y = Integer.parseInt(m.group(2));
             }
             assert x != null && y != null;
             out.add(new Coord(id, x,y));
             id++;
        }
        return out;
    }

    public static void run(String inputFileName){
        ArrayList<String> input = FileLoader.loader(inputFileName);
        ArrayList<Coord> coords = buildCoordinateList(input);
        System.out.println("Part 1: " + part1(coords));
        System.out.println("Part 2: " + part2(coords));
    }
}
