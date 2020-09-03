package net.sf.freecol.common.model.pathfinding.goaldeciders;

import net.sf.freecol.common.model.PathNode;
import net.sf.freecol.common.model.Unit;

public class ComposedGoalDecider implements GoalDecider {
    private int winner;
    private PathNode goal;
    private boolean all;
    private GoalDecider[] gds;

    public ComposedGoalDecider(final boolean all, final GoalDecider... gds) {
        winner = gds.length;
        goal = null;
        this.all = all;
        this.gds = gds;
    }

    @Override
    public PathNode getGoal() { return goal; }
    @Override
    public boolean hasSubGoals() {
        for (int i = 0; i < gds.length; i++) {
            if (!all && i > this.winner) break;
            if (gds[i].hasSubGoals()) {
                if (!all) return true;
            } else {
                if (all) return false;
            }
        }
        return !all;
    }
    @Override
    public boolean check(Unit u, PathNode path) {
        for (int i = 0; i < gds.length; i++) {
            if (!all && i > this.winner) break;
            if (gds[i].check(u, path)) {
                if (!all) {
                    this.winner = i;
                    this.goal = path;
                    return true;
                }
            } else {
                if (all) {
                    return false;
                }
            }
        }
        if (all) {
            this.winner = 0;
            this.goal = path;
            return true;
        }
        return false;
    }
}
