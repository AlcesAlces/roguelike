package roguelike.pathfinding;

import roguelike.creatures.Creature;
import roguelike.map.MapTiny;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Contains all of the methods which find a path.
 * from: http://trystans.blogspot.com/2011/09/roguelike-tutorial-13-aggressive.html
 */
public class OverworldPathfinder {

    private ArrayList<Point> open;
    private ArrayList<Point> closed;
    private HashMap<Point, Point> parents;
    private HashMap<Point,Integer> totalCost;
    private MapTiny[][] overworld;
    private int sizeX;
    private int sizeY;

    public OverworldPathfinder(int x, int y, MapTiny[][] tempMap) {
        this.open = new ArrayList<Point>();
        this.closed = new ArrayList<Point>();
        this.parents = new HashMap<Point, Point>();
        this.totalCost = new HashMap<Point, Integer>();
        sizeX = x;
        sizeY = y;
        overworld = tempMap;
    }

    private int heuristicCost(Point from, Point to) {
        return Math.max(Math.abs(from.x - to.x), Math.abs(from.y - to.y));
    }

    private int costToGetTo(Point from) {
        return parents.get(from) == null ? 0 : (1 + costToGetTo(parents.get(from)));
    }

    private int totalCost(Point from, Point to) {
        if (totalCost.containsKey(from))
            return totalCost.get(from);

        int cost = costToGetTo(from) + heuristicCost(from, to);
        totalCost.put(from, cost);
        return cost;
    }

    private void reParent(Point child, Point parent){
        parents.put(child, parent);
        totalCost.remove(child);
    }

    //Entry point
    public ArrayList<Point> findPath(Point start, Point end, int maxTries) {
        open.clear();
        closed.clear();
        parents.clear();
        totalCost.clear();

        open.add(start);

        for (int tries = 0; tries < maxTries && open.size() > 0; tries++){
            Point closest = getClosestPoint(end);

            open.remove(closest);
            closed.add(closest);

            if (closest.equals(end))
                return createPath(start, closest);
            else
                checkNeighbors(end, closest);
        }
        return null;
    }

    private Point getClosestPoint(Point end) {
        Point closest = open.get(0);
        for (Point other : open){
            if (totalCost(other, end) < totalCost(closest, end))
                closest = other;
        }
        return closest;
    }

    private void checkNeighbors(Point end, Point closest) {

        ArrayList<Point> neighbors = new ArrayList<>();

        //North
        if(closest.y + 1 < sizeY)
        {
            neighbors.add(new Point(closest.x, closest.y + 1));
        }
        //South
        if(closest.y - 1 >= 0)
        {
            neighbors.add(new Point(closest.x, closest.y - 1));
        }
        //East
        if(closest.x + 1 < sizeX)
        {
            neighbors.add(new Point(closest.x + 1, closest.y));
        }
        //West
        if(closest.x -1 >= 0)
        {
            neighbors.add(new Point(closest.x - 1, closest.y));
        }
        for (Point neighbor : neighbors) {
            if (closed.contains(neighbor)
                    //|| !creature.canEnter(neighbor.x, neighbor.y)
                    && !neighbor.equals(end))
                continue;

            if (open.contains(neighbor))
                reParentNeighborIfNecessary(closest, neighbor);
            else
                reParentNeighbor(closest, neighbor);
        }
    }

    private void reParentNeighbor(Point closest, Point neighbor) {
        reParent(neighbor, closest);
        open.add(neighbor);
    }

    private void reParentNeighborIfNecessary(Point closest, Point neighbor) {
        Point originalParent = parents.get(neighbor);
        double currentCost = costToGetTo(neighbor);
        reParent(neighbor, closest);
        double reparentCost = costToGetTo(neighbor);

        if (reparentCost < currentCost)
            open.remove(neighbor);
        else
            reParent(neighbor, originalParent);
    }

    private ArrayList<Point> createPath(Point start, Point end) {
        ArrayList<Point> path = new ArrayList<Point>();

        while (!end.equals(start)) {
            path.add(end);
            end = parents.get(end);
        }

        Collections.reverse(path);
        return path;
    }

}
