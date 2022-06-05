package com.onepoint.bowling_game;

import com.onepoint.bowling_game.util.FrameBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Hassan EL MZABI
 * @since 6/5/2022
 */
@SpringBootApplication
public class BowlingGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(BowlingGameApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            String case1 = "x x x x x x x x x x x x";
            String case2 = "9- 9- 9- 9- 9- 9- 9- 9- 9- 9-";
            String case3 = "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5";

            Line line1 = new Line(FrameBuilder.buildFromString(case1));
            System.out.printf("Total score for line 1 is %d", line1.getTotalScore());

            Line line2 = new Line(FrameBuilder.buildFromString(case2));
            System.out.printf("\r\nTotal score for line 2 is %d", line2.getTotalScore());

            Line line3 = new Line(FrameBuilder.buildFromString(case3));
            System.out.printf("\r\nTotal score for line 3 is %d", line3.getTotalScore());
        };
    }
}
