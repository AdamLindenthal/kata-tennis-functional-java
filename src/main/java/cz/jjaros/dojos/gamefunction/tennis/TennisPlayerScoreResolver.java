package cz.jjaros.dojos.gamefunction.tennis;

import cz.jjaros.dojos.game.tennis.TennisPlayerScore;
import cz.jjaros.dojos.gamefunction.GameScoreResolver;

/**
 * Functional interface that provides method for resolving two {@link TennisPlayerScore Tennis scores}
 * (for given player and his opponent) and provides new {@link TennisPlayerScore player score} for given player.
 */
@FunctionalInterface
public interface TennisPlayerScoreResolver extends GameScoreResolver<TennisPlayerScore, TennisPlayerScore> {
    // no methods needed
}