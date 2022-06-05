package com.onepoint.bowling_game.util;

import com.onepoint.bowling_game.Frame;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FrameBuilder {
    public static List<Frame> buildFromString(String string) {
        if (string == null || !string.contains(" ")) {
            throw new IllegalArgumentException("invalid string to build frames");
        }

        int length = string.split(" ").length;
        if (length < 10 || length > 12) {
            throw new IllegalArgumentException("invalid number of frames generated from string");
        }

        return Arrays.stream(string.split(" ")).map(
                s -> {
                    if (s.equals("x")) {
                        return new Frame(10, 0);
                    } else if (s.contains("/")) {
                        if (s.length() > 2 || s.charAt(0) == '/' || isNotNumeric(s.charAt(0) + "")) {
                            throw new IllegalArgumentException("invalid character for a spare, valid character should be like: '3/', but found:" + s);
                        } else {
                            return new Frame(Integer.parseInt(s.charAt(0) + ""), 10 - Integer.parseInt(s.charAt(0) + ""));
                        }

                    } else if (s.contains("-")) {
                        if (s.length() > 2) {
                            throw new IllegalArgumentException("invalid character for a normal frame:" + s);
                        }

                        if (s.charAt(0) != '-' && isNotNumeric(s.charAt(0) + "")) {
                            throw new IllegalArgumentException("invalid character for a normal frame:" + s);
                        }
                        if (s.charAt(1) != '-' && isNotNumeric(s.charAt(0) + "")) {
                            throw new IllegalArgumentException("invalid character for a normal frame:" + s);
                        }

                        int firstCharacter = s.charAt(0) == '-' ? 0 : Integer.parseInt(s.charAt(0) + "");
                        int secondCharacter = s.charAt(1) == '-' ? 0 : Integer.parseInt(s.charAt(1) + "");

                        return new Frame(firstCharacter, secondCharacter);
                    } else {
                        if (isNotNumeric(s) || s.length() > 2 || s.length() < 1) {
                            throw new IllegalArgumentException("invalid character for normal frame: " + s);
                        }
                        if (s.length() == 2)
                            return new Frame(Integer.parseInt(s.charAt(0) + ""), Integer.parseInt(s.charAt(1) + ""));
                        else
                            return new Frame(Integer.parseInt(s.charAt(0) + ""), 0);

                    }
                }
        ).collect(Collectors.toList());


    }


    private static boolean isNotNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return true;
        }
        return false;
    }
}
