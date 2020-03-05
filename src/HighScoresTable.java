import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * HighScoresTable class.
 */
class HighScoresTable {
    private int sizeTable;
    private List<ScoreInfo> scoreInfos;

    /**
     * Instantiates a new High scores table.
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param size1 the size 1
     */
    HighScoresTable(int size1) {
        this.sizeTable = size1;
        this.scoreInfos = new ArrayList<>();
    }

    /**
     * Add.
     * Add a high-score.
     *
     * @param score the score
     */

    public void add(ScoreInfo score) {
        int place = getRank(score.getScore()) - 1;
        if (place > this.sizeTable) {
            return;

        }
        this.scoreInfos.add(place, score);
        if (this.size() > this.sizeTable) {
            this.scoreInfos.remove(this.scoreInfos.size() - 1);
        }
    }

    /**
     * Size int.
     * Return table size.
     *
     * @return the int
     */

    public int size() {
        return this.scoreInfos.size();
    }

    /**
     * Gets high scores.
     * Return the current high scores.
     * The list is sorted such that the highest
     * scores come first.
     *
     * @return the high scores
     */
    public List<ScoreInfo> getHighScores() {
        return this.scoreInfos;
    }

    /**
     * Gets rank.
     * return the rank of the current score: where will it
     * be on the list if added?
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not
     * be added to the list.
     *
     * @param score the score
     * @return the rank
     */

    public int getRank(int score) {
        if (this.scoreInfos.size() == 0) {
            return 1;
        }

        if ((this.scoreInfos.get(this.size() - 1).getScore() > score) && (this.sizeTable == this.size())) {
            return size() + 8;
        }
        for (int i = 0; i < size(); i++) {
            if (this.scoreInfos.get(i).getScore() < score) {
                return i + 1;
            }
        }
        return size() + 1;

    }

    /**
     * Clear.
     * Clears the table
     */

    public void clear() {
        this.scoreInfos.clear();
    }

    /**
     * load - Load table data from file.
     * Current table data is cleared.
     *
     * @param filename the file.
     * @throws IOException - throw exception if somthing wrong.
     */
    public void load(File filename) throws IOException {
        Scanner in = new Scanner(filename);
        while (in.hasNext()) {
            String string = in.nextLine();
            String score = in.nextLine();
            int score1 = Integer.parseInt(score);
            ScoreInfo scoreInfo1 = new ScoreInfo(string, score1);
            this.scoreInfos.add(scoreInfo1);
        }

    }

    /**
     * save - Save table data to the specified file.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
    public void save(File filename) throws IOException {
        PrintWriter printWriter = new PrintWriter(filename);
        for (int i = 0; i < this.scoreInfos.size(); i++) {
            printWriter.println(this.scoreInfos.get(i).getName());
            printWriter.println(this.scoreInfos.get(i).getScore());
        }
        printWriter.close();
    }

    /**
     * Load from file high scores table.
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename the filename
     * @return the high scores table
     */

    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable highScoresTable = new HighScoresTable(10);
        try {
            Scanner in = new Scanner(filename);
            while (in.hasNext()) {
                String string = in.nextLine();
                String score = in.nextLine();
                int score1 = Integer.parseInt(score);
                ScoreInfo scoreInfo1 = new ScoreInfo(string, score1);
                highScoresTable.add(scoreInfo1);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return highScoresTable;
    }

    /**
     * Gets size table.
     *
     * @return the size table
     */
    public int getSizeTable() {
        return sizeTable;
    }

}