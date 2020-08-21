package net.sf.freecol.common.model.pathfinding.goaldeciders;

import net.sf.freecol.common.model.Location;
import net.sf.freecol.common.model.PathNode;
import net.sf.freecol.common.model.Tile;
import net.sf.freecol.common.model.Unit;
import net.sf.freecol.common.util.LogBuilder;

public class AdjacentLocationGoalDecider implements GoalDecider {

    private PathNode best;
    private Location target;
    private Tile tile;

    public AdjacentLocationGoalDecider(Location target) {
        this.target = target;
        best = null;
        tile = target.getTile();
    }

    @Override
    public PathNode getGoal() { return best; }
    @Override
    public boolean hasSubGoals() { return false; }
    @Override
    public boolean check(Unit u, PathNode path) {
        Tile t = path.getTile();
        if (t != null && t.isAdjacent(tile)) {
            best = path;
            return true;
        }
        return false;
    }
}
