package org.aoc.days.day9;

public class MarbleGame {
    private final long marbleScore;
    private final int noOfPlayers;
    private int currentPlayersTurn;
    private int lastMarblePlayed;
    private GameState gs;


    public MarbleGame(int noOfPlayers, int marbleScore) {
        this.gs = new GameState();
        this.marbleScore = marbleScore;
        this.noOfPlayers = noOfPlayers;
        this.currentPlayersTurn = 0;
        this.lastMarblePlayed = 0;
    }

    public long getMarbleScore() {
        return marbleScore;
    }

    public int getNoOfPlayers() {
        return noOfPlayers;
    }

    public GameState getGamestate() {
        return gs;
    }

    public void takeTurn() {
        int nextMarble = lastMarblePlayed + 1;
        gs.insertMarble(nextMarble, currentPlayersTurn);
        currentPlayersTurn = (currentPlayersTurn % noOfPlayers) + 1;
        lastMarblePlayed = nextMarble;
    }

    @Override
    public String toString() {
        return "[" + (currentPlayersTurn) + "]" + gs.getState().toString();
    }
}
