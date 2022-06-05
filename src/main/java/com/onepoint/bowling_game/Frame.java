package com.onepoint.bowling_game;

import com.onepoint.bowling_game.enums.FrameType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class Frame {
    private final int firstRoll;
    private final int secondRoll;
    private final int score;
    private FrameType type;


    public Frame(int firstTry, int secondTry) {
        validateParameters(firstTry, secondTry);
        assignFrameType(firstTry, secondTry);
        this.firstRoll = firstTry;
        this.secondRoll = secondTry;
        score = firstTry + secondTry;
    }

    private void assignFrameType(int firstRoll, int secondRoll) {
        if (firstRoll == 10 && secondRoll == 0) {
            type = FrameType.STRIKE;
        } else if (firstRoll + secondRoll == 10) {
            type = FrameType.SPARE;
        } else
            type = FrameType.NORMAL;
    }

    private void validateParameters(int firstRoll, int secondRoll) {
        if (firstRoll < 0 || firstRoll > 10 || secondRoll < 0 || secondRoll > 10) {
            log.error("Invalid parameters for this frame, first Roll: {}, second Roll: {}", firstRoll, secondRoll);
            throw new IllegalArgumentException("Invalid score for this frame");
        }
        if (firstRoll + secondRoll > 10) {
            log.error("invalid score for this frame: the sum of the first roll and second roll is greater that 10");
            throw new IllegalArgumentException("Invalid score for this frame");
        }
    }

}
