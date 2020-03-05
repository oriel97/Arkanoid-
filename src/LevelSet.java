/**
 * set the level.
 */
public class LevelSet {
    private String key;
    private String messege;
    private String path;

    /**
     * set the messege.
     *
     * @param messege1 the messege.
     */
    public void setMessege(String messege1) {
        this.messege = messege1;
    }

    /**
     * set the key.
     *
     * @param key1 the key.
     */
    public void setKey(String key1) {
        this.key = key1;
    }

    /**
     * set the path.
     *
     * @param path1 the path.
     */
    public void setPath(String path1) {
        this.path = path1;
    }

    /**
     * get the messege.
     *
     * @return the messege.
     */
    public String getMessege() {
        return this.messege;
    }

    /**
     * get the path.
     *
     * @return the path.
     */
    public String getPath() {
        return this.path;
    }

    /**
     * get the key.
     *
     * @return the key.
     */
    public String getKey() {
        return this.key;
    }
}
