package com.onepoint.bowling_game;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LineTest {
    private Line line_12_strikes;
    private Line line_20_rolls;
    private Line line_21_rolls;

    @BeforeAll()
    void setUpLine() {
        String strikes_12 = "x x x x x x x x x x x x";
        String rolls_20 = "9 9 9 9 9 9 9 9 9 9";
        String rolls_21 = "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5";

        List<Frame> frames_12 = Arrays.stream(strikes_12.split(" "))
                .map(x -> new Frame(10,0)).collect(Collectors.toList());

        List<Frame> frames_rolls_20 = Arrays.stream(rolls_20.split(" ")).map(x -> new Frame(9,0)).collect(Collectors.toList());


        List<Frame> frames_rolls_21= Arrays.stream(rolls_21.split(" ")).map(s -> {
            if(s.equals("5/"))
                return new Frame(5,5);
            else
                return new Frame(5,0);
        }).collect(Collectors.toList());

        line_12_strikes = new Line(frames_12);
        line_20_rolls = new Line(frames_rolls_20);
        line_21_rolls = new Line(frames_rolls_21);
    }

    @Test
    void getTotalScore() {
        assertEquals(300, line_12_strikes.getTotalScore());
        assertEquals(90,line_20_rolls.getTotalScore());
        assertEquals(150,line_21_rolls.getTotalScore());
    }

    /**
     * @apiNote index starts from 1, max is 10
     */
    @Test
    void getScoreAtFrame() {
        //index starts from 1
        assertEquals(90, line_12_strikes.getScoreAtFrame(3));
    }

    @Test
    void getFrames() {
        assertEquals(12, line_12_strikes.getFrames().size());
    }
}