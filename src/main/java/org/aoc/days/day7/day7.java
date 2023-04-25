package org.aoc.days.day7;

import org.aoc.filehandling.FileLoader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class day7 {

    public static String part1(ArrayList<Character> starts,
                               Character end,
                               HashMap<Character, ArrayList<Character>> adjacencyMap,
                               HashMap<Character, ArrayList<Character>> requirementsMap){
        // Use BFS, but only going to node we can build with requirements.
        ArrayList<Character> toVisit = new ArrayList<>();
        toVisit.addAll(starts);
        ArrayList<Character> visited = new ArrayList<>();
        while(!toVisit.isEmpty()){
            Character current = getNext(toVisit, visited, requirementsMap);
            toVisit.remove(current);
            visited.add(current);
            if(current == end){ // we're done, got to the end
                break;
            }
            for(Character c : adjacencyMap.get(current)){
                if(!visited.contains(c) && !toVisit.contains(c)){
                    toVisit.add(c);
                }
            }
        }
        return visited.stream().map(a -> a.toString()).reduce("", (a, b) -> (a + b));
    }

    public static int part2(String p1Result, HashMap<Character, ArrayList<Character>> requirementsMap){
        WorkerHandler wh = new WorkerHandler(5); // 4 workers
        return wh.run(p1Result, requirementsMap);
    }

    private static Character getNext(ArrayList<Character> toVisit, ArrayList<Character> visited, HashMap<Character, ArrayList<Character>> requirementsMap) {
        toVisit.sort(Character::compareTo);
        for(Character c : toVisit){
            if(!requirementsMap.containsKey(c)) return c; // must be start node
            if(visited.containsAll(requirementsMap.get(c))) return c;
        }
        return null;
    }

    public static void run(String inputFileName){
        ArrayList<String> input = FileLoader.loader(inputFileName);
        HashMap<Character, ArrayList<Character>> adjacencyMap = buildAdjacencyMap(input);
        HashMap<Character, ArrayList<Character>> requirementsMap = buildRequirementsMap(adjacencyMap);
        ArrayList<Character> starts = null;
        Character end = null;
        try {
            starts = getStarts(adjacencyMap, requirementsMap);
            end = getEnd(adjacencyMap, requirementsMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String p1Result = part1(starts, end, adjacencyMap, requirementsMap);
        System.out.println("Part 1: " + p1Result);
        System.out.println("Part 2: " + part2(p1Result, requirementsMap));
    }

    private static Character getEnd(HashMap<Character, ArrayList<Character>> adjacencyMap, HashMap<Character, ArrayList<Character>> requirementsMap) throws Exception {
        Set<Character> nodes = new HashSet<>(adjacencyMap.keySet());
        nodes.addAll(requirementsMap.keySet());
        // difference between all nodes and requirements is start as start has no requirements
        for(Character k : requirementsMap.keySet()){
            if(!adjacencyMap.containsKey(k)){
                return k;
            }
        }
        throw new Exception("No End node found.");
    }

    private static ArrayList<Character> getStarts(HashMap<Character, ArrayList<Character>> adjacencyMap, HashMap<Character, ArrayList<Character>> requirementsMap){
        ArrayList<Character> starts = new ArrayList<>();
        // difference between all nodes and requirements is start as start has no requirements
        for(Character k : adjacencyMap.keySet()){
            if(!requirementsMap.containsKey(k)){
                starts.add(k);
            }
        }
        return starts;
    }

    private static HashMap<Character, ArrayList<Character>> buildRequirementsMap(HashMap<Character, ArrayList<Character>> adjacencyMap) {
        HashMap<Character, ArrayList<Character>> requirementsMap = new HashMap<>();
        for(Character c : adjacencyMap.keySet()){
            for(Character requires : adjacencyMap.get(c)){
                if(requirementsMap.containsKey(requires)) requirementsMap.get(requires).add(c);
                else requirementsMap.put(requires, new ArrayList<>(List.of(c)));
            }
        }
        return requirementsMap;
    }

    private static HashMap<Character, ArrayList<Character>> buildAdjacencyMap(ArrayList<String> input) {
        Pattern p = Pattern.compile(" ([A-Z]) ");
        HashMap<Character, ArrayList<Character>> adjMap = new HashMap<>();
        for(String s : input){
            Matcher m = p.matcher(s);
            m.find();
            Character before = m.group(1).charAt(0);
            m.find();
            Character after = m.group(1).charAt(0);
            if(adjMap.containsKey(before))
                adjMap.get(before).add(after);
            else
                adjMap.put(before, new ArrayList<>(List.of(after)));
        }
        return adjMap;
    }
}
