import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * read the level set.
 */
public class LevelSetsReader {
    /**
     * read the set of the level.
     *
     * @param reader the file.
     * @return list of level set.
     */
    public  List<LevelSet> fromReader(java.io.Reader reader) {
        List<LevelSet> setList = new ArrayList<>();
        String tempLine;
        String[] afterSplit;
        LineNumberReader lineNumberReader = null;
        try {
            lineNumberReader = new LineNumberReader(reader);
            tempLine = lineNumberReader.readLine();
            LevelSet levelSet = new LevelSet();
            while (tempLine != null) {
                if (lineNumberReader.getLineNumber() % 2 == 0) {
                    levelSet.setPath(tempLine);
                    setList.add(levelSet);
                    levelSet = new LevelSet();
                } else {
                    afterSplit = tempLine.split(":");
                    levelSet.setKey(afterSplit[0]);
                    levelSet.setMessege(afterSplit[1]);
                }
                tempLine = lineNumberReader.readLine();
            }
            return setList;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (lineNumberReader != null) {
                try {
                    lineNumberReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
