package net.sf.freecol.common.model.pathfinding.goaldeciders;

import net.sf.freecol.common.model.PathNode;
import net.sf.freecol.common.model.Tile;
import net.sf.freecol.common.model.Unit;

public class SimpleHighSeasGoalDecider implements GoalDecider {

    private PathNode first = null;

    @Override
    public PathNode getGoal() { return first; }
    @Override
    public boolean hasSubGoals() { return false; }
    @Override
    public boolean check(Unit u, PathNode path) {
        Tile tile = path.getTile();
        if (tile != null
                && tile.isDirectlyHighSeasConnected()) {
            first = path;
            return true;
        }
        return false;
    }
}
