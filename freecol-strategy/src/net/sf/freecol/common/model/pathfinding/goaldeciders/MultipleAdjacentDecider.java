package net.sf.freecol.common.model.pathfinding.goaldeciders;

import net.sf.freecol.common.model.Location;
import net.sf.freecol.common.model.PathNode;
import net.sf.freecol.common.model.Tile;
import net.sf.freecol.common.model.Unit;

import java.util.HashMap;
import java.util.List;

import static net.sf.freecol.common.util.CollectionUtils.transform;

public class MultipleAdjacentDecider {

    private final GoalDecider gd;

    private final HashMap<Location, PathNode> results = new HashMap<>();


    /**
     * Create a multiple decider.
     *
     * @param locs The list of {@code Location}s to search for
     *     paths to an adjacent location for.
     */
    public MultipleAdjacentDecider(final List<Location> locs) {
        this.gd = new GoalDecider() {

            @Override
            public PathNode getGoal() { return null; }
            @Override
            public boolean hasSubGoals() { return true; }
            @Override
            public boolean check(Unit u, PathNode path) {
                Tile tile = path.getTile();
                if (tile == null) return false;
                for (Location loc : transform(locs,
                        l -> tile.isAdjacent(l.getTile()))) {
                    PathNode p = results.get(loc);
                    if (p == null
                            || p.getCost() > path.getCost()) {
                        results.put(loc, path);
                    }
                }
                return false;
            }
        };
    }

    public GoalDecider getGoalDecider() {
        return gd;
    }

    public HashMap<Location, PathNode> getResults() {
        return results;
    }
}
