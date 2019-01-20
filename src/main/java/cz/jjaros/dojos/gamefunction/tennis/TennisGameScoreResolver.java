package cz.jjaros.dojos.gamefunction.tennis;

import cz.jjaros.dojos.game.tennis.TennisPlayerScore;
import cz.jjaros.dojos.game.tennis.TennisScore;
import cz.jjaros.dojos.gamefunction.GameScoreResolver;

/**
 * Functional interface that provides method for resolving the given {@link TennisPlayerScore Tennis scores}
 * and provides {@link TennisScore Tennis score} as result.
 */
@FunctionalInterface
public interface TennisGameScoreResolver extends GameScoreResolver<TennisPlayerScore, TennisScore> {
    // no methods needed
}