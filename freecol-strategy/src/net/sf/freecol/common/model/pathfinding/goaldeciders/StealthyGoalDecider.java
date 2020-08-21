package net.sf.freecol.common.model.pathfinding.goaldeciders;

import net.sf.freecol.common.model.PathNode;
import net.sf.freecol.common.model.Player;
import net.sf.freecol.common.model.Tile;
import net.sf.freecol.common.model.Unit;

public class StealthyGoalDecider implements GoalDecider {

    private PathNode goal;
    private Player enemy;

    public StealthyGoalDecider(final Player enemy) {
        this.enemy = enemy;
        goal = null;
    }

    @Override
    public PathNode getGoal() { return goal; }
    @Override
    public boolean hasSubGoals() { return true; }
    @Override
    public boolean check(Unit u, PathNode pathNode) {
        Tile tile = pathNode.getTile();
        if (enemy.canSee(tile)) return false;
        this.goal = pathNode;
        return true;
    }
}
