package cz.jjaros.dojos.gamefunction;

import cz.jjaros.dojos.game.Score;

/**
 * Functional interface that provides method for starting new game.
 */
@FunctionalInterface
public interface GameStarter<T extends Score> {

    /**
     * Starts new game and returns starting {@link T}.
     *
     * @return starting {@link T score}
     */
    T startGame();
}
