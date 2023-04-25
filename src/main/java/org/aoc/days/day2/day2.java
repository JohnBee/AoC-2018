package org.aoc.days.day2;

import org.aoc.filehandling.FileLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class day2 {
    public static Integer part1(ArrayList<String> input){
        Integer twos = 0;
        Integer threes = 0;
        for(String s : input){
            Integer twos_sub = 0;
            Integer threes_sub = 0;
            for(char c : s.toCharArray()){
                IntStream sStream = s.chars();
                long count = sStream.filter(x -> x == c).count();
                if(count == 2)
                    twos_sub++;
                if(count == 3)
                    threes_sub++;
            }
            if(twos_sub > 0)
                twos++;
            if(threes_sub > 0)
                threes++;
        }
        return twos*threes;
    }
    public static String part2(ArrayList<String> input){
        String out = null;
        for(Integer i = 0; i < input.size()-1; i++){
            for(Integer j = i+1; j < input.size(); j++){
                String s1 = input.get(i);
                String s2 = input.get(j);
                int differentChars = 0;
                for(Integer cIndex = 0; cIndex < s1.length(); cIndex++){

                    if(s1.charAt(cIndex) != s2.charAt(cIndex)){
                        differentChars++;
                    }
                    if(differentChars > 2) {
                        continue;
                    }
                }
                if(differentChars == 1){
                    // found answer, get same chars
                    out = "";
                    for(Integer cIndex = 0; cIndex < s1.length(); cIndex++) {
                        if(s1.charAt(cIndex) == s2.charAt(cIndex)) {
                            out += s1.charAt(cIndex);
                        }
                    }
                    return out;
                }

            }

        }
        return out;
    }

    public static void run(String inputFileName) {
        ArrayList<String> input = FileLoader.loader(inputFileName);
        System.out.println("Part 1 " + part1(input));
        System.out.println("Part 2 " + part2(input));
    }
}
