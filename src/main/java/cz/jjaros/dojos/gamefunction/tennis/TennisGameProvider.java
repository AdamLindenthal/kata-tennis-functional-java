package cz.jjaros.dojos.gamefunction.tennis;

import cz.jjaros.dojos.game.tennis.TennisScore;
import cz.jjaros.dojos.gamefunction.GameProvider;

/**
 * Functional interface that provides method for playing Tennis game
 * and actualize {@link TennisScore Tennis score}.
 */
@FunctionalInterface
public interface TennisGameProvider extends GameProvider<TennisScore> {
    // no methods needed
}
