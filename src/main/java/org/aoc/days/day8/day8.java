package org.aoc.days.day8;

import com.sun.source.tree.Tree;
import org.aoc.filehandling.FileLoader;

import java.util.ArrayList;
import java.util.List;

public class day8 {

    public static int part1(List<String> input){
        List<Integer> inputData = TreeBuilder.inputStringToIntegers(input.get(0)); // first line;
        Node tree = TreeBuilder.buildTree(inputData);
        return TreeBuilder.sumMetadata(tree);
    }
    public static int part2(List<String> input){
        List<Integer> inputData = TreeBuilder.inputStringToIntegers(input.get(0)); // first line;
        Node tree = TreeBuilder.buildTree(inputData);
        return TreeBuilder.getRootNodeValue(tree);
    }
    public static void run(String inputFileName){
        List<String> input = FileLoader.loader(inputFileName);
        System.out.println("Part 1: " + part1(input));
        System.out.println("Part 2: " + part2(input));
    }
}
