package org.aoc.days.day9;

import java.util.*;

public class GameState {
    private final LinkedListTape<Integer> state;
    private int lastInsertIndex = 0;
    private final Map<Integer, Long> playerScores;

    public GameState() {
        this.state = new LinkedListTape<>();
        this.state.add(0);
        playerScores = new HashMap<>();
    }

    public Map<Integer, Long> getPlayerScores() {
        return playerScores;
    }

    public LinkedListTape<Integer> getState() {
        return state;
    }

    public void addToPlayerScore(int currentPlayer, long value) {
        if (playerScores.containsKey(currentPlayer)) {
            playerScores.replace(currentPlayer, playerScores.get(currentPlayer) + value);
            return;
        }
        playerScores.put(currentPlayer, value);
    }

    public void insertMarble(int value, int currentPlayer) {
        if (value % 23 == 0 && value != 0) {
            // modulo
            state.moveTape(-7);

            int score = state.getAtPointer().getValue();
            state.remove();
            addToPlayerScore(currentPlayer, value + score);
            return;
        }
        state.moveTape(1);
        state.add(value);
    }


}
