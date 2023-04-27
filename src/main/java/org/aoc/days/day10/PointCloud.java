package org.aoc.days.day10;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class PointCloud {
    private final List<Point> cloud;
    private int steps = 0;

    public PointCloud() {
        cloud = new ArrayList<>();
    }

    public int getSteps() {
        return steps;
    }

    public void step() {
        for (Point p : cloud) {
            p.step();
        }
        steps++;
    }
    public void reverseStep() {
        for (Point p : cloud) {
            p.reverseStep();
        }
        steps--;
    }

    public List<Point> getCloud() {
        return cloud;
    }

    public void addPoint(Point p) {
        cloud.add(p);
    }

    public void addPoint(String s) {
        cloud.add(Point.fromString(s));
    }

    public long entropy() {
        // get the manhattan distance between all the points, sum return entropy
        long x_entropy = 0;
        long y_entropy = 0;
        for (int iter = 0; iter < cloud.size() - 1; iter++) {
            for (int jiter = iter + 1; jiter < cloud.size(); jiter++) {
                Point a = cloud.get(iter);
                Point b = cloud.get(jiter);
                x_entropy += abs(a.getPos().getX() - b.getPos().getX());
                y_entropy += abs(a.getPos().getY() - b.getPos().getY());
            }
        }
        return x_entropy + y_entropy;
    }

    public String pointCloudToString() {
        int topx, topy, botx, boty;
        topx = topy = Integer.MAX_VALUE;
        botx = boty = Integer.MIN_VALUE;
        List<Point.Coordinate> pointCoords = new ArrayList<>();

        for (Point p : cloud) {
            if (p.getPos().getX() < topx)
                topx = p.getPos().getX();
            if (p.getPos().getY() < topy)
                topy = p.getPos().getY();
            if (p.getPos().getX() > botx)
                botx = p.getPos().getX();
            if (p.getPos().getY() > boty)
                boty = p.getPos().getY();
            pointCoords.add(p.getPos());
        }
        StringBuilder out = new StringBuilder();
        for (int y = topy; y <= boty; y++) {
            for (int x = topx; x <= botx; x++) {
                if (pointCoords.contains(new Point.Coordinate(x, y))) {
                    out.append("#");
                } else {
                    out.append(".");
                }
            }
            out.append("\n");
        }
        return out.toString();
    }

}
