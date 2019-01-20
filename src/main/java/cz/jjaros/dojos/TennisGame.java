package cz.jjaros.dojos;

import cz.jjaros.dojos.game.ScoringPlayer;
import cz.jjaros.dojos.game.exception.GameAlreadyEndException;
import cz.jjaros.dojos.game.tennis.TennisScore;
import cz.jjaros.dojos.gamefunction.GameStarter;
import cz.jjaros.dojos.gamefunction.tennis.TennisGameProvider;
import cz.jjaros.dojos.gamefunction.tennis.TennisGameScoreResolver;
import cz.jjaros.dojos.gamefunction.tennis.TennisPlayerScoreResolver;

import static cz.jjaros.dojos.game.tennis.TennisPlayerScore.ADVANTAGE;
import static cz.jjaros.dojos.game.tennis.TennisPlayerScore.FORTY;
import static cz.jjaros.dojos.game.tennis.TennisPlayerScore.WIN;
import static cz.jjaros.dojos.game.tennis.TennisPlayerScore.ZERO;

/**
 * Kata: Tennis
 * <br>
 * For more information about required behavior you can see:
 * <a href="http://codingdojo.org/kata/Tennis/">codingdojo.org/kata/Tennis/</a>
 */
public final class TennisGame {

    private static final GameStarter<TennisScore> gameStarter = () -> TennisScore.of(ZERO, ZERO);

    private static final TennisPlayerScoreResolver advantageToWinScoreResolver =
            (playerScore, opponentScore) -> opponentScore == FORTY ? playerScore : playerScore.getNextScore();

    private static final TennisPlayerScoreResolver advantageScoreResolver = (playerScore, opponentScore) -> playerScore == ADVANTAGE
            ? (opponentScore == ADVANTAGE ? FORTY : advantageToWinScoreResolver.resolve(playerScore, opponentScore))
            : playerScore;

    private static final TennisGameScoreResolver tennisGameScoreResolver = (firstScore, secondScore) -> TennisScore.of(
            advantageScoreResolver.resolve(firstScore, secondScore),
            advantageScoreResolver.resolve(secondScore, firstScore)
    );

    private static final TennisGameProvider tennisGame = (score, player) -> player == ScoringPlayer.FIRST
            ? tennisGameScoreResolver.resolve(score.getFirstPlayerScore().getNextScore(), score.getSecondPlayerScore())
            : tennisGameScoreResolver.resolve(score.getFirstPlayerScore(), score.getSecondPlayerScore().getNextScore());

    /**
     * Provides {@link TennisScore Tennis score} for new Tennis game.
     *
     * @return initial {@link TennisScore Tennis score}
     */
    public static TennisScore startGame() {
        return gameStarter.startGame();
    }

    /**
     * Play one tennis exchange.
     *
     * @param actualScore   actual {@link TennisScore Score}
     * @param scoringPlayer {@link ScoringPlayer scoring player}
     * @return {@link TennisScore Tennis score} after the one ball exchange
     * @throws GameAlreadyEndException
     */
    public static TennisScore play(TennisScore actualScore, ScoringPlayer scoringPlayer) {
        if (actualScore.getFirstPlayerScore() == WIN
                || actualScore.getSecondPlayerScore() == WIN) {
            throw new GameAlreadyEndException("The game is already in final state.", actualScore);
        }
        return tennisGame.play(actualScore, scoringPlayer);
    }

}