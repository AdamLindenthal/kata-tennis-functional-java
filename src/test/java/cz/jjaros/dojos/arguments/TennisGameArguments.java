package cz.jjaros.dojos.arguments;

import cz.jjaros.dojos.game.ScoringPlayer;
import cz.jjaros.dojos.game.tennis.TennisPlayerScore;
import cz.jjaros.dojos.game.tennis.TennisScore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.junit.jupiter.params.provider.Arguments;

/**
 * Encapsulates all required input arguments for tests in TennisGameTest.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TennisGameArguments implements Arguments {

    private TennisScore startingScore;
    private ScoringPlayer scoringPlayer;
    private TennisScore expectedScore;

    @Override
    public Object[] get() {
        return new Object[]{startingScore, scoringPlayer, expectedScore};
    }

    /**
     * Creates new set of {@link TennisGameArguments Tennis game arguments} for one test case.
     *
     * @param playerOneStartingScore starting {@link TennisPlayerScore score} of first player for one test case
     * @param playerTwoStartingScore starting {@link TennisPlayerScore score} of second player for one test case
     * @param scoringPlayer {@link ScoringPlayer scoring player} in current test case
     * @param playerOneExpectedScore expected {@link TennisPlayerScore score} of first player for one test case
     * @param playerTwoExpectedScore expected {@link TennisPlayerScore score} of second player for one test case
     * @return new {@link TennisGameArguments Tennis game arguments} represents one test case
     */
    public static TennisGameArguments of(TennisPlayerScore playerOneStartingScore, TennisPlayerScore playerTwoStartingScore,
                                         ScoringPlayer scoringPlayer,
                                         TennisPlayerScore playerOneExpectedScore, TennisPlayerScore playerTwoExpectedScore) {
        return new TennisGameArguments(
                TennisScore.of(playerOneStartingScore, playerTwoStartingScore),
                scoringPlayer,
                TennisScore.of(playerOneExpectedScore, playerTwoExpectedScore)
        );
    }

}
