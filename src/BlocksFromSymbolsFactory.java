import java.util.Map;

/**
 * BlocksFromSymbolsFactory class.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * BlocksFromSymbolsFactory - constructor.
     *
     * @param spacerWidths1  - the spaces map.
     * @param blockCreators2 - the blocks creator's class.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths1, Map<String, BlockCreator> blockCreators2) {
        this.spacerWidths = spacerWidths1;
        this.blockCreators = blockCreators2;
    }

    /**
     * isSpaceSymbol - returns true if 's' is a valid space symbol.
     *
     * @param s - the string - the symbol.
     * @return - true or false depend if the key is in the map.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * isBlockSymbol -returns true if 's' is a valid block symbol.
     *
     * @param s - the symbol of the block.
     * @return - true or false depend if the key is in the map.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * getBlock - Return a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos).
     *
     * @param s    - the key in the map.
     * @param xpos - the x position of the block.
     * @param ypos - the y position of the block.
     * @return - a block.
     */

    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * getSpaceWidth - Returns the width in pixels associated with the given spacer-symbol.
     *
     * @param s - the key.
     * @return - the width that the spacer give.
     */

    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}