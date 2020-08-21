package net.sf.freecol.common.model.pathfinding.goaldeciders;

import net.sf.freecol.common.model.*;

import java.util.Comparator;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

import static net.sf.freecol.common.util.CollectionUtils.*;

public class DisembarkGoalDecider implements GoalDecider {

    final double NO_DANGER_BONUS = 1000.0;
    private Tile target;
    private double bestScore;
    private PathNode goal;

    public DisembarkGoalDecider(final Tile target) {
        this.target = target;
        bestScore = -1.0;
        goal = null;
    }

    @Override
    public PathNode getGoal() { return goal; }
    @Override
    public boolean hasSubGoals() { return true; }
    @Override
    public boolean check(Unit u, PathNode pathNode) {
        final Tile tile = pathNode.getTile();
        if (tile == null || !tile.isLand() || !tile.isEmpty()
                || tile.hasSettlement()) return false;

        final Player owner = u.getOwner();
        final Map map = u.getGame().getMap();
        final Predicate<Tile> dockPred = t ->
                t.isHighSeasConnected() && !t.isLand();
        final Predicate<Tile> dangerPred = t -> {
            Settlement settlement = t.getSettlement();
            return (settlement != null
                    && !owner.owns(settlement)
                    && settlement.hasAbility(Ability.BOMBARD_SHIPS)
                    && (owner.atWarWith(settlement.getOwner())
                    || u.hasAbility(Ability.PIRACY)));
        };
        final ToDoubleFunction<Tile> tileScorer = cacheDouble(t ->
                (t.getDefenceValue() / (1.0 + map.getDistance(target, t))
                        + ((none(t.getSurroundingTiles(1, 1), dangerPred))
                        ? NO_DANGER_BONUS : 0.0)));
        Tile best = maximize(tile.getSurroundingTiles(1, 1), dockPred,
                Comparator.comparingDouble(tileScorer));
        double score;
        if (best != null
                && (score = tileScorer.applyAsDouble(best)) > bestScore) {
            bestScore = score;
            goal = pathNode;
            return true;
        }
        return false;
    }
}
