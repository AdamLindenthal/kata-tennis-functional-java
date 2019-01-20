package cz.jjaros.dojos.game.tennis;

import cz.jjaros.dojos.game.Score;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Holds actual Tennis score for two players.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class TennisScore extends Score<TennisPlayerScore> {

    /**
     * Creates new {@link TennisScore Tennis score} for given partial player scores.
     * Should be used only inside this class.
     * For others is provided method {@link TennisScore#of(TennisPlayerScore, TennisPlayerScore)}.
     *
     * @param firstPlayerScore first {@link TennisPlayerScore player score}
     * @param secondPlayerScore second {@link TennisPlayerScore player score}
     * @see TennisScore#of(TennisPlayerScore, TennisPlayerScore)
     */
    private TennisScore(TennisPlayerScore firstPlayerScore, TennisPlayerScore secondPlayerScore) {
        super(firstPlayerScore, secondPlayerScore);
    }

    /**
     * Gets {@link TennisScore Tennis score} for given partial player scores.
     *
     * @param firstPlayerScore first {@link TennisPlayerScore player score}
     * @param secondPlayerScore second {@link TennisPlayerScore player score}
     * @return new immutable object of {@link TennisScore Tennis score}
     */
    public static TennisScore of(TennisPlayerScore firstPlayerScore, TennisPlayerScore secondPlayerScore) {
        return new TennisScore(firstPlayerScore, secondPlayerScore);
    }
}
