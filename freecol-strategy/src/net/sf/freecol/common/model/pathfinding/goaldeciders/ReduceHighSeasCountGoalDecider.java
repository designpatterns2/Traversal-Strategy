package net.sf.freecol.common.model.pathfinding.goaldeciders;

import net.sf.freecol.common.model.PathNode;
import net.sf.freecol.common.model.Settlement;
import net.sf.freecol.common.model.Tile;
import net.sf.freecol.common.model.Unit;

public class ReduceHighSeasCountGoalDecider implements GoalDecider {

    private Unit unit;
    private PathNode goal;
    private int score;

    public ReduceHighSeasCountGoalDecider(final Unit unit) {
        this.unit = unit;
        goal = null;
        score = unit.getTile().getHighSeasCount();
    }

    @Override
    public PathNode getGoal() { return goal; }
    @Override
    public boolean hasSubGoals() { return true; }
    @Override
    public boolean check(Unit u, PathNode pathNode) {
        Tile tile = pathNode.getTile();
        if (tile.getHighSeasCount() < score) {
            Settlement s = tile.getSettlement();
            if (unit.getOwner().owns(s)) {
                this.goal = pathNode;
                this.score = tile.getHighSeasCount();
                return true;
            }
        }
        return false;
    }
}
