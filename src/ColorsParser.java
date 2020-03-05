import java.awt.Color;
import java.util.Map;
import java.util.TreeMap;

/**
 * class that change the string to color.
 */
public class ColorsParser {
    /**
     * convert to color from string.
     * @param s is the color as a string.
     * @return Color.
     */
    public java.awt.Color colorFromString(String s) {
        Map<String, Color> map = new TreeMap<String, Color>();
        map.put("yellow", Color.yellow);
        map.put("red", Color.red);
        map.put("black", Color.black);
        map.put("blue", Color.blue);
        map.put("cyan", Color.cyan);
        map.put("gray", Color.gray);
        map.put("lightGray", Color.lightGray);
        map.put("green", Color.green);
        map.put("orange", Color.orange);
        map.put("pink", Color.pink);
        map.put("white", Color.white);

        if (s.startsWith("color(RGB")) {
            s = s.substring(10, s.length() - 2);
            String[] strColor = s.split(",");
            int[] intColor = new int[strColor.length];
            if (strColor.length > 3) {
                return null;
            }
            for (int i = 0; i < strColor.length; i++) {
                intColor[i] = Integer.parseInt(strColor[i]);
            }
            return new Color(intColor[0], intColor[1], intColor[2]);
        } else {
            for (String key : map.keySet()) {
                if (s.contains(key)) {
                    return map.get(key);
                }
            }
        }
        return null;
    }

}
