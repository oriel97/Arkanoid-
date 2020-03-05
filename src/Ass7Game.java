import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Ass5Game class.
 * the class Create a game object, initializes and runs it.
 * this class make the game running.
 */
public class Ass7Game {
    /**
     * The main function of the "game".
     *
     * @param args - not using.
     */
    public static void main(String[] args) {
        Sleeper sleeper = new biuoop.Sleeper();
        GUI gui = new GUI("Arkanoid", 800, 600);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        AnimationRunner animationRunner = new AnimationRunner(gui, sleeper);
        File file = new File("highscores");
        HighScoresTable highScoresTable = null;
        if (!file.exists()) {
            highScoresTable = new HighScoresTable(10);
            try {
                highScoresTable.save(file);
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            try {
                highScoresTable = HighScoresTable.loadFromFile(file);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        String path = "level_sets.txt";
        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor, sleeper, gui, highScoresTable, file);
        List<LevelInformation> levelInformationList = new ArrayList<>();
        List<LevelSet> levelSets = null;
        try {
            if (args.length == 0) {
                InputStream ireader = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
                InputStreamReader reader = new InputStreamReader(ireader);
                LevelSetsReader levelSetsReader = new LevelSetsReader();
                levelSets = levelSetsReader.fromReader(reader);
            } else if (args.length == 1) {
                InputStream ireader = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
                InputStreamReader reader = new InputStreamReader(ireader);
                LevelSetsReader levelSetsReader = new LevelSetsReader();
                levelSets = levelSetsReader.fromReader(reader);
            }
        } catch (Exception e) {
            System.out.println("cant found the file!");
        }

        Task<Void> quit = new Task<Void>() {
            @Override
            public Void run() {
                System.exit(0);
                return null;
            }
        };


        Animation animation1 = new HighScoresAnimation(highScoresTable, keyboardSensor);

        SubMenuAnimation subMenuAnimation = new SubMenuAnimation(keyboardSensor, "level set");
        StartGameTask startGameTask = new StartGameTask(animationRunner, keyboardSensor,
                sleeper, gui, highScoresTable, subMenuAnimation, levelSets);
        // run the game.
        Menu<Task<Void>> subMenu = new SubMenuAnimation<>(keyboardSensor, "level set");
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(keyboardSensor, "Arkanoid");
        menu.addSelection("s", "start game", startGameTask);
        startGameTask.setFile(file);
        menu.addSelection("h", "High scores", new ShowHiScoresTask(animationRunner, animation1));
        menu.addSelection("q", "Quit", quit);


        while (true) {
            animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            try {
                task.run();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                gui.close();
                return;
            }

        }
    }
}