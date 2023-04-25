package org.aoc.days.day3;

import org.aoc.filehandling.FileLoader;

import java.util.ArrayList;
import org.aoc.days.day3.Claim;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day3 {
    public static Pair<Integer, HashMap<Integer, HashMap<Integer, Integer>>> part1(ArrayList<Claim> claims){
        HashMap<Integer, HashMap<Integer, Integer>> fabricClaim = new HashMap<>();
        for(Claim c : claims){
            for(Integer y = c.getY(); y < c.getY() + c.getHeight(); y++){
                for(Integer x = c.getX(); x < c.getX() + c.getWidth(); x++) {
                    if(fabricClaim.containsKey(x)) {
                        if (fabricClaim.get(x).containsKey(y)) {
                            fabricClaim.get(x).replace(y, fabricClaim.get(x).get(y) + 1); // increment overlapping claims;
                        }
                        else{
                            fabricClaim.get(x).put(y, 1);
                        }
                    }
                    else{
                        fabricClaim.put(x, new HashMap<>());
                        fabricClaim.get(x).put(y,1);
                    }
                }
            }
        }
        // counter overlapping claims
        Set<Integer> xkeys = fabricClaim.keySet();
        int multipleClaims = 0;
        for(Integer xk : xkeys){
            Set<Integer> ykeys = fabricClaim.get(xk).keySet();
            for(Integer yk : ykeys){
                if(fabricClaim.get(xk).get(yk) > 1){
                    multipleClaims++;
                }
            }
        }

        return new Pair<Integer,HashMap< Integer, HashMap<Integer, Integer>>>(multipleClaims, fabricClaim);
    }
    public static Integer part2(ArrayList<Claim> claims, HashMap<Integer, HashMap<Integer, Integer>> fabricClaim){
        for(Claim c : claims){
            // check that each claim definitely doesn't overlap
            boolean overlapped = false;
            for(Integer y = c.getY(); y < c.getY() + c.getHeight(); y++) {
                for (Integer x = c.getX(); x < c.getX() + c.getWidth(); x++) {
                    if(fabricClaim.get(x).get(y) > 1){
                        overlapped = true;
                        break;
                    }
                }
                if(overlapped){
                    break;
                }
            }
            if(!overlapped){
                return c.getId();
            }
        }
        return 0;
    }

    public static void run(String inputFileName) {
        ArrayList<String> input = FileLoader.loader(inputFileName);

        // parse regex, convert to claims
        ArrayList<Claim> claims = new ArrayList<>();
        Pattern p = Pattern.compile("(\\d+)");

        for(String s : input){
            List<Integer> intMatches = new ArrayList<>();
            Matcher matcher = p.matcher(s);
            while(matcher.find())
            {
                intMatches.add(Integer.parseInt(matcher.group(0)));
            }
            claims.add(new Claim(intMatches.get(0), intMatches.get(1), intMatches.get(2),intMatches.get(3),
                                 intMatches.get(4)));

        }



        Pair<Integer, HashMap<Integer, HashMap<Integer, Integer>>> p1Result = part1(claims);
        System.out.println("Part 1 " + p1Result.getFirst());
        System.out.println("Part 2 " + part2(claims, p1Result.getSecond()));
    }
}
