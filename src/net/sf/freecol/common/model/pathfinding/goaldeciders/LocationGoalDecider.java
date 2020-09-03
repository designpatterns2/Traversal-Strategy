package net.sf.freecol.common.model.pathfinding.goaldeciders;

import net.sf.freecol.common.model.Location;
import net.sf.freecol.common.model.Map;
import net.sf.freecol.common.model.PathNode;
import net.sf.freecol.common.model.Unit;

import static net.sf.freecol.common.model.Constants.INFINITY;

public class LocationGoalDecider implements GoalDecider {

    private PathNode best;
    private int bestCost;
    private Location target;

    public LocationGoalDecider(final Location target) {
        this.target = target;
        bestCost = INFINITY;
        best = null;
    }

    @Override
    public PathNode getGoal() { return best; }
    @Override
    public boolean hasSubGoals() { return false; }
    @Override
    public boolean check(Unit u, PathNode path) {
        int cost;
        if (Map.isSameLocation(path.getLocation(), target)) {
            if ((cost = path.getCost()) < bestCost) {
                best = path;
                bestCost = cost;
            }
            return true;
        }
        return false;
    }
}
