package net.sf.freecol.common.model.pathfinding.goaldeciders;

import net.sf.freecol.common.model.PathNode;
import net.sf.freecol.common.model.Tile;
import net.sf.freecol.common.model.Unit;

public class CornerGoalDecider implements GoalDecider {

    private PathNode goal = null;
    private int score = Integer.MAX_VALUE;

    @Override
    public PathNode getGoal() { return goal; }
    @Override
    public boolean hasSubGoals() { return true; }
    @Override
    public boolean check(Unit u, PathNode pathNode) {
        Tile tile = pathNode.getTile();
        if (tile.getHighSeasCount() < score && tile.isRiverCorner()) {
            score = tile.getHighSeasCount();
            goal = pathNode;
            return true;
        }
        return false;
    }
}
