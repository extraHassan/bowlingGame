package com.onepoint.bowling_game;

import com.onepoint.bowling_game.enums.FrameType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Slf4j
public class Line {
    private int totalScore;
    private final List<Frame> frames;

    public Line(List<Frame> frames) {
        this.frames = frames;

        if (!validSequenceOfFrames()) {
            throw new IllegalArgumentException("Invalid number of frames");
        }
    }

    /**
     * @return total score of the line
     */
    public int getTotalScore() {
        for (int i = 0; i < 10; i++) {
            totalScore = getScore(totalScore, i);
        }
        return totalScore;
    }

    /**
     * @param index start from 1 to 10
     * @return total score at the frame's index specified
     */
    public int getScoreAtFrame(int index) {
        int score = 0;
        if (index < 1 || index > 10)
            throw new IllegalArgumentException("invalid index " + index + ": index should be between  1 and 10");
        for (int i = 0; i < index; i++) {
            score = getScore(score, i);
        }
        return score;
    }

    private int getScore(int score, int i) {
        switch (frames.get(i).getType()) {
            case NORMAL:
                score += frames.get(i).getScore();
                break;
            case SPARE:
                score += frames.get(i).getScore() + frames.get(i + 1).getFirstRoll();
                break;
            case STRIKE:
                if (frames.get(i + 1).getType() == FrameType.NORMAL || frames.get(i + 1).getType() == FrameType.SPARE) {
                    score += frames.get(i).getScore() + frames.get(i + 1).getScore();
                } else {
                    score += frames.get(i).getScore() + frames.get(i + 1).getScore();
                    if (frames.get(i + 2).getType() == FrameType.NORMAL || frames.get(i + 2).getType() == FrameType.SPARE)
                        score += frames.get(i + 2).getFirstRoll();
                    else
                        score += frames.get(i + 2).getScore();

                }
                break;
            default:
        }
        return score;
    }

    private boolean validSequenceOfFrames() {
        if (frames.size() < 10 || frames.size() > 12) {
            log.error("total number of frames should be between 10 and 12, found {}", frames.size());
            return false;
        }

        Frame frameNine = frames.get(8);
        Frame frameTen = frames.get(9);

        if (frames.size() == 11) {
            if (frameNine.getType() == FrameType.STRIKE || frameTen.getType() == FrameType.SPARE) {
                return true;
            } else {
                log.error(
                        "invalid number of frames {}:" +
                                " neither the tenth frame or the ninth frame are spare or strike respectively"
                        , frames.size()
                );
                return false;
            }
        }

        if (frames.size() == 12) {
            if (frameTen.getType() == FrameType.STRIKE) {
                return true;
            } else {
                log.error("invalid number of frames {}:the last frame is not a strike", frames.size());
                return false;
            }
        }

        if (frameNine.getType() != FrameType.STRIKE && frameTen.getType() == FrameType.NORMAL) {
            return true;
        } else {
            log.error("invalid number of frames {}:" +
                            " the ninth frame should not be a strike and the last frame should be normal",
                    frames.size()
            );
            return false;
        }


    }
}
