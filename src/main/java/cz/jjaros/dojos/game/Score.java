package cz.jjaros.dojos.game;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static lombok.AccessLevel.PROTECTED;

/**
 * Holds actual game score for two players.
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = PROTECTED)
public class Score<T> {

    private T firstPlayerScore;
    private T secondPlayerScore;

    @Override
    public String toString() {
        return firstPlayerScore + ":" + secondPlayerScore;
    }
}

