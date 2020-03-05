import java.io.File;

/**
 * Menu interface.
 *
 * @param <T> - <Task<Void>>
 */
public interface Menu<T> extends Animation {
    /**
     * addSelection.
     *
     * @param key       - the key.
     * @param message   - the messege.
     * @param returnVal - the value.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * getStatus.
     *
     * @return status. status
     */
    T getStatus();

    /**
     * Sets file.
     *
     * @param file the file
     */
    void setFile(File file);
}