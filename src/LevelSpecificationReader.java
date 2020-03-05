import javax.imageio.ImageIO;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.awt.Image;

/**
 * LevelSpecificationReader class.
 */
public class LevelSpecificationReader {
    private int ballAmount = 0;
    private int paddleSpeed = 0;
    private int paddleWidth = 0;
    private int blocksStartX = 0;
    private int blocksStartY = 0;
    private int numBlocks = 0;
    private int rowHeight = 0;
    private BackGround backGround = null;
    private String levelName = null;
    private List<Velocity> velocityList = new ArrayList<>();
    private String blockDefinition = null;
    private List<Block> blockList1 = new ArrayList<>();

    /**
     * Reader - read from the text the qualifies of a level and create
     * a list of level infomation that represent a set of levels.
     *
     * @param reader1 - the path.
     * @return - level information.
     * @throws IOException - not sucseed to read.
     */
    public List<LevelInformation> reader(java.io.Reader reader1) throws IOException {
        List<LevelInformation> levelInformationList = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(reader1);
        String line;
        List<String> blockSymbolList = new ArrayList<>();
        // while there is more lines to read.
        while ((line = bufferedReader.readLine()) != null) {
            // if in the start of the line there is "START_LEVEL".
            if (line.equals("START_LEVEL")) {
                // run till write in the text the words "START_BLOCKS".
                /*
                now in this section i check for the qualifies of the level in the text
                and read from the text that's qualifies.
               after im reading those qualifies im puting them in the right variables.
                 */
                while (!line.contains("START_BLOCKS")) {
                    String[] split = line.split(":");
                    if (split[0].equals("level_name")) {
                        this.setLevelName(split[1]);
                    }
                    if (split[0].equals("ball_velocities")) {
                        String[] splitVelocities = split[1].split(" ");
                        for (int i = 0; i < splitVelocities.length; i++) {
                            String[] splitXY = splitVelocities[i].split(",");
                            String x = splitXY[0];
                            double xInt = Double.parseDouble(x);
                            String y = splitXY[1];
                            double yInt = Double.parseDouble(y);
                            Velocity velocity = new Velocity(0, 0).fromAngleAndSpeed(xInt, yInt);
                            this.velocityList.add(velocity);
                        }
                        this.setBallAmount(this.getVelocityList().size());
                    }
                    if (split[0].equals("paddle_speed")) {
                        String paddleSpeed1 = split[1];
                        this.setPaddleSpeed(Integer.parseInt(paddleSpeed1));
                    }
                    if (split[0].equals("paddle_width")) {
                        String paddleWidth1 = split[1];
                        this.setPaddleWidth(Integer.parseInt(paddleWidth1));
                    }
                    if (split[0].equals("block_definitions")) {
                        this.setBlockDefinition(split[1]);
                    }
                    if (split[0].equals("blocks_start_x")) {
                        String blocksStartX1 = split[1];
                        this.setBlocksStartX(Integer.parseInt(blocksStartX1));
                    }
                    if (split[0].equals("blocks_start_y")) {
                        String blocksStartY1 = split[1];
                        this.setBlocksStartY(Integer.parseInt(blocksStartY1));
                    }
                    if (split[0].equals("num_blocks")) {
                        String numBlocks1 = split[1];
                        this.setNumBlocks(Integer.parseInt(numBlocks1));
                    }
                    if (split[0].equals("row_height")) {
                        String rowHeight1 = split[1];
                        this.setRowHeight(Integer.parseInt(rowHeight1));
                    }
                    if (split[0].equals("background")) {
                        Image image = null;
                        Color color = null;
                        if (split[1].contains("image")) {
                            image = imageSeter(split[1]);
                        } else if (split[1].contains("color")) {
                            ColorsParser colorsParser = new ColorsParser();
                            color = colorsParser.colorFromString(split[1]);
                        }
                        this.setBackGround(new BackGround(image, color, this.levelName));
                    }
                    line = bufferedReader.readLine();
                }

            }
            /*
            if in the there is "START_LEVEL".
            and run till the line contain the words "END_BLOCKS".
             */
            if (line.equals("START_BLOCKS")) {
                while (!line.contains("END_BLOCKS")) {
                    line = bufferedReader.readLine();
                    blockSymbolList.add(line);
                }
                blockSymbolList.remove(blockSymbolList.size() - 1);
            }
            /*
            if in the there is "START_LEVEL".
             */
            if (line.contains("END_BLOCKS")) {
                /*
                in this section im taking all the varibles i got from the text and i create a level information
                and i do that again and again. and i make a list from that.
                 */
                this.setBlockList1(this.getBlockList(getBlockDefinition(), blockSymbolList,
                        getBlocksStartX(), getBlocksStartY(), getRowHeight()));
                List<Block> blockList2 = new ArrayList<>();
                for (Block b1 : this.blockList1) {
                    blockList2.add(b1);
                }

                List<Velocity> velocityArrayList = new ArrayList<>();
                for (Velocity v1 : this.velocityList) {
                    velocityArrayList.add(v1);
                }

                LevelInformation levelInformation = new LevelSpecificationInfofmation(
                        velocityArrayList, getPaddleSpeed(), getPaddleWidth(),
                        getLevelName(), getBackGround(), getNumBlocks(), blockList2);
                int x = getBallAmount();
                levelInformation.setBallsNumber(x);
                levelInformationList.add(levelInformation);
                this.velocityList.removeAll(this.velocityList);
                this.blockList1.removeAll(this.blockList1);
                blockSymbolList.removeAll(blockSymbolList);

            }
        }
        return levelInformationList;
    }

    /**
     * imageSeter - load image.
     *
     * @param imageSpliterString - the path for the image.
     * @return null. image
     */
    public Image imageSeter(String imageSpliterString) {
        String afterString = imageSpliterString.substring(6, imageSpliterString.length() - 1);
        Image image = null;
        try {
            String imagePath = afterString;
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(imagePath);
            image = ImageIO.read(inputStream);
//            BufferedImage myPicture = ImageIO.read(new File(imagePath));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * getPaddleSpeed - getter.
     *
     * @return - paddleSpeed.
     */
    public int getPaddleSpeed() {
        return paddleSpeed;
    }

    /**
     * getVelocityList - getter.
     *
     * @return velocityList. velocity list
     */
    public List<Velocity> getVelocityList() {
        return velocityList;
    }

    /**
     * getBackGround - getter.
     *
     * @return backGround. back ground
     */
    public BackGround getBackGround() {
        return backGround;
    }

    /**
     * getBallAmount - getter.
     *
     * @return ballAmount. ball amount
     */
    public int getBallAmount() {
        return ballAmount;
    }

    /**
     * getBlocksStartX - getter.
     *
     * @return blocksStartX. blocks start x
     */
    public int getBlocksStartX() {
        return blocksStartX;
    }

    /**
     * getBlocksStartY - getter.
     *
     * @return blocksStartY. blocks start y
     */
    public int getBlocksStartY() {
        return blocksStartY;
    }

    /**
     * getNumBlocks - getter.
     *
     * @return numBlocks. num blocks
     */
    public int getNumBlocks() {
        return numBlocks;
    }

    /**
     * getPaddleWidth - getter.
     *
     * @return paddleWidth. paddle width
     */
    public int getPaddleWidth() {
        return paddleWidth;
    }

    /**
     * getRowHeight - getter.
     *
     * @return rowHeight. row height
     */
    public int getRowHeight() {
        return rowHeight;
    }

    /**
     * getLevelName - getter.
     *
     * @return levelName. level name
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * setBackGround - setter.
     *
     * @param backGround1 the backGround.
     */
    public void setBackGround(BackGround backGround1) {
        this.backGround = backGround1;
    }

    /**
     * getBlockDefinition - getter.
     *
     * @return the block definition
     */
    public String getBlockDefinition() {
        return blockDefinition;
    }

    /**
     * setBallAmount - setter.
     *
     * @param ballAmount1 the ball amount
     */
    public void setBallAmount(int ballAmount1) {
        this.ballAmount = ballAmount1;
    }

    /**
     * setBlocksStartX - setter.
     *
     * @param blocksStartX1 the blocks start x
     */
    public void setBlocksStartX(int blocksStartX1) {
        this.blocksStartX = blocksStartX1;
    }

    /**
     * setBlocksStartY - setter.
     *
     * @param blocksStartY1 the blocks start y
     */
    public void setBlocksStartY(int blocksStartY1) {
        this.blocksStartY = blocksStartY1;
    }

    /**
     * setLevelName - setter.
     *
     * @param levelName1 the level name
     */
    public void setLevelName(String levelName1) {
        this.levelName = levelName1;
    }

    /**
     * setNumBlocks - setter.
     *
     * @param numBlocks1 the num blocks
     */
    public void setNumBlocks(int numBlocks1) {
        this.numBlocks = numBlocks1;
    }

    /**
     * setPaddleSpeed - setter.
     *
     * @param paddleSpeed1 the paddle speed
     */
    public void setPaddleSpeed(int paddleSpeed1) {
        this.paddleSpeed = paddleSpeed1;
    }

    /**
     * setPaddleWidth - setter.
     *
     * @param paddleWidth1 the paddle width
     */
    public void setPaddleWidth(int paddleWidth1) {
        this.paddleWidth = paddleWidth1;
    }

    /**
     * setRowHeight - setter.
     *
     * @param rowHeight2 the rowHeight.
     */
    public void setRowHeight(int rowHeight2) {
        this.rowHeight = rowHeight2;
    }

    /**
     * setVelocityList - setter.
     *
     * @param velocityList1 the velocityList.
     */
    public void setVelocityList(List<Velocity> velocityList1) {
        this.velocityList = velocityList1;
    }

    /**
     * setBlockDefinition - setter.
     *
     * @param blockDefinition1 the blockDefinition.
     */
    public void setBlockDefinition(String blockDefinition1) {
        this.blockDefinition = blockDefinition1;
    }

    /**
     * getBlockList1 - getter.
     *
     * @return blockList1.
     */
    public List<Block> getBlockList1() {
        return blockList1;
    }

    /**
     * setBlockList1 - setter.
     *
     * @param blockList2 the block List.
     */
    public void setBlockList1(List<Block> blockList2) {
        this.blockList1 = blockList2;
    }

    /**
     * Gets block list.
     *
     * @param blockDef        the block def
     * @param blockSymbolList the block symbol list
     * @param statrtX         the statrt x
     * @param startY          the start y
     * @param rowHeight1      the row height
     * @return the block list
     */
    public List<Block> getBlockList(String blockDef, List<String> blockSymbolList,
                                    int statrtX, int startY, int rowHeight1) {
        List<Block> blockList = new ArrayList<>();
        BlocksDefinitionReader blocksDefinitionReader = new BlocksDefinitionReader();
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = null;
        try {
            InputStream ireader = ClassLoader.getSystemClassLoader().getResourceAsStream(blockDef);
            InputStreamReader reader = new InputStreamReader(ireader);
            blocksFromSymbolsFactory = blocksDefinitionReader.fromReader(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int xSaver = statrtX;
        int highBlock = 0;
        for (String line : blockSymbolList) {
            String[] strings = line.split("");
            for (int i = 0; i < strings.length; i++) {
                if (blocksFromSymbolsFactory.isBlockSymbol(strings[i])) {
                    Block block = blocksFromSymbolsFactory.getBlock(strings[i], statrtX, startY);
                    if (block.getRecangle().getWidth() + statrtX <= 780) {
                        blockList.add(block);
                        statrtX += block.getRecangle().getWidth();
                        if (highBlock < block.getRecangle().getHeight()) {
                            highBlock = (int) block.getRecangle().getHeight();
                        }
                    }
                } else if (blocksFromSymbolsFactory.isSpaceSymbol(strings[i])) {
                    statrtX = statrtX + blocksFromSymbolsFactory.getSpaceWidth(strings[i]);
                }
            }
            if (highBlock > rowHeight1) {
                startY = startY + highBlock;
            } else {
                startY = startY + rowHeight1;
            }
            statrtX = xSaver;
        }
        return blockList;
    }


}
