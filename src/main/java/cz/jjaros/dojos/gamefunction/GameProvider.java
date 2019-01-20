package cz.jjaros.dojos.gamefunction;

import cz.jjaros.dojos.game.Score;
import cz.jjaros.dojos.game.ScoringPlayer;

/**
 * Functional interface that provides method for playing the game.
 */
@FunctionalInterface
public interface GameProvider<T extends Score> {

    /**
     * Play the game and returns new {@link T score}.
     *
     * @param score actual {@link T score}
     * @param player {@link ScoringPlayer scoring player}
     * @return new {@link T score} after the game
     */
    T play(T score, ScoringPlayer player);
}
