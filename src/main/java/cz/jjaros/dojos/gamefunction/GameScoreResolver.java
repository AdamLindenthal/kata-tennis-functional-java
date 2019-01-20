package cz.jjaros.dojos.gamefunction;

/**
 * Functional interface that provides method for resolving the game score.
 */
@FunctionalInterface
public interface GameScoreResolver<T, R> {

    /**
     * Resolves the game {@link R score} by given {@link T partial score states}.
     *
     * @param firstScore {@link T score state} of first player
     * @param secondScore {@link T score state} of second player
     * @return the resolved game {@link R score}
     */
    R resolve(T firstScore, T secondScore);
}