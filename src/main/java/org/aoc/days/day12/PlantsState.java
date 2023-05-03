package org.aoc.days.day12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlantsState {
    Map<Integer, Character> state;

    public PlantsState(String initState) {
        state = new HashMap<>();
        int index = 0;
        for (char c : initState.toCharArray()) {
            state.put(index, c);
            index++;
        }
    }

    public PlantsState(Map<Integer, Character> state) {
        this.state = state.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public PlantsState() {
        state = new HashMap<>();
    }

    public Map<Integer, Character> getState() {
        return state;
    }

    public char getAtIndex(int index) {
        if (state.containsKey(index)) return state.get(index);
        return '.';
    }

    public void replaceAtIndex(int index, char c) {
        if (state.containsKey(index)) {
            state.replace(index, c);
        } else {
            state.put(index, c);
        }
    }

    public PlantsState applyRulesGenerateNewState(List<PlantPattern> pPatterns) {
        //scan and match
        PlantsState nextState = new PlantsState();
        int startIndex = getStartIndex();
        int endIndex = getEndIndex();
        for(PlantPattern pPattern : pPatterns){
            for(int index = startIndex; index <= endIndex; index++){
                boolean allMatch = true;
                for(int cIndex = -2; cIndex <= 2; cIndex++){
                    allMatch = pPattern.getPattern().charAt(cIndex + 2) == this.getAtIndex(index + cIndex);
                    if(!allMatch) break;
                }
                if(allMatch){
                    nextState.replaceAtIndex(index, pPattern.result);
                }
            }
        }
        return nextState;

    }

    @Override
    public String toString() {
        Integer startIndex = getStartIndex();
        Integer endIndex = getEndIndex();
        StringBuilder out = new StringBuilder();
        for (int index = startIndex; index <= endIndex; index++) {
            out.append(getAtIndex(index));
        }
        return out.toString();
    }

    public Integer getEndIndex() {
        Integer index =this.state.entrySet().stream().filter(e -> e.getValue() == '#').map(Map.Entry::getKey).max(Integer::compareTo).orElse(null);
        if(index != null){
            return index + 5;
        }
        return null;
    }

    public Integer getStartIndex() {
        Integer index = this.state.entrySet().stream().filter(e -> e.getValue() == '#').map(Map.Entry::getKey).min(Integer::compareTo).orElse(null);
        if(index != null){
            return index - 5;
        }
        return null;
    }
}
