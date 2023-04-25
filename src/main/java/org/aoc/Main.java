package org.aoc;
import org.aoc.days.day1.day1;
import org.aoc.days.day2.day2;
import org.aoc.days.day3.day3;
import org.aoc.days.day4.day4;
import org.aoc.days.day5.day5;
import org.aoc.days.day6.day6;
import org.aoc.days.day7.day7;

public class Main {
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Missing input filename arg.");
            return;
        }
        day7.run(args[0]);
    }
}