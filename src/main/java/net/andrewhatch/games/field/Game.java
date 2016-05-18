package net.andrewhatch.games.field;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private String name;
    private Map<String,Integer> points;
    private String[][] grid;
    private int score = 0;

    public Game(final String name,
                final Map<String, Integer> points,
                final String[][] grid) {
        this.name = name;
        this.points = points;
        this.grid = grid;
    }

    public void play() {
        final Scanner in = new Scanner(System.in);

        System.out.println("You're playing " + name + ".") ;

        while (true) {
            playRound(in);
        }
    }

    private void playRound(final Scanner in) {
        promptForMove();

        try {
            int x = in.nextInt();
            int y = in.nextInt();

            if (grid[x][y] != null) {
                final String treasure = grid[x][y];
                score += points.get(treasure);
                grid[x][y] = null;
                System.out.println("You found " + treasure + "! Your score is " + score + ".");
            } else {
                System.out.println("Sorry, nothing there!");
            }

        } catch (InputMismatchException ex) {
            System.out.println("That's not a valid move. Do something like '3 4'");
            in.next();
        }

    }

    private void promptForMove() {
        System.out.println("Where do you want to dig (enter x then y)?");
    }
}