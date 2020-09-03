package net.sf.freecol.common.model.pathfinding.goaldeciders;

import net.sf.freecol.common.model.PathNode;
import net.sf.freecol.common.model.Tile;
import net.sf.freecol.common.model.Unit;

public class HighSeasGoalDecider implements GoalDecider {
    private PathNode best = null;

    @Override
    public PathNode getGoal() { return best; }
    @Override
    public boolean hasSubGoals() { return false; }
    @Override
    public boolean check(Unit u, PathNode path) {
        Tile tile = path.getTile();
        if (tile != null
                && tile.isExploredBy(u.getOwner())
                && tile.isDirectlyHighSeasConnected()
                && (tile.getFirstUnit() == null
                || u.getOwner().owns(tile.getFirstUnit()))) {
            if (best == null || path.getCost() < best.getCost()) {
                best = path;
                return true;
            }
        }
        return false;
    }
}
