import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * class that create a new block.
 */
public class CreatBlock implements BlockCreator {

    private double hight;
    private double width;
    private int countOfHit;
    private Map<Integer, String> fill;
    private java.awt.Color stroke;
    private Map<Integer, Color> mapColors = new HashMap<>();
    private Map<Integer, Image> mapImages = new HashMap<>();

    /**
     * constructor.
     *
     * @param hight      is the hight of the block.
     * @param width      is the width of the block.
     * @param countOfHit is the number of thr hits for the block.
     * @param fill       the fill to fill the block.
     * @param stroke     the frame for each block.
     */
    public CreatBlock(double hight, double width, int countOfHit,
                      Map<Integer, String> fill, java.awt.Color stroke) {
        this.hight = hight;
        this.width = width;
        this.countOfHit = countOfHit;
        this.fill = fill;
        this.stroke = stroke;
        for (int i = fill.size() - 1; i >= 0; i--) {
            Image img;
            // if its start with "image" load the image
            if (fill.get(i).startsWith("image")) {
                String imagePath = fill.get(i).trim();
                imagePath = imagePath.substring(6, fill.get(i).length() - 1);
                Image image = null;
                try {
//                    BufferedImage myPicture = ImageIO.read(new File(imagePath));
                    InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(imagePath);
                    image = ImageIO.read(inputStream);
                    this.mapImages.put(i, image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // if its start with "color" load the color
            } else if (fill.get(i).startsWith("color")) {
                ColorsParser colorsParser = new ColorsParser();
                Color color1 = colorsParser.colorFromString(fill.get(i));
                this.mapColors.put(i, color1);
            }
        }
    }

    /**
     * create - create a block.
     *
     * @param xpos - the x position.
     * @param ypos - the y position.
     * @return - a new block.
     */
    public Block create(int xpos, int ypos) {
        Block block = new Block(xpos, ypos, this.hight, this.width, this.countOfHit, this.mapColors, this.mapImages);
        block.setStroke(this.stroke);
        return block;
    }
}
