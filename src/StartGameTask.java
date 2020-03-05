import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * task that start the game.
 */
public class StartGameTask implements Task<Void> {

    private AnimationRunner ar;
    private KeyboardSensor ks;
    private GUI gui;
    private Sleeper sleeper;
    private List<LevelInformation> levels;
    private HighScoresTable highScoresTable;
    private File file;
    private SubMenuAnimation subMenu;
    private List<LevelSet> levelSetList;

    /**
     * constructor.
     *
     * @param ar              the animation runner.
     * @param ks              the keyboard.
     * @param sleeper         is th sleeper for the game.
     * @param gui             is the gui.
     * @param highScoresTable the score table.
     * @param subMenu1        the sub menu 1
     * @param levelSetList1   the level set list 1
     */
    public StartGameTask(AnimationRunner ar, KeyboardSensor ks, Sleeper sleeper, GUI gui,
                         HighScoresTable highScoresTable, SubMenuAnimation subMenu1,
                         List<LevelSet> levelSetList1) {
        this.ar = ar;
        this.ks = ks;
        this.gui = gui;
        this.sleeper = sleeper;
        this.levels = levels;
        this.highScoresTable = highScoresTable;
        //  this.file = file;
        this.subMenu = subMenu1;
        this.levelSetList = levelSetList1;
    }

    /**
     * run - run the levels of the game.
     *
     * @return null.
     */
    public Void run() {
        for (int i = 0; i < this.levelSetList.size(); i++) {
            this.subMenu.addSelection(this.levelSetList.get(i).getKey(),
                    this.levelSetList.get(i).getMessege(), this.levelSetList.get(i).getPath());
        }
        ar.run(this.subMenu);
        String path = this.subMenu.getPath();
        LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
        try {
            InputStream ireader = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
            InputStreamReader reader = new InputStreamReader(ireader);
            levels = levelSpecificationReader.reader(reader);
        } catch (IOException e) {
            levels = null;
        }
        GameFlow gameFlow = new GameFlow(ar, ks, sleeper, gui, highScoresTable, this.file);
        gameFlow.runLevels(levels);
        return null;
    }

    /**
     * Sets file.
     *
     * @param file1 the file
     */
    public void setFile(File file1) {
        this.file = file1;
    }


}
