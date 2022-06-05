package com.onepoint.bowling_game;

import com.onepoint.bowling_game.enums.FrameType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FrameTest {

    private Frame frame;

    @Test
    @DisplayName("it should throw an exception when one or both of the arguments are invalid")
    void shouldThrowExceptionWhenArgumentsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Frame(11, 0));
        assertThrows(IllegalArgumentException.class, () -> new Frame(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> new Frame(5, 6));
        assertDoesNotThrow(() -> new Frame(2, 2));
        assertDoesNotThrow(() -> new Frame(0, 10));
        assertDoesNotThrow(() -> new Frame(0, 0));
    }


    @Test
    void getFirstRoll() {
        frame = new Frame(10, 0);
        assertEquals(10, frame.getFirstRoll());
    }

    @Test
    void getSecondRoll() {
        frame = new Frame(0, 3);
        assertEquals(3, frame.getSecondRoll());
    }

    @Test
    void getScore() {
        frame = new Frame(5, 4);
        assertEquals(9, frame.getScore());

        frame = new Frame(5, 5);
        assertEquals(10, frame.getScore());

        frame = new Frame(0, 5);
        assertEquals(5, frame.getScore());


    }

    @Test
    void getType() {
        frame = new Frame(10, 0);
        assertEquals(FrameType.STRIKE, frame.getType());

        //it is only a strike when the shot got 10 in the first try and not the second, else it's a sapre
        frame = new Frame(0, 10);
        assertEquals(FrameType.SPARE, frame.getType());

        frame = new Frame(5, 5);
        assertEquals(FrameType.SPARE, frame.getType());

        frame = new Frame(3, 5);
        assertEquals(FrameType.NORMAL, frame.getType());

        frame = new Frame(0, 0);
        assertEquals(FrameType.NORMAL, frame.getType());

        frame = new Frame(2, 1);
        assertEquals(FrameType.NORMAL, frame.getType());

    }
}