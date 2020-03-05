import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Image;

/**
 * BlocksDefinitionReader class.
 */
public class BlocksDefinitionReader {
    /**
     * in this function im trying to read from a text file.
     * im reading from the text the qualifies of a block and the orders of the
     * new blocks in the level.
     * at first we read the default qualifies.
     * if we are not getting that qualifies from the "bdef" part im using the default qualifies.
     * after i finish read i make the propit varible from the text insruction.
     * from the "sdef" part i get the qualifies of the symbol that they are symbol who represent spaces.
     * after i get all the qualifies from the text im instering him to two maps.
     * the first maps: the key is symbol and the value is the spacers - width - length.
     * the secoend map: the key is symbol and the value is the block create by the qualifies of the text file.
     *
     * @param reader1 - the text.
     * @return - new BlocksFromSymbolsFactory withe the two maps..
     * @throws IOException - if cant read the txt.
     */
    public BlocksFromSymbolsFactory fromReader(java.io.Reader reader1) throws IOException {
        //define varible.
        BufferedReader br = null;
        br = new BufferedReader(reader1);
        Map<String, Integer> spacerWidths = new HashMap<>();
        Map<String, BlockCreator> blockCreators = new HashMap<>();
        Map<String, String> definitInfo = new HashMap<>();
        Map<Integer, String> fillMap = new HashMap<>();
        String line;
        line = br.readLine();
        String symbol = null;
        int hitPoints1 = 0;
        Image img = null;
        Color stroke = null;
        double height = 0;
        double width = 0;
        List<String> fillList = new ArrayList<>();
        // read the text while there is what to read.
        while (line != null) {
            // split the line bt " ".
            String[] split = line.split(" ");
            // if the first word in the line is default.
            if (split[0].equals("default")) {
                for (int i = 1; i < split.length; i++) {
                    // split again by ":" and put all the def
                    String[] afterSplit = split[i].split(":");
                    definitInfo.put(afterSplit[0], afterSplit[1]);
                    // if after the secoend split there is in the deafult line the word fill
                    // sending map with number(the hit) and the color.
                    if (afterSplit[0].contains("fill")) {
                        if (afterSplit[0].contains("fill-")) {
                            String[] split2 = afterSplit[0].split("-");
                            int place = Integer.parseInt(split2[1]) - 1;
                            fillMap.put(place, afterSplit[1]);
                        } else {
                            fillMap.put(0, afterSplit[1]);
                        }
                    }
                }
            }
            // if the first word in the line is "bdef".
            if (split[0].equals("bdef")) {
                String[] afterSplit;
                // loop that tun at all the line and check if the qualifies is in the line
                // and if it does put it in varible.
                for (int i = 1; i < split.length; i++) {
                    afterSplit = split[i].split(":");

                    if (afterSplit[0].equals("symbol")) {
                        symbol = afterSplit[1];
                    }
                    if (afterSplit[0].equals("hit_points")) {
                        hitPoints1 = Integer.parseInt(afterSplit[1]);
                    }
                    if (afterSplit[0].contains("fill")) {
                        if (afterSplit[0].contains("fill-")) {
                            String[] split2 = afterSplit[0].split("-");
                            int place = Integer.parseInt(split2[1]) - 1;
                            fillMap.put(place, afterSplit[1]);
                        } else {
                            fillMap.put(0, afterSplit[1]);
                        }
                    }
                    if (afterSplit[0].equals("stroke")) {
                        ColorsParser colorsParser = new ColorsParser();
                        stroke = colorsParser.colorFromString(split[1]);
                    }
                    if (afterSplit[0].equals("height")) {
                        height = Double.parseDouble(afterSplit[1]);
                    }
                    if (afterSplit[0].equals("width")) {
                        if (afterSplit[0].equals("width")) {
                            width = Double.parseDouble(afterSplit[1]);
                        }
                    }
                }
                // if after moving on all the line and there is not the qualify in the "bdef"
                // check in the deafult and if in the deafukt list have that qualify use it to the block.
                if (stroke == null && definitInfo.containsKey("stroke")) {
                    ColorsParser colorsParser = new ColorsParser();
                    stroke = colorsParser.colorFromString(definitInfo.get("stroke"));
                }
                if (height == 0 && definitInfo.containsKey("height")) {
                    height = Double.parseDouble(definitInfo.get("height"));
                }
                if (width == 0 && definitInfo.containsKey("width")) {
                    width = Double.parseDouble(definitInfo.get("width"));
                }
                if (hitPoints1 == 0 && definitInfo.containsKey("hit_points")) {
                    hitPoints1 = Integer.parseInt(definitInfo.get("hit_points"));
                }
                Map<Integer, String> fillMap2 = new HashMap<>();
                fillMap2.putAll(fillMap);
                // create new creatBlock.
                CreatBlock creatBlock = new CreatBlock(height, width, hitPoints1, fillMap2, stroke);
                // add the new createBloc to the map.
                blockCreators.put(symbol, creatBlock);
                fillMap.clear();
                fillMap2.clear();
                hitPoints1 = 0;
                width = 0;
                height = 0;
                stroke = null;
                symbol = null;
            }
            // if the first word in the line is "sdef".
            if (split[0].equals("sdef")) {
                String[] afterSlipt = split[1].split(":");
                String key = afterSlipt[1];
                String[] anotheAfterSlipt = split[2].split(":");
                String value = anotheAfterSlipt[1];
                spacerWidths.put(key, Integer.parseInt(value));
            }
            // read the next line in the text/
            line = br.readLine();
        }
        // send a new BlocksFromSymbolsFactory withe the two maps i creat.
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
        return blocksFromSymbolsFactory;

    }


}