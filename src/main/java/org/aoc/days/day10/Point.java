package org.aoc.days.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Point {
    private Coordinate pos = null;
    private Coordinate velocity = null;

    public Point(int x, int y, int velX, int velY) {
        this.pos = new Coordinate(x, y);
        this.velocity = new Coordinate(velX, velY);
    }

    public static Point fromString(String s) {
        Pattern p = Pattern.compile("(-?\\d)+");
        Matcher m = p.matcher(s);
        List<Integer> found = new ArrayList<>();
        while(m.find()){
            found.add(Integer.parseInt(m.group(0)));
        }
        return new Point(found.get(0), found.get(1), found.get(2),found.get(3));
    }

    public Coordinate getPos() {
        return pos;
    }

    public void setPos(Coordinate pos) {
        this.pos = pos;
    }

    public Coordinate getVelocity() {
        return velocity;
    }

    public void setVelocity(Coordinate velocity) {
        this.velocity = velocity;
    }

    @Override
    public String toString() {
        return getPos().toString() + " @ " + getVelocity().toString();
    }

    public void step() {
        this.pos = Coordinate.add(this.pos, this.velocity);
    }
    public void reverseStep(){
        this.pos = Coordinate.subtract(this.pos, this.velocity);
    }

    public static class Coordinate {
        private int x = 0;
        private int y = 0;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Coordinate add(Coordinate lhs, Coordinate rhs) {
            return new Coordinate(lhs.getX() + rhs.getX(), lhs.getY() + rhs.getY());
        }

        public static Coordinate subtract(Coordinate lhs, Coordinate rhs) {
            return new Coordinate(lhs.getX() - rhs.getX(), lhs.getY() - rhs.getY());
        }

        @Override
        public String toString() {
            return "[" + getX() + ", " + getY() + "]";
        }

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

        @Override
        public boolean equals(Object o){
            if(o instanceof Coordinate){
                return ((Coordinate) o).getX() == this.getX() && ((Coordinate) o).getY() == this.getY();
            }
            return false;
        }
    }
}
