package cz.jjaros.dojos.game.exception;

import cz.jjaros.dojos.game.Score;
import lombok.Getter;

/**
 * Provides stop mechanism for the game which state is already at the end.
 */
@Getter
public class GameAlreadyEndException extends RuntimeException {

    private Score gameScore;

    /**
     * Creates new {@link GameAlreadyEndException exception}.
     *
     * @param message exception message
     * @param gameScore actual game {@link Score score}
     */
    public GameAlreadyEndException(String message, Score gameScore) {
        super(message);
        this.gameScore = gameScore;
    }
}
