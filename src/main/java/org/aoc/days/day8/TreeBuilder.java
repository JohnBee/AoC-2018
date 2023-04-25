package org.aoc.days.day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeBuilder {
    public static List<Integer> inputStringToIntegers(String input){
        List<Integer> out = new ArrayList<>();
        Arrays.stream(input.split("\\s")).forEach((s)->out.add(Integer.parseInt(s)));
        return out;
    }
    private static Integer consumeNextData(List<Integer> inputData){
        Integer out = inputData.get(0);
        inputData.remove(0);
        return out;
    }

    public static Node buildTree(List<Integer> inputData){
        return buildNode(inputData);
    }

    private static Node buildNode(List<Integer> inputData) {
        // read no of children, and metadata
        int childrenCount = consumeNextData(inputData);
        int metadataCount = consumeNextData(inputData);
        Node current = new Node(childrenCount,metadataCount);
        for(int c = 0; c < childrenCount; c++){
            current.addNode(buildNode(inputData));
        }
        for(int m = 0; m < metadataCount; m++){
            current.addMetaData(consumeNextData(inputData));
        }
        return current;
    }
    public static int sumMetadata(Node tree){
        List<Node> toVisit = new ArrayList<>(List.of(tree));
        int metadataSum = 0;
        while(!toVisit.isEmpty()){
            Node current = toVisit.get(0);
            toVisit.remove(0);
            metadataSum += current.getMetadataList().stream().reduce(0, Integer::sum); // sum up the metadata
            toVisit.addAll(current.getChildrenList());
        }
        return metadataSum;
    }
    public static int getRootNodeValue(Node current){
        int nodeSum = 0;
        if(current.getNoOfChildren() == 0){
            return current.getMetadataList().stream().reduce(0, Integer::sum);
        }
        for(int childIndex : current.getMetadataList()){
            int index = childIndex - 1;

            if(index <= current.getNoOfChildren() - 1){
                nodeSum += getRootNodeValue(current.getChildrenList().get(index));
            }
        }
        return nodeSum;
    }

}
