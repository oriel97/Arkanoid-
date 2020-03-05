/**
 * ScoreInfo class.
 */
public class ScoreInfo {
    private String name1;
    private int score1;

    /**
     * ScoreInfo - constructor.
     *
     * @param name  - the name.
     * @param score - the score
     */
    public ScoreInfo(String name, int score) {
        this.name1 = name;
        this.score1 = score;
    }

    /**
     * getName - getter.
     *
     * @return name1.
     */
    public String getName() {
        return this.name1;
    }

    /**
     * getScore - getter.
     *
     * @return score1.
     */
    public int getScore() {
        return this.score1;
    }
}