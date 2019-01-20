package cz.jjaros.dojos.game.tennis;

/**
 * Represents possible points for Tennis score.
 */
public enum TennisPlayerScore {
    WIN("WIN", null),
    ADVANTAGE("A", WIN),
    FORTY("40", ADVANTAGE),
    THIRTY("30", FORTY),
    FIFTEEN("15", THIRTY),
    ZERO("0", FIFTEEN);

    private String stringScore;
    private TennisPlayerScore nextScore;

    TennisPlayerScore(String stringScore, TennisPlayerScore nextScore) {
        this.stringScore = stringScore;
        this.nextScore = nextScore;
    }

    /**
     * Gets next possible {@link TennisPlayerScore score} value.
     *
     * @return next possible {@link TennisPlayerScore}
     */
    public TennisPlayerScore getNextScore() {
        return nextScore;
    }

    @Override
    public String toString() {
        return stringScore;
    }
}
