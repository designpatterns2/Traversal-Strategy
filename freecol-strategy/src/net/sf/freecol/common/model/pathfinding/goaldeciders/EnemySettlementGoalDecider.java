package net.sf.freecol.common.model.pathfinding.goaldeciders;

import net.sf.freecol.common.model.*;

import java.util.Collection;

public class EnemySettlementGoalDecider implements GoalDecider {

    private Collection<Player> enemies;
    private PathNode best;

    public EnemySettlementGoalDecider(final Collection<Player> enemies) {
        this.enemies = enemies;
        best = null;
    }

    @Override
    public PathNode getGoal() { return best; }
    @Override
    public boolean hasSubGoals() { return false; }
    @Override
    public boolean check(Unit u, PathNode path) {
        Tile t = path.getTile();
        if (t == null || !t.isLand()) return false;
        Settlement s = t.getSettlement();
        if (s == null) return false;
        if (enemies.contains(s.getOwner())) {
            best = path;
            return true;
        }
        return false;
    }
}
