package lotto;

import java.util.Scanner;

public class Application {
    public static Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        Player player = new Player();
        player.playLotto();
    }
}
