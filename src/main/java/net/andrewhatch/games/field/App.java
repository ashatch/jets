package net.andrewhatch.games.field;

public class App {
    public static void main(String[] args) throws Exception {
        new GameFactory()
            .createGame(App.class.getResourceAsStream("/example.field"))
            .play();
    }
}