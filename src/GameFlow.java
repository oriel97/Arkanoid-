import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import biuoop.GUI;

/**
 * GameFlow class.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter liveCounter;
    private Counter scoreCounter;
    private GUI gui;
    private Sleeper sleeper;
    private HighScoresTable highScoresTable;
    private File file;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar               the ar
     * @param ks               the ks
     * @param sleeper1         the sleeper 1
     * @param gui1             the gui 1
     * @param highScoresTable1 the high scores table 1
     * @param file1            the file 1
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, Sleeper sleeper1, GUI gui1,
                    HighScoresTable highScoresTable1, File file1) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.liveCounter = new Counter(7);
        this.scoreCounter = new Counter(0);
        this.gui = gui1;
        this.sleeper = sleeper1;
        this.highScoresTable = highScoresTable1;
        this.file = file1;
    }

    /**
     * runLevels -  initialize the game and run it by sending to play one turn.
     *
     * @param levels - the list of levels that the run method run.
     */
    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {
            // create new game level
            GameLevel level = new GameLevel(levelInfo, this.gui, this.animationRunner,
                    this.keyboardSensor, this.liveCounter, this.scoreCounter, this.sleeper);
            // initialize the game , create the ball paddle listeners..
            level.initialize();
            // while there is block and lives run the game.
            while (level.getRemover().getRemainingBlocks().getValue() > 0 && this.getLiveCounter().getValue() > 0) {
                level.playOneTurn();

            }
            // if there is no lives.
            if (level.getCountLive().getValue() == 0) {
                break;
            }

        }
        // if there is not live - that mean the player lost.
        if (this.liveCounter.getValue() == 0) {
            this.animationRunner.run(new Lose(this.keyboardSensor, this.scoreCounter));
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            this.highScoresTable.add(new ScoreInfo(name, this.scoreCounter.getValue()));
            this.animationRunner.run(new HighScoresAnimation(this.highScoresTable, this.keyboardSensor));
            // if the player won the game - finished all the blocks and levels.
        } else {
            this.animationRunner.run(new Win(this.keyboardSensor, this.scoreCounter));
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            this.highScoresTable.add(new ScoreInfo(name, this.scoreCounter.getValue()));
            this.animationRunner.run(new HighScoresAnimation(this.highScoresTable, this.keyboardSensor));
        }
        try {
            highScoresTable.save(file);
        } catch (IOException ex) {
            System.out.println(ex);
        }
//        this.gui.close();
    }

    /**
     * getLiveCounter - getter.
     *
     * @return - the live counter.
     */
    public Counter getLiveCounter() {
        return liveCounter;
    }
}