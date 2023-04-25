package org.aoc.days.day8;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final List<Node> childrenList;
    private final List<Integer> metadataList;
    private int noOfChildren;
    private int noOfMetadata;

    public Node(int noOfChildren, int noOfMetadata) {
        this.setNoOfChildren(noOfChildren);
        this.setNoOfMetadata(noOfMetadata);
        childrenList = new ArrayList<>();
        metadataList = new ArrayList<>();
    }
    public void addNode(Node childNode){
        if(this.getChildrenList().size() < getNoOfChildren()){
            getChildrenList().add(childNode);
            return;
        }
        throw new RuntimeException("Attempted to add more child nodes than allowed.");
    }
    public void addMetaData(int metadata){
        if(this.getMetadataList().size() < getNoOfMetadata()){
            getMetadataList().add(metadata);
            return;
        }
        throw new RuntimeException("Attempted to add more metadata than allowed.");
    }

    public int getNoOfChildren() {
        return noOfChildren;
    }

    public int getNoOfMetadata() {
        return noOfMetadata;
    }

    public List<Node> getChildrenList() {
        return childrenList;
    }

    public List<Integer> getMetadataList() {
        return metadataList;
    }

    private void setNoOfChildren(int noOfChildren) {
        this.noOfChildren = noOfChildren;
    }

    private void setNoOfMetadata(int noOfMetadata) {
        this.noOfMetadata = noOfMetadata;
    }
}
