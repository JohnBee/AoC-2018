package org.aoc.days.day6;

public class Coord {

    private int x;
    private int y;
    private int id;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coord(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

}
