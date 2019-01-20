package cz.jjaros.dojos;

import cz.jjaros.dojos.arguments.TennisGameArguments;
import cz.jjaros.dojos.game.Score;
import cz.jjaros.dojos.game.ScoringPlayer;
import cz.jjaros.dojos.game.exception.GameAlreadyEndException;
import cz.jjaros.dojos.game.tennis.TennisScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static cz.jjaros.dojos.game.ScoringPlayer.FIRST;
import static cz.jjaros.dojos.game.ScoringPlayer.SECOND;
import static cz.jjaros.dojos.game.tennis.TennisPlayerScore.ADVANTAGE;
import static cz.jjaros.dojos.game.tennis.TennisPlayerScore.FIFTEEN;
import static cz.jjaros.dojos.game.tennis.TennisPlayerScore.FORTY;
import static cz.jjaros.dojos.game.tennis.TennisPlayerScore.THIRTY;
import static cz.jjaros.dojos.game.tennis.TennisPlayerScore.WIN;
import static cz.jjaros.dojos.game.tennis.TennisPlayerScore.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for main functionality of Tennis game.
 * <br>
 * For more information about required behavior you can see:
 * <a href="http://codingdojo.org/kata/Tennis/">codingdojo.org/kata/Tennis/</a>
 */
@DisplayName("Kata: Tennis")
class TennisGameTest {

    @Test
    @DisplayName("Test score after the game is started.")
    void testStartTennisGame() {
        Score newGame = TennisGame.startGame();

        assertEquals(ZERO, newGame.getFirstPlayerScore());
        assertEquals(ZERO, newGame.getSecondPlayerScore());
    }

    @ParameterizedTest(name = "{0} won {1} to {2}")
    @ArgumentsSource(ScoreAfterFirstBallExchangeData.class)
    @ArgumentsSource(ScoreAfterSecondBallExchangeData.class)
    @ArgumentsSource(ScoreAfterThirdBallExchangeData.class)
    @DisplayName("Basic Tennis game without deuce and advantage.")
    void testTennisGameWithoutDeuce(TennisScore startingScore, ScoringPlayer scoringPlayer, TennisScore expectedScore) {
        assertNotEquals(ADVANTAGE, startingScore.getFirstPlayerScore(), "Advantage is testes in other test.");
        assertNotEquals(ADVANTAGE, startingScore.getSecondPlayerScore(), "Advantage is testes in other test.");

        assertEquals(expectedScore, TennisGame.play(startingScore, scoringPlayer));
    }

    @ParameterizedTest(name = "{0} won {1} to {2}")
    @ArgumentsSource(FinalScoreWithoutDeuceData.class)
    @DisplayName("Tennis game that is ended without any deuce and advantage.")
    void testWinTennisGameWithoutDeuce(TennisScore startingScore, ScoringPlayer scoringPlayer, TennisScore expectedScore) {
        assertNotEquals(ADVANTAGE, startingScore.getFirstPlayerScore(), "Advantage is testes in other test.");
        assertNotEquals(ADVANTAGE, startingScore.getSecondPlayerScore(), "Advantage is testes in other test.");
        assertTrue(startingScore.getFirstPlayerScore() == FORTY || startingScore.getSecondPlayerScore() == FORTY,
                "At least one state of starting score must be " + FORTY.name());

        assertEquals(expectedScore, TennisGame.play(startingScore, scoringPlayer));
    }

    @ParameterizedTest(name = "{0} won {1} to {2}")
    @ArgumentsSource(DeuceTennisGameLoseAdvantageData.class)
    @DisplayName("Tennis game deuce that isn't ended and one of players get and lose advantage.")
    void testTennisGameDeuceLoseAdvantage(TennisScore startingScore, ScoringPlayer scoringPlayer, TennisScore expectedScore) {
        Score gameScore = TennisGame.play(startingScore, scoringPlayer);

        assertEquals(expectedScore, gameScore);
        assertNotEquals(WIN, gameScore.getFirstPlayerScore());
        assertNotEquals(WIN, gameScore.getSecondPlayerScore());
    }

    @ParameterizedTest(name = "{0} won {1} to {2}")
    @ArgumentsSource(FinalScoreWithDeuceAdvantageData.class)
    @DisplayName("Tennis game that is ended after one of players had advantage.")
    void testWinTennisGameWithAdvantage(TennisScore startingScore, ScoringPlayer scoringPlayer, TennisScore expectedScore) {
        assertTrue(startingScore.getFirstPlayerScore() == ADVANTAGE || startingScore.getSecondPlayerScore() == ADVANTAGE,
                "Expected at least of players with score advantage.");

        assertEquals(expectedScore, TennisGame.play(startingScore, scoringPlayer));
    }

    @ParameterizedTest(name = "{0} won {1} -> illegal state")
    @ArgumentsSource(GameAlreadyEndData.class)
    @DisplayName("Play already ended game shouldn't be available.")
    void testPlayAlreadyEndedGame(TennisScore startingScore, ScoringPlayer scoringPlayer) {
        GameAlreadyEndException gameAlreadyEndException =
                assertThrows(GameAlreadyEndException.class, () -> TennisGame.play(startingScore, scoringPlayer));

        assertNotNull(gameAlreadyEndException.getMessage(), "Exception message is expected.");
        assertEquals(startingScore, gameAlreadyEndException.getGameScore());
    }

    /**
     * Provides source data for testing {@link TennisGame Tennis game} <b>after the first ball exchange</b>.
     */
    static class ScoreAfterFirstBallExchangeData implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(TennisGame.startGame(), FIRST, TennisScore.of(FIFTEEN, ZERO)),
                    Arguments.of(TennisGame.startGame(), SECOND, TennisScore.of(ZERO, FIFTEEN))
            );
        }
    }

    /**
     * Provides source data for testing {@link TennisGame Tennis game} <b>after the second ball exchange</b>.
     */
    static class ScoreAfterSecondBallExchangeData implements ArgumentsProvider {
        @Override
        public Stream<TennisGameArguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    TennisGameArguments.of(FIFTEEN, ZERO, FIRST, THIRTY, ZERO),
                    TennisGameArguments.of(FIFTEEN, ZERO, SECOND, FIFTEEN, FIFTEEN),
                    TennisGameArguments.of(ZERO, FIFTEEN, FIRST, FIFTEEN, FIFTEEN),
                    TennisGameArguments.of(ZERO, FIFTEEN, SECOND, ZERO, THIRTY)
            );
        }
    }

    /**
     * Provides source data for testing {@link TennisGame Tennis game} <b>after the third ball exchange</b>.
     */
    static class ScoreAfterThirdBallExchangeData implements ArgumentsProvider {
        @Override
        public Stream<TennisGameArguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    TennisGameArguments.of(THIRTY, ZERO, FIRST, FORTY, ZERO),
                    TennisGameArguments.of(THIRTY, ZERO, SECOND, THIRTY, FIFTEEN),
                    TennisGameArguments.of(FIFTEEN, FIFTEEN, FIRST, THIRTY, FIFTEEN),
                    TennisGameArguments.of(FIFTEEN, FIFTEEN, SECOND, FIFTEEN, THIRTY),
                    TennisGameArguments.of(ZERO, THIRTY, FIRST, FIFTEEN, THIRTY),
                    TennisGameArguments.of(ZERO, THIRTY, SECOND, ZERO, FORTY)
            );
        }
    }

    /**
     * Provides source data for testing {@link TennisGame Tennis game} that one of the players won without deuce or advantage.
     */
    static class FinalScoreWithoutDeuceData implements ArgumentsProvider {
        @Override
        public Stream<TennisGameArguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    TennisGameArguments.of(FORTY, ZERO, FIRST, WIN, ZERO),
                    TennisGameArguments.of(ZERO, FORTY, SECOND, ZERO, WIN),
                    TennisGameArguments.of(FORTY, FIFTEEN, FIRST, WIN, FIFTEEN),
                    TennisGameArguments.of(FIFTEEN, FORTY, SECOND, FIFTEEN, WIN),
                    TennisGameArguments.of(FORTY, THIRTY, FIRST, WIN, THIRTY),
                    TennisGameArguments.of(THIRTY, FORTY, SECOND, THIRTY, WIN)
            );
        }
    }

    /**
     * Provides source data for testing {@link TennisGame Tennis game} that one of the players loses his advantage.
     */
    static class DeuceTennisGameLoseAdvantageData implements ArgumentsProvider {
        @Override
        public Stream<TennisGameArguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    TennisGameArguments.of(FORTY, FORTY, FIRST, ADVANTAGE, FORTY),
                    TennisGameArguments.of(FORTY, FORTY, SECOND, FORTY, ADVANTAGE),
                    TennisGameArguments.of(FORTY, ADVANTAGE, FIRST, FORTY, FORTY),
                    TennisGameArguments.of(ADVANTAGE, FORTY, SECOND, FORTY, FORTY)
            );
        }
    }

    /**
     * Provides source data for testing {@link TennisGame Tennis game} that one of the players has advantage and wins the game.
     */
    static class FinalScoreWithDeuceAdvantageData implements ArgumentsProvider {
        @Override
        public Stream<TennisGameArguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    TennisGameArguments.of(ADVANTAGE, FORTY, FIRST, WIN, FORTY),
                    TennisGameArguments.of(FORTY, ADVANTAGE, SECOND, FORTY, WIN)
            );
        }
    }

    /**
     * Provides source data for testing {@link TennisGame Tennis game} that is already in final state.
     */
    static class GameAlreadyEndData implements ArgumentsProvider {
        @Override
        public Stream<TennisGameArguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    // expected score isn't relevant in these cases
                    TennisGameArguments.of(WIN, FIFTEEN, SECOND, ZERO, ZERO),
                    TennisGameArguments.of(FIFTEEN, WIN, FIRST, ZERO, ZERO)
            );
        }
    }
}
